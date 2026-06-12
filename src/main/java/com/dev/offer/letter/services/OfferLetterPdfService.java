package com.dev.offer.letter.services;

import com.dev.offer.letter.handlers.HeaderEventHandler;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.dev.offer.letter.model.OfferLetterData;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import com.itextpdf.layout.element.*;


@Service
public class OfferLetterPdfService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    private String logoBase64;

    public byte[] generateOfferLetterPdf(OfferLetterData data) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc, PageSize.A4);
        document.setMargins(100, 40, 40, 40); // Increased top margin for header

        this.logoBase64 = data.getLogoBase64();

        // Load fonts
        PdfFont normalFont = PdfFontFactory.createFont("Helvetica", "Cp1252");
        PdfFont boldFont = PdfFontFactory.createFont("Helvetica-Bold", "Cp1252");

        // Add header to all pages
        // In generateOfferLetterPdf method, when creating HeaderEventHandler:
        HeaderEventHandler headerHandler = new HeaderEventHandler(boldFont, normalFont, this.logoBase64);
        pdfDoc.addEventHandler(PdfDocumentEvent.START_PAGE, headerHandler);


        // Add footer with page numbers
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, event -> {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            com.itextpdf.kernel.geom.Rectangle pageSize = docEvent.getPage().getPageSize();
            int pageNumber = docEvent.getDocument().getPageNumber(docEvent.getPage());
            int totalPages = docEvent.getDocument().getNumberOfPages();

            com.itextpdf.kernel.geom.Rectangle rect = new com.itextpdf.kernel.geom.Rectangle(
                    pageSize.getLeft() + 36,
                    pageSize.getBottom() + 20,
                    pageSize.getWidth() - 72,
                    20
            );

            com.itextpdf.layout.Canvas canvas = new com.itextpdf.layout.Canvas(docEvent.getPage(), rect);
            canvas.add(new Paragraph("Page " + pageNumber + " of " + totalPages)
                    .setFont(normalFont)
                    .setFontSize(8)
                    .setTextAlignment(TextAlignment.CENTER));
            canvas.close();
        });

        // ========== PAGE 1 ==========
        pdfDoc.addNewPage();

        // Date line (Top Right)
        Paragraph datePara = new Paragraph(data.getOfferDate().format(DATE_FORMATTER))
                .setFont(normalFont)
                .setFontSize(10)
                .setTextAlignment(TextAlignment.RIGHT);
        document.add(datePara);

        document.add(new Paragraph("\n"));

        // To line
        Paragraph toPara = new Paragraph("To,")
                .setFont(normalFont)
                .setFontSize(11);
        document.add(toPara);

        // Candidate Name and Address
        Paragraph candidatePara = new Paragraph(data.getCandidateName())
                .setFont(boldFont)
                .setFontSize(11);
        document.add(candidatePara);

        if (data.getCandidateAddress() != null && !data.getCandidateAddress().isEmpty()) {
            Paragraph addressPara = new Paragraph(data.getCandidateAddress())
                    .setFont(normalFont)
                    .setFontSize(10);
            document.add(addressPara);
        }

        document.add(new Paragraph("\n"));

        // Date again (left side)
        Paragraph dateLeftPara = new Paragraph(data.getOfferDate().format(DATE_FORMATTER))
                .setFont(normalFont)
                .setFontSize(10);
        document.add(dateLeftPara);

        document.add(new Paragraph("\n"));

        // Letter of Offer Title
        Paragraph titlePara = new Paragraph("Letter of Offer")
                .setFont(boldFont)
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginTop(10)
                .setMarginBottom(10);
        document.add(titlePara);

        // Dear Salutation
        Paragraph dearPara = new Paragraph("Dear " + data.getCandidateName() + ",")
                .setFont(normalFont)
                .setFontSize(11)
                .setMarginBottom(10);
        document.add(dearPara);

        // Introduction
        Paragraph introPara = new Paragraph("We are pleased to make you an offer of employment with us and this letter sets forth the terms of appointment.")
                .setFont(normalFont)
                .setFontSize(11)
                .setMarginBottom(15);
        document.add(introPara);

        // Terms Section
        addTermSection(document, boldFont, normalFont, data);

        document.add(new Paragraph("\n"));

        // Sharing information warning
        Paragraph warningPara = new Paragraph("Sharing of this information will result in withdrawal of your letter of offer.")
                .setFont(normalFont)
                .setFontSize(10)
                .setFontColor(ColorConstants.RED);
        document.add(warningPara);

        document.add(new Paragraph("\n"));

        // Service Agreement and Surety Bond section
        Paragraph agreementPara = new Paragraph("As informed earlier during the pre-placement session, you are required to sign a service agreement with FIS on the day of joining and the Surety Bond signed by either of your Parents, is notarized by a Notary Public and submitted to FIS within one week from the date of joining.")
                .setFont(normalFont)
                .setFontSize(10);
        document.add(agreementPara);

        document.add(new Paragraph("\n"));

        // Conditions
        Paragraph conditionTitle = new Paragraph("This offer is only valid subject to the fulfillment of all the below listed conditions:")
                .setFont(boldFont)
                .setFontSize(10);
        document.add(conditionTitle);

        List conditionList = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(normalFont)
                .setFontSize(10);
        conditionList.add("You should not have any outstanding or arrears in your academics as on date of joining.");
        conditionList.add("Subject to clearing your final semester examinations and scoring a minimum of " +
                data.getRequiredGraduationPercentage() + "% overall in your Under Graduate/Post Graduate Course as applicable.");
        document.add(conditionList);

        Paragraph terminationPara = new Paragraph("The company reserves the right to terminate your services with immediate effect, if any of the above conditions are violated.")
                .setFont(normalFont)
                .setFontSize(10)
                .setFontColor(ColorConstants.RED);
        document.add(terminationPara);

        document.add(new Paragraph("\n"));

        // Document submission references
        Paragraph docRefPara = new Paragraph("1) The documents listed in Annexure 2 at the time of acceptance of offer.\n2) The documents listed in Annexure 3 on the day of joining.")
                .setFont(normalFont)
                .setFontSize(10);
        document.add(docRefPara);

        document.add(new Paragraph("\n"));

        // Closing
        Paragraph closingPara = new Paragraph("We look forward to having you on board with Team FIS.")
                .setFont(normalFont)
                .setFontSize(11)
                .setMarginTop(15);
        document.add(closingPara);

        document.add(new Paragraph("\n"));

        Paragraph sincerelyPara = new Paragraph("Yours sincerely,")
                .setFont(normalFont)
                .setFontSize(11)
                .setMarginTop(10);
        document.add(sincerelyPara);

        document.add(new Paragraph("\n\n\n"));

        // Signature Table
        Table signatureTable = new Table(UnitValue.createPercentArray(new float[]{50, 50}));
        signatureTable.setWidth(UnitValue.createPercentValue(100));
        signatureTable.setMarginTop(10);

        Cell hrCell = new Cell();
        hrCell.add(new Paragraph(data.getHrName())
                .setFont(boldFont)
                .setFontSize(10));
        hrCell.add(new Paragraph(data.getHrTitle())
                .setFont(normalFont)
                .setFontSize(9));
        hrCell.setBorder(Border.NO_BORDER);

        Cell candidateCell = new Cell();
        candidateCell.add(new Paragraph("I hereby accept the above offer")
                .setFont(normalFont)
                .setFontSize(10));
        candidateCell.add(new Paragraph(""));
        candidateCell.add(new Paragraph("_________________________")
                .setFont(normalFont)
                .setFontSize(10));
        candidateCell.add(new Paragraph("Candidate's Name" + " & Signature")
                .setFont(normalFont)
                .setFontSize(9));
        candidateCell.setBorder(Border.NO_BORDER);

        signatureTable.addCell(hrCell);
        signatureTable.addCell(candidateCell);
        document.add(signatureTable);

        // ========== PAGE 2 - ANNEXURE 1 ==========
        pdfDoc.addNewPage();

        Paragraph annexureTitle = new Paragraph("ANNEXURE - Compensation and Benefits")
                .setFont(boldFont)
                .setFontSize(14)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(15);
        document.add(annexureTitle);

        // Employee Info Table
        Table infoTable = new Table(UnitValue.createPercentArray(new float[]{20, 30, 20, 30}));
        infoTable.setWidth(UnitValue.createPercentValue(100));
        infoTable.setMarginBottom(10);

        infoTable.addCell(new Cell().add(new Paragraph("Name:").setFont(boldFont)).setBorder(Border.NO_BORDER));
        infoTable.addCell(new Cell().add(new Paragraph(data.getCandidateName()).setFont(normalFont)).setBorder(Border.NO_BORDER));
        infoTable.addCell(new Cell().add(new Paragraph("w.e.f.").setFont(boldFont)).setBorder(Border.NO_BORDER));
        infoTable.addCell(new Cell().add(new Paragraph(data.getJoiningDate().format(DATE_FORMATTER)).setFont(normalFont)).setBorder(Border.NO_BORDER));

        document.add(infoTable);

        // Fixed Pay Table
        Paragraph fixedPayTitle = new Paragraph("FIXED PAY")
                .setFont(boldFont)
                .setFontSize(11)
                .setBackgroundColor(ColorConstants.CYAN)
                .setPadding(5);
        document.add(fixedPayTitle);

        Table fixedPayTable = new Table(UnitValue.createPercentArray(new float[]{50, 25, 25}));
        fixedPayTable.setWidth(UnitValue.createPercentValue(100));
        fixedPayTable.setMarginTop(5);

        addTableHeader(fixedPayTable, "Particulars", "Amount (PM)", "Amount (PA)", boldFont);
        addTableRow(fixedPayTable, "Basic Salary", data.getBasicSalaryMonthly(), data.getBasicSalaryAnnual(), normalFont);
        addTableRow(fixedPayTable, "House Rent Allowance", data.getHraMonthly(), data.getHraAnnual(), normalFont);
        addTableRow(fixedPayTable, "Management Allowance", data.getManagementAllowanceMonthly(), data.getManagementAllowanceAnnual(), normalFont);
        addTableRow(fixedPayTable, "Employer's contribution to Provident Fund", data.getEmployerPfMonthly(), data.getEmployerPfAnnual(), normalFont);

        Cell basePayCell = new Cell().add(new Paragraph("BASE PAY (FIXED PAY)").setFont(boldFont));
        basePayCell.setBorder(new SolidBorder(ColorConstants.BLACK, 1)).setBackgroundColor(ColorConstants.LIGHT_GRAY);
        fixedPayTable.addCell(basePayCell);
        fixedPayTable.addCell(new Cell().add(new Paragraph("₹" + data.getBasePayMonthly()).setFont(boldFont)).setBorder(new SolidBorder(ColorConstants.BLACK, 1)).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        fixedPayTable.addCell(new Cell().add(new Paragraph("₹" + data.getBasePayAnnual()).setFont(boldFont)).setBorder(new SolidBorder(ColorConstants.BLACK, 1)).setBackgroundColor(ColorConstants.LIGHT_GRAY));

        document.add(fixedPayTable);

        // Performance Bonus
        Table bonusTable = new Table(UnitValue.createPercentArray(new float[]{50, 25, 25}));
        bonusTable.setWidth(UnitValue.createPercentValue(100));
        bonusTable.setMarginTop(5);

        Cell bonusLabelCell = new Cell().add(new Paragraph("Performance Bonus*").setFont(normalFont));
        bonusLabelCell.setBorder(Border.NO_BORDER);
        bonusTable.addCell(bonusLabelCell);
        bonusTable.addCell(new Cell().setBorder(Border.NO_BORDER));
        bonusTable.addCell(new Cell().add(new Paragraph("₹" + data.getPerformanceBonus()).setFont(normalFont)).setBorder(Border.NO_BORDER));

        document.add(bonusTable);

        // Total Compensation
        Table totalCompTable = new Table(UnitValue.createPercentArray(new float[]{50, 25, 25}));
        totalCompTable.setWidth(UnitValue.createPercentValue(100));
        totalCompTable.setMarginTop(5);

        Cell totalLabelCell = new Cell().add(new Paragraph("TOTAL COMPENSATION (TC)").setFont(boldFont));
        totalLabelCell.setBorder(Border.NO_BORDER);
        totalCompTable.addCell(totalLabelCell);
        totalCompTable.addCell(new Cell().setBorder(Border.NO_BORDER));
        totalCompTable.addCell(new Cell().add(new Paragraph("₹" + data.getTotalCompensation()).setFont(boldFont)).setBorder(Border.NO_BORDER));

        document.add(totalCompTable);

        document.add(new Paragraph("\n"));

        // Annual Benefits
        Paragraph benefitsTitle = new Paragraph("ANNUAL BENEFITS")
                .setFont(boldFont)
                .setFontSize(11)
                .setBackgroundColor(ColorConstants.ORANGE)
                .setPadding(5);
        document.add(benefitsTitle);

        Table benefitsTable = new Table(UnitValue.createPercentArray(new float[]{60, 40}));
        benefitsTable.setWidth(UnitValue.createPercentValue(100));
        benefitsTable.setMarginTop(5);

        benefitsTable.addCell(new Cell().add(new Paragraph("Benefit Particulars").setFont(boldFont)).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        benefitsTable.addCell(new Cell().add(new Paragraph("Amount (PA)").setFont(boldFont)).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        benefitsTable.addCell(new Cell().add(new Paragraph("Gratuity (As per payment of Gratuity Act)").setFont(normalFont)));
        benefitsTable.addCell(new Cell().add(new Paragraph("₹" + data.getGratuityAnnual()).setFont(normalFont)));
        benefitsTable.addCell(new Cell().add(new Paragraph("Premium paid by the employer for Group Health Medical Insurance**").setFont(normalFont)));
        benefitsTable.addCell(new Cell().add(new Paragraph("₹" + data.getMedicalInsurancePremium()).setFont(normalFont)));
        benefitsTable.addCell(new Cell().add(new Paragraph("Premium paid by the employer for Accident Insurance policy").setFont(normalFont)));
        benefitsTable.addCell(new Cell().add(new Paragraph("₹" + data.getAccidentInsurancePremium()).setFont(normalFont)));

        document.add(benefitsTable);

        // CTC
        Table ctcTable = new Table(UnitValue.createPercentArray(new float[]{60, 40}));
        ctcTable.setWidth(UnitValue.createPercentValue(100));
        ctcTable.setMarginTop(5);

        Cell ctcLabelCell = new Cell().add(new Paragraph("COST TO COMPANY (CTC)").setFont(boldFont));
        ctcLabelCell.setBorder(Border.NO_BORDER);
        ctcTable.addCell(ctcLabelCell);
        ctcTable.addCell(new Cell().add(new Paragraph("₹" + data.getCostToCompany()).setFont(boldFont)).setBorder(Border.NO_BORDER));

        document.add(ctcTable);

        document.add(new Paragraph("\n"));

        // Footnotes
        Paragraph footnote1 = new Paragraph("* Your Performance Bonus represents the target amount (at 100% payout). Actual monthly payouts can vary depending on performance and subject to the terms and conditions of the Incentive plan policy. Plan details are at the sole discretion of the company and subject to change.")
                .setFont(normalFont)
                .setFontSize(8)
                .setFontColor(ColorConstants.GRAY);
        document.add(footnote1);

        Paragraph footnote2 = new Paragraph("* Fixed Bonus and Performance Bonus are adjustable against any Statutory Bonus payable (if applicable)\nTaxes and other statutory deductions/payments as per applicable law.\n** To know your eligibilities for Group Health Medical Insurance, please refer to the policy")
                .setFont(normalFont)
                .setFontSize(8)
                .setFontColor(ColorConstants.GRAY);
        document.add(footnote2);

        Paragraph footnote3 = new Paragraph("Your compensation can be restructured at any time protecting Total Compensation (TC)\nAll salary components are governed by the company policies and statutory guidelines\nThis salary sheet is strictly confidential and must not be discussed with anyone other than your Reporting Manager")
                .setFont(normalFont)
                .setFontSize(8)
                .setFontColor(ColorConstants.GRAY);
        document.add(footnote3);

        // ========== PAGE 3 - ANNEXURE 2 ==========
        pdfDoc.addNewPage();

        addAnnexure2(document, boldFont, normalFont, data);

        // ========== PAGE 4 - ANNEXURE 3 ==========
        pdfDoc.addNewPage();

        addAnnexure3(document, boldFont, normalFont, data);

        document.close();
        return baos.toByteArray();
    }

    // Add all the helper methods here (addTermSection, addTableHeader, addTableRow, addAnnexure2, addAnnexure3, addDocumentSection)
    // ... (keep all your existing helper methods)

    private void addTermSection(Document document, PdfFont boldFont, PdfFont normalFont, OfferLetterData data) {
        // Your existing addTermSection code
        Paragraph term1 = new Paragraph()
                .add(new Text("1) Designation/Band:\t").setFont(boldFont))
                .add(new Text("FIS India Title: " + data.getFisTitle() + " FIS Band: " + data.getFisBand()).setFont(normalFont));
        document.add(term1);

        Paragraph term2 = new Paragraph()
                .add(new Text("2) Place of Posting:\t").setFont(boldFont))
                .add(new Text(data.getPlaceOfPosting() + " " + data.getPlaceOfPostingAddress()).setFont(normalFont));
        document.add(term2);

        Paragraph term3 = new Paragraph()
                .add(new Text("3) Date of Joining:\t").setFont(boldFont))
                .add(new Text(data.getJoiningDate().format(DATE_FORMATTER)).setFont(normalFont));
        document.add(term3);

        Paragraph term4 = new Paragraph()
                .add(new Text("4) Compensation & Benefits:\t").setFont(boldFont))
                .add(new Text("Annualized Fixed Total : Rs. " + data.getBasePayAnnual() +
                        " /- Performance Pay : Rs. " + data.getPerformanceBonus() +
                        " /- Total Compensation : Rs. " + data.getTotalCompensation() +
                        " /- (Break up of above Compensation details are provided in Annexure 1)").setFont(normalFont));
        document.add(term4);

        Paragraph term5 = new Paragraph()
                .add(new Text("5) Background Checks:\t").setFont(boldFont))
                .add(new Text("Your appointment is subject to the background check clearance in all aspects, any discrepancies in the background check will lead to withdrawal of the offer. Our TPO Team will let you know the final status of your check once it is completed.").setFont(normalFont));
        document.add(term5);

        Paragraph term6 = new Paragraph()
                .add(new Text("6) Confidentiality:\t").setFont(boldFont))
                .add(new Text("You are requested to maintain confidentiality on all aspects of the letter of offer at all times. You shall not divulge, communicate or pass on any information, regarding the company, its business, customers, work practices and security practices to any outsider or any external vendor or contractor employed by the Company. Sharing of this information will result in withdrawal of your letter of offer.").setFont(normalFont));
        document.add(term6);

        Paragraph term7 = new Paragraph()
                .add(new Text("7) Notice Period:\t").setFont(boldFont))
                .add(new Text("Notwithstanding anything stated above, your services are liable to be terminated by the Company without assigning any reason, by giving you " + data.getNoticePeriodDays() +
                        " days' Notice or salary in lieu of such Notice. Likewise, you may resign from the services of the Company by giving " + data.getNoticePeriodDays() +
                        " days' Notice or salary in lieu of Notice. Salary for this purpose will be computed @ one month of Basic Salary.").setFont(normalFont));
        document.add(term7);

        Paragraph term8 = new Paragraph()
                .add(new Text("8) Probation period:\t").setFont(boldFont))
                .add(new Text("You shall be on probation for a period of " + data.getProbationPeriodMonths() +
                        " months from the date of your joining. Your services shall be confirmed based on the performance review conducted by your Manager").setFont(normalFont));
        document.add(term8);
    }

    private void addTableHeader(Table table, String col1, String col2, String col3, PdfFont boldFont) {
        Cell cell1 = new Cell().add(new Paragraph(col1).setFont(boldFont));
        cell1.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        cell1.setBorder(new SolidBorder(ColorConstants.BLACK, 1));
        table.addCell(cell1);

        Cell cell2 = new Cell().add(new Paragraph(col2).setFont(boldFont));
        cell2.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        cell2.setBorder(new SolidBorder(ColorConstants.BLACK, 1));
        table.addCell(cell2);

        if (col3 != null && !col3.isEmpty()) {
            Cell cell3 = new Cell().add(new Paragraph(col3).setFont(boldFont));
            cell3.setBackgroundColor(ColorConstants.LIGHT_GRAY);
            cell3.setBorder(new SolidBorder(ColorConstants.BLACK, 1));
            table.addCell(cell3);
        }
    }

    private void addTableRow(Table table, String label, BigDecimal amount1, BigDecimal amount2, PdfFont normalFont) {
        table.addCell(new Cell().add(new Paragraph(label).setFont(normalFont)).setBorder(new SolidBorder(ColorConstants.BLACK, 1)));
        table.addCell(new Cell().add(new Paragraph("₹" + amount1).setFont(normalFont)).setBorder(new SolidBorder(ColorConstants.BLACK, 1)));
        if (amount2 != null) {
            table.addCell(new Cell().add(new Paragraph("₹" + amount2).setFont(normalFont)).setBorder(new SolidBorder(ColorConstants.BLACK, 1)));
        } else {
            table.addCell(new Cell().setBorder(new SolidBorder(ColorConstants.BLACK, 1)));
        }
    }

    private void addAnnexure2(Document document, PdfFont boldFont, PdfFont normalFont, OfferLetterData data) {
        Paragraph annexure2Title = new Paragraph("ANNEXURE 2")
                .setFont(boldFont)
                .setFontSize(14)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(15);
        document.add(annexure2Title);

        Paragraph congratsPara = new Paragraph("Dear " + data.getCandidateName() + ",")
                .setFont(normalFont)
                .setFontSize(11);
        document.add(congratsPara);

        Paragraph congratsMsg = new Paragraph("Congratulations for successfully clearing all the rounds!")
                .setFont(boldFont)
                .setFontSize(11)
                .setMarginBottom(10);
        document.add(congratsMsg);

        Paragraph requestPara = new Paragraph("You are requested to submit following documents at the earliest,")
                .setFont(normalFont)
                .setFontSize(11)
                .setMarginBottom(15);
        document.add(requestPara);

        addDocumentSection(document, boldFont, normalFont, "A", "Academic Qualification",
                "SSC/HSC/Graduation/Diploma certificate & Mark sheets");
        addDocumentSection(document, boldFont, normalFont, "B", "Work Experience Certificate (Not applicable in case you are fresher)",
                "Experience Letter/Relieving Letters from last two organization(s) Current Company appointment letter Latest 3 months Salary Slips");
        addDocumentSection(document, boldFont, normalFont, "C", "Six Passport sized colored photographs", "");
        addDocumentSection(document, boldFont, normalFont, "D", "Address Proof (Any of the following)",
                "Ration Card\nPassport copy\nUtility Bills");
        addDocumentSection(document, boldFont, normalFont, "E", "Proof of Date of Birth (Any of the following)",
                "Birth Certificate\nSchool Leaving Certificate\nPassport copy");
        addDocumentSection(document, boldFont, normalFont, "F", "Photo ID proof (Any of the following)",
                "Passport copy\nDriver's License\nPan Card");

        Paragraph instructionPara = new Paragraph("You are required to submit originals and 1 photocopy of the above documents. TPO (The People Office) will retain the photocopies and return you the originals immediately. In case you are not able to submit the originals then attested photocopies from a Gazette Officer will be a must.")
                .setFont(normalFont)
                .setFontSize(9)
                .setMarginTop(15)
                .setFontColor(ColorConstants.GRAY);
        document.add(instructionPara);
    }

    private void addAnnexure3(Document document, PdfFont boldFont, PdfFont normalFont, OfferLetterData data) {
        Paragraph annexure3Title = new Paragraph("ANNEXURE 3")
                .setFont(boldFont)
                .setFontSize(14)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(15);
        document.add(annexure3Title);

        Paragraph dearPara = new Paragraph("Dear " + data.getCandidateName() + ",")
                .setFont(normalFont)
                .setFontSize(11);
        document.add(dearPara);

        Paragraph requestPara = new Paragraph("You are also requested to submit us the filled-up documents, as detailed below, on your date of joining (" +
                data.getJoiningDate().format(DATE_FORMATTER) + ").")
                .setFont(normalFont)
                .setFontSize(11)
                .setMarginBottom(15);
        document.add(requestPara);

        List joiningDocList = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(normalFont)
                .setFontSize(10);
        joiningDocList.add("Joining forms");
        joiningDocList.add("Payment of Gratuity form");
        joiningDocList.add("Family Health Declaration Form");
        joiningDocList.add("Employee Confidential Agreement");
        joiningDocList.add("Nomination and Declaration form (two original copies)");
        joiningDocList.add("Relieving letter of your last employer");
        document.add(joiningDocList);

        document.add(new Paragraph("\n"));

        Paragraph wishPara = new Paragraph("Wishing you a great career ahead in FIS!!!")
                .setFont(boldFont)
                .setFontSize(11)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginTop(20);
        document.add(wishPara);
    }

    private void addDocumentSection(Document document, PdfFont boldFont, PdfFont normalFont,
                                    String section, String title, String details) {
        Paragraph sectionPara = new Paragraph()
                .add(new Text("[" + section + "] ").setFont(boldFont))
                .add(new Text(title).setFont(boldFont));
        document.add(sectionPara);

        if (details != null && !details.isEmpty()) {
            Paragraph detailsPara = new Paragraph(details)
                    .setFont(normalFont)
                    .setFontSize(10)
                    .setMarginLeft(20)
                    .setMarginBottom(10);
            document.add(detailsPara);
        } else {
            document.add(new Paragraph("\n").setMarginBottom(5));
        }
    }
}

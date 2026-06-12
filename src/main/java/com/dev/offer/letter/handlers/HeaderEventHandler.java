package com.dev.offer.letter.handlers;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;

public class HeaderEventHandler implements IEventHandler {

    private final PdfFont boldFont;
    private final PdfFont normalFont;
    private final String logoBase64;

    public HeaderEventHandler(PdfFont boldFont, PdfFont normalFont, String logoBase64) {
        this.boldFont = boldFont;
        this.normalFont = normalFont;
        this.logoBase64 = logoBase64;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        Rectangle pageSize = page.getPageSize();

        // Create header table
        Table headerTable = new Table(UnitValue.createPercentArray(new float[]{15, 70, 15}));
        headerTable.setWidth(UnitValue.createPercentValue(100));

        // Logo Cell - FIXED: Now uses actual logo
        Cell logoCell = new Cell();
        if (logoBase64 != null && !logoBase64.isEmpty()) {
            try {
                String base64Image = logoBase64.contains(",") ? logoBase64.split(",")[1] : logoBase64;
                byte[] imageBytes = java.util.Base64.getDecoder().decode(base64Image);
                Image logo = new Image(ImageDataFactory.create(imageBytes));
                logo.setWidth(50);
                logo.setHeight(40);
                logoCell.add(logo);
            } catch (Exception e) {
                // Fallback to text if logo fails
                logoCell.add(new Paragraph("FIS").setFont(boldFont).setFontSize(16));
            }
        } else {
            logoCell.add(new Paragraph("FIS").setFont(boldFont).setFontSize(16));
        }
        logoCell.setBorder(Border.NO_BORDER);
        logoCell.setVerticalAlignment(VerticalAlignment.MIDDLE);

        // Company Info Cell
        Cell companyCell = new Cell();
        Paragraph companyName = new Paragraph("Fidelity Information Services India Private Limited")
                .setFont(boldFont)
                .setFontSize(9)
                .setTextAlignment(TextAlignment.CENTER);
        Paragraph companyDetails = new Paragraph("CIN: U72200DL2002PTC114964 | C-5, Sector-126, Noida-201301, U.P., India")
                .setFont(normalFont)
                .setFontSize(7)
                .setTextAlignment(TextAlignment.CENTER);
        Paragraph contactDetails = new Paragraph("Tel: +91 120 402 1400, 483 3600 | www.fisglobal.com")
                .setFont(normalFont)
                .setFontSize(7)
                .setTextAlignment(TextAlignment.CENTER);

        companyCell.add(companyName);
        companyCell.add(companyDetails);
        companyCell.add(contactDetails);
        companyCell.setBorder(Border.NO_BORDER);
        companyCell.setVerticalAlignment(VerticalAlignment.MIDDLE);

        // Empty Cell
        Cell emptyCell = new Cell();
        emptyCell.setBorder(Border.NO_BORDER);

        headerTable.addCell(logoCell);
        headerTable.addCell(companyCell);
        headerTable.addCell(emptyCell);

        // Position the header at the top of the page
        float y = pageSize.getTop() - 50;
        float x = pageSize.getLeft() + 36;
        float width = pageSize.getWidth() - 72;

        // Draw the header on the page
        Canvas canvas = new Canvas(page, new Rectangle(x, y - 40, width, 60));
        canvas.add(headerTable);
        canvas.close();
    }
}
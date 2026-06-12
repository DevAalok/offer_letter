package com.dev.offer.letter.controllers;

import com.dev.offer.letter.model.OfferLetterData;
import com.dev.offer.letter.services.OfferLetterPdfService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Base64;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/offer-letter-pdf")
public class OfferLetterPdfController {

    private final OfferLetterPdfService pdfService;

    public OfferLetterPdfController(OfferLetterPdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateOfferLetter(@Valid @RequestBody OfferLetterData data) {
        try {
            byte[] pdfBytes = pdfService.generateOfferLetterPdf(data);

            String filename = "FIS_Offer_Letter_" + data.getCandidateName().replace(" ", "_") + ".pdf";

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF", e);
        }
    }

    //-----------------------------------------------------------------------------------------------------------------

    @GetMapping("/generate-sample")
    public ResponseEntity<byte[]> generateSampleOfferLetter() {
        OfferLetterData data = createSampleData();

        try {
            byte[] pdfBytes = pdfService.generateOfferLetterPdf(data);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"FIS_Sample_Offer_Letter.pdf\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF", e);
        }
    }

    //--------------------------------------------------------------------------------------------------------------------

    @PostMapping("/generate-with-logo")
    public ResponseEntity<byte[]> generateOfferLetterWithLogo(
            @RequestPart("data") String dataJson,
            @RequestPart(value = "logo", required = false) MultipartFile logo) throws Exception {

        // Create ObjectMapper with JavaTimeModule
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        mapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Convert JSON string to OfferLetterData object
        OfferLetterData data = mapper.readValue(dataJson, OfferLetterData.class);

        // Add logo to data if provided
        if (logo != null && !logo.isEmpty()) {
            String base64Logo = "data:image/png;base64," +
                    java.util.Base64.getEncoder().encodeToString(logo.getBytes());
            data.setLogoBase64(base64Logo);
        }

        // Generate PDF
        byte[] pdfBytes = pdfService.generateOfferLetterPdf(data);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"offer_letter.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

//---------------------------------------------------------------------------------------------------------------------

    private OfferLetterData createSampleData() {
        OfferLetterData data = new OfferLetterData();
        data.setCandidateName("Sanjeev Kumar");
        data.setCandidateAddress("Noida, Uttar Pradesh, India");
        data.setOfferDate(LocalDate.of(2019, 4, 18));
        data.setJoiningDate(LocalDate.of(2019, 5, 1));
        data.setPlaceOfPosting("FIS Noida");
        data.setPlaceOfPostingAddress("C-5, Sector-126, Noida-201301, U.P., India");

        data.setBasicSalaryMonthly(new BigDecimal("11500"));
        data.setBasicSalaryAnnual(new BigDecimal("138000"));
        data.setHraMonthly(new BigDecimal("5750"));
        data.setHraAnnual(new BigDecimal("69000"));
        data.setManagementAllowanceMonthly(new BigDecimal("8453"));
        data.setManagementAllowanceAnnual(new BigDecimal("101440"));
        data.setEmployerPfMonthly(new BigDecimal("1380"));
        data.setEmployerPfAnnual(new BigDecimal("16560"));
        data.setBasePayMonthly(new BigDecimal("27083"));
        data.setBasePayAnnual(new BigDecimal("325000"));
        data.setPerformanceBonus(new BigDecimal("24375"));
        data.setTotalCompensation(new BigDecimal("349375"));
        data.setGratuityAnnual(new BigDecimal("6638"));
        data.setMedicalInsurancePremium(new BigDecimal("8380"));
        data.setAccidentInsurancePremium(new BigDecimal("179"));
        data.setCostToCompany(new BigDecimal("364572"));

        data.setParentName("Mr. Ramesh Kumar");
        data.setNotaryNumber("NOTARY/2019/12345");

        return data;
    }
}
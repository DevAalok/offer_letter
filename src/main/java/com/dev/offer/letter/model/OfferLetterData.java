/*
package com.dev.offer.letter.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class OfferLetterData {
    private String candidateName;
    private String candidateAddress;
    private LocalDate offerDate;
    private LocalDate joiningDate;
    private String fisTitle = "IT Trainee";
    private String fisBand = "AP3";
    private String placeOfPosting;
    private String placeOfPostingAddress;

    // Compensation Details
    private BigDecimal basicSalaryMonthly = new BigDecimal("11500");
    private BigDecimal basicSalaryAnnual = new BigDecimal("138000");
    private BigDecimal hraMonthly = new BigDecimal("5750");
    private BigDecimal hraAnnual = new BigDecimal("69000");
    private BigDecimal managementAllowanceMonthly = new BigDecimal("8453");
    private BigDecimal managementAllowanceAnnual = new BigDecimal("101440");
    private BigDecimal employerPfMonthly = new BigDecimal("1380");
    private BigDecimal employerPfAnnual = new BigDecimal("16560");
    private BigDecimal basePayMonthly = new BigDecimal("27083");
    private BigDecimal basePayAnnual = new BigDecimal("325000");
    private BigDecimal performanceBonus = new BigDecimal("24375");
    private BigDecimal totalCompensation = new BigDecimal("349375");
    private BigDecimal gratuityAnnual = new BigDecimal("6638");
    private BigDecimal medicalInsurancePremium = new BigDecimal("8380");
    private BigDecimal accidentInsurancePremium = new BigDecimal("179");
    private BigDecimal costToCompany = new BigDecimal("364572");

    // Terms
    private Integer probationPeriodMonths = 6;
    private Integer noticePeriodDays = 75;
    private Double requiredGraduationPercentage = 60.0;

    // Parent/Surety Details
    private String parentName;
    private String notaryNumber;

    // Signature
    private String hrName = "Mamta Wasan";
    private String hrTitle = "Sr. Vice President- Human Resource";
    private byte[] companyLogo; // Optional company logo image

    private String logoBase64;

    //Getters and Setters
    public BigDecimal getAccidentInsurancePremium() {
        return accidentInsurancePremium;
    }

    public void setAccidentInsurancePremium(BigDecimal accidentInsurancePremium) {
        this.accidentInsurancePremium = accidentInsurancePremium;
    }

    public BigDecimal getBasePayAnnual() {
        return basePayAnnual;
    }

    public void setBasePayAnnual(BigDecimal basePayAnnual) {
        this.basePayAnnual = basePayAnnual;
    }

    public BigDecimal getBasePayMonthly() {
        return basePayMonthly;
    }

    public void setBasePayMonthly(BigDecimal basePayMonthly) {
        this.basePayMonthly = basePayMonthly;
    }

    public BigDecimal getBasicSalaryAnnual() {
        return basicSalaryAnnual;
    }

    public void setBasicSalaryAnnual(BigDecimal basicSalaryAnnual) {
        this.basicSalaryAnnual = basicSalaryAnnual;
    }

    public BigDecimal getBasicSalaryMonthly() {
        return basicSalaryMonthly;
    }

    public void setBasicSalaryMonthly(BigDecimal basicSalaryMonthly) {
        this.basicSalaryMonthly = basicSalaryMonthly;
    }

    public String getCandidateAddress() {
        return candidateAddress;
    }

    public void setCandidateAddress(String candidateAddress) {
        this.candidateAddress = candidateAddress;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public byte[] getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(byte[] companyLogo) {
        this.companyLogo = companyLogo;
    }

    public BigDecimal getCostToCompany() {
        return costToCompany;
    }

    public void setCostToCompany(BigDecimal costToCompany) {
        this.costToCompany = costToCompany;
    }

    public BigDecimal getEmployerPfAnnual() {
        return employerPfAnnual;
    }

    public void setEmployerPfAnnual(BigDecimal employerPfAnnual) {
        this.employerPfAnnual = employerPfAnnual;
    }

    public BigDecimal getEmployerPfMonthly() {
        return employerPfMonthly;
    }

    public void setEmployerPfMonthly(BigDecimal employerPfMonthly) {
        this.employerPfMonthly = employerPfMonthly;
    }

    public String getFisBand() {
        return fisBand;
    }

    public void setFisBand(String fisBand) {
        this.fisBand = fisBand;
    }

    public String getFisTitle() {
        return fisTitle;
    }

    public void setFisTitle(String fisTitle) {
        this.fisTitle = fisTitle;
    }

    public BigDecimal getGratuityAnnual() {
        return gratuityAnnual;
    }

    public void setGratuityAnnual(BigDecimal gratuityAnnual) {
        this.gratuityAnnual = gratuityAnnual;
    }

    public BigDecimal getHraAnnual() {
        return hraAnnual;
    }

    public void setHraAnnual(BigDecimal hraAnnual) {
        this.hraAnnual = hraAnnual;
    }

    public BigDecimal getHraMonthly() {
        return hraMonthly;
    }

    public void setHraMonthly(BigDecimal hraMonthly) {
        this.hraMonthly = hraMonthly;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public String getHrTitle() {
        return hrTitle;
    }

    public void setHrTitle(String hrTitle) {
        this.hrTitle = hrTitle;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public BigDecimal getManagementAllowanceAnnual() {
        return managementAllowanceAnnual;
    }

    public void setManagementAllowanceAnnual(BigDecimal managementAllowanceAnnual) {
        this.managementAllowanceAnnual = managementAllowanceAnnual;
    }

    public BigDecimal getManagementAllowanceMonthly() {
        return managementAllowanceMonthly;
    }

    public void setManagementAllowanceMonthly(BigDecimal managementAllowanceMonthly) {
        this.managementAllowanceMonthly = managementAllowanceMonthly;
    }

    public BigDecimal getMedicalInsurancePremium() {
        return medicalInsurancePremium;
    }

    public void setMedicalInsurancePremium(BigDecimal medicalInsurancePremium) {
        this.medicalInsurancePremium = medicalInsurancePremium;
    }

    public String getNotaryNumber() {
        return notaryNumber;
    }

    public void setNotaryNumber(String notaryNumber) {
        this.notaryNumber = notaryNumber;
    }

    public Integer getNoticePeriodDays() {
        return noticePeriodDays;
    }

    public void setNoticePeriodDays(Integer noticePeriodDays) {
        this.noticePeriodDays = noticePeriodDays;
    }

    public LocalDate getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(LocalDate offerDate) {
        this.offerDate = offerDate;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public BigDecimal getPerformanceBonus() {
        return performanceBonus;
    }

    public void setPerformanceBonus(BigDecimal performanceBonus) {
        this.performanceBonus = performanceBonus;
    }

    public String getPlaceOfPosting() {
        return placeOfPosting;
    }

    public void setPlaceOfPosting(String placeOfPosting) {
        this.placeOfPosting = placeOfPosting;
    }

    public String getPlaceOfPostingAddress() {
        return placeOfPostingAddress;
    }

    public void setPlaceOfPostingAddress(String placeOfPostingAddress) {
        this.placeOfPostingAddress = placeOfPostingAddress;
    }

    public Integer getProbationPeriodMonths() {
        return probationPeriodMonths;
    }

    public void setProbationPeriodMonths(Integer probationPeriodMonths) {
        this.probationPeriodMonths = probationPeriodMonths;
    }

    public Double getRequiredGraduationPercentage() {
        return requiredGraduationPercentage;
    }

    public void setRequiredGraduationPercentage(Double requiredGraduationPercentage) {
        this.requiredGraduationPercentage = requiredGraduationPercentage;
    }

    public BigDecimal getTotalCompensation() {
        return totalCompensation;
    }

    public void setTotalCompensation(BigDecimal totalCompensation) {
        this.totalCompensation = totalCompensation;
    }

    public String getLogoBase64() {
        return logoBase64;
    }

    public void setLogoBase64(String logoBase64) {
        this.logoBase64 = logoBase64;
    }
}
*/

package com.dev.offer.letter.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class OfferLetterData {

    // Existing fields...
    private String candidateName;
    private String candidateAddress;
    private LocalDate offerDate;
    private LocalDate joiningDate;
    private String fisTitle = "IT Trainee";
    private String fisBand = "AP3";
    private String placeOfPosting;
    private String placeOfPostingAddress;

    // Input field - Set this and others auto-calculate
    private BigDecimal costToCompany;  // Main input

    // Calculated fields (auto-generated from CTC)
    private BigDecimal basicSalaryMonthly;
    private BigDecimal basicSalaryAnnual;
    private BigDecimal hraMonthly;
    private BigDecimal hraAnnual;
    private BigDecimal managementAllowanceMonthly;
    private BigDecimal managementAllowanceAnnual;
    private BigDecimal employerPfMonthly;
    private BigDecimal employerPfAnnual;
    private BigDecimal basePayMonthly;
    private BigDecimal basePayAnnual;
    private BigDecimal performanceBonus;
    private BigDecimal totalCompensation;
    private BigDecimal gratuityAnnual;
    private BigDecimal medicalInsurancePremium;
    private BigDecimal accidentInsurancePremium;

    // Other fields...
    private Integer probationPeriodMonths = 6;
    private Integer noticePeriodDays = 75;
    private Double requiredGraduationPercentage = 60.0;
    private String parentName;
    private String notaryNumber;
    private String hrName = "Mamta Wasan";
    private String hrTitle = "Sr. Vice President- Human Resource";
    private String logoBase64;

    // Auto-calculate all values based on CTC
    public void calculateFromCTC() {
        if (costToCompany != null) {
            // Formula: CTC = Total Compensation + Gratuity + Medical + Accident
            // Total Compensation = CTC - (Gratuity + Medical + Accident)

            // Fixed benefits (as per standard percentages)
            this.gratuityAnnual = costToCompany.multiply(new BigDecimal("0.0182")).setScale(0, RoundingMode.HALF_UP);
            this.medicalInsurancePremium = costToCompany.multiply(new BigDecimal("0.023")).setScale(0, RoundingMode.HALF_UP);
            this.accidentInsurancePremium = costToCompany.multiply(new BigDecimal("0.0005")).setScale(0, RoundingMode.HALF_UP);

            // Total Compensation = CTC - Benefits
            BigDecimal totalBenefits = gratuityAnnual.add(medicalInsurancePremium).add(accidentInsurancePremium);
            this.totalCompensation = costToCompany.subtract(totalBenefits);

            // Performance Bonus = 7.5% of Total Compensation
            this.performanceBonus = totalCompensation.multiply(new BigDecimal("0.075")).setScale(0, RoundingMode.HALF_UP);

            // Base Pay = Total Compensation - Performance Bonus
            this.basePayAnnual = totalCompensation.subtract(performanceBonus);
            this.basePayMonthly = basePayAnnual.divide(new BigDecimal("12"), 0, RoundingMode.HALF_UP);

            // Basic Salary = 42% of Base Pay
            this.basicSalaryAnnual = basePayAnnual.multiply(new BigDecimal("0.42")).setScale(0, RoundingMode.HALF_UP);
            this.basicSalaryMonthly = basicSalaryAnnual.divide(new BigDecimal("12"), 0, RoundingMode.HALF_UP);

            // HRA = 21% of Base Pay
            this.hraAnnual = basePayAnnual.multiply(new BigDecimal("0.21")).setScale(0, RoundingMode.HALF_UP);
            this.hraMonthly = hraAnnual.divide(new BigDecimal("12"), 0, RoundingMode.HALF_UP);

            // Management Allowance = Rest of Base Pay
            this.managementAllowanceAnnual = basePayAnnual.subtract(basicSalaryAnnual).subtract(hraAnnual);
            this.managementAllowanceMonthly = managementAllowanceAnnual.divide(new BigDecimal("12"), 0, RoundingMode.HALF_UP);

            // Employer PF = 12% of Basic Salary
            this.employerPfAnnual = basicSalaryAnnual.multiply(new BigDecimal("0.12")).setScale(0, RoundingMode.HALF_UP);
            this.employerPfMonthly = employerPfAnnual.divide(new BigDecimal("12"), 0, RoundingMode.HALF_UP);
        }
    }

    // Getters and Setters
    public BigDecimal getCostToCompany() { return costToCompany; }

    public void setCostToCompany(BigDecimal costToCompany) {
        this.costToCompany = costToCompany;
        calculateFromCTC();  // Auto-calculate everything
    }

    // Add all other getters and setters...
    public String getCandidateName() { return candidateName; }
    public void setCandidateName(String candidateName) { this.candidateName = candidateName; }

    public String getCandidateAddress() { return candidateAddress; }
    public void setCandidateAddress(String candidateAddress) { this.candidateAddress = candidateAddress; }

    public LocalDate getOfferDate() { return offerDate; }
    public void setOfferDate(LocalDate offerDate) { this.offerDate = offerDate; }

    public LocalDate getJoiningDate() { return joiningDate; }
    public void setJoiningDate(LocalDate joiningDate) { this.joiningDate = joiningDate; }

    public String getFisTitle() { return fisTitle; }
    public void setFisTitle(String fisTitle) { this.fisTitle = fisTitle; }

    public String getFisBand() { return fisBand; }
    public void setFisBand(String fisBand) { this.fisBand = fisBand; }

    public String getPlaceOfPosting() { return placeOfPosting; }
    public void setPlaceOfPosting(String placeOfPosting) { this.placeOfPosting = placeOfPosting; }

    public String getPlaceOfPostingAddress() { return placeOfPostingAddress; }
    public void setPlaceOfPostingAddress(String placeOfPostingAddress) { this.placeOfPostingAddress = placeOfPostingAddress; }

    public BigDecimal getBasicSalaryMonthly() { return basicSalaryMonthly; }
    public void setBasicSalaryMonthly(BigDecimal basicSalaryMonthly) { this.basicSalaryMonthly = basicSalaryMonthly; }

    public BigDecimal getBasicSalaryAnnual() { return basicSalaryAnnual; }
    public void setBasicSalaryAnnual(BigDecimal basicSalaryAnnual) { this.basicSalaryAnnual = basicSalaryAnnual; }

    public BigDecimal getHraMonthly() { return hraMonthly; }
    public void setHraMonthly(BigDecimal hraMonthly) { this.hraMonthly = hraMonthly; }

    public BigDecimal getHraAnnual() { return hraAnnual; }
    public void setHraAnnual(BigDecimal hraAnnual) { this.hraAnnual = hraAnnual; }

    public BigDecimal getManagementAllowanceMonthly() { return managementAllowanceMonthly; }
    public void setManagementAllowanceMonthly(BigDecimal managementAllowanceMonthly) { this.managementAllowanceMonthly = managementAllowanceMonthly; }

    public BigDecimal getManagementAllowanceAnnual() { return managementAllowanceAnnual; }
    public void setManagementAllowanceAnnual(BigDecimal managementAllowanceAnnual) { this.managementAllowanceAnnual = managementAllowanceAnnual; }

    public BigDecimal getEmployerPfMonthly() { return employerPfMonthly; }
    public void setEmployerPfMonthly(BigDecimal employerPfMonthly) { this.employerPfMonthly = employerPfMonthly; }

    public BigDecimal getEmployerPfAnnual() { return employerPfAnnual; }
    public void setEmployerPfAnnual(BigDecimal employerPfAnnual) { this.employerPfAnnual = employerPfAnnual; }

    public BigDecimal getBasePayMonthly() { return basePayMonthly; }
    public void setBasePayMonthly(BigDecimal basePayMonthly) { this.basePayMonthly = basePayMonthly; }

    public BigDecimal getBasePayAnnual() { return basePayAnnual; }
    public void setBasePayAnnual(BigDecimal basePayAnnual) { this.basePayAnnual = basePayAnnual; }

    public BigDecimal getPerformanceBonus() { return performanceBonus; }
    public void setPerformanceBonus(BigDecimal performanceBonus) { this.performanceBonus = performanceBonus; }

    public BigDecimal getTotalCompensation() { return totalCompensation; }
    public void setTotalCompensation(BigDecimal totalCompensation) { this.totalCompensation = totalCompensation; }

    public BigDecimal getGratuityAnnual() { return gratuityAnnual; }
    public void setGratuityAnnual(BigDecimal gratuityAnnual) { this.gratuityAnnual = gratuityAnnual; }

    public BigDecimal getMedicalInsurancePremium() { return medicalInsurancePremium; }
    public void setMedicalInsurancePremium(BigDecimal medicalInsurancePremium) { this.medicalInsurancePremium = medicalInsurancePremium; }

    public BigDecimal getAccidentInsurancePremium() { return accidentInsurancePremium; }
    public void setAccidentInsurancePremium(BigDecimal accidentInsurancePremium) { this.accidentInsurancePremium = accidentInsurancePremium; }

    public Integer getProbationPeriodMonths() { return probationPeriodMonths; }
    public void setProbationPeriodMonths(Integer probationPeriodMonths) { this.probationPeriodMonths = probationPeriodMonths; }

    public Integer getNoticePeriodDays() { return noticePeriodDays; }
    public void setNoticePeriodDays(Integer noticePeriodDays) { this.noticePeriodDays = noticePeriodDays; }

    public Double getRequiredGraduationPercentage() { return requiredGraduationPercentage; }
    public void setRequiredGraduationPercentage(Double requiredGraduationPercentage) { this.requiredGraduationPercentage = requiredGraduationPercentage; }

    public String getParentName() { return parentName; }
    public void setParentName(String parentName) { this.parentName = parentName; }

    public String getNotaryNumber() { return notaryNumber; }
    public void setNotaryNumber(String notaryNumber) { this.notaryNumber = notaryNumber; }

    public String getHrName() { return hrName; }
    public void setHrName(String hrName) { this.hrName = hrName; }

    public String getHrTitle() { return hrTitle; }
    public void setHrTitle(String hrTitle) { this.hrTitle = hrTitle; }

    public String getLogoBase64() { return logoBase64; }
    public void setLogoBase64(String logoBase64) { this.logoBase64 = logoBase64; }
}

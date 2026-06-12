package com.dev.offer.letter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class OfferLetterPdfRequest {

    @NotBlank(message = "Candidate name is required")
    private String candidateName;

    private String candidateAddress;

    @NotNull(message = "Offer date is required")
    private LocalDate offerDate;

    @NotNull(message = "Joining date is required")
    private LocalDate joiningDate;

    @NotBlank(message = "Place of posting is required")
    private String placeOfPosting;

    private String placeOfPostingAddress;

    @NotNull(message = "Basic salary is required")
    @Positive(message = "Basic salary must be positive")
    private BigDecimal basicSalaryMonthly;

    private BigDecimal hraMonthly;
    private BigDecimal managementAllowanceMonthly;
    private BigDecimal employerPfMonthly;

    @NotNull(message = "Performance bonus is required")
    private BigDecimal performanceBonus;

    @NotNull(message = "Total compensation is required")
    private BigDecimal totalCompensation;

    private String parentName;
    private String notaryNumber;

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

    public BigDecimal getEmployerPfMonthly() {
        return employerPfMonthly;
    }

    public void setEmployerPfMonthly(BigDecimal employerPfMonthly) {
        this.employerPfMonthly = employerPfMonthly;
    }

    public BigDecimal getHraMonthly() {
        return hraMonthly;
    }

    public void setHraMonthly(BigDecimal hraMonthly) {
        this.hraMonthly = hraMonthly;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public BigDecimal getManagementAllowanceMonthly() {
        return managementAllowanceMonthly;
    }

    public void setManagementAllowanceMonthly(BigDecimal managementAllowanceMonthly) {
        this.managementAllowanceMonthly = managementAllowanceMonthly;
    }

    public String getNotaryNumber() {
        return notaryNumber;
    }

    public void setNotaryNumber(String notaryNumber) {
        this.notaryNumber = notaryNumber;
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

    public BigDecimal getTotalCompensation() {
        return totalCompensation;
    }

    public void setTotalCompensation(BigDecimal totalCompensation) {
        this.totalCompensation = totalCompensation;
    }
}
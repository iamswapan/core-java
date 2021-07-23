package com.test.eligiblity;

public class CriteriaMaster {
    String employmentType;
    String gender;
    Integer minimumMonthlyIncome;
    String residenceType;
    String residingSince;
    Integer minCibil;
    Integer maxCibil;
    Financier financier;

    public String getEmploymentType() {
        return employmentType;
    }

    public CriteriaMaster setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public CriteriaMaster setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Integer getMinimumMonthlyIncome() {
        return minimumMonthlyIncome;
    }

    public CriteriaMaster setMinimumMonthlyIncome(Integer minimumMonthlyIncome) {
        this.minimumMonthlyIncome = minimumMonthlyIncome;
        return this;
    }

    public String getResidenceType() {
        return residenceType;
    }

    public CriteriaMaster setResidenceType(String residenceType) {
        this.residenceType = residenceType;
        return this;
    }

    public String getResidingSince() {
        return residingSince;
    }

    public CriteriaMaster setResidingSince(String residingSince) {
        this.residingSince = residingSince;
        return this;
    }

    public Integer getMinCibil() {
        return minCibil;
    }

    public CriteriaMaster setMinCibil(Integer minCibil) {
        this.minCibil = minCibil;
        return this;
    }

    public Integer getMaxCibil() {
        return maxCibil;
    }

    public CriteriaMaster setMaxCibil(Integer maxCibil) {
        this.maxCibil = maxCibil;
        return this;
    }

    public Financier getFinancier() {
        return financier;
    }

    public CriteriaMaster setFinancier(Financier financier) {
        this.financier = financier;
        return this;
    }

    @Override
    public String toString() {
        return "CriteriaMaster{" +
                "employmentType='" + employmentType + '\'' +
                ", gender='" + gender + '\'' +
                ", minimumMonthlyIncome=" + minimumMonthlyIncome +
                ", residenceType='" + residenceType + '\'' +
                ", residingSince='" + residingSince + '\'' +
                ", minCibil=" + minCibil +
                ", maxCibil=" + maxCibil +
                ", financier=" + financier +
                '}';
    }
}

package com.test.eligiblity;

public class UserData {
    String employmentType;
    String gender;
    Integer monthlyIncome;
    String residenceType;
    String residingSince;
    Integer cibil;

    public String getEmploymentType() {
        return employmentType;
    }

    public UserData setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public UserData setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Integer getMonthlyIncome() {
        return monthlyIncome;
    }

    public UserData setMonthlyIncome(Integer monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
        return this;
    }

    public String getResidenceType() {
        return residenceType;
    }

    public UserData setResidenceType(String residenceType) {
        this.residenceType = residenceType;
        return this;
    }

    public String getResidingSince() {
        return residingSince;
    }

    public UserData setResidingSince(String residingSince) {
        this.residingSince = residingSince;
        return this;
    }

    public Integer getCibil() {
        return cibil;
    }

    public UserData setCibil(Integer cibil) {
        this.cibil = cibil;
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "employmentType='" + employmentType + '\'' +
                ", gender='" + gender + '\'' +
                ", monthlyIncome=" + monthlyIncome +
                ", residenceType='" + residenceType + '\'' +
                ", residingSince='" + residingSince + '\'' +
                ", cibil=" + cibil +
                '}';
    }
}

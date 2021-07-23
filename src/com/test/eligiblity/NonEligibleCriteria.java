package com.test.eligiblity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class NonEligibleCriteria {
    public static void main(String[] args) {
        List<CriteriaMaster> eligibilityList = buildInitialData();
        //System.out.println(eligibilityList);
        UserData data = new UserData().setEmploymentType("private").setCibil(700).setGender("male").setMonthlyIncome(10000).setResidenceType("rent");
        List<CriteriaMaster> nonEligibleList = ComputeEligiblity(data, eligibilityList);
        System.out.println(nonEligibleList);
    }

    public static List<CriteriaMaster> buildInitialData() {
        List<CriteriaMaster> criteriaMasterList = new ArrayList<>();
        CriteriaMaster hdfc = new CriteriaMaster().setFinancier(Financier.HDFC).setEmploymentType("govt").setMinimumMonthlyIncome(25000).setResidenceType("self").setResidingSince("2");
        CriteriaMaster sbi = new CriteriaMaster().setFinancier(Financier.SBI).setEmploymentType("govt").setGender("male").setResidenceType("rent").setMinimumMonthlyIncome(5000).setMinCibil(606).setMaxCibil(900);
        criteriaMasterList.add(hdfc);
        criteriaMasterList.add(sbi);
        return criteriaMasterList;
    }

    public static List<CriteriaMaster> ComputeEligiblity(UserData data, List<CriteriaMaster> eligibilityList) {
        List<CriteriaMaster> nonEligibleList=new ArrayList<>();
        for (CriteriaMaster criteria : eligibilityList) {
            CriteriaMaster nonEligible = new CriteriaMaster();
            switch (criteria.getFinancier()) {
                case HDFC:
                    nonEligible.setFinancier(Financier.HDFC);
                    if (!criteria.getEmploymentType().equals(data.getEmploymentType())) {
                        nonEligible.setEmploymentType(criteria.getEmploymentType());
                    }
                    if (criteria.getMinimumMonthlyIncome() > data.getMonthlyIncome()) {
                        nonEligible.setMinimumMonthlyIncome(criteria.getMinimumMonthlyIncome());
                    }
                    if (!criteria.getResidenceType().equals(data.getResidenceType())) {
                        nonEligible.setResidenceType(criteria.getResidenceType());
                    }
                    if (!criteria.getResidingSince().equals(data.getResidingSince())) {
                        nonEligible.setResidingSince(criteria.getResidingSince());
                    }
                    break;
                case SBI:
                    nonEligible.setFinancier(Financier.SBI);
                    if (!criteria.getEmploymentType().equals(data.getEmploymentType())) {
                        nonEligible.setEmploymentType(criteria.getEmploymentType());
                    }
                    if (!criteria.getGender().equals(data.getGender())) {
                        nonEligible.setGender(criteria.getGender());
                    }
                    if (criteria.getMinimumMonthlyIncome() > data.getMonthlyIncome()) {
                        nonEligible.setMinimumMonthlyIncome(criteria.getMinimumMonthlyIncome());
                    }
                    if (!criteria.getResidenceType().equals(data.getResidenceType())) {
                        nonEligible.setResidenceType(criteria.getResidenceType());
                    }
                    if (criteria.getMinCibil() > data.getCibil() || criteria.getMaxCibil() < data.getCibil()) {
                        nonEligible.setMinCibil(criteria.getMinCibil()).setMaxCibil(criteria.getMaxCibil());
                    }
                    break;
            }
            nonEligibleList.add(nonEligible);
        }
        return nonEligibleList;
    }
}

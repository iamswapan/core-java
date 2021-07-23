package com.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FOIRTest {
    public static void main(String[] args) {
        System.out.println(calculateMaxFoirAmount(25000, 0, 60, 9.0, 60));

        System.out.println("emi start===="+getEmiStartDate());
        System.out.println("emi start param===="+getEmiStartDate("25-Dec-2020"));
        //System.out.println("emi end===="+getEmiEndDate("05-Jan-2021", "1"));
    }

    public static double calculateMaxFoirAmount(Integer monthlyIncome, Integer monthlyObligation, double foir, Double interest, Integer tenure) {
        System.out.println("Entry: RuleServiceImpl :: calculateMaxFoirAmount()");
        double maxFoir = 0.0;
        double availableFoir = monthlyIncome * foir / 100;
        double customerCapacity = availableFoir - monthlyObligation;
        if (customerCapacity <= 0.0) {
            return maxFoir;
        }
        maxFoir = calculateMaxFOIR(customerCapacity, interest, tenure);
        System.out.println("Response data: FOIR="+ maxFoir);

        return maxFoir;
    }

    public static double calculateMaxFOIR(Double emiCapacity, Double rateOfInterest, Integer tenure) {
        double loanAmount = 0;
        try {
            double y = calculateY(rateOfInterest);
            loanAmount = emiCapacity * (y * (1 - Math.pow(y, tenure))) / (1 - y);
        } catch (Exception e) {
            System.out.println("Error while calculating Max FOIR"+ e);
        }

        return loanAmount;

    }
    public static Double calculateY(Double rateOfInterest) {
        return 1 / (1 + ((rateOfInterest / 100) / 12));
    }

    public static String getEmiStartDate() {
        LocalDate currentDate = LocalDate.now();
        int dateOfMonth = currentDate.getDayOfMonth();
        LocalDate emiStartDate;
        /*if (dateOfMonth <= 20) {
            emiStartDate = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue() + 1, 7);
        } else {
            emiStartDate = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue() + 2, 5);
        }*/

        int monthValue = currentDate.getMonthValue();
        if (dateOfMonth <= 20) {
            emiStartDate = LocalDate.of(monthValue == 12 ? currentDate.plusYears(1).getYear() : currentDate.getYear(),
                    currentDate.plusMonths(1).getMonthValue(), 7);
        } else {
            emiStartDate = LocalDate.of(monthValue >= 11 ? currentDate.plusYears(1).getYear() : currentDate.getYear(),
                    currentDate.plusMonths(2).getMonthValue(), 5);
        }

        DateTimeFormatter format =  DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return emiStartDate.format(format);
    }

    public static String getEmiStartDate(String date) {
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate currentDate = LocalDate.parse(date, format1);
        int dateOfMonth = currentDate.getDayOfMonth();
        LocalDate emiStartDate;
        /*if (dateOfMonth <= 20) {
            emiStartDate = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue() + 1, 7);
        } else {
            emiStartDate = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue() + 2, 5);
        }*/

        int monthValue = currentDate.getMonthValue();
        if (dateOfMonth <= 20) {
            emiStartDate = LocalDate.of(monthValue == 12 ? currentDate.plusYears(1).getYear() : currentDate.getYear(),
                    currentDate.plusMonths(1).getMonthValue(), 7);
        } else {
            emiStartDate = LocalDate.of(monthValue >= 11 ? currentDate.plusYears(1).getYear() : currentDate.getYear(),
                    currentDate.plusMonths(2).getMonthValue(), 5);
        }

        DateTimeFormatter format =  DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return emiStartDate.format(format);
    }

    public static String getEmiEndDate(String emiStartDate, String tenure) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate startDate = LocalDate.parse(emiStartDate, format);
        LocalDate endDate = startDate.plusYears(Long.valueOf(tenure));
        endDate = endDate.minusMonths(1);
        return endDate.format(format);
    }
}

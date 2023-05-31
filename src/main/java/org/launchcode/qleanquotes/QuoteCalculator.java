package org.launchcode.qleanquotes;

import org.launchcode.qleanquotes.models.Quote;
import org.launchcode.qleanquotes.models.enums.CleaningOption;

public class QuoteCalculator {
        public Long calculateTotalCharge(Quote quote) {
            Long totalCharge = 0L;

             totalCharge += quote.getSquareFeet()/100 + quote.getNumOfRoom()*10 + quote.getNumOfBathroom()*20;

             if (quote.getCleaningOption() == CleaningOption.valueOf("AVERAGE")){
                totalCharge += totalCharge * 10;
                System.out.println("Average Cleaning");
            }
            if (quote.getCleaningOption() == CleaningOption.valueOf("DEEP")){
                totalCharge += totalCharge * 10;
                System.out.println("Deep Cleaning");
            }
            return totalCharge;
        }

        public double calculateTotalCost(Quote quote){
            double totalCost = 0.00;

            totalCost += quote.getSquareFeet()/1 + quote.getNumOfRoom()*10 + quote.getNumOfBathroom()*20;

            return totalCost;

    }
}

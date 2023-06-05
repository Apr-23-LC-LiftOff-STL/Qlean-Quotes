package org.launchcode.qleanquotes;

import org.junit.Assert;
import org.launchcode.qleanquotes.models.Quote;
import org.launchcode.qleanquotes.models.enums.CleaningOption;
import java.text.DecimalFormat;

public class QuoteCalculator {

    public double calculateTotalCost(Quote quote){
        double totalCost = (quote.getSquareFeet()/quote.getNumOfRoom()*0.05) + quote.getNumOfBathroom()*20;

        if (quote.getCleaningOption() == CleaningOption.valueOf("AVERAGE")){
            totalCost += totalCost;
            System.out.println(totalCost);
        }
        else if (quote.getCleaningOption() == CleaningOption.valueOf("DEEP")){
            totalCost += totalCost + 50;
            System.out.println(totalCost);
        }

        return totalCost;

    }

    public Long calculateTotalCharge(Quote quote) {

        double totalCost = calculateTotalCost(quote);

        long totalCharge = Math.round(totalCost * 100);

        return totalCharge;
    }

    public String formatTotalCost(Quote quote){

        double totalCost = calculateTotalCost(quote);
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String formattedTotalCost = decimalFormat.format(totalCost);
        System.out.println(formattedTotalCost);
        return formattedTotalCost;
    }

}

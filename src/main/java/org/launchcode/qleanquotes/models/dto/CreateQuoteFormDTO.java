package org.launchcode.qleanquotes.models.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;



public class CreateQuoteFormDTO {


    @NotNull(message = "Information of square feet is required!")
    @Min(value = 70, message = "Minimum requirement of square feet is 70!")
    private Integer squareFeet;

    @NotNull (message = "Number of room is required!")
    @Min(value = 1, message = "Number of rooms is required!")
    private Integer numOfRoom;

    private Integer numOfBathroom;


    @NotNull (message = "Please select one of the options!")
    private String cleaningOptions;

    private double totalCost;

    private Long totalCharge;

    public CreateQuoteFormDTO() {
    }

    public CreateQuoteFormDTO(Integer squareFeet, Integer numOfRoom, Integer numOfBathroom, String cleaningOptions, double totalCost, Long totalCharge) {
        this.squareFeet = squareFeet;
        this.numOfRoom = numOfRoom;
        this.numOfBathroom = numOfBathroom;
        this.cleaningOptions = cleaningOptions;
        this.totalCost = totalCost;
        this.totalCharge = totalCharge;

    }


    public  Integer getSquareFeet() {
        return squareFeet;
    }

    public  Integer getNumOfRoom() {
        return numOfRoom;
    }

    public  Integer getNumOfBathroom() {
        return numOfBathroom;
    }

    public void setSquareFeet(Integer squareFeet) {
        this.squareFeet = squareFeet;
    }

    public void setNumOfRoom(Integer numOfRoom) {
        this.numOfRoom = numOfRoom;
    }

    public void setNumOfBathroom(Integer numOfBathroom) {
        this.numOfBathroom = numOfBathroom;
    }

    public String getCleaningOptions() {
        return cleaningOptions;
    }

    public void setCleaningOptions(String cleaningOptions) {
        this.cleaningOptions = cleaningOptions;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Long getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(Long totalCharge) {
        this.totalCharge = totalCharge;
    }
}



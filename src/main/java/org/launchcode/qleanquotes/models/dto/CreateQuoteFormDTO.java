package org.launchcode.qleanquotes.models.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class CreateQuoteFormDTO {


    @NotNull(message = "Information of square feet is required!")
    @Min(value = 70, message = "Minimum requirement of square feet is 70!")
    private Integer squareFeet;

    @NotNull (message = "Number of room is required!")
    @Min(value = 1, message = "Number of rooms is required!")
    private Integer numOfRoom;

    private Integer numOfBathroom;



    public CreateQuoteFormDTO() {
    }

    public CreateQuoteFormDTO(Integer squareFeet, Integer numOfRoom, Integer numOfBathroom) {
        this.squareFeet = squareFeet;
        this.numOfRoom = numOfRoom;
        this.numOfBathroom = numOfBathroom;

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


}



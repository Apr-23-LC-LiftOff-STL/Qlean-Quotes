package org.launchcode.qleanquotes.models;

import jakarta.persistence.Entity;

@Entity
public class Quote extends AbstractEntity {

//    @NotNull
//    @NotBlank(message = "Information of square feet is required!")
//    @Size(min = 70, message = "Minimum requirement of square feet is 70")
    private Integer squareFeet;

//    @NotNull
//    @NotBlank
//    @Size(min = 1, message = "Number of room is required")
    private Integer numOfRoom;

    private Integer numOfBathroom;


    public Quote() {
    }

    public Quote(Integer squareFeet, Integer numOfRoom, Integer numOfBathroom) {
        this.squareFeet = squareFeet;
        this.numOfRoom = numOfRoom;
        this.numOfBathroom = numOfBathroom;
    }


    public Integer getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(Integer squareFeet) {
        this.squareFeet = squareFeet;
    }

    public Integer getNumOfRoom() {
        return numOfRoom;
    }

    public void setNumOfRoom(Integer numOfRoom) {
        this.numOfRoom = numOfRoom;
    }

    public Integer getNumOfBathroom() {
        return numOfBathroom;
    }

    public void setNumOfBathroom(Integer numOfBathroom) {
        this.numOfBathroom = numOfBathroom;
    }


}

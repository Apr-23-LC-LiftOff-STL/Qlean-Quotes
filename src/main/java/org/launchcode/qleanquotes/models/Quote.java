package org.launchcode.qleanquotes.models;

import jakarta.persistence.Entity;


//TODO there needs to be persistence annotations in this model for the databases tables to relate to each other (foreign key! think @manytoone, @onetomany, blah blah)
@Entity
public class Quote extends AbstractEntity {


    private Integer squareFeet;

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

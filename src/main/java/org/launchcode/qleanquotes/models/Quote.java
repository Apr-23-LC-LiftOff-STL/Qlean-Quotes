package org.launchcode.qleanquotes.models;

import jakarta.persistence.Entity;
import org.launchcode.qleanquotes.models.data.QuoteRepository;
import org.launchcode.qleanquotes.models.enums.CleaningOption;


//TODO there needs to be persistence annotations in this model for the databases tables to relate to each other (foreign key! think @manytoone, @onetomany, blah blah)
@Entity
public class Quote extends AbstractEntity {

    private Integer squareFeet;

    private Integer numOfRoom;

    private Integer numOfBathroom;

    private CleaningOption cleaningOption;


    public Quote() {
    }

    public Quote(Integer squareFeet, Integer numOfRoom, Integer numOfBathroom, CleaningOption cleaningOption) {
        this();
        this.squareFeet = squareFeet;
        this.numOfRoom = numOfRoom;
        this.numOfBathroom = numOfBathroom;
        this.cleaningOption = cleaningOption;
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

    public CleaningOption getCleaningOption() {
        return cleaningOption;
    }

    public void setCleaningOption(CleaningOption cleaningOption) {
        this.cleaningOption = cleaningOption;
    }
}

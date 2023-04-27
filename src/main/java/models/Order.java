package models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Order extends AbstractEntity{


    @NotNull
    @NotBlank(message = "Information of square feet is required!")
    @Size(min= 70, message = "Minimum requirement of square feet is 70")
    private int squareFeet;

    @NotNull
    @NotBlank
    @Size(min= 1, message = "Number of room is required")
    private int numOfRoom;

    private int numOfBathroom;


    public Order() {

    }

    public Order(int squareFeet, int numOfRoom, int numOfBathroom){
        this.squareFeet = squareFeet;
        this.numOfRoom = numOfRoom;
        this.numOfBathroom = numOfBathroom;

    }




    public int getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(int squareFeet) {
        this.squareFeet = squareFeet;
    }

    public int getNumOfRoom() {
        return numOfRoom;
    }

    public void setNumOfRoom(int numOfRoom) {
        this.numOfRoom = numOfRoom;
    }

    public int getNumOfBathroom() {
        return numOfBathroom;
    }

    public void setNumOfBathroom(int numOfBathroom) {
        this.numOfBathroom = numOfBathroom;
    }





}

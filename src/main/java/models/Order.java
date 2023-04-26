package models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Order extends AbstractEntity{


    @NotNull
    @NotBlank(message = "Information if square feet is required!")
    @Size(min= 70, message = "Minimum requirement of square feet is 70")
    private int squareFeet;

    @NotNull
    @NotBlank
    @Size(min= 1, message = "Number of room is required")
    private int numOfRoom;

    private int numOfBathroom;

    @NotNull
    @NotBlank (message = "Address is required")
    private Address address;

    public Order(int squareFeet, int numOfRoom, int numOfBathroom, Address address){
        this.squareFeet = squareFeet;
        this.numOfRoom = numOfRoom;
        this.numOfBathroom = numOfBathroom;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    //Since address have integar and String info
    public class Address {
        private  String streetAddress;
        private int streetNumber;
        private int zipCode;

        public Address(String streetAddress, int streetNumber, int zipCode) {
            this.streetAddress = streetAddress;
            this.streetNumber = streetNumber;
            this.zipCode = zipCode;
        }

        public String getStreetAddress() {
            return streetAddress;
        }

        public void setStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress;
        }

        public int getStreetNumber() {
            return streetNumber;
        }

        public void setStreetNumber(int streetNumber) {
            this.streetNumber = streetNumber;
        }

        public int getZipCode() {
            return zipCode;
        }

        public void setZipCode(int zipCode) {
            this.zipCode = zipCode;
        }
    }



}

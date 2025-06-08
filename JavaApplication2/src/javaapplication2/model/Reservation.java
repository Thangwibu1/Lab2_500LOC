package javaapplication2.model;

import javaapplication2.storage.GuestList;

import java.io.IOException;
import java.io.Serializable;

public class Reservation implements Serializable {
    private String reservedId;
    private String nationalIdNumber;
    private String roomId;
    private int rentalDays;
    private String startDate;
    private String endDate;
    private String nameOfCoTenant;

    // Default constructor
    public Reservation() {
    }
    // Constructor with parameters
    public Reservation(String reservedId, String nationalIdNumber, String roomId, int rentalDays, String startDate, String endDate, String nameOfCoTenant) {
        this.reservedId = reservedId;
        this.nationalIdNumber = nationalIdNumber;
        this.roomId = roomId;
        this.rentalDays = rentalDays;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nameOfCoTenant = nameOfCoTenant;
    }
    // Constructor with reservedId
    public Reservation(String reservedId) {
        this.reservedId = reservedId;
    }
    // Getters and Setters

    public String getReservedId() {
        return reservedId;
    }

    public void setReservedId(String reservedId) {
        this.reservedId = reservedId;
    }

    public String getNationalIdNumber() {
        return nationalIdNumber;
    }

    public void setNationalIdNumber(String nationalIdNumber) {
        this.nationalIdNumber = nationalIdNumber;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNameOfCoTenant() {
        return nameOfCoTenant;
    }

    public void setNameOfCoTenant(String nameOfCoTenant) {
        this.nameOfCoTenant = nameOfCoTenant;
    }

    public void showInformation() throws IOException {
        System.out.println("Reservation ID: " + reservedId);
        System.out.println("National ID Number: " + nationalIdNumber);
        GuestList readGuestList = new GuestList();
        readGuestList.readFromFile();

        readGuestList.searchById(nationalIdNumber).showInformation();
        System.out.println("Room ID: " + roomId);
        System.out.println("Rental Days: " + rentalDays);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Name of Co-Tenant: " + nameOfCoTenant);
    }
}

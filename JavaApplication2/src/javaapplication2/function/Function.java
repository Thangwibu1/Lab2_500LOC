package javaapplication2.function;

import javaapplication2.controller.Inputer;
import javaapplication2.model.Guest;
import javaapplication2.model.Reservation;
import javaapplication2.storage.GuestList;
import javaapplication2.storage.ReservationList;
import javaapplication2.storage.RoomList;

import java.io.IOException;
import java.time.LocalDate;

public class Function {
    RoomList roomList = new RoomList();
    GuestList guestList = new GuestList();
    ReservationList reservationList = new ReservationList();

    public void importRoomData() throws IOException {
        if (roomList.readFromFile()) {
            System.out.println(roomList.size() + " Rooms loaded successfully from file.");
            System.out.println((18 - roomList.size()) + " Failed to load rooms from file.");
        } else {
            System.out.println((18 - roomList.size()) + " Failed to load rooms from file.");
        }
    }

    public void displayRooms() {
        roomList.showAll();
    }

    public void displayGuests() {
        guestList.showAll();
    }

    public void importGuestData() throws IOException {
        guestList.readFromFile();
    }

    public void importReservationData() throws IOException {
        reservationList.readFromFile();
    }

    public void enterGuestInformation() throws IOException {
        // Search customer by name
        String nationalId = Inputer.inputString("^[0-9]{12}$", "Please enter customer's national ID number: ");
        String firstName = Inputer.inputString("^[a-zA-Z]+$", "Please enter customer's first name: ");
        String lastName = Inputer.inputString("^[a-zA-Z]+$", "Please enter customer's last name: ");

        String name = firstName + ", " + lastName;

        String birthDate = "";
        while (true) {
            birthDate = Inputer.inputString("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([12][0-9]{3})$", "Please enter customer's birth date dd/mm/yyyy: ");
            LocalDate yearGet = LocalDate.now();
            String[] birthDateParts = birthDate.split("/");
            if (Integer.parseInt(birthDateParts[2]) > yearGet.getYear() || Integer.parseInt(birthDateParts[2]) < 0) {
                continue;
            }
            break;
        }

        String gender = Inputer.inputString("^[MF]$", "Please enter customer's gender (M/F): ");
        String phoneNumber = Inputer.inputPhone("Please enter customer's phone number: ");

        int rentalDays = 0;
        while (true) {
            rentalDays = Inputer.inputInt("^[1-9][0-9]*$", "Please enter the number of rental days (positive integer): ");
            if (rentalDays <= 0) {
                System.out.println("Rental days must be a positive integer. Please try again.");
                continue;
            }
            break;
        }
        String startDate = "";
        String endDate = "";
        while (true) {
            startDate = Inputer.inputString("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([12][0-9]{3})$", "Please enter the start date dd/mm/yyyy: ");
            String[] startDateParts = startDate.split("/");
            LocalDate startDateLocal = LocalDate.parse(startDateParts[2] + "-" + startDateParts[1] + "-" + startDateParts[0]);
            if (!startDateLocal.isAfter(LocalDate.now())) {
                System.out.println("Strt date must be in future!");
                continue;
            }
            LocalDate endDateLocal = startDateLocal.plusDays(rentalDays);
            endDate = String.format("%02d/%02d/%04d", endDateLocal.getDayOfMonth(), endDateLocal.getMonthValue(), endDateLocal.getYear());
            break;
        }
        //handle end date
        //end part handle end date

        roomList.showAll();
        String roomId = "";
        while (true) {
            roomId = Inputer.inputString("^[R]{1}[0-9]{3}$", "Please enter customer's room ID: ");
            if (roomList.searchById(roomId) == null || !reservationList.isAvailable(roomId, startDate, endDate)) {
                System.out.println(reservationList.isAvailable(roomId, startDate, endDate));
                System.out.println("Incorrect room ID. Please enter a valid room ID.");
                continue;
            }
            break;
        }

        String nameOfCotenant = Inputer.inputString("^[a-zA-Z\\s]+$", "Please enter the name of co-tenant (if any, if not enter null): ");
        if (nameOfCotenant.equalsIgnoreCase("null")) {
            nameOfCotenant = "";
        }

        String reservationId = "Rs" + (reservationList.size() + 1);
        if (reservationList.addNew(new Reservation(reservationId, roomId, nationalId, rentalDays, startDate, endDate, nameOfCotenant))
                && guestList.addNew(new Guest(nationalId, name, birthDate, gender, phoneNumber))) {
            System.out.println("Guest information and reservation successfully added.");
            reservationList.saveToFile();
            guestList.saveToFile();
            new Reservation(reservationId, nationalId, roomId, rentalDays, startDate, endDate, nameOfCotenant).showInformation();
            new Guest(nationalId, name, birthDate, gender, phoneNumber).showInformation();
        } else {
            System.out.println("Failed to add guest information or reservation.");
        }


    }
}

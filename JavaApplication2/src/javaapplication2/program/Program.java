package javaapplication2.program;

import javaapplication2.controller.Menu;
import javaapplication2.function.Function;
import javaapplication2.model.Room;
import javaapplication2.storage.GuestList;

import java.io.IOException;


public class Program {

    public static void main(String[] args) throws IOException {
        //inialize variables


        Menu menu = new Menu();
        Function function = new Function();
        function.importGuestData();
        function.importReservationData();
        while (true) {
            int choose = menu.showMenu();
            switch (choose) {
                case 1:
                    // Import Room Data from Text File
                    function.importRoomData();
                    break;

                case 2:
                    function.displayRooms();

                    break;
                case 3:
                    function.enterGuestInformation();
                    break;
                case 4:


                    break;
                case 5:

                    break;
                case 6:
                    // Update order information

                    break;
                case 7:

                    // Save data to file
                    break;
                case 8:

                    // Display Customer or Order lists
                    break;
                case 0:
                    // Exit

                    return;
            }
        }

    }
    
}

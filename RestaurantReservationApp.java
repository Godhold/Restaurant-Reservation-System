import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Scanner;

public class RestaurantReservationApp {
    public static void main(String[] args) {
        RestaurantReservationSystem restaurantSystem = new RestaurantReservationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n----- Restaurant Reservation System Menu -----");
            System.out.println("1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Reschedule Reservation");
            System.out.println("4. Display Occupancy");
            System.out.println("5. Display Waitlist");
            System.out.println("6. Add to Waitlist");
            System.out.println("7. Display Reservation");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    makeReservation(restaurantSystem, scanner);
                    break;
                case 2:
                    cancelReservation(restaurantSystem, scanner);
                    break;
                case 3:
                    rescheduleReservation(restaurantSystem, scanner);
                    break;
                case 4:
                    restaurantSystem.displayOccupancy();
                    break;
                case 5:
                    restaurantSystem.displayWaitlist();
                    break;
                case 6:
                    addToWaitlist(restaurantSystem, scanner);
                    break;
                case 7:
                    displayReservation(restaurantSystem, scanner);
                    break;
                case 8:
                    System.out.println("Exiting the Restaurant Reservation System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void rescheduleReservation(RestaurantReservationSystem restaurantSystem, Scanner scanner) {
        System.out.print("Enter customer name for rescheduling: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter new date (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();
        Date newDate = parseDate(dateString);

        System.out.print("Enter new time (24-hour format): ");
        int newTime = scanner.nextInt();

        System.out.print("Enter new table size: ");
        int newTableSize = scanner.nextInt();

        restaurantSystem.rescheduleReservation(customerName, newDate, newTime, newTableSize);
    }

    private static void displayReservation(RestaurantReservationSystem restaurantSystem, Scanner scanner) {
        System.out.print("Enter customer name for reservation lookup: ");
        String customerName = scanner.nextLine();

        restaurantSystem.displayReservation(customerName);
    }

    private static void makeReservation(RestaurantReservationSystem restaurantSystem, Scanner scanner) {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter date (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();
        Date date = parseDate(dateString);

        System.out.print("Enter time (24-hour format): ");
        int time = scanner.nextInt();

        System.out.print("Enter table size: ");
        int tableSize = scanner.nextInt();

        restaurantSystem.makeReservation(customerName, date, time, tableSize);
    }

    private static void cancelReservation(RestaurantReservationSystem restaurantSystem, Scanner scanner) {
        System.out.print("Enter customer name for cancellation: ");
        String customerName = scanner.nextLine();

        restaurantSystem.cancelReservation(customerName);
    }

    private static void addToWaitlist(RestaurantReservationSystem restaurantSystem, Scanner scanner) {
        System.out.print("Enter customer name for waitlist: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter date (yyyy-MM-dd) for waitlist: ");
        String dateString = scanner.nextLine();
        Date date = parseDate(dateString);

        System.out.print("Enter time (24-hour format) for waitlist: ");
        int time = scanner.nextInt();

        System.out.print("Enter table size for waitlist: ");
        int tableSize = scanner.nextInt();

        restaurantSystem.addToWaitlist(customerName, date, time, tableSize);
    }

    private static Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            return null;
        }
    }
}

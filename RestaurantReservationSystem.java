import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RestaurantReservationSystem {
    private TableNode root;
    private Map<String, Reservation> reservationMap;
    private RestQueue<String> restQueue;

    public RestaurantReservationSystem() {
        this.root = null;
        this.reservationMap = new HashMap<>();
        this.restQueue = new RestQueue<>();
    }

    private TableNode insertTable(TableNode root, int capacity) {
        if (root == null) {
            return new TableNode(capacity);
        }

        if (capacity < root.getCapacity()) {
            root.setLeft(insertTable(root.getLeft(), capacity));
        } else if (capacity > root.getCapacity()) {
            root.setRight(insertTable(root.getRight(), capacity));
        }

        return root;
    }

    public void makeReservation(String customerName, Date date, int time, int tableSize) {
        int availableTableSize = findAvailableTable(root, tableSize);
    
        if (availableTableSize != -1) {
            Reservation reservation = new Reservation(customerName, date, time, availableTableSize);
            reservationMap.put(customerName, reservation);
            System.out.println("Reservation successful: " + reservation.getCustomerName() +
                " on " + reservation.getDate() + " for " + reservation.getTableSize() + " people.");
        } else {
            restQueue.enqueue(customerName);
            System.out.println("No available tables. Added to the waitlist: " + customerName);
        }
    }
    
    private int findAvailableTable(TableNode root, int targetSize) {
        // Create a helper method to find the smallest available table
        return findSmallestTable(root, targetSize, targetSize);
    }
    
    // Helper method to find the smallest available table
    private int findSmallestTable(TableNode root, int targetSize, int smallestSoFar) {
        if (root == null) {
            return smallestSoFar; // No available table in the current subtree
        }
    
        if (targetSize < root.getCapacity()) {
            // Search in the left subtree for a smaller table
            return findSmallestTable(root.getLeft(), targetSize, Math.min(smallestSoFar, root.getCapacity()));
        } else if (targetSize > root.getCapacity()) {
            // Search in the right subtree for a larger table
            return findSmallestTable(root.getRight(), targetSize, smallestSoFar);
        } else {
            // Found a table of the exact size
            return root.getCapacity();
        }
    }
    
    
    public void cancelReservation(String customerName) {
        Reservation reservation = reservationMap.get(customerName);

        if (reservation != null) {
            reservationMap.remove(customerName);
            root = insertTable(root, reservation.getTableSize());
            System.out.println("Reservation canceled: " + customerName);
        } else {
            System.out.println("Reservation not found for: " + customerName);
        }
    }

    public void addToWaitlist(String customerName, Date date, int time, int tableSize) {
        restQueue.enqueue(customerName);
    
        
        System.out.println("Added to the waitlist: " + customerName + " on " + date + " at " + time + " for " + tableSize + " people.");
    }
    

    public void rescheduleReservation(String customerName, Date newDate, int newTime, int newTableSize) {
        Reservation existingReservation = reservationMap.get(customerName);

        if (existingReservation != null) {
            int availableTableSize = findAvailableTable(root, newTableSize);

            if (availableTableSize != -1) {
                Reservation updatedReservation = new Reservation(
                        existingReservation.getCustomerName(),
                        newDate, 
                        newTime,
                        availableTableSize
                );

                reservationMap.put(customerName, updatedReservation);
                System.out.println("Reservation rescheduled successfully: " + customerName);
            } else {
                System.out.println("No available tables for rescheduling: " + customerName);
            }
        } else {
            System.out.println("Reservation not found for rescheduling: " + customerName);
        }
    }

    public void displayReservation(String customerName) {
        Reservation reservation = reservationMap.get(customerName);

        if (reservation != null) {
            System.out.println("Reservation details for " + customerName + ":");
            System.out.println("Table Size: " + reservation.getTableSize() +
                    ", Date: " + reservation.getDate() +
                    ", Time: " + reservation.getTime());
        } else {
            System.out.println("Reservation not found for: " + customerName);
        }
    }
    public void displayWaitlist() {
        System.out.println("\nWaitlist:");
        restQueue.display();
    }

    public void displayOccupancy() {
        System.out.println("\nOccupancy Overview:");

        for (Map.Entry<String, Reservation> entry : reservationMap.entrySet()) {
            Reservation reservation = entry.getValue();
            System.out.println("Table Size: " + reservation.getTableSize() +
                    ", Customer: " + reservation.getCustomerName() +
                    ", Date: " + reservation.getDate() +
                    ", Time: " + reservation.getTime());
        }
    }
}

import java.util.LinkedList;
import java.util.List;

public class ReservationManagementTable {
    private static final int TABLE_SIZE = 100;  // Set a default table size
    private static List<Reservation>[] reservedTable;

    @SuppressWarnings("unchecked")
    public ReservationManagementTable() {
        ReservationManagementTable.reservedTable = new LinkedList[TABLE_SIZE];
    }

    public static List<Reservation>[] getReservedTable() {
        return reservedTable;
    }

    public void setReservedTable(List<Reservation>[] reservedTable) {
        ReservationManagementTable.reservedTable = reservedTable;
    }

    private int hash(String key) {
        return key.hashCode() % TABLE_SIZE;
    }

    public void put(String key, Reservation reservation) {
        int index = hash(key);

        if (reservedTable[index] == null) {
            reservedTable[index] = new LinkedList<>();
        }

        // Check for duplicates and update if found
        for (Reservation existingReservation : reservedTable[index]) {
            if (existingReservation.getCustomerName().equals(key)) {
                existingReservation.setDate(reservation.getDate());
                existingReservation.setTime(reservation.getTime());
                existingReservation.setTableSize(reservation.getTableSize());
                return;
            }
        }

        reservedTable[index].add(reservation);
    }

    public Reservation get(String key) {
        int index = hash(key);

        if (reservedTable[index] != null) {
            for (Reservation reservation : reservedTable[index]) {
                if (reservation.getCustomerName().equals(key)) {
                    return reservation;
                }
            }
        }

        return null;
    }

    public void displayOccupancy() {
        System.out.println("\nOccupancy Overview:");

        for (List<Reservation> reservations : reservedTable) {
            if (reservations != null && !reservations.isEmpty()) {
                for (Reservation reservation : reservations) {
                    System.out.println("Table Size: " + reservation.getTableSize() +
                            ", Customer: " + reservation.getCustomerName() +
                            ", Date: " + reservation.getDate() +
                            ", Time: " + reservation.getTime());
                }
            }
        }
    }
}

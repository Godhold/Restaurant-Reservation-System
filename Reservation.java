import java.util.*;
public class Reservation {
    private String customerName;
    private Date date;
    private int time;
    private  int tableSize;


    public Reservation( String customerName, Date date, int time, int tableSize){
        this.customerName=customerName;
        this.date=date;
        this.time =time;
        this.tableSize=tableSize;

    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }
    
}

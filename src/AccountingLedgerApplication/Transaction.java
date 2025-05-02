package AccountingLedgerApplication;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    //Constructor
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount ){
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    //Getter method
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

     public String toString(){
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
     }

     public String toCsvFormat(){
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
     }

}

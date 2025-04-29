package AccountingLedgerApplication;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReportService {
    public void monthToDate(ArrayList<Transaction> transactions){
        LocalDate now = LocalDate.now();
        for(Transaction t: transactions){
            if(t.getDate().getMonth().equals(now.getMonth()) && t.getDate().getYear() == now.getYear()){
                System.out.println(t);
            }
        }
    }
    public void previousMonth(ArrayList<Transaction> transactions){
        LocalDate now = LocalDate.now();
        LocalDate previousMonth = now.minusMonths(1);
        for(Transaction t: transactions){
            if(t.getDate().getMonth().equals(previousMonth.getMonth()) && t.getDate().getYear() == previousMonth.getYear()){
                System.out.println(t);
            }
        }
    }
    public void yearToDate(ArrayList<Transaction> transactions){
        LocalDate now = LocalDate.now();
        for(Transaction t : transactions){
            if(t.getDate().getYear() == now.getYear()){
                System.out.println(t);
            }
        }
    }
    public void previousYear(ArrayList<Transaction> transactions){
        LocalDate now = LocalDate.now();
        for(Transaction t : transactions){
            if(t.getDate().getYear() == now.minusYears(1).getYear()){
                System.out.println(t);
            }
        }
    }
    public void searchByVendor(ArrayList<Transaction> transactions, String vendor){
        for(Transaction t: transactions){
            if(t.getVendor().equalsIgnoreCase(vendor)){
                System.out.println(t);
            }
        }
    }
}

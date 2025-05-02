package AccountingLedgerApplication;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReportService {
    //Display all transactions that occurred in the current month and year
    public void monthToDate(ArrayList<Transaction> transactions){
        System.out.println("=========***** Month to Date Transactions *****========0=\n");
        LocalDate now = LocalDate.now();
        for(Transaction t: transactions){
            //Check if the transaction's month and year match the current month and year
            if(t.getDate().getMonth().equals(now.getMonth()) && t.getDate().getYear() == now.getYear()){
                System.out.println(t);
            }
        }
    }
    //Displays all transactions that occurred in the previous month.
    public void previousMonth(ArrayList<Transaction> transactions){
        System.out.println("=========***** Previous month Transactions *****=========\n");
        LocalDate now = LocalDate.now();
        LocalDate previousMonth = now.minusMonths(1);
        for(Transaction t: transactions){
            //Check if the transaction's month and year match the previous month and year
            if(t.getDate().getMonth().equals(previousMonth.getMonth()) && t.getDate().getYear() == previousMonth.getYear()){
                System.out.println(t);
            }
        }
    }
    //Displays all transactions that occurred in the current year.
    public void yearToDate(ArrayList<Transaction> transactions){
        System.out.println("=========***** YearToDate Transactions *****=========\n");
        LocalDate now = LocalDate.now();
        for(Transaction t : transactions){
            //Check if the transaction's year matches the current year
            if(t.getDate().getYear() == now.getYear()){
                System.out.println(t);
            }
        }
    }
    //Displays all transactions that occurred in the previous year
    public void previousYear(ArrayList<Transaction> transactions){
        System.out.println("=========***** PreviousYear Transactions *****=========\n");
        LocalDate now = LocalDate.now();
        for(Transaction t : transactions){
            //Check if the transactions year matches the previous year
            if(t.getDate().getYear() == now.minusYears(1).getYear()){
                System.out.println(t);
            }
        }
    }
    //Searches for transactions by vendor name, ignoring case and leading/trailing spaces
    public ArrayList<Transaction> searchByVendor(ArrayList<Transaction> transactions, String vendor){
        System.out.println("=========***** Show Vendor Transactions *****=========\n");
        ArrayList<Transaction> allTransactions = TransactionFileHandler.readTransaction();
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            //check if the vendor field id not null and matches the search term, ignoring case and trimming spaces
            if (t.getVendor() != null && t.getVendor().trim().equalsIgnoreCase(vendor.trim())) {
                result.add(t);
            }
        }
        return result;
    }
}

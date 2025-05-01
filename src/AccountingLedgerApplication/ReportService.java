package AccountingLedgerApplication;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReportService {
    public void monthToDate(ArrayList<Transaction> transactions){
        System.out.println("=========***** Month to Date Transactions *****========0=\n");
        LocalDate now = LocalDate.now();
        for(Transaction t: transactions){
            if(t.getDate().getMonth().equals(now.getMonth()) && t.getDate().getYear() == now.getYear()){
                System.out.println(t);
            }
        }
    }
    public void previousMonth(ArrayList<Transaction> transactions){
        System.out.println("=========***** Previous month Transactions *****=========\n");
        LocalDate now = LocalDate.now();
        LocalDate previousMonth = now.minusMonths(1);
        for(Transaction t: transactions){
            if(t.getDate().getMonth().equals(previousMonth.getMonth()) && t.getDate().getYear() == previousMonth.getYear()){
                System.out.println(t);
            }
        }
    }
    public void yearToDate(ArrayList<Transaction> transactions){
        System.out.println("=========***** YearToDate Transactions *****=========\n");
        LocalDate now = LocalDate.now();
        for(Transaction t : transactions){
            if(t.getDate().getYear() == now.getYear()){
                System.out.println(t);
            }
        }
    }
    public void previousYear(ArrayList<Transaction> transactions){
        System.out.println("=========***** PreviousYear Transactions *****=========\n");
        LocalDate now = LocalDate.now();
        for(Transaction t : transactions){
            if(t.getDate().getYear() == now.minusYears(1).getYear()){
                System.out.println(t);
            }
        }
    }
    public ArrayList<Transaction> searchByVendor(ArrayList<Transaction> transactions, String vendor){
        System.out.println("=========***** Show Vendor Transactions *****=========\n");
        ArrayList<Transaction> allTransactions = TransactionFileHandler.readTransaction();
        ArrayList<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getVendor() != null && t.getVendor().trim().equalsIgnoreCase(vendor.trim())) {
                result.add(t);
            }
        }
        return result;
    }
}

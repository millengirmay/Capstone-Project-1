package AccountingLedgerApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LedgerService {
    private ArrayList<Transaction> transactions;
    private TransactionFileHandler fileHandler;

    public LedgerService(TransactionFileHandler fileHandler){
        this.fileHandler = fileHandler;
        this.transactions = fileHandler.readTransaction();
    }

    public void addDeposit(Transaction transaction){
        transactions.add(transaction);
        fileHandler.saveTransaction(transaction);
    }

    public void addPayment(Transaction transaction){
        transactions.add(transaction);
        fileHandler.saveTransaction(transaction);
    }

    public void displayAllTransactions(){
        sortTransactions();
        for(Transaction transaction : transactions){
            System.out.println(transaction);
        }
    }

    public void dispalyDeposits(){
        sortTransactions();
        for(Transaction transaction : transactions){
            if(transaction.getAmount() > 0){
                System.out.println(transaction);
            }
        }
    }
    public void displayPayments(){
        sortTransactions();
        for(Transaction transaction : transactions){
            if(transaction.getAmount() < 0){
                System.out.println(transaction);
            }
        }
    }
    public ArrayList<Transaction> getTransactions(){
        sortTransactions();
        return transactions;
    }
    private void sortTransactions(){
        Collections.sort(transactions, new Comparator<Transaction>(){
            public int compare(Transaction t1,Transaction t2){
                return t2.getDate().compareTo(t1.getDate());
            }
        });
    }
}

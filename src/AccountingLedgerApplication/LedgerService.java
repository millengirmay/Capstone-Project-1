package AccountingLedgerApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LedgerService {
    private ArrayList<Transaction> transactions;   //List to store all transactions
    private TransactionFileHandler fileHandler;    // Handler for reading and writing transactions to a file

    //Constructor initializes the file handler and loads existing transactions from the file.
    public LedgerService(TransactionFileHandler fileHandler){
        this.fileHandler = fileHandler;
        this.transactions = fileHandler.readTransaction();
    }

    //Adds a deposit transaction to the list and saves it to the file
    public void addDeposit(Transaction transaction){
        transactions.add(transaction);      //Add to in memory list
        fileHandler.saveTransaction(transaction);     //persist to file
    }
    //Adds a payment transaction to the list and saves it to the file
    public void addPayment(Transaction transaction){
        transactions.add(transaction);   //Add to in memory list
        fileHandler.saveTransaction(transaction);   //persist to file
    }
    //Displays all transactions sorted by date in descending order
    public void displayAllTransactions(){
        sortTransactions();   //Sort transactions before displaying
        for(Transaction transaction : transactions){
            System.out.println(transaction);
        }
    }
    //Displays only deposit transactions (amount > 0) sorted by date in descending order
    public void displayDeposits(){
        sortTransactions();
        for(Transaction transaction : transactions){
            if(transaction.getAmount() > 0){
                System.out.println(transaction);
            }
        }
    }
    //Displays only payment transactions(amount < 0) sorted by date in descending order
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
        //Sort transactions using a custom comparator for descending date order
        Collections.sort(transactions, new Comparator<Transaction>(){
            public int compare(Transaction t1,Transaction t2){
                return t2.getDate().compareTo(t1.getDate());
            }
        });
    }
}

package AccountingLedgerApplication;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TransactionFileHandler {
    private static final String fileName = "Transactions.CSV";

    public static ArrayList<Transaction> readTransaction() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try  {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;   //Skip header line
                }

                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    LocalTime time = LocalTime.parse(parts[1]);
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    Transaction transaction = new Transaction(date, time, description, vendor, amount);
                    transactions.add(transaction);
                }
            }
        } catch(IOException e){
            System.out.println("Error loading transactions: " + e.getMessage());
        }
        return transactions;
    }

    public void saveTransaction(Transaction transaction) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(transaction.toCsvFormat());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }
}
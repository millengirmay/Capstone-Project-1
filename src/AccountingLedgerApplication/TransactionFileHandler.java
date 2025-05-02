package AccountingLedgerApplication;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TransactionFileHandler {
    //Define the name of the CSV file where transactions are stored
    private static final String fileName = "Transactions.CSV";

    public static ArrayList<Transaction> readTransaction() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        //Reads transactions from the CSV file and returns them as an ArrayList. ArrayList of Transaction objects
        try  {
            //Create a bufferedReader to read from the CSV file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            boolean isFirstLine = true;

            //Read each line from the file
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;   //Skip header line
                }

                //Split the line into parts using the '|' delimiter
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    //parse each part into the appropriate date type
                    LocalDate date = LocalDate.parse(parts[0]);
                    LocalTime time = LocalTime.parse(parts[1]);
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);
                    //Create a new Transaction object and add it to the list
                    Transaction transaction = new Transaction(date, time, description, vendor, amount);
                    transactions.add(transaction);
                }
            }
            reader.close();  //Close the reader to free system resources
        } catch(IOException e){
            //Handle any IO exceptions that may occur
            System.out.println("Error loading transactions");
        }
        return transactions;
    }

    public void saveTransaction(Transaction transaction) {
        try {
            //Create a BufferedWriter in append mode to add to the CSV file
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            //Write the transaction in CSV format
            writer.write(transaction.toCsvFormat());
            //Add a new line after the transaction
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving transaction");
        }
    }
}
   ********************Accounting Ledger Application********************
      
A command-line Java application designed to manage financial transactions, including deposits and payments. 

 * It offers features such as:
 -Add deposits and payments with descriptions, vendors, and amounts.
  
 -View all ledger entries or filter by deposits/payments.
 
 -Generate reports: Month-to-Date, Previous Month, Year-to-Date, Previous Year.
 
 -Search transactions by vendor.
 
 -Custom search functionality allowing filtering by:
  -Start Date
  -End Date
  -Description
  -Vendor
  -Amount
 * Upon running the application, it will be presented with a home screen offering the following options:​

 -Add Deposit: Prompted to enter description, vendor, and amount.

 -Make Payment (Debit): Prompted to enter description, vendor, and amount.

 -Ledger: Access the ledger menu to view transactions and reports.

 -Exit: Exit the application.​

 * Within the Ledger Menu, it shows:​

View all entries, deposits only, or payments only.

Access the Reports Menu to generate various reports or perform searches.

***Screenshots***

![Home Screen](https://github.com/user-attachments/assets/805d3ede-0759-40fe-95e5-4c01c687594e)

![Home Screen   Ledger menu](https://github.com/user-attachments/assets/803a5c7b-e8aa-429d-9c9b-3d0b3c5dabd1)

![Ledger Menu   Reports Menu](https://github.com/user-attachments/assets/8d872c26-f3be-4380-9dd8-1d7b348046a9)

![choose the Custom Search](https://github.com/user-attachments/assets/f3ac0c1b-2e49-4055-aa9e-94a555a9b2d7)

 Interesting Code Snippet
The customSearch method allows users to filter transactions based on multiple optional criteria. Here's a simplified version:



private static void customSearch(Scanner scanner, ArrayList<Transaction> transactions) {
    // Prompt user for search criteria
    // Parse inputs and handle empty entries
    // Iterate through transactions and apply filters
    // Display matching transactions
}



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



 ****Interesting Code**** 

In the customSearch method, we use the ternary operator to simplify conditional logic when handling user input.  
This compact syntax replaces longer if-else statements and helps keep the code clean and readable.

Here's how the method works, with the ternary operator usage explained in the comments:

private static void customSearch(Scanner scanner, ArrayList<Transaction> transactions) {

    // Prompt user for search criteria

    // Parse inputs and handle empty entries using ternary operators:
    // If user input is empty, assign null; otherwise, process the input.
    // Example: Convert description to lowercase only if it's not empty
    // String description = descriptionInput.isEmpty() ? null : descriptionInput.toLowerCase();

    // This logic is also applied to:
    // - Dates: convert only if input is not blank
    // - Vendor: convert to lowercase for case-insensitive match
    // - Amount: parse to double only if input is provided

    // Iterate through transactions and apply filters based on non-null values

    // Display matching transactions
    
    // Syntax of Ternary Operator:  condition ? valueIfTrue : valueIfFalse;
    //String vendor = vendorInput.isEmpty() ? null : vendorInput.toLowerCase();
    
}




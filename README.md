   ********************Accounting Ledger Application********************

      
Project Type:

Command-Line Java Application : A Java program that runs in the terminal or command prompt, without any graphical user interface (GUI).

Purpose:

This application is designed to help users manage personal or business financial transactions by tracking deposits and payments, generating detailed financial reports, and enabling transaction search and filtering—all through a clean, menu-driven command-line interface.

Key Functionalities
1. Transaction Management

 -Add Deposit
   Users are prompted to enter:

    Description: Brief explanation of the deposit

    Vendor: Source of the income

    Amount: Positive number representing funds added

    Automatically timestamps the entry with the current date and time.

 -Make Payment (Debit)
   Users are prompted to enter:

    Description: Purpose of the payment

    Vendor: Recipient of the funds

    Amount: Positive number recorded as a debit (displayed as a negative in the ledger)

    Like deposits, each payment is timestamped.

2. Ledger Viewing and Filtering
    Users can choose to view:

       All Ledger Entries: Chronological list of all transactions

       Deposits Only: Filters entries where amount is positive

       Payments Only: Filters entries where amount is negative

3. Reporting Features

       Month-to-Date: Displays all transactions from the beginning of the current month to the current date.

       Previous Month: Displays transactions from the full previous month.

       Year-to-Date: Shows transactions starting January 1st through today.

       Previous Year: Lists all transactions from the last calendar year.

4. Transaction Search

   Search by Vendor: Allows users to input a vendor name and see all transactions involving that vendor.

   Custom Search: Users can apply one or more of the following filters:

       Start Date and End Date

       Description Keywords

       Vendor

       Amount

    The program returns transactions matching all specified criteria.

******User Interface Flow******
When the application starts, users are presented with a Main Menu (Home Screen) offering these options:

 -Add Deposit

 -Make Payment (Debit)

 -Ledger – Opens a sub-menu:

    View All Entries

    View Deposits Only

    View Payments Only

    Access Reports

    Perform Search

 -Exit – Safely terminates the application

*****Architecture and Code Highlights*****

-Date Handling:

    Uses LocalDateTime and DateTimeFormatter for accurate date recording and formatting.

    Date filtering logic parses string inputs and compares them to transaction timestamps.

-Data Storage:

    Transactions are stored in a structured  ArrayList<Transaction>) during runtime.

    Optionally, a CSV file is used for persistent storage and loading data.

-Error Handling:

    Input validations ensure correct number formats and prevent crashes from invalid entries.

    Clear prompts and re-tries improve user experience.

*****Why This Project Matters*****

This application simulates a real-world tool used by freelancers, small businesses, or even individuals looking to manage their finances manually. 

Core Java concepts (loops, conditionals, classes, collections)

File I/O and data parsing

Clean code architecture

Problem-solving and user-centric design





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




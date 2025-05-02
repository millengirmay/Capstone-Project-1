package AccountingLedgerApplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class LedgerApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionFileHandler fileHandler = new TransactionFileHandler();
        LedgerService ledgerService = new LedgerService(fileHandler);
        ReportService reportService = new ReportService();

        boolean options = true;
        while (options) {
            System.out.println("\n=*=*=*=*=*=*=*=*=*=*= Home Screen =*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("-----------------------------------------------------------");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "D":
                    //Prompt user for deposit details
                    System.out.println("Enter description: ");
                    String descriptionD = scanner.nextLine();
                    System.out.println("Enter vendor: ");
                    String vendorD = scanner.nextLine();
                    System.out.println("Enter amount: ");
                    double amountD = Double.parseDouble(scanner.nextLine());
                    //Create and add deposit transaction
                    Transaction deposit = new Transaction(LocalDate.now(), LocalTime.now(), descriptionD, vendorD, amountD);
                    ledgerService.addDeposit(deposit);
                    System.out.println("Deposit added!");
                    break;
                case "P":
                    //Prompt user for payment details
                    System.out.println("Enter description: ");
                    String descriptionP = scanner.nextLine();
                    System.out.println("Enter vendor: ");
                    String vendorP = scanner.nextLine();
                    System.out.println("Enter amount: ");
                    double amountP = Double.parseDouble(scanner.nextLine());
                    //Create and add payment transaction
                    Transaction payment = new Transaction(LocalDate.now(), LocalTime.now(), descriptionP, vendorP, -amountP);
                    ledgerService.addPayment(payment);
                    System.out.println("Payment added!");
                    break;
                case "L":
                    //Navigate to ledger menu
                    ledgerMenu(scanner, ledgerService, reportService);
                    break;
                case "X":
                    //Exit the application
                    options = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void ledgerMenu(Scanner scanner, LedgerService ledgerService, ReportService reportService) {
        boolean ledgerMenuOptions = true;
        while (ledgerMenuOptions) {
            System.out.println("\n=*=*=*=*=*=*=*=*=*=*= Ledger Menu =*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("-----------------------------------------------------------");
            System.out.println("A) All Entries");
            System.out.println("D) Deposits Only");
            System.out.println("P) Payments Only");
            System.out.println("R) Reports Menu");
            System.out.println("H) Home Screen");
            System.out.println("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "A":
                    //Display all transactions
                    System.out.println("\n=*=*=*=*=*=*=*=*=*=*= All Entries =*=*=*=*=*=*=*=*=*=*=*=");
                    ledgerService.displayAllTransactions();
                    break;
                case "D":
                    //Display only deposit transactions
                    System.out.println("\n=*=*=*=*=*=*=*=*=*=*= Deposits =*=*=*=*=*=*=*=*=*=*=*=");
                    ledgerService.displayDeposits();
                    break;
                case "P":
                    //Display only payment transactions
                    System.out.println("\n=*=*=*=*=*=*=*=*=*=*= Payments =*=*=*=*=*=*=*=*=*=*=*=");
                    ledgerService.displayPayments();
                    break;
                case "R":
                    //Navigate to reports menu
                    reportMenu(scanner, ledgerService, reportService);
                    break;
                case "H":
                    //Return to home screen
                    ledgerMenuOptions = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void reportMenu(Scanner scanner, LedgerService ledgerService, ReportService reportService) {
        boolean reportsMenuOptions = true;
        while (reportsMenuOptions) {
            System.out.println("\n=*=*=*=*=*=*=*=*=*=*= Reports Menu =*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("-----------------------------------------------------------");
            System.out.println("1) Month to Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year to Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Custom Search");
            System.out.println("0) Back - return to Ledger Menu");
            System.out.println("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    //Generate month-to-date report
                    reportService.monthToDate(ledgerService.getTransactions());
                    break;
                case "2":
                    //Generate previous month report
                    reportService.previousMonth(ledgerService.getTransactions());
                    break;
                case "3":
                    //Generate year-to-date report
                    reportService.yearToDate(ledgerService.getTransactions());
                    break;
                case "4":
                    //Generate previous year report
                    reportService.previousYear(ledgerService.getTransactions());
                    break;
                case "5":
                    //Search transactions by vendor
                    System.out.println("Enter vendor name to search: ");
                    String vendor = scanner.nextLine();
                    ArrayList<Transaction> vendorResults = reportService.searchByVendor(ledgerService.getTransactions(), vendor);
                    if (vendorResults.isEmpty()) {
                        System.out.println("No transactions found for vendor: " + vendor);
                    } else {
                        System.out.println("Transactions for vendor: " + vendor);
                        for (Transaction t : vendorResults) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "6":
                    //Search transactions by different custom
                    customSearch(scanner, ledgerService.getTransactions());
                    break;
                case "0":
                    //Return to ledger menu
                    reportsMenuOptions = false; //This will exit reportMenu() and return to the Ledger Menu
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    //Custom search method moved outside of reportMenu to avoid illegal start of expression error
        private static void customSearch(Scanner scanner, ArrayList<Transaction> transactions) {
            System.out.println("Enter start date (YYYY-MM-DD) or leave blank:");
            String startDateInput = scanner.nextLine();
            System.out.println("Enter end date (YYYY-MM-DD) or leave blank:");
            String endDateInput = scanner.nextLine();
            System.out.println("Enter description keyword or leave blank:");
            String descriptionInput = scanner.nextLine();
            System.out.println("Enter vendor name or leave blank:");
            String vendorInput = scanner.nextLine();
            System.out.println("Enter amount or leave blank:");

            String amountInput = scanner.nextLine();

            LocalDate startDate = startDateInput.isEmpty() ? null : LocalDate.parse(startDateInput); //uses the ternary operator, which is a shortcut for an if-else statement
            LocalDate endDate = endDateInput.isEmpty() ? null : LocalDate.parse(endDateInput);
            String description = descriptionInput.isEmpty() ? null : descriptionInput.toLowerCase();
            String vendor = vendorInput.isEmpty() ? null : vendorInput.toLowerCase();
            Double amount = amountInput.isEmpty() ? null : Double.parseDouble(amountInput);

            System.out.println("=*=*=*=*=*=*=*=*=*=*= Custom Search Results =*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("-----------------------------------------------------------");
            for (Transaction t : transactions) {
                if ((startDate == null || !t.getDate().isBefore(startDate)) &&
                        (endDate == null || !t.getDate().isAfter(endDate)) &&
                        (description == null || t.getDescription().toLowerCase().contains(description)) &&
                        (vendor == null || t.getVendor().toLowerCase().contains(vendor)) &&
                        (amount == null || t.getAmount() == amount)) {
                    System.out.println(t);
                }
            }
        }
    }


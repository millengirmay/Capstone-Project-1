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
                    System.out.println("Enter description: ");
                    String descriptionD = scanner.nextLine();
                    System.out.println("Enter vendor: ");
                    String vendorD = scanner.nextLine();
                    System.out.println("Enter amount: ");
                    double amountD = Double.parseDouble(scanner.nextLine());
                    Transaction deposit = new Transaction(LocalDate.now(), LocalTime.now(), descriptionD, vendorD, amountD);
                    ledgerService.addDeposit(deposit);
                    System.out.println("Deposit added!");
                    break;

                case "P":
                    System.out.println("Enter description: ");
                    String descriptionP = scanner.nextLine();
                    System.out.println("Enter vendor: ");
                    String vendorP = scanner.nextLine();
                    System.out.println("Enter amount: ");
                    double amountP = Double.parseDouble(scanner.nextLine());
                    Transaction payment = new Transaction(LocalDate.now(), LocalTime.now(), descriptionP, vendorP, -amountP);
                    ledgerService.addPayment(payment);
                    System.out.println("Payment added!");
                    break;

                case "L":
                    ledgerMenu(scanner, ledgerService, reportService);
                    break;

                case "X":
                    options = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void ledgerMenu(Scanner scanner, LedgerService ledgerService, ReportService reportService) {
        boolean inLedger = true;
        while (inLedger) {
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
                    System.out.println("\n=*=*=*=*=*=*=*=*=*=*= All Entries =*=*=*=*=*=*=*=*=*=*=*=");
                    ledgerService.displayAllTransactions();
                    break;
                case "D":
                    System.out.println("\n=*=*=*=*=*=*=*=*=*=*= Deposits =*=*=*=*=*=*=*=*=*=*=*=");
                    ledgerService.displayDeposits();
                    break;
                case "P":
                    System.out.println("\n=*=*=*=*=*=*=*=*=*=*= Payments =*=*=*=*=*=*=*=*=*=*=*=");
                    ledgerService.displayPayments();
                    break;
                case "R":
                    reportMenu(scanner, ledgerService, reportService);
                    break;
                case "H":
                    inLedger = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void reportMenu(Scanner scanner, LedgerService ledgerService, ReportService reportService) {
        boolean inReports = true;
        while (inReports) {
            System.out.println("\n=*=*=*=*=*=*=*=*=*=*= Reports Menu =*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("-----------------------------------------------------------");
            System.out.println("1) Month to Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year to Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Custom Search");
            System.out.println("0) Back - return to Ledger Menu");
            System.out.println("H) Home - return to  Report Menu");
            System.out.println("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    reportService.monthToDate(ledgerService.getTransactions());
                    break;
                case "2":
                    reportService.previousMonth(ledgerService.getTransactions());
                    break;
                case "3":
                    reportService.yearToDate(ledgerService.getTransactions());
                    break;
                case "4":
                    reportService.previousYear(ledgerService.getTransactions());
                    break;
                case "5":
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
                    customSearch(scanner, ledgerService.getTransactions());
                    break;
                case "0":
                    inReports = false; //This will exit reportMenu() and return to the Ledger Menu
                    break;
                case "H":
                    inReports = false;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
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

            LocalDate startDate = startDateInput.isEmpty() ? null : LocalDate.parse(startDateInput);
            LocalDate endDate = endDateInput.isEmpty() ? null : LocalDate.parse(endDateInput);
            String description = descriptionInput.isEmpty() ? null : descriptionInput.toLowerCase();
            String vendor = vendorInput.isEmpty() ? null : vendorInput.toLowerCase();
            Double amount = amountInput.isEmpty() ? null : Double.parseDouble(amountInput);

            System.out.println("\n=*=*=*=*=*=*=*=*=*=*= Custom Search Results =*=*=*=*=*=*=*=*=*=*=*=");

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


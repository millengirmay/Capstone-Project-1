package AccountingLedgerApplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class LedgerApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionFileHandler fileHandler = new TransactionFileHandler();
        LedgerService ledgerService = new LedgerService(fileHandler);
        ReportService reportService = new ReportService();

        boolean options = true;
        while (options) {
            System.out.println("\n==== Home Screen ====");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "D":
                    System.out.print("Enter description: ");
                    String descriptionD = scanner.nextLine();
                    System.out.print("Enter vendor: ");
                    String vendorD = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amountD = Double.parseDouble(scanner.nextLine());
                    Transaction deposit = new Transaction(LocalDate.now(), LocalTime.now(), descriptionD, vendorD, amountD);
                    ledgerService.addDeposit(deposit);
                    System.out.println("Deposit added!");
                    break;

                case "P":
                    System.out.print("Enter description: ");
                    String descriptionP = scanner.nextLine();
                    System.out.print("Enter vendor: ");
                    String vendorP = scanner.nextLine();
                    System.out.print("Enter amount: ");
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
            System.out.println("\n==== Ledger Menu ====");
            System.out.println("A) All Entries");
            System.out.println("D) Deposits Only");
            System.out.println("P) Payments Only");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "A":
                    ledgerService.displayAllTransactions();
                    break;
                case "D":
                    ledgerService.displayDeposits();
                    break;
                case "P":
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
            System.out.println("\n==== Reports Menu ====");
            System.out.println("1) Month to Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year to Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back - return to Ledger Menu");
            System.out.println("H) Home - return to  Report Menu");
            System.out.print("Choose an option: ");
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
                    System.out.print("Enter vendor name to search: ");
                    String vendor = scanner.nextLine();
                    reportService.searchByVendor(ledgerService.getTransactions(), vendor);
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
}

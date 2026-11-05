package AccountingLedger;
import java.io.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;

public class Main {
    //Scanner gets input from user
     static Scanner scanner = new Scanner(System.in);
    //Name of file where transactions will be saved
    static String fileName = "transactions.csv";

    //Main method
    public static void main(String[] args) {
        //Create a transactions file where transactions file
        createFile();
        //Store the user's menu choice
        String choice = "";
        //Show the menu
        while(!choice.equalsIgnoreCase("X")) {
            //Display home screen
            System.out.println("\n===ACCOUNTING LEDGER ====");
            System.out.println("D) Add deposit");
            System.out.println("P) Make payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Enter your choice: ");
            //Read the user's choice
            choice = scanner.nextLine();
            //Using if-else statements to handle menu choices
            if(choice.equalsIgnoreCase("D")) {
                //True means we are making a deposit
                addTransaction(true);
            }else if(choice.equalsIgnoreCase("P")) {
                //False means we are making a payment
                addTransaction(false);
            }else if(choice.equalsIgnoreCase("L")) {
                //open ledger screen
                ledger();
            }else if(choice.equalsIgnoreCase("X")) {
                //Exit the application
                System.out.println("Goodbye!");
            }else{
                //Show error for an incorrect choice
                System.out.println("Invalid choice!");
            }
        }
        //Close scanner
        scanner.close();

    }
    //Method 1:Create File
    //Creates transactions.csv
    public static void createFile() {
        try {
            //Create a file object
            File file = new File(fileName);
            //Create a new file if one does not exit
            if (file.createNewFile()){
                System.out.println("Transactions File created");
            }
        }catch(IOException e){
            //Display an error if the file cannot be created
            System.out.println("Error creating file");
        }
    }
    //Method 2: Add Transaction
    //Used for both deposits and payments
    public static void addTransaction(boolean deposit) {
        //Ask user for a description
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        //Ask for vendor name
        System.out.print("Enter vendor name: ");
        String vendor = scanner.nextLine();
        //Ask for transaction amount
        System.out.print("Enter amount:");
        double amount;
        //Check if  the user entered a valid number
        try{
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Invalid amount");
            return;
        }
        //Amount must be greater than zero
        if (amount <= 0){
            System.out.println("Amount must be greater than 0");
            return;
        }
        //Prevent the user from entering the file separator
        if (description.contains("|") || vendor.contains("|")){
            System.out.println("Do not use | in your entries");
            return;
        }
        //Payments are saved as negative amounts
        if(!deposit){
            amount = -amount;
        }
        //Get today's date automatically
        String date = LocalDate.now().toString();
        //Get current time automatically
        String time = LocalTime.now().withNano(0).toString();
        //Combine the information into one transaction
        //Each value separated by |
        String transaction = date +"|"+time+"|"+description+"|"+vendor
                + String.format(java.util.Locale.US, "%.2f", amount);
        //Save the transaction to the CSV file
        try{
            //True means add to the file without deleting old data
            FileWriter writer = new FileWriter(fileName, true);
            //Write the transaction on a new line
            writer.write(transaction+"\n");
            //close the file after saving
            writer.close();
            System.out.println("Transaction saved successfully");
        } catch (IOException e){
            System.out.println("Error saving transaction");
        }
    }
    //Method 3: Read Transactions
    //Reading transactions from the CSV file into an array
    public static String[]readTransactions() {
        //Create an array to store transactions
        String[] transactions = new String[10000];
        //Count how many transactions have been read
        int count = 0;
        try {
            //Open the file for reading
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            //Read the first line
            String line = reader.readLine();
            //Continue reading until there are no more line
            while (line != null && count<transactions.length) {
                //Ignore empty lines
                if(!line.isBlank()){
                    //store transaction in the array
                    transactions[count] = line;
                    //move to the next array position
                    count++;
                }
                //Read the next line
                line = reader.readLine();
            }
            //Close the file
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
        //Sort transactions so the newest appear first
        for (int i = 0; i < count; i++) {
            for (int j = i; j < count; j++) {
                //Split each transaction into separate values
                String[] first = transactions[i].split("\\|");
                String[] second = transactions[j].split("\\|");
                //Combine date and time for comparison
                String firstDate = first[0]+ first[1];
                String secondDate = second[0]+ second[1];
                //Compare the dates and times
                if(firstDate.compareTo(secondDate)<0){
                    //Swap the transactions
                    String temp = transactions[i];
                    transactions[i] = transactions[j];
                    transactions[j] = temp;
                }

            }
        }
        //Return the array of transactions
        return transactions;
    }
    //Method 4: Ledger Menu
    //Allow users to view transactions and reports
    public static void ledger() {
        String choice = "";
        //Keep showing the ledger until H is selected
        while(!choice.equalsIgnoreCase("H")) {
            System.out.println("\n===LEDGER ====");
            System.out.println("A) All Transactions");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Enter your choice: ");

            choice = scanner.nextLine();
            if(choice.equalsIgnoreCase("A")) {
                //Display all transactions
                displayTransactions("A");
            }else if(choice.equalsIgnoreCase("D")) {
                displayTransactions("D");
            }else if(choice.equalsIgnoreCase("P")) {
                displayTransactions("P");
            }else if(choice.equalsIgnoreCase("R")) {
                //Open reports menu
                reports();
            }else if(!choice.equalsIgnoreCase("H")) {
                System.out.println("Invalid choice");
            }
        }
    }
    //Method 5:Display Transactions
    //Shows all transactions, deposits, or payments
    public static void displayTransactions(String type) {
        //Get the transactions from the file
        String[]transactions = readTransactions();
        //Check whether any matching transactions are found
        boolean found  = false;
        System.out.println("===Transactions====");
        //Go through each transaction in the array
        for(String transaction : transactions) {
            //Stop when there are no more transactions
            if(transaction == null){
                break;
            }
            //Split the transaction into five parts
            String[]parts=transaction.split("\\|");
            //Skip incorrectly formated transactions
            if(parts.length !=5){
                continue;
            }
            double amount;
            //Convert the amount from String to double
            try{
                amount = Double.parseDouble(parts[4]);
            } catch (NumberFormatException e){
                continue;
            }
            //A shows all transactions
            //D shows positive amounts(deposits)
            //P shows negative amounts(payments)
            if(type.equals("A")||(type.equals("D")&& amount > 0 ||(type.equals("P")&& amount < 0) )){
                //print the matching transactions
                System.out.println(transaction);
                //At least one transaction was found
                found = true;
            }
        }
        //Display a message if there are no matching records
        if(!found){
            System.out.println("No transactions found.");
        }
    }
    //Method 6: REPORTS MENU
    //Allow users to select different reports
    public static void reports() {}
}
**Accounting Ledger-Java**

**Project Overview**

A Java command-line application designed to track financial transactions 
for personal or business use.

The application allows users to record deposits, make payments, view transaction history,
and generate financial reports.

**Technologies Used**

* Java

* IntelliJ IDEA

* Git and GitHub

* CSV file handling

**Project Development Timeline**

**Day 1 Goal: Project Setup and Core Functionality**

* Set up the Main.java class.

* Create the home screen and menu navigation

* Implement the Add Deposit feature.

* Implement the Make Payment Feature.

* Create the transactions.csv file.

* Testing saving transactions

**Day 2 Goal: Ledger, Reports, and Testing**
* Implement the Ledger menu.
* Display all transactions,newest first.
* Filter deposits and payments.
* Implement Month-to-Date and Previous Month Reports
* Implement Year-to-Date and Previous Year reports
* Implement Search by Vendor
* Test application functionality.
* Fix errors and improve code comments.
* Push the project to GitHub.

**Day 3 Goal: Final Review and Submission**
* Run final tests.
* Verify the GitHub repository contains all required files.
* Review project documentation.
* Submit the GitHub repository link.

**Java Concepts Demonstrated.**
* Strings and arrays.
* Methods.
* If-else statements.
* While and for loops
* Scanner for user input
* File reading and writing.
* Date and time Handling.
* Basic error handling.

## How to Run the Application

### Prerequisites
Before running the application, ensure you have the following installed:

- Java Development Kit (JDK)
- IntelliJ IDEA
- Git (optional, for cloning the repository)

### Steps to Run

**1. Clone the Repository**

Open your terminal and run:

```bash
git clone https://github.com/YOUR-USERNAME/Accounting-Ledger-Application.git
```

Replace `YOUR-USERNAME` with your GitHub username.

**2. Open the Project in IntelliJ IDEA**

- Launch IntelliJ IDEA.
- Select **File → Open**.
- Navigate to the cloned `Accounting-Ledger-Application` folder.
- Select the folder and open the project.
- Ensure the project JDK is configured correctly.

**3. Run the Application**

- Navigate to `src/main/java/AccountingLedger/Main.java`.
- Open `Main.java`.
- Click the green Run button next to the `main()` method.
- The application will start in the IntelliJ console.

**4. Use the Application**

The Home Screen displays the following options:

```text
=== ACCOUNTING LEDGER ===
D) Add Deposit
P) Make Payment
L) Ledger
X) Exit
```

- Enter `D` to record a deposit.
- Enter `P` to record a payment.
- Enter `L` to view transactions and reports.
- Enter `X` to exit the application.

**5. View Saved Transactions**

All transactions are saved in a file named `transactions.csv` in the application's working directory.

The file is automatically created if it does not exist.

Each transaction follows this format:

```text
date|time|description|vendor|amount
```

Deposits are recorded as positive amounts, while payments are recorded as negative amounts.

**6. View Reports**

From the Home Screen, select `L` for Ledger, then `R` for Reports.

Available reports include:

- Month to Date
- Previous Month
- Year to Date
- Previous Year
- Search by Vendor

The application continues running until the user selects `X` from the Home Screen.


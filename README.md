# Millionaires-Banking-Application-JAVA-w-MySQL
Java-based banking app that enables user account creation, login, and transactions (deposit/withdraw). Integrates with MySQL to store and manage account data securely. Uses prepared statements for safe balance updates and ensures accurate user authentication and data integrity.

Banking Application
Overview

This is a Java-based banking application that simulates core banking operations such as account creation, user authentication, and financial transactions. The system is integrated with a MySQL database to securely store and manage user data.

Features

Create a new user account

Secure login with username and account number

View account details (username, account number, balance)

Deposit and withdraw funds

Update account balance in real-time using MySQL

Input validation and duplicate username checks

Technologies Used

Java (Core application logic)

MySQL (Database management)

JDBC (Database connectivity)

Database Structure

Example table structure:

CREATE TABLE accounts (
  accountNumber INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(45) UNIQUE NOT NULL,
  balance DOUBLE
);
How It Works

User selects to create an account or log in

Credentials are verified against the database

Upon successful login, account details are displayed

User can deposit or withdraw funds

Balance is updated in the database using prepared statements

How to Run

Clone the repository:

git clone https://github.com/your-username/banking-app.git

Set up MySQL:

Create a database (e.g., bank_db)

Run the table creation script

Update database credentials in your Java code:

Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/bank_db", "username", "password"
);

Compile and run:

javac Main.java
java Main
Security Notes

Uses PreparedStatement to prevent SQL injection

Validates user inputs before database operations

Ensures correct user matching using username + account number

Future Improvements

Graphical User Interface (GUI)

Transaction history tracking

Password authentication

Enhanced error handling and security

Author

Raul Henriquez

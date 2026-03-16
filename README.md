# Core Java Banking System

A simple **Banking Management System** built using **Core Java, Java HTTP Server, JDBC, MySQL, HTML, CSS, and JavaScript**.

This project demonstrates how a banking application can be implemented using Java's built-in **HttpServer API** for handling web requests.

The system allows users to create accounts, deposit money, withdraw money, check balances, and view transaction history through a web-based dashboard.

---

## Features

- Create Bank Account
- Automatic 11-digit Account Number Generation
- Aadhaar Validation (12 digits)
- Phone Number Validation (10 digits)
- Duplicate Aadhaar Prevention
- Deposit Money
- Withdraw Money
- Minimum Balance Rule (₹1000)
- Insufficient Balance Handling
- Transaction History Tracking
- Real-time result display without page reload

---

## Technologies Used

- **Java**
- **Java HTTP Server (com.sun.net.httpserver)**
- **JDBC**
- **MySQL**
- **HTML**
- **CSS**
- **JavaScript (Fetch API)**
---

## Project Structure
Java Mini Project
│
├── backend
│ ├── BankHttpServer.java
│ ├── BankHandler.java
│ ├── FrontendHandler.java
│ ├── BankService.java
│ ├── BankAccount.java
│ └── DBConnection.java
│ └── mysql-connector-j-9.6.0.jar
├── frontend
  ├── index.html
  └── login.html


---

## System Architecture

Browser (HTML + CSS + JS)
    │
    ▼
Java HTTP Server (HttpServer)
    │
    ▼
BankHandler
    │
    ▼
BankService (Business Logic)
    │
    ▼
MySQL Database

---

## Database Setup

Create database:

```sql
CREATE DATABASE bankdb;
USE bankdb;

CREATE TABLE accounts(
account_id BIGINT PRIMARY KEY,
name VARCHAR(100),
aadhaar VARCHAR(12),
phone VARCHAR(10),
address VARCHAR(255),
balance DOUBLE
);
CREATE TABLE transactions(
id INT AUTO_INCREMENT PRIMARY KEY,
account_id BIGINT,
description VARCHAR(255),
before_balance DOUBLE,
after_balance DOUBLE,
time VARCHAR(50)
);

---

## How to Run the Project

###1. Navigate  to backend folder
cd backend
###2. Compile Java Files
javac -cp ".;mysql-connector-j-9.6.0.jar" *.java
###3. Run the Server
java -cp ".;mysql-connector-j-9.6.0.jar" BankHttpServer
###4. Open Browser
http://localhost:9000

## Functional Modules

### 1. Account Creation
Creates a new bank account with automatic 11-digit account number generation.(images/Create Account and Dashboard)

### 2. Deposit
Allows depositing money into an existing account. (images/Deposit)

### 3. Withdraw
Allows withdrawing money while maintaining a minimum balance of ₹1000. (images/Withdraw)

### 4. Balance Check
Displays the current balance of the account. (images/Balance Check)

### 5. Transaction History
Displays all deposit and withdrawal transactions with timestamps. (images/Transactions history)

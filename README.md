# Core Java Banking System

A simple **Banking Management System** built using **Core Java, Java
HTTP Server, JDBC, MySQL, HTML, CSS, and JavaScript**.

This project demonstrates how a banking application can be implemented
using Java's built-in **HttpServer API** for handling web requests.

The system allows users to create accounts, deposit money, withdraw
money, check balances, and view transaction history through a web-based
dashboard.

\---

## Features

* Create Bank Account
* Automatic 11-digit Account Number Generation
* Aadhaar Validation (12 digits)
* Phone Number Validation (10 digits)
* Duplicate Aadhaar Prevention
* Deposit Money
* Withdraw Money
* Minimum Balance Rule (₹1000)
* Insufficient Balance Handling
* Transaction History Tracking
* Real-time result display without page reload

\---

## Technologies Used

* **Java**
* **Java HTTP Server (com.sun.net.httpserver)**
* **JDBC**
* **MySQL**
* **HTML**
* **CSS**
* **JavaScript (Fetch API)**

\---

## Project Structure

&#x20;   Java Mini Project
    │
    ├── backend
    │   ├── BankHttpServer.java
    │   ├── BankHandler.java
    │   ├── FrontendHandler.java
    │   ├── BankService.java
    │   ├── BankAccount.java
    │   └── DBConnection.java
    │
    ├── frontend
    │   ├── index.html
    │   └── login.html
    │
    ├── images
    │   ├── create-account.png
    │   ├── deposit.png
    │   ├── withdraw.png
    │   ├── balance-check.png
    │   └── transactions.png
    │
    ├── mysql-connector-j-9.6.0.jar
    └── README.md


\---

## System Architecture

&#x20;   Browser (HTML + CSS + JS)
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


\---

## Database Setup

Create the database and tables in MySQL.

``` sql
CREATE DATABASE bankdb;
USE bankdb;

CREATE TABLE accounts(
account\_id BIGINT PRIMARY KEY,
name VARCHAR(100),
aadhaar VARCHAR(12),
phone VARCHAR(10),
address VARCHAR(255),
balance DOUBLE
);

CREATE TABLE transactions(
id INT AUTO\_INCREMENT PRIMARY KEY,
account\_id BIGINT,
description VARCHAR(255),
before\_balance DOUBLE,
after\_balance DOUBLE,
time VARCHAR(50)
);
```

\---

## How to Run the Project

### 1\. Navigate to Backend Folder

``` bash
cd backend
```

### 2\. Compile Java Files

``` bash
javac -cp ".;mysql-connector-j-9.6.0.jar" \*.java
```

### 3\. Run the Server

``` bash
java -cp ".;mysql-connector-j-9.6.0.jar" BankHttpServer
```

### 4\. Open Browser

&#x20;   http://localhost:9000


\---

## Functional Modules

### 1\. Account Creation

Creates a new bank account with automatic **11-digit account number generation**.(images/Create Account and Dashboard)

\---

### 2\. Deposit

Allows depositing money into an existing account.(images/Deposit)

\---

### 3\. Withdraw

Allows withdrawing money while maintaining a **minimum balance of ₹1000**.(images/Withdraw)

\---

### 4\. Check Balance

Displays the current balance of the account.(image/Balance Check)

\---

### 5\. Transaction History

Displays all deposit and withdrawal transactions with timestamps.(images/Transactions history)



\---

## 


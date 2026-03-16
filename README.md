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

1. Account Creation
Creates a new bank account with automatic 11-digit account number
generation.
<img width="1166" height="914" alt="Screenshot 2026-03-16 211438" src="https://github.com/user-attachments/assets/05f46e69-1efc-4a58-9ab6-f36b14c42f2f" />

---
2. Deposit
Allows depositing money into an existing account.
<img width="850" height="620" alt="Screenshot 2026-03-16 211637" src="https://github.com/user-attachments/assets/9fd1761f-a767-493c-8f1a-a2aac9d4c316" />

---
3. Withdraw
Allows withdrawing money while maintaining a minimum balance of
₹1000.
<img width="834" height="590" alt="Screenshot 2026-03-16 211752" src="https://github.com/user-attachments/assets/ddf53d2e-5347-47b8-88c9-6d5a7ff0ac1e" />

---
4. Balance Check
Displays the current balance of the account.
<img width="808" height="500" alt="Screenshot 2026-03-16 211803" src="https://github.com/user-attachments/assets/51f30aac-cb9c-47d6-836a-f4eb818aa196" />

---
5. Transaction History
Displays all deposit and withdrawal transactions with timestamps.
<img width="860" height="670" alt="Screenshot 2026-03-16 211830" src="https://github.com/user-attachments/assets/0894449a-149b-4d34-9e99-d302ad6c8fba" />



\---

## 


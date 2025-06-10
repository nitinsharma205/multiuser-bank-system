# 🏦 Banking Management System

A Java-based multi-user Banking Management System with both **Console** and **Swing GUI** interfaces. Built using Java, this project supports account creation, transaction processing, interest calculations, and file-based data persistence.

---

## 📌 Key Features

- ✅ Create **Savings** and **Current** accounts
- 💰 Deposit and Withdraw funds
- 🔍 View account details
- 📈 Interest calculation for Savings Accounts (4% annually)
- 🧾 Overdraft support for Current Accounts (up to ₹10,000)
- 💾 File-based persistence using `accounts.txt`
- 💻 Switch between **Console UI** and **GUI (Swing)** at runtime

---

## 🛠️ Tech Stack

- **Java 11+**
- **Swing** (for GUI)
- **Maven** (project management and build)
- **Java I/O** (file handling)
- **OOP** principles with abstract classes and inheritance

## 🚀 How to Run

### 1️⃣ Prerequisites

- Java JDK 11 or higher
- Maven 3.6 or higher

### 2️⃣ Clone and Build

```bash
git clone https://github.com/<your-username>/bank-management-system.git
cd bank-management-system
mvn clean install
````

### 3️⃣ Run the Application

#### GUI Mode:

```bash
mvn exec:java -Dexec.mainClass="com.bankapp.BankingApplication" -Dexec.args="gui"
```

#### Console Mode:

```bash
mvn exec:java -Dexec.mainClass="com.bankapp.BankingApplication" -Dexec.args="console"
```

---

## 🧠 Design Overview

* **Model Layer**: Abstract base class `Account` with two implementations: `SavingsAccount` and `CurrentAccount`.
* **DAO Layer**: Interface-based design (`AccountDAO`), with `FileAccountDAO` implementation for file storage.
* **Service Layer**: Handles operations like creation, deposit, withdrawal, and interest calculation.
* **UI Layer**: `SwingUI` for GUI and `ConsoleUI` for terminal-based interaction.

---

## 🧑‍💻 Contribution Guidelines

1. Fork this repository.
2. Create your branch: `git checkout -b feature/feature-name`
3. Commit your changes: `git commit -m "Add feature"`
4. Push to your branch: `git push origin feature/feature-name`
5. Open a Pull Request for review

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---

## ✨ Acknowledgments

* Inspired by core Java OOP and layered architecture practices
* Built for academic evaluation (Review 1 & 2 aligned with institutional rubrics)

```

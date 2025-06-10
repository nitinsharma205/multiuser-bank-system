-- database.sql
-- SQL script to create schema for Banking Management System

-- Drop existing tables if needed (optional)
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;

-- Create accounts table
CREATE TABLE accounts (
    account_number VARCHAR(20) PRIMARY KEY,
    holder_name VARCHAR(100) NOT NULL,
    account_type ENUM('SAVINGS', 'CURRENT') NOT NULL,
    balance DECIMAL(12, 2) NOT NULL DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create transactions table (optional enhancement)
CREATE TABLE transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    account_number VARCHAR(20),
    type ENUM('DEPOSIT', 'WITHDRAW'),
    amount DECIMAL(12, 2) NOT NULL,
    transaction_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_number) REFERENCES accounts(account_number) ON DELETE CASCADE
);

-- Optional: Insert some initial sample accounts
INSERT INTO accounts (account_number, holder_name, account_type, balance)
VALUES 
('10001', 'Alice Sharma', 'SAVINGS', 5000.00),
('10002', 'Rohit Mehta', 'CURRENT', 10000.00);

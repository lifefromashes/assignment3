package com.meritamerica.assignment3;

import java.text.ParseException;

public class SavingsAccount extends BankAccount {
		
		//Constructor to initialize the objects
		public SavingsAccount(double openingBalance) {
			super(openingBalance, 0.01);
		}
		
		public SavingsAccount(BankAccount bankAccount) {
			super(bankAccount.getAccountNumber(), bankAccount.getBalance(), bankAccount.getInterestRate(), bankAccount.getOpenedOn());
		}
		
		public static SavingsAccount readFromString(String accountData)  throws ParseException {
			return new SavingsAccount(BankAccount.readFromString(accountData));
		}

	
			
		
//		//begin getters and setters
//		
//		public long getAccountNumber() {
//			return this.accountNumber;
//		}
//		
//		public double getBalance() { 
//			return this.balance;
//		}
//		
//		public double getInterestRate() {
//			return this.interestRate; //savingsInterestRate;
//		}
//		
//		public void setInterestRate(double interestRate, int years) {
//			this.interestRate = interestRate;
//			
//		}
//		
//		//methods for withdrawing and depositing money into savings account
//		public boolean withdraw(double amount) {
//			if (this.balance <= 0 || amount > this.balance) {
//				System.out.println("Unable to make withdrawal. Not enough funds.");
//				return false;
//			}
//			else {
//				this.balance = this.balance - amount;
//				System.out.println("Withdrawing: " + amount + " from savings.  Your balance is now: " + this.balance);
//			}
//			return true;
//		}
//		
//		public boolean deposit(double amount) {
//			if (amount < 0) {
//				System.out.println("You can't deposit an amount of 0 or less.");
//				return false;
//			}
//			else {
//				this.balance = this.balance + amount;
//				System.out.println("Depositing: " + amount + " to savings. Your balance is now: " + this.balance);
//			}
//			return true;
//		}
//		
//		
//		//formula to get the future value of savings with interest accrued
//		public double futureValue(int years) {
//			savingsValue = this.balance * Math.pow((this.interestRate + 1), years);
//			return savingsValue;
//
//		}
//		
//		//method to print savings account information
//		@Override
//		public String toString() {
//			DecimalFormat df = new DecimalFormat("0.00");
//			DecimalFormat iformat = new DecimalFormat(".0000");
//			return (
//					"Savings Account Balance: " + df.format(this.balance) + 
//					"\nSavings Account Interest Rate: " + iformat.format(this.interestRate) +  
//					"\nSavings Account Balance in 3 years: " + df.format(futureValue(3))
//					);
//		}
	}




package com.meritamerica.assignment3;

import java.text.ParseException;

public class CheckingAccount extends BankAccount {
	

	 //Constructor to initialize the objects
	public CheckingAccount(double openingBalance) {
		super(openingBalance, .0001);
	}
	
	public CheckingAccount(BankAccount bankAccount) {
		super(bankAccount.getAccountNumber(), bankAccount.getBalance(), bankAccount.getInterestRate(), bankAccount.getDateOpened());
	}

	public static CheckingAccount readFromString(String accountData) throws ParseException {
		return new CheckingAccount(BankAccount.readFromString(accountData));  //to return a checkingAccount object
	}

//	// Method and conditions for withdrawing money
//	public boolean withdraw(double amount) {
//		if (this.balance <= 0) {
//			System.out.println("Unable to make withdrawal. Not enough funds.");
//			return false;
//		}
//		if (amount > this.balance) {
//			System.out.println("Unable to make withdrawal. Not enough funds.");
//			return false;
//
//		}
//		if (amount < 0) {
//			System.out.println("Unable to make withdrawal.");
//			return false;
//		} else {
//			this.balance = this.balance - amount;
//			System.out.println("Withdrawing: " + amount + " from checking. Your balance is now: " + balance);
//
//		}
//		return true;
//
//	}
//
//	// methods and conditions for depositing money
//	public boolean deposit(double amount) {
//		if (amount <= 0) {
//			System.out.println("You have to deposit a positive amount.");
//			return false;
//		} else {
//			this.balance = this.balance + amount;
//			System.out.println("Depositing: " + amount + " to checking. Your balance is now: " + this.balance);
//		}
//		return true;
//	}

//	// method to calculate future value of current balance in the checking account
//	public double futureValue(int years) {
//		// Formula: FV = PV(1 + interestRate) ^ years;
//		double checkingBalanceThreeYears = this.balance * Math.pow((this.interestRate + 1), years);
//		return checkingBalanceThreeYears;
//
//	}

	// method to return the string representation of our object
//	@Override
//	public String toString() {
//		DecimalFormat df = new DecimalFormat("0.00"); // format the currency
//		DecimalFormat iformat = new DecimalFormat(".0000"); // format the interest rate
//		return ("Checking Account Balance: " + df.format(this.balance) + "\nChecking Account Interest Rate: "
//				+ iformat.format(this.interestRate) + "\nChecking Account Balance in 3 Years: "
//				+ df.format(futureValue(3)));
//	}

	// getters and setters
//	public long getAccountNumber() {
//		return this.accountNumber;
//	}
//
//	public double getBalance() {
//		return this.balance;
//	}
//
//	public double getInterestRate() {
//		return this.interestRate;
//	}
}

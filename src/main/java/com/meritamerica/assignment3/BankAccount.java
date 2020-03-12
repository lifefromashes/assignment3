package com.meritamerica.assignment3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BankAccount {
	private double balance;
	private double interestRate;
	private Date dateOpened; 
	private long accountNumber;

	public BankAccount(double balance, double interestRate) {
		this.balance = balance;
		this.interestRate = interestRate;
	}

	public BankAccount(double balance, double interestRate, java.util.Date accountOpenedOn) {
		this(0, balance, interestRate, accountOpenedOn);
//		this.balance = balance;
//		this.interestRate = interestRate;
//		this.dateOpened = accountOpenedOn;

	}

	public BankAccount(long accountNumber, double balance, double interestRate, java.util.Date accountOpenedOn) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
		this.dateOpened = accountOpenedOn;
	}

	public boolean withdraw(double amount) {
		if (this.balance <= 0) {
			System.out.println("Unable to make withdrawal. Not enough funds.");
			return false;
		}
		if (amount > this.balance) {
			System.out.println("Unable to make withdrawal. Not enough funds.");
			return false;

		}
		if (amount < 0) {
			System.out.println("Unable to make withdrawal.");
			return false;
		} else {
			this.balance = this.balance - amount;
			System.out.println("Withdrawing: " + amount + " from checking. Your balance is now: " + balance);

		}
		return true;

	}

	public boolean deposit(double amount) {
		if (amount <= 0) {
			System.out.println("You have to deposit a positive amount.");
			return false;
		} else {
			this.balance = this.balance + amount;
			System.out.println("Depositing: " + amount + " to checking. Your balance is now: " + this.balance);
		}
		return true;

	}

	public static BankAccount readFromString(String accountData) throws ParseException, NumberFormatException { 
		
		String accountNumber = " ";
		String balance = "";
		String interestRate = "";
		String date = "";
		int stringPosistion = 1;
		
		for (char c : accountData.toCharArray()) {
			if (c == ',') {
				stringPosistion++;
				continue;
			}
			if (stringPosistion == 1) {
				accountNumber += c;
			}
			if(stringPosistion == 2) {
				balance += c;
			}
			if(stringPosistion == 3) {
				interestRate += c;
			}
			if(stringPosistion == 4) {
				date += c;
			}
			if(accountNumber == "" || balance == ""|| interestRate == "" || date == "" || stringPosistion != 4) {
				throw new NumberFormatException();
			}
		}
		
		long newAccountNumber = Long.parseLong(accountNumber);
		double newBalance = Double.parseDouble(balance);
		double newInterestRate = Double.parseDouble(interestRate);
		Date newDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		
		BankAccount newBankAccount = new  BankAccount(newAccountNumber, newBalance, newInterestRate, newDate);
		return newBankAccount;
	
	}
	
	public String writeToString() { 
		String newStringFile = "";
		newStringFile += this.accountNumber + "," + this.balance + "," + this.interestRate + "," + this.dateOpened;
		return newStringFile;
	}
	
	public  double futureValue(int years) {
		double formula = Math.pow(1 + this.interestRate, years);
		double futureValue = balance * formula;
		return futureValue;
		
	}
	
	public double futureValue() {
		return futureValue(5);
	}
	

//	public static double futureValue(double presentValue, double interestRate, int term) {
//		double futureValue = presentValue * (Math.pow(1 + presentValue, term));
//		return futureValue;
//	}
	
	


// begin getters and setters

	public long getAccountNumber() {
		return this.accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public java.util.Date getOpenedOn() { 
		return dateOpened;
	}

}

package com.meritamerica.assignment3;

import java.text.ParseException;
import java.util.Date;

public class CDAccount extends BankAccount {

	private int term;
	
	public CDAccount(BankAccount bankAccount) {
		super(bankAccount.getAccountNumber(), bankAccount.getBalance(), bankAccount.getInterestRate(), bankAccount.getOpenedOn());
	}
	
	public CDAccount(double balance, double interestRate, int term) {
		super(balance, interestRate, new Date());
		this.term = term;
	}

	public static CDAccount readFromString(String accountData) throws ParseException {
		return new CDAccount(BankAccount.readFromString(accountData));  //to return a CDAccount object
	}
	
	@Override
	public String writeToString() {
		String accountInfo = super.writeToString();
		accountInfo += ", " + this.term;
		return accountInfo;
	}
	
	@Override
	public boolean withdraw(double amount) {
		return false;
	}
	
	@Override
	public boolean deposit(double amount) {
		return false;
	}

	public int getTerm() {
		return this.term;
	}
	
}


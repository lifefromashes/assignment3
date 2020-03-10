package com.meritamerica.assignment3;

import java.text.ParseException;

public class AccountHolder implements Comparable <AccountHolder> {
	private String firstName;
	private String middleName;
	private String lastName;
	private String ssn;
	private CheckingAccount[] checkingAccounts;
	private int numOfCheckingAccounts;
	private SavingsAccount[] savingsAccounts;
	private int numOfSavingsAccounts;
	private CDAccount[] cdAccounts;
	private int numOfCDAccounts;

	public AccountHolder() {
	}

	public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		// setting the attributes to the value from the constructor parameters
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;

		this.numOfCheckingAccounts = 0;
		this.checkingAccounts = new CheckingAccount[1];
		this.numOfSavingsAccounts = 0;
		this.savingsAccounts = new SavingsAccount[1];
		this.numOfCDAccounts = 0;
		this.cdAccounts = new CDAccount[1];

	}

	public CheckingAccount addCheckingAccount(double openingBalance) {
		CheckingAccount tempAccount = new CheckingAccount(openingBalance);
		return addCheckingAccount(tempAccount);
	}

	public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) {
		if(getCheckingBalance() + getSavingsBalance() + checkingAccount.getBalance() >= 250000) {	
			System.out.println("Unable to create new account, balance too high.");
			return null;
		}

		int currentArrayLimit = this.numOfCheckingAccounts;
		CheckingAccount[] temp = new CheckingAccount[currentArrayLimit + 1];

		for(int i=0; i<currentArrayLimit; i++) {
			temp[i] = this.checkingAccounts[i];
		}

		temp[currentArrayLimit] = checkingAccount;
		this.numOfCheckingAccounts ++;
		this.checkingAccounts = temp;

		return checkingAccount;
	}

	public SavingsAccount addSavingsAccount(double openingBalance) {
		SavingsAccount tempAccount = new SavingsAccount(openingBalance);
		return addSavingsAccount(tempAccount);
	}

	public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) {
		if(getCheckingBalance() + getSavingsBalance() + savingsAccount.getBalance()>= 250000) {
			System.out.println("Unable to create new account, balance too high.");
			return null;
		}

		int currentArrayLimit = this.numOfSavingsAccounts;
		SavingsAccount[] temp = new SavingsAccount[currentArrayLimit + 1];

		for(int i=0; i<currentArrayLimit; i++) {
			temp[i] = this.savingsAccounts[i];
		}

		temp[currentArrayLimit] = savingsAccount;
		this.numOfSavingsAccounts ++;
		this.savingsAccounts = temp;

		return savingsAccount;
	}
	
	
	public  CDAccount addCDAccount(CDOffering offering, double openingBalance) {
		if(offering == null) {
			System.out.println("Unable to find CD Offer.");
			return null;
		}
		
		CDAccount newCDAccount = new CDAccount(openingBalance, offering.getInterestRate(), offering.getTerm());
		return addCDAccount(newCDAccount);
	}

	public CDAccount addCDAccount(CDAccount cdAccount) {
		if (cdAccount == null) {
			System.out.println("Unable to find account.");
		}

		int currentArrayLimit = this.numOfCDAccounts;
		CDAccount[] temp = new CDAccount[currentArrayLimit + 1];

		for (int i = 0; i < currentArrayLimit; i++) {
			temp[i] = this.cdAccounts[i];
		}

		temp[currentArrayLimit] = cdAccount;
		this.numOfCDAccounts++;
		this.cdAccounts = temp;
		
		return cdAccount;
	}
	
	public static AccountHolder readFromString(String accountHolderData) throws ParseException {
		String lastName = " ";
		String middleName = "";
		String firstName = "";
		String ssn = "";
		int stringPosistion = 1;
		
		for (char c : accountHolderData.toCharArray()) {
			if (c == ',') {
				stringPosistion++;
				continue;
			}
			if (stringPosistion == 1) {
				lastName += c;
			}
			if(stringPosistion == 2) {
				middleName += c;
			}
			if(stringPosistion == 3) {
				firstName += c;
			}
			if(stringPosistion == 4) {
				ssn += c;
			}
			if(lastName == "" || middleName == ""|| firstName == "" || ssn == "" || stringPosistion != 4) {
				throw new ParseException(accountHolderData, stringPosistion);
			}
		}
			
			AccountHolder newAccountHolder = new AccountHolder(lastName, middleName, firstName, ssn);
			return newAccountHolder;
		}
	
	public String writeToString() {
		String accountInfo = "";
		accountInfo += lastName + " ," + middleName + "," + firstName + "," + ssn;
		return accountInfo;
	}
				
	@Override
	public int compareTo(AccountHolder otherAccountHolderBalance) {
		int accountsSum = (int) getCombinedBalance();
		int otherSum = (int) otherAccountHolderBalance.getCombinedBalance();
		return accountsSum - otherSum;
			
		}

//	@Override
//	public String toString() {
//		return ("Name: " + this.firstName + " " + this.middleName + " " + this.lastName + "\nSSN: " + this.ssn + "\n"
//				+ checkingAccounts[0].toString() + "\n" + savingsAccounts[0].toString());
//
//	}
	// create getters and setters for retrieving and updating the value of the
	// variables
	public String getFirstName() {
		return firstName;
	}

	public void setFirstname(String name) {
		this.firstName = name;

	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String midName) {
		this.middleName = midName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lstName) {
		this.lastName = lstName;
	}

	public String getSSN() {
		return ssn;
	}

	public void setSSN(String SSN) {
		this.ssn = SSN;

	}

	public CheckingAccount[] getCheckingAccounts() {
		return this.checkingAccounts;
	}

	public SavingsAccount[] getSavingsAccounts() {
		return this.savingsAccounts;
	}

	public CDAccount[] getCDAccounts() {
		return this.cdAccounts;
	}

	public int getNumberOfCheckingAccounts() {
		return this.numOfCheckingAccounts;
	}

	public int getNumberOfSavingsAccounts() {
		return this.numOfSavingsAccounts;

	}

	public int getNumberOfCDAccounts() {
		return this.numOfSavingsAccounts;

	}

	public double getCheckingBalance() { // iterate over array of checking accounts to find the total sum of the
											// accounts
		double chkAccSums = 0;

		for (int i = 0; i < this.numOfCheckingAccounts; i++) {
			chkAccSums += this.checkingAccounts[i].getBalance();
		}
		return chkAccSums;
	}

	public double getSavingsBalance() {
		double savAccSums = 0;

		for (int i = 0; i < this.numOfSavingsAccounts; i++) {
			savAccSums += this.savingsAccounts[i].getBalance();
		}

		return savAccSums;

	}

	public double getCDAccountsBalance() {
//		if (this.cdAccounts[0] == null) {
//			return 0;
//		}
		double cdAccSums = 0;

		for (int i = 0; i < this.cdAccounts.length; i++) {
			cdAccSums += this.cdAccounts[i].getBalance();
		}
		
		return cdAccSums;

	}

	public double getCDBalance() {
		return getCDAccountsBalance();
	}

	public double getCombinedBalance() {
		double accountSums = getCheckingBalance();
		accountSums += getCDAccountsBalance();
		accountSums += getSavingsBalance();
		return accountSums;
	}

	
}


	
	
	
	



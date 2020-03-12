package com.meritamerica.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class MeritBank {
		private static AccountHolder[] accountHolders = new AccountHolder[100];
		private static int accountHolderIndex = 0;
		private static CDOffering[] cdOfferings = new CDOffering[100];
		private static long nextAccountNumber = 1234567;
		

		
		public static void addAccountHolder(AccountHolder accountHolder) {
			accountHolders[accountHolderIndex] = accountHolder;
			
			int arraySize = 0;
			for(int i = 0; i < accountHolders.length; i++) {
				if(accountHolders[i] == null) {
					break;
				}
				arraySize++;
			}
			AccountHolder[] newAccountHolder = new AccountHolder[arraySize + 1];
			for(int i  = 0; i < arraySize; i++) {
				newAccountHolder[i] = accountHolders[i];
			}
			
			newAccountHolder[arraySize] = accountHolder;
			accountHolders = newAccountHolder;
			accountHolderIndex++;
			
		}
		
		public static AccountHolder[] getAccountHolders() { //returns the array of accountHolders
			return  accountHolders;	
		}
		
		public static CDOffering[] getCDOfferings() {
			return cdOfferings;
		}
		
		public static CDOffering getBestCDOffering(double depositAmount) {
			if(cdOfferings == null) {
				return null;
			}
			
			double bestValue = 0;
			int bestIndex = -1;
			
			for (int i = 0; i < cdOfferings.length; i++) {
				if(cdOfferings[i].getInterestRate() > bestValue) {
					bestValue = cdOfferings[i].getInterestRate();
					bestIndex = i;
				}
			}
			
			return cdOfferings[bestIndex];		
		}
		
		public static CDOffering getSecondBestCDOffering(double depositAmount) {
//			if(cdOfferings == null) {
//				return null;
//			}
			CDOffering best = getBestCDOffering(depositAmount);
			
			double secondBestValue = 0;
			int secondBestIndex = -1;		
			
			for (int i = 0; i < cdOfferings.length; i ++) {
				if(cdOfferings[i].getInterestRate() > secondBestValue && !best.equals(cdOfferings[i])) {
					secondBestValue = cdOfferings[i].getInterestRate();
					secondBestIndex = i;
				}
			}
			
			return cdOfferings[secondBestIndex];
			
		} 
		
		public static void clearCDOfferings() {
			cdOfferings = null;     //to clear 
			
		}
		
		public static void setCDOfferings(CDOffering[] offerings) {
			int cdOfferingArraySize = offerings.length;
			cdOfferings = new CDOffering[cdOfferingArraySize];
			for (int i = 0; i < cdOfferingArraySize; i++) {
				cdOfferings[i] = offerings[i];
			}
		}
		
		public static long getNextAccountNumber() {
			return nextAccountNumber;
		}
		
		public static void setNextAccountNumber(long nextAccountNumber) {
			MeritBank.nextAccountNumber = nextAccountNumber;
		}

		public static double totalBalances() {
			double sum = 0;
			
			for(AccountHolder acctHolder : accountHolders) {
				if(acctHolder == null) {
					break;
				}
				
				
				for(CheckingAccount chkAccountBalance : acctHolder.getCheckingAccounts()) {
					sum += chkAccountBalance.getBalance();
				}
				
				for(SavingsAccount savAccountBalance : acctHolder.getSavingsAccounts()) {
					sum += savAccountBalance.getBalance();
				}
				
				for(CDAccount account : acctHolder.getCDAccounts()) { //Try/catch here??
					try {
					sum += account.getBalance();
					} catch (NullPointerException e) {
						System.out.println("Handled null pointer.");
					}
				}
				
			}
		
			return sum;
	}
		
		public static double futureValue(double presentValue, double interestRate, int term) {
			double futureValue = presentValue * (Math.pow(1 + presentValue, term));
			return futureValue;
			
		}
		
		public static void clearMemory() {
			accountHolders = new AccountHolder[100];
			accountHolderIndex = 0;
			cdOfferings = new CDOffering[100];
		}
		
		/**
		 * load saved information- customers, accounts, offerings, etc
		 * 
		 * data is saved in the following format:
		 * (position)
		 *    0    next account number
		 *    1    number of CD Offerings
		 *    2        CD Offering Details
		 *    3    number of account holders
		 *    4    account holder string
		 *    5        number of checking accounts
		 *    6            checking account details
		 *    7        number of savings accounts
		 *    8            savings account details
		 *    9        number of cd accounts
		 *   10            cd account details
		 *    4    next account holder...    
		 * 
		 * @param fileName a string of the full filename to open
		 * @return return true if successful, false otherwise
		 */
		
		public static boolean readFromFile(String fileName) {
			clearMemory();
			
			try {
				FileReader fileReader = new FileReader(fileName);
				BufferedReader reader = new BufferedReader(fileReader);
				
				CDOffering[] loadCDOfferings = new CDOffering[100];
				AccountHolder loadAccountHolders = new AccountHolder();
				String line; //current line form the file
				
				line = reader.readLine(); //reads account number
				MeritBank.setNextAccountNumber(Long.parseLong(line));
				
				line = reader.readLine(); //read num of CD offerings
				int totalCDOfferings = Integer.parseInt(line);
				
				for (int i = 0; i < totalCDOfferings; i++) {
					line = reader.readLine(); //read CD offerings
					loadCDOfferings[i] = CDOffering.readFromString(line);
				}
				
				line = reader.readLine(); //read num of account holders
				int totalAccountHolders = Integer.parseInt(line);
			
				for (int i = 0; i < totalAccountHolders; i++) {
					line = reader.readLine(); //read account Holder
					loadAccountHolders = AccountHolder.readFromString(line);
					addAccountHolder(loadAccountHolders);
				
				
				line = reader.readLine(); //read num of checking Accounts
				int totalNumOfCheckingAccounts = Integer.parseInt(line);
				
				for (int j = 0; j < totalNumOfCheckingAccounts; j++) {
					line = reader.readLine(); //reads checking accounts
					CheckingAccount chkAcc = CheckingAccount.readFromString(line);
					loadAccountHolders.addCheckingAccount(chkAcc);
				}
				
				line = reader.readLine(); //read num of Savings accounts
				int totalSavingsAccounts = Integer.parseInt(line);
				
				for (int j = 0 ; j < totalSavingsAccounts; j++) {
					line = reader.readLine(); //reads savings accounts
					SavingsAccount savAcc = SavingsAccount.readFromString(line);
					loadAccountHolders.addSavingsAccount(savAcc);
				}
				
				line = reader.readLine(); //reads num of CD accounts
				int totalNumOfCDAccounts = Integer.parseInt(line);
				
				for (int j = 0; j < totalNumOfCDAccounts; j++) {
					line = reader.readLine(); //read cd Accounts
					CDAccount cdAcc = CDAccount.readFromString(line);
					loadAccountHolders.addCDAccount(cdAcc);
				}
				}
					
					reader.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error. Unable to access file: " + fileName);
		}
		return false;
}
		/**
		 * Save current information in memory to a text file for future access
		 * 
		 * currently not implemented, no tests require it.
		 * 
		 * when implemented, should iterate though all objects similar to the 
		 * getTotalBalance method and call each object's writeToString 
		 * 
		 * @param fileName
		 * @return true if successful
		 */
		
		public static boolean writeToString(String fileName) {
			return false;
		}
		
		public static  AccountHolder[] sortAccountHolders() {
			Arrays.sort(accountHolders);
			return accountHolders;
			}
					
//				while (reader.readLine() != null) {
//					counter++;
//				}
//				String[] words = new String[counter];
//				
//				BufferedReader reader2 = new BufferedReader(new FileReader(fileName));
//				for (int i = 0; i < counter; i++) {
//					words[i] = reader2.readLine();
//				}
//				reader.close();
//				reader2.close();
//				
//			}
//			
//			catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//			catch(IOException e) {
//				e.printStackTrace();
//			}
//			int cdCounter = 0;
//			try {
//				Scanner  s1 = new Scanner(new FileReader(fileName));
//				while (s1.hasNextLine()) {
//					cdCounter++;
//				}
//				CDOffering[] loadCDOfferings =  new CDOffering[100];
//				Scanner s2 = new Scanner(new FileReader(fileName));
//				for(int i = 0;  i < cdCounter; i++) {
//					loadCDOfferings[i] = s2.nextLine)();	
//				}
//			}
//			return true;
//		}
//		
		
		
		
		
		
		}
	
	



  

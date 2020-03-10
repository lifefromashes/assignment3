package com.meritamerica.assignment3;


public class CDOffering {
	private int term;
	private double interestRate;
	
	public CDOffering(int term, double interestRate) {
		this.term = term;
		this.interestRate = interestRate;
	}
	
	public static CDOffering readFromString(String cdOfferingDataString) {
		String termString = "";
		String rateString = "";
		int stringPosistion = 1;
		
		for (char cdInfo : cdOfferingDataString.toCharArray()) {
			if (cdInfo == ',') {
				stringPosistion++;
				continue;
			}
			if (cdInfo == 1) {
				termString += cdInfo;
			}
			if(cdInfo == 2) {
				rateString+= cdInfo;
			}
			
			if(termString == " " || rateString == "" || stringPosistion != 2) {
				throw new NumberFormatException();
			}
		}
			
			int term = Integer.parseInt(termString);
			double rate = Double.parseDouble(rateString);
			
		
		CDOffering newCDOffering = new CDOffering(term, rate);
		return newCDOffering;
	}
	
	public int getTerm() {
		return this.term;
		
	}
	
	public double getInterestRate() {
		return this.interestRate;
	}

}




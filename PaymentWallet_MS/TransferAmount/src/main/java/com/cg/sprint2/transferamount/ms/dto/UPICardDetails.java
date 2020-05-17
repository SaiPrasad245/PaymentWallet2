package com.cg.sprint2.transferamount.ms.dto;

public class UPICardDetails {

	private String fromMobileNo;
	private String toMobileNo;
	private double amount;

	private int pin;

	public String getFromMobileNo() {
		return fromMobileNo;
	}

	public void setFromMobileNo(String fromMobileNo) {
		this.fromMobileNo = fromMobileNo;
	}

	public String getToMobileNo() {
		return toMobileNo;
	}

	public void setToMobileNo(String toMobileNo) {
		this.toMobileNo = toMobileNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

}

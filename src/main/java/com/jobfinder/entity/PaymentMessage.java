package com.jobfinder.entity;

public class PaymentMessage {

  private String name;
  private String packageName; 
  private double amount;
  private String paymentDate;
  private int postNumber;

  public PaymentMessage(String name, String packageName, double amount, String paymentDate, int postNumber) {
    this.name = name;
    this.packageName = packageName;
    this.amount = amount;
    this.paymentDate = paymentDate; 
    this.postNumber = postNumber;
  }

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getPackageName() {
	return packageName;
}

public void setPackageName(String packageName) {
	this.packageName = packageName;
}

public double getAmount() {
	return amount;
}

public void setAmount(double amount) {
	this.amount = amount;
}

public String getPaymentDate() {
	return paymentDate;
}

public void setPaymentDate(String paymentDate) {
	this.paymentDate = paymentDate;
}

public int getPostNumber() {
	return postNumber;
}

public void setPostNumber(int postNumber) {
	this.postNumber = postNumber;
}
 
  // các getter, setter 

}
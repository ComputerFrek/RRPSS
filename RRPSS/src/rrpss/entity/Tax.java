package rrpss.entity;
public class Tax {
	String taxName;
	double taxPercentage;
	
	public Tax(String taxName, double taxPercentage) {
		this.taxName = taxName;
		this.taxPercentage = taxPercentage;
	}
	public String getTaxName() {
		return this.taxName;
	}
	public double getTaxPercentage() {
		return this.taxPercentage;
	}
	public double calculateTax(Order order) {
		return order.getSubtotal() * (this.getTaxPercentage() / 100);
	}
}

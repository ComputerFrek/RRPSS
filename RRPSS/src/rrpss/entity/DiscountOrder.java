package rrpss.entity;
public class DiscountOrder {
	private Discount discount;
	private double discountPrice;
	
	public Discount getDiscount() {
		return this.discount;
	}
	public void setDiscount(Discount discount) {
		this.discount = discount;
	}
	
	public double getDiscountPrice() {
		return this.discountPrice;
	}
	public void setDiscountPrice(double discountedPrice) {
		this.discountPrice = discountedPrice;
	}
}

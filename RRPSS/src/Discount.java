
public abstract class Discount implements iCalculateDiscount{
	private String description;
	private double discount;
	
	public Discount(double discount, String description) {
		this.discount = discount;
		this.description = description;
	}
	public String getDescription() {
		return this.description;
	}
	public double getDiscount() {
		return this.discount;
	}
}
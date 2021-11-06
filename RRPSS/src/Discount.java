
public abstract class Discount {
	String description;
	double discount;
	
	public Discount(double discount, String description) {
		this.discount = discount;
		this.description = description;
	}
}

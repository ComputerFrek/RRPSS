package rrpss.entity;
public class DiscountCoupon extends Discount  {
	
	boolean isPercentage;
	public DiscountCoupon(double discount, String description, boolean isPercentage) {
		super(discount, description);
		// TODO Auto-generated constructor stub
		this.isPercentage = isPercentage;
	}

	@Override
	public double CalulcateDiscount(double subTotal) {
		// TODO Auto-generated method stub
		if(this.isPercentage)
			return subTotal * this.getDiscount();
		return this.getDiscount();
	}

}

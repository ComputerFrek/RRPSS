package rrpss.entity;
public class Membership extends Discount {
	
	String membershipID;
	String membershipName;

	public Membership(double discount, String description, String membershipID, String membershipName) {
		super(discount, description);
		// TODO Auto-generated constructor stub
		this.membershipID = membershipID;
		this.membershipName = membershipName;
	}

	@Override
	public double CalulcateDiscount(double subTotal) {
		// TODO Auto-generated method stub
		return subTotal * this.getDiscount();
	}
	public String getMembershipID() {
		return this.membershipID;
	}
	public String getMembershipName() {
		return this.membershipName;
	}
	
}

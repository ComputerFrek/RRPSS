
public class Membership extends Discount {
	
	int membershipID;
	String membershipName;

	public Membership(int membershipID, String membershipName, double discount, String description) {
		super(discount, description);
		// TODO Auto-generated constructor stub
		this.membershipID = membershipID;
		this.membershipName = membershipName;
	}
	
}

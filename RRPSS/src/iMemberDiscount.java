import java.util.ArrayList;

public interface iMemberDiscount {
	Discount CheckMembershipStatus(String membershipID, ArrayList<Discount> membershipDiscount);
	Discount CalculateMembershipDiscount(ArrayList<Discount> membershipDiscount);
}

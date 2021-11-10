package rrpss.service;
import java.util.ArrayList;

import rrpss.entity.Discount;

public interface iMemberDiscount {
	Discount CheckMembershipStatus(String membershipID, ArrayList<Discount> membershipDiscount);
	Discount CalculateMembershipDiscount(ArrayList<Discount> membershipDiscount);
}

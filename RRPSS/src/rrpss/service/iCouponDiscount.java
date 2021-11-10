package rrpss.service;
import java.util.ArrayList;

import rrpss.entity.Discount;

public interface iCouponDiscount {
	void DisplayCoupons(ArrayList<Discount> couponDiscount);
	Discount CalculateCouponDiscount(ArrayList<Discount> couponDiscount);
}

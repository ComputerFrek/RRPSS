package rrpss.service;
import java.util.ArrayList;

import rrpss.entity.Discount;
import rrpss.entity.Order;

public interface iDiscountCalculator {
	double CalculateDiscount(Order order, ArrayList<Discount> membershipList, ArrayList<Discount> couponList);
}

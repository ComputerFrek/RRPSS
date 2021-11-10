package rrpss.service;
import java.util.ArrayList;

import rrpss.entity.Discount;
import rrpss.entity.Order;
import rrpss.entity.Tax;

public interface iClosedOrder extends iTaxCalculator{
	boolean CloseOrder(Order order, ArrayList<Tax> taxList, ArrayList<Discount> membershipList, ArrayList<Discount> couponList);
	double CalculateSubTotal(Order order);
	
}

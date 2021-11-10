package rrpss.service;
import java.util.Map;

import rrpss.entity.Order;
import rrpss.entity.Staff;
import rrpss.entity.Table;

public interface iOpenOrder {
	void OpenOrder();
	Order addOrder(int orderID, int noOfPax, Table selectedTable, Staff createdStaff);
	
}

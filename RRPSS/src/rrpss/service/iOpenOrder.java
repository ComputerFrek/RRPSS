package rrpss.service;
import java.util.Map;

import rrpss.entity.Order;
import rrpss.entity.Staff;
import rrpss.entity.Table;

public interface iOpenOrder {
	void OpenOrder(Map<Integer, Order> orderMap);
	Order addOrder(int orderID, int noOfPax, Table selectedTable, Staff createdStaff, Map<Integer, Order> orderMap);
}

package rrpss.controller;
import java.time.LocalDateTime;
import java.util.*;

import rrpss.entity.Discount;
import rrpss.entity.Invoice;
import rrpss.entity.Order;
import rrpss.entity.Tax;

public class OrderController {
	Map<Integer, Order> orderMap = new HashMap<Integer, Order>();
	ViewOrderController viewOC;
	UpdateOrderController updateOC;
	ClosedOrderController closedOC;
	OpenOrderController openOC;
	
	public OrderController() 
	{
		viewOC = new ViewOrderController(this);
		updateOC = new UpdateOrderController(this);
		closedOC = new ClosedOrderController(this);
		openOC = new OpenOrderController(this);
	}	

	public void ViewOrder() {
		viewOC.ViewOrderMenu(orderMap);
	}
	
	public void PrintInvoice(Order order)
	{
		Scanner sc = new Scanner(System.in);
		String choice = "";
		System.out.println();
		System.out.print("Print Invoice? (Y/N): ");
		choice = sc.next();
		if(!choice.trim().toLowerCase().equals("y"))
			return; 
		Invoice.PrintInvoice(order);
	}
	public Order SelectOrder(int orderID) {
		if(!orderMap.containsKey(orderID))
			return null;
		return orderMap.get(orderID);
	}
	
	public void UpdateOrder() {
		
		Order order = SelectionOrder();
		if(order == null)
			return;
		updateOC.UpdateOrderMenu(order);
	}
	public void OpenOrder() {
		openOC.OpenOrder(orderMap);
	}
	public void CloseOrder(ArrayList<Tax> taxList, ArrayList<Discount> membershipList, ArrayList<Discount> couponList) {
		Order order = SelectionOrder();
		
		if(order == null)
			return;
		if(closedOC.CloseOrder(order, taxList, membershipList,couponList))
			PrintInvoice(order);
	}
	public Order SelectionOrder() {
		
		int orderCount = viewOC.ViewExistingOrder(orderMap);
		if(orderCount <=1)
			return null;
		
		Order order = viewOC.SelectOrder();
		
		if(order == null || order.getEndTimeStamp() != null)
		{	
			System.out.println();
			System.out.println("Invalid Input: Either Order has been closed or it does not exist.");
			return null;
		}
		return order;
	}
	public Map<Integer, Order> getAllOrders()
	{
		return this.orderMap;
	}
}

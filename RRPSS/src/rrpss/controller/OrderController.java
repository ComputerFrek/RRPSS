package rrpss.controller;
import java.time.LocalDateTime;
import java.util.*;

import rrpss.entity.Discount;
import rrpss.entity.Invoice;
import rrpss.entity.Order;
import rrpss.entity.Tax;

public class OrderController {
	private Map<Integer, Order> orderMap = new HashMap<Integer, Order>();
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
		Invoice.printInvoice(order);
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
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		while(choice != 0)
		{
			System.out.println("(1) Create Walk-In Order");
			System.out.println("(2) Create Reservation Order");
			System.out.println("(0) Exit");
			
			System.out.print("Enter Choice: ");
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				openOC.OpenOrder(); // Walk in order
				return;
			case 2:
				openOC.OpenOrderByReservation();
				return;
			case 0:
				return;
			default:
				break;
			}
		}

		
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
	public Map<Integer, Order> getOrderMap()
	{
		return this.orderMap;
	}
	public void addOrderMap(Order order)
	{
		this.orderMap.put(order.getOrderID(), order);
	}
}

package rrpss.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

import rrpss.entity.Order;
import rrpss.entity.OrderItems;
import rrpss.service.iViewOrderController;

public class ViewOrderController implements iViewOrderController{
	
	private OrderController oC;

	public ViewOrderController(OrderController oC) {
		this.oC = oC;
	}
	
	public void ViewOrderMenu(Map<Integer, Order> orderMap) {
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		int orderSearchSize;
		
		try {
			do
			{
				System.out.println();
				Order order;
				System.out.println("(1) View All Orders");
				System.out.println("(2) View Existing Order");
				System.out.println("(3) View Close Order");
				System.out.println("(0) Exit");
				System.out.print("Enter Choice: ");
				choice = sc.nextInt();
				
				switch(choice) {
					case 1:
						orderSearchSize = ViewAllOrder(orderMap);
						if(orderSearchSize > 1)
						{
							order = SelectOrder();
							if(order == null)
								break;
							ViewMoreDetails(order);
						}
						break;
					case 2:
						orderSearchSize = ViewExistingOrder(orderMap);
						if(orderSearchSize > 1)
						{
							order = SelectOrder();
							if(order == null)
								break;
							ViewMoreDetails(order);
							EditMenuItems(order);
						}
						break;
					case 3:
						orderSearchSize = ViewClosedOrder(orderMap);
						if(orderSearchSize > 1)
						{
							order = SelectOrder();
							if(order == null)
								break;
							ViewMoreDetails(order);
						}
						break;
					case 0:
						return;
					default:
						break;
				}
			}while(choice != 0);
		}
		catch(Exception ex)
		{
			System.out.println("Error: Invalid Input. Please Try Again.");
		}
	}
	
	public Order SelectOrder() {
		
		Scanner sc = new Scanner(System.in);
		System.out.println();
		int orderID;
		System.out.println();
		System.out.print("Enter a Order ID: ");
		orderID = sc.nextInt();
		
		return oC.SelectOrder(orderID);
	}
	
	

	@Override
	public int ViewExistingOrder(Map<Integer, Order> orderMap) {
		// TODO Auto-generated method stub
		int count = 1;
		System.out.println();
		if(orderMap.keySet().size() <= 0)
		{
			System.out.println("There are no orders.");
			return 0;
		}
		
		System.out.println("Existing Order List: ");
		System.out.println("No. \tOrder ID \tStaff By");
        for (int orderID : orderMap.keySet())
        {
            Order order = orderMap.get(orderID);
            if(order.getEndTimeStamp() != null)
            	continue;
            System.out.printf("%d \t%s \t\t%s \r\n",count, orderID, order.getStaff().getName());
            count++;
        }
        
		if(count == 1)
		{
			System.out.println("There are no Closed Orders.");
			return 0;
		}
		
        return count;
	}
	
	@Override
	public void ViewMoreDetails(Order order) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String showMore;
		
		System.out.println();
		System.out.printf("Order Details: \r\n");
		System.out.printf("Order ID: \t\t%d \r\n", order.getOrderID());
		System.out.printf("Table ID: \t\t%d \r\n", order.getTable().getTableID());
		System.out.printf("Staff Name: \t\t%s \r\n", order.getStaff().getName());
		System.out.printf("Start Date/Time: \t%s \r\n", ConvertLocalToString(order.getStartTimeStamp()));
		System.out.printf("End Date/Time: \t\t%s \r\n", ConvertLocalToString(order.getEndTimeStamp()));
		System.out.printf("Number of Pax: \t\t%s \r\n", order.getNoOfPax());
		
		
		if(order.getOrderItems().size() > 0)
		{
			System.out.printf("View Order Items(Y/N) ?\r\n");
			showMore = sc.next().toLowerCase();
			if(showMore.equals("y"))
			{
				System.out.printf(showMore.toLowerCase());
				ShowAllOrderItems(order);
			}
				
		}
	}
	
	private void EditMenuItems(Order order) {
		Scanner sc = new Scanner(System.in);
		String isEdit;
		System.out.println();
		System.out.println("Edit Order List(Y/N) ?");
		isEdit = sc.next().toLowerCase();
		if(isEdit.equals("y"))
		{
			// Call AddOrde
			oC.updateOC.UpdateOrderMenu(order);
		}
	}
	

	@Override
	public int ViewClosedOrder(Map<Integer, Order> orderMap) {
		// TODO Auto-generated method stub
		int count = 1;
		System.out.println();
		if(orderMap.keySet().size() <= 0)
		{
			System.out.println("There are no orders.");
			return 0;
		}
		
		System.out.println("Order List: ");
		System.out.println("No. \tOrder ID \tStaff By");
        for (int orderID : orderMap.keySet())
        {
            Order order = orderMap.get(orderID);
            if(order.getEndTimeStamp() == null)
            	continue;
            System.out.printf("%d \t%s \t\t%s \r\n",count, orderID, order.getStaff().getName());
            count++;
        }
        
		if(count == 1)
		{
			System.out.println("There are no Closed Orders.");
			return 0;
		}
		
		return count;
	}

	@Override
	public int ViewAllOrder(Map<Integer, Order> orderMap) {
		// TODO Auto-generated method stub
		int count = 1;
		System.out.println();
		if(orderMap.keySet().size() <= 0)
		{
			System.out.println("There are no orders.");
			return 0;
		}
		
		System.out.println("Order List: ");
		System.out.println("No. \tOrder ID \tIs Open");
        for (int orderID : orderMap.keySet())
        {
            Order order = orderMap.get(orderID);
            String isOpen = ((order.getEndTimeStamp() == null) ? "Open" : "Closed");
            System.out.printf("%d \t%s \t\t%s \r\n",count, orderID, isOpen);
            count++;
        }
                
		return count;
	}

	@Override
	public void ShowAllOrderItems(Order order) {
		// TODO Auto-generated method stub
		int count = 1;
		System.out.println();
		System.out.println("Order List: ");
		System.out.println("No. \tItem Name \t\tQTY");
        for (String menuItem : order.getOrderItems().keySet())
        {
            OrderItems orderItem = order.getOrderItems().get(menuItem);
            System.out.printf("%03d \t%-15s \t%02d\r\n",count, orderItem.getMenuItem().getItemName(), orderItem.getQuantity());
            count++;
        }
	}
	
	private String ConvertLocalToString(LocalDateTime timeStamp) {
		if(timeStamp == null)
			return "NIL";
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm");
		return timeStamp.format(formatDate);
	}
}

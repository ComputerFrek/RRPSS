import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

public class OrderController {
	static Map<Integer, Order> orderMap;
	public OrderController() 
	{
		
	}
	
	public void ViewOrder()
	{
		System.out.println("Order List: ");
		for(Order o : new ArrayList<Order>(orderMap.values()))
			System.out.println("OrderID("+ o.getOrderID() + "): " + o.getTimeStamp());
		System.out.println();
		//add items in order
	}
	
	public static void CreateOrder() {
		Scanner sc = new Scanner(System.in);
		// Creating New Order
		// Check Which Staff is Creating the Order;
		// Ask for Number of PAX for the Order (2-10)
		// Recommend Table, and show the Table Available  
		// Select the Table
		// Order Created

		RRPSS_App.staffController.ViewStaff();
		Staff staff = RRPSS_App.staffController.SelectStaff();
		System.out.println("No of Pax?: ");
		int noOfPax = sc.nextInt();
		System.out.println("Choose A Table: ");
		RRPSS_App.tableController.ShowRecommendTable(noOfPax);
		RRPSS_App.tableController.ViewTable();
		System.out.println("Select One Of The Table Above: ");
		int tableSelected = sc.nextInt();
		Table table = RRPSS_App.tableController.SelectTable(tableSelected);
		
		Order newOrder = addOrder(orderMap.size(), noOfPax, table, staff);
		if(newOrder != null)
		{
			// Show Message
		}
	}
	
	public static void AddOrderItem() {
		
	}
	public static void RemoveOrderItem() {
		
	}
	
	public static Order addOrder(int orderID, int noOfPax, Table selectedTable, Staff createdStaff) {
		Order newOrder = new Order(orderID, noOfPax, selectedTable, createdStaff);
		return orderMap.put(orderID, newOrder);
	}
	

}

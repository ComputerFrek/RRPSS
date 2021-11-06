import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

public class OrderController {
	static Map<Integer, Order> orderMap = new HashMap<Integer, Order>();
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
	
	public static void AddOrderItem(Order order) {
		Scanner sc = new Scanner(System.in);
		String categoryInput = "";
		String menuInput = "";
		MenuItem menuItem;
		int quantity = 0;
		while(CategoryController.GetCategory(categoryInput) == null)
		{
			System.out.println("Choose a Category: ");
			RRPSS_App.categoryController.ViewCategory();
			System.out.println("Select a Category: ");
			categoryInput = sc.nextLine();
			if(categoryInput == "exit")
				return;
		}
		while((menuItem = CategoryController.GetMenuItems(menuInput)) == null)
		{
			System.out.printf("%s:Menu Item: ", categoryInput);
			CategoryController.ViewMenuList(categoryInput);
			System.out.println("Select an Item: ");
			menuInput = sc.nextLine();
			if(menuInput == "exit")
				return;
		}
		// Show Menu Item Description and Price
		System.out.printf("Menu Item Selected:\n"
				+ "%s \n"
				+ "%s \n"
				+ "%.2f", 
				menuItem.getItemName(), menuItem.getDescription(), menuItem.getPrice());

		if((quantity = order.getMenuQuantity(menuItem)) > 0)
			System.out.printf("Existing Quantity: %d", quantity);
		System.out.println("Enter Desired Quantity: ");	// Do You want do it by addition or replace the value
		quantity = sc.nextInt();
		
		order.setMenuQuantity(menuItem, quantity);
		if(order.getMenuQuantity(menuItem) == quantity)
			System.out.printf("Item: %s was added into Order List - QTY: %d", menuItem.getItemName(), order.getMenuQuantity(menuItem));	
		else
			System.out.println("Item was not added into Order List");	
	}
	public static void RemoveOrderItem() {
		
	}
	
	public static Order addOrder(int orderID, int noOfPax, Table selectedTable, Staff createdStaff) {
		Order newOrder = new Order(orderID, noOfPax, selectedTable, createdStaff);
		return orderMap.put(orderID, newOrder);
	}
	

}

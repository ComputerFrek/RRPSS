import java.util.ArrayList;
import java.time.LocalDateTime;
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
			System.out.println("OrderID("+ o.getOrderID() + "): " + o.getStartTimeStamp());
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
	public static Order addOrder(int orderID, int noOfPax, Table selectedTable, Staff createdStaff) {
		Order newOrder = new Order(orderID, noOfPax, selectedTable, createdStaff);
		return orderMap.put(orderID, newOrder);
	}
	
	public static void AddOrderItem(Order order) {
		Scanner sc = new Scanner(System.in);
		String categoryInput = "";
		String menuInput = "";
		Category category;
		MenuItem menuItem;
		OrderItems orderItem; 
		while((category = CategoryController.GetCategory(categoryInput)) == null)
		{
			System.out.println("Choose a Category: ");
			RRPSS_App.categoryController.ViewCategory();
			System.out.println("Select a Category: ");
			categoryInput = sc.nextLine();
			if(categoryInput == "exit")
				return;
		}
		while((menuItem = CategoryController.GetMenuItems(category, menuInput)) == null)
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

		if((orderItem = order.getOrderItem(menuItem.getItemName())) != null)
			System.out.printf("Existing Quantity: %d", orderItem.getQuantity());
		System.out.println("Enter Desired Quantity: ");	// Do You want do it by addition or replace the value
		orderItem.setQuantity(sc.nextInt());
		
		System.out.printf("Item: %s was added into Order List - QTY: %d", menuItem.getItemName(), orderItem.getQuantity());	
	}
	public static void RemoveOrderItem(Order order) {
		Scanner sc = new Scanner(System.in);
		OrderItems orderItem;
		String itemInput;
		int quantityRemove, newQuantity;
				
		ShowAllOrderItems(order);
		System.out.println("Select an Item: ");
		itemInput = sc.nextLine();
		orderItem = order.getOrderItem(itemInput);
		if(orderItem == null)
			return;

		// Show Item Selected
		System.out.printf("Item Selected:\n"
				+ "Name: %s \n"
				+ "Quantity: %d \n", 
				orderItem.getMenuItem().getItemName(), orderItem.getQuantity());
		
		// Check Quantity
		quantityRemove = sc.nextInt();
		newQuantity = orderItem.getQuantity() - quantityRemove;
		// Remove Item
		if(newQuantity <= 0)
		{
			order.removeOrderItems(itemInput);
			System.out.printf("Item: %s has been removered from Order List", itemInput);
		}
		else
		{
			orderItem.setQuantity(newQuantity);
			System.out.printf("%d has been removed from %s", quantityRemove, itemInput);
		}
	}
	public static void ShowAllOrderItems(Order order)
	{
		int count = 1;
        for (String menuItem : order.getOrderItems().keySet())
        {
            OrderItems orderItem = order.getOrderItems().get(menuItem);
            System.out.printf("%d | %s",count, orderItem.getMenuItem().getItemName());
            count++;
        }
	}
	
	public void CloseOrder(Order order) {
		order.setEndTimeStamp(LocalDateTime.now()); // Closed Order with DateTime
		order.setSubTotal(CalculateSubTotal(order)); // Update Order Bill SubTotal
		double discountTotal = DiscountTotal(order);
		double taxTotal = CalculateTax(order);
		
		double total = order.getSubtotal() - discountTotal + taxTotal;
		order.setTotal(total);
		
	}
	private double CalculateTax(Order order) {
		return 0;
	}
	private double DiscountTotal(Order order) {
		Scanner sc = new Scanner(System.in);
		Discount discount = null;
		System.out.println("Apply Discount?(Y/N): ");
		String isApply = sc.nextLine();
		if(isApply == "N")
			return 0;
		PrintDiscountOption();
		
		// Retrieve the Discount Details(Membership and Coupon)
		
		
		DiscountOrder discountOrder = new DiscountOrder();
		discountOrder.setDiscount(discount);
		discountOrder.setDiscountPrice(discount.CalulcateDiscount(order.getSubtotal()));
		order.setDiscount(discountOrder);
		
		return discountOrder.getDiscountPrice();
	}
	private void PrintDiscountOption() {
		System.out.println("Select Discount Type: ");
		System.out.println("1) Membership: ");
		System.out.println("2) Coupon: ");
	}
	private double CalculateSubTotal(Order order) {
		double subTotalBill = 0;
		// Calculate Items SubTotal
		for (String item : order.getOrderItems().keySet())
        {
			String keyItem = item;
			MenuItem menuItem = order.getOrderItem(keyItem).getMenuItem();
			int qty = order.getOrderItem(keyItem).getQuantity();
			
            double subTotalItem = menuItem.getPrice() * qty;
            order.getOrderItem(keyItem).setSubTotal(subTotalItem);
            subTotalBill += subTotalItem;
        }
		return subTotalBill;
	}
	
	public static void printInvoice(Order order)
	{
		Invoice invoice = new Invoice(order);
		
	}
	

	

}

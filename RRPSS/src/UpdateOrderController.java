import java.util.Scanner;

public class UpdateOrderController implements iUpdateOrderController{
	public void AddOrderItem(Order order) {
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
	public void RemoveOrderItem(Order order) {
		Scanner sc = new Scanner(System.in);
		OrderItems orderItem;
		String itemInput;
		int quantityRemove, newQuantity;
				
		OrderController.vOC.ShowAllOrderItems(order);
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
}

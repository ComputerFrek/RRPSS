import java.util.Scanner;

public class UpdateOrderController implements iUpdateOrderController{
	
	private OrderController oC;
	public UpdateOrderController(OrderController oC) {
		this.oC = oC;
	}
	public void UpdateOrderMenu(Order order) {
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		
		try {
			do
			{
				System.out.println("(1) Add Item(s) To/From Order");
				System.out.println("(2) Remove Item(s) To/From Order");
				System.out.println("(0) Exit");
				
				choice = sc.nextInt();
				
				switch(choice) {
					case 1:
						AddOrderItem(order);
						break;
					case 2:
						RemoveOrderItem(order);
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

	public void AddOrderItem(Order order) {
		Scanner sc = new Scanner(System.in);
		String categoryInput = "";
		String menuInput = "";
		Category category;
		MenuItem menuItem = null;
		OrderItems orderItem; 
		
		do {		
			menuItem = AddOrderMenu();
			if(menuItem == null)
			{
				System.out.println("No Menu Item Selected.");
			}
			
			// Show Menu Item Description and Price
			System.out.printf("Menu Item Selected: \r\n"
					+ "%s \r\n"
					+ "%s \r\n"
					+ "%.2f \r\n", 
					menuItem.getItemName(), menuItem.getDescription(), menuItem.getPrice());

			if((orderItem = order.getOrderItem(menuItem.getItemName())) != null)
				System.out.printf("Existing Quantity: %d \r\n", orderItem.getQuantity());
			
			if(orderItem == null)
				orderItem = new OrderItems(menuItem, 1);
			
			System.out.println("Enter Desired Quantity: ");	// Do You want do it by addition or replace the value
			orderItem.setQuantity(sc.nextInt());
			order.addOrderItem(orderItem);
			
			System.out.printf("Item: %s was added into Order List - QTY: %d\r\n", menuItem.getItemName(), orderItem.getQuantity());
			
			System.out.println("Type any key to continue adding. \r\n(Type: Exit, To Exit Adding Items)");
			menuInput = sc.next();
			if(menuInput.trim().toLowerCase().equals("exit"))
				return;
		} while(!menuInput.trim().toLowerCase().equals("exit"));
	}
	private MenuItem AddOrderMenu() {
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		do
		{
			System.out.println("(1) Add Ala Carte");
			System.out.println("(2) Add Promo Package");
			System.out.println("(0) Exit");
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					return AlaCarteChoice();
				case 2:
					return PromoChoice();
				case 0:
					return null;
				default:
					break;
			}
		}while(choice != 0);
		return null;
	}
	private MenuItem AlaCarteChoice() {
		Scanner sc = new Scanner(System.in);
		RRPSS_App.alacartecontroller.printAlacarteMenu();
		
		System.out.println("Select a");
		
		System.out.println("Select an Item: ");
		String menuItemSelected = sc.nextLine();
		return RRPSS_App.alacartecontroller.getAlacarteItem(menuItemSelected);
		
	}
	private MenuItem PromoChoice() {
		Scanner sc = new Scanner(System.in);
		RRPSS_App.promopackagecontroller.printPromoMenu();
		
		System.out.println("Select a Promo Package: ");
		String menuItemSelected = sc.nextLine();
		return RRPSS_App.promopackagecontroller.getPromoPackage(menuItemSelected);
		
	}
	
	
	
	
	
	
	
	
	public void RemoveOrderItem(Order order) {
		Scanner sc = new Scanner(System.in);
		OrderItems orderItem;
		String itemInput = "";
		int quantityRemove, newQuantity;
				
		do {
			
			oC.viewOC.ShowAllOrderItems(order);
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
			System.out.printf("Enter New Quantity: ");
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
			
			System.out.println("Type any key to continue adding. \r\n(Type: Exit, To Exit Adding Items)");
			itemInput = sc.nextLine();
			if(itemInput.trim().toLowerCase() == "exit")
				return;
			
		} while(itemInput.trim().toLowerCase() != "exit");
	}
}

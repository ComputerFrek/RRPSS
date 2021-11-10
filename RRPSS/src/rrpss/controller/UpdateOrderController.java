package rrpss.controller;
import java.util.Scanner;

import rrpss.entity.MenuItem;
import rrpss.entity.Order;
import rrpss.entity.OrderItems;
import rrpss.main.RRPSS_App;
import rrpss.service.iUpdateOrderController;

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
				System.out.println();
				System.out.println("(1) Add Item(s) To/From Order");
				System.out.println("(2) Remove Item(s) To/From Order");
				System.out.println("(0) Exit");
				System.out.print("Enter Choice: ");
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
	private MenuItem AddOrderMenu() {
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		do
		{
			System.out.println("(1) Add Ala Carte");
			System.out.println("(2) Add Promo Package");
			System.out.println("(0) Exit");
			System.out.print("Enter Choice: ");
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
	
	public void AddOrderItem(Order order) {
		Scanner sc = new Scanner(System.in);
		String menuInput = "";
		MenuItem menuItem = null;
		OrderItems orderItem; 
		System.out.println();
		try {
			while(!menuInput.trim().toLowerCase().equals("exit")) {		
				menuItem = AddOrderMenu();
				
				if(menuItem == null)
				{
					System.out.println("No Menu Item Selected.");
					break;
				}
				
				// Show Menu Item Description and Price
				System.out.printf("Menu Item Selected: \r\n"
						+ "Item Name: \t%s \r\n"
						+ "Category: \t%s \r\n"
						+ "Price: \t\t%.2f \r\n", 
						menuItem.getItemName(), menuItem.getDescription(), menuItem.getPrice());

				if((orderItem = order.getOrderItem(menuItem.getItemName())) != null)
					System.out.printf("Existing Quantity: %d \r\n", orderItem.getQuantity());
				
				if(orderItem == null)
					orderItem = new OrderItems(menuItem, 0);
				
				System.out.print("Enter Quantity to Add: ");	// Do You want do it by addition or replace the value
				orderItem.setQuantity(orderItem.getQuantity()+sc.nextInt());
				order.addOrderItem(orderItem);
				
				System.out.printf("Item: %s was added into Order List - QTY: %d\r\n", menuItem.getItemName(), orderItem.getQuantity());
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error: Adding Item Failed.");
		}
 
	}
	private MenuItem AlaCarteChoice() {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		RRPSS_App.menuItemController.acC.printAlacarteMenu();
				
		System.out.print("Select an Item: ");
		int menuIndex = sc.nextInt();
		return RRPSS_App.menuItemController.acC.getAllAlacarteItems().get(menuIndex-1);
		
	}
	private MenuItem PromoChoice() {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		RRPSS_App.menuItemController.ppC.printPromoMenu();
		
		System.out.print("Select a Promo Package: ");
		int menuIndex = sc.nextInt();
		return RRPSS_App.menuItemController.ppC.getAllPromoItems().get(menuIndex-1);
		
	}
	
	public void RemoveOrderItem(Order order) {
		Scanner sc = new Scanner(System.in);
		OrderItems orderItem;
		String itemInput = "";
		int quantityRemove, newQuantity;
		
		System.out.println();
		
		if(order.getOrderItems().size() <= 0)
		{
			System.out.println("Order's order list is Empty.");
			return;
		}
		try {
			while(!itemInput.trim().toLowerCase().equals("exit"))
			{
				
				oC.viewOC.ShowAllOrderItems(order);
				System.out.println();
				System.out.print("Enter Item Name: ");
				itemInput = sc.next();
				orderItem = order.getOrderItem(itemInput);
				if(orderItem == null)
				{
					System.out.println("Item not included in order list.");
					System.out.println();
					continue;
				}

				// Show Item Selected

				System.out.println();
				System.out.printf("Item Selected: \n"
						+ "Item Name: \t%s \n"
						+ "Quantity: \t%d \n", 
						orderItem.getMenuItem().getItemName(), orderItem.getQuantity());
				
				// Check Quantity
				System.out.println();
				System.out.printf("Enter Reduce Quantity By: ");
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
				
				if(order.getOrderItems().size() <= 0)
				{
					System.out.println("Order's order list is Empty.");
					return;
				}
				
				System.out.println("Type any key to continue adding. \r\n(Type: Exit, To Exit Adding Items)");
				itemInput = sc.next();			
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error: Adding Item Failed.");
		}
	}
}

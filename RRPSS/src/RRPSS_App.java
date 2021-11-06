import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.Scanner;

public class RRPSS_App {
	
	static OrderController orderController;
	static StaffController staffController;
	static TableController tableController;
	static CategoryController categoryController;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		orderController = new OrderController();
		staffController = new StaffController();
		tableController = new TableController();
		categoryController = new CategoryController();
		
		int choice;
		Scanner sc = new Scanner(System.in);

		System.out.println("Restaurant Reservation and Point of Sale System (RRPSS)");
		System.out.println("========================================================");
		
		ArrayList<Order> orderlist = new ArrayList<Order>();
		Table[] tables = new Table[10];
		for(int i = 0; i < tables.length; i++)
			tables[i] = new Table(i+1);
		int ordernum = 1;
		
		do{
			System.out.println("(1) Create/Update/Remove Menu Item");
			System.out.println("(2) Create/Update/Remove Promotion");
			System.out.println("(3) Create Order");
			System.out.println("(4) View Order");
			System.out.println("(5) Add/Remove Order From Item(s) To/From Order");
			System.out.println("(6) Create Reservation Booking");
			System.out.println("(7) Check/Remove Reservation Booking");
			System.out.println("(8) Check Table Availability");
			System.out.println("(9) Print Order Invoice");
			System.out.println("(10) Print Sale Revenue Report By Period");
			System.out.println("(11) Exit");
			System.out.print("Enter Choice: ");
			choice = sc.nextInt();
			System.out.println();

			switch(choice)
			{
			case 1:
				MenuItemController menuitemcontroller = new MenuItemController();
				//call menuitem controller display
				menuitemcontroller.printMenu();
				System.out.println("(1) Create Menu Item");
				System.out.println("(2) Update Menu Item");
				System.out.println("(3) Remove Menu Item");
				System.out.println("(0) Back");
				System.out.print("Enter Choice: ");
				choice = sc.nextInt();
				
				switch(choice)
				{
					case 1:
						System.out.println("Enter Dish Name");
						String dishname = sc.next();
						System.out.println("Enter Description");
						String dishdesc = sc.next();
						System.out.println("Enter Price");
						String dishprice = sc.next();
						
						menuitemcontroller.createMenuItem(dishname, dishdesc, dishprice);
						
				}
				
				
				break;
			case 2:
				break;
			case 3:	
				break;
			case 4:
				orderController.ViewOrder();
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				tableController.ViewTable();
				break;
			case 9:
				printInvoice();
				break;
			case 10:
				break;
			case 11:
				System.exit(0);
			default:
				System.out.println("\nInvalid choice.\n");
				break;	
			}
		}while(choice!=0);
	}
	
	public static void generateMenu() throws IOException
	{
		Path file = Paths.get("menuItem.txt");
		List<String> lines = Files.readAllLines(file);
		List<MenuItem> menu = new ArrayList<>();
		
		for(int i = 0;i<lines.size();i++)
		{
			String[] fields = lines.get(i).split("\\|");
			MenuItem m = new MenuItem(fields[0].trim(),fields[1].trim(),fields[2].trim());
			menu.add(m);
		}
		
		System.out.println("Name,Category,Price");
		for(MenuItem me : menu)
		{
			System.out.printf("%s,%s,%.2f",me.getItemName(),me.getDescription(),me.getPrice());
			System.out.println();
		}
		System.out.println();
	}

	

	


}

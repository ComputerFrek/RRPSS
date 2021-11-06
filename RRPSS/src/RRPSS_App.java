import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.Scanner;

public class RRPSS_App {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
						System.out.print("Enter Dish Name: ");
						String dishname = sc.next();
						System.out.print("Enter Description: ");
						String dishdesc = sc.next();
						System.out.print("Enter Price: ");
						String dishprice = sc.next();
						
						menuitemcontroller.createMenuItem(dishname, dishdesc, dishprice);
						break;
					case 2:
						System.out.println("Enter Old Dish Name");
						String olddishname = sc.next();
						System.out.println("Enter Old Description");
						String olddishdesc = sc.next();
						System.out.println("Enter Old Price");
						String olddishprice = sc.next();
						System.out.println("Enter New Dish Name");
						String newdishname = sc.next();
						System.out.println("Enter New Description");
						String newdishdesc = sc.next();
						System.out.println("Enter New Price");
						String newdishprice = sc.next();
						
						menuitemcontroller.updateMenuItem(olddishname, olddishdesc, newdishname, newdishdesc, olddishprice, newdishprice);
						break;
					case 3:
						
				}
				
				
				break;
			case 2:
				break;
			case 3:	
				orderlist.add(new Order(ordernum));
				ordernum++;
				System.out.println();
				break;
			case 4:
				viewOrder(orderlist);
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				showTableAvail(tables);
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

	
	public static void printInvoice()
	{
		System.out.println("Server: Test\t\t Date:12/06/2011");
		System.out.println("Table: 11\t\t Time:21:26");
		System.out.println("\t     Client: 2");
		System.out.println("----------------------------------------");
		System.out.println();
		System.out.println("  1  Oysters\t\t\t6.00");
		System.out.println("  1  Stuffs\t\t\t6.00");
		System.out.println("----------------------------------------");
		System.out.println("\t\tSUB-TOTAL:\t6.00");
		System.out.println("\t\t    Taxes:\t2.00");
		System.out.println("----------------------------------------");
		System.out.println("\t\t    TOTAL:\t8.00");
		System.out.println();
		
	}
	
	public static void viewOrder(ArrayList<Order> orderlist)
	{
		System.out.println("Order List: ");
		for(Order o : orderlist)
			System.out.println("OrderID("+ o.getOrderID() + "): " + o.getTimeStamp());
		System.out.println();
		
		//add items in order
	}

	public static void showTableAvail(Table[] tables)
	{
		System.out.println("Tables Available:");
		for(Table t: tables)
		{
			if(!t.getReserved())
				System.out.println("TableID " + t.getTableID());
		}
		System.out.println();
	}
}

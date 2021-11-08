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
			System.out.println("(1) View/Create/Update/Remove Menu Item");
			System.out.println("(2) View/Create/Update/Remove Promotion");
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
				runCreateUpdateRemoveAlacarte();
				break;
			case 2:
				runCreateUpdateRemovePromo();
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
	
	public static void runCreateUpdateRemoveAlacarte() {
		int choice;
		Scanner sc = new Scanner(System.in);
		AlacarteController alacartecontroller = new AlacarteController();
		
		do {
			//call menuitem controller display
			System.out.println("(1) View Menu Item");
			System.out.println("(2) Create Menu Item");
			System.out.println("(3) Update Menu Item");
			System.out.println("(4) Remove Menu Item");
			System.out.println("(0) Back");
			System.out.print("Enter Choice: ");
			choice = sc.nextInt();
			
			switch(choice)
			{
				case 1:
					alacartecontroller.printAlacarteMenu();
					break;
				case 2:
					System.out.print("Enter Dish Name: ");
					String dishname = sc.next();
					System.out.print("Enter Description: ");
					String dishdesc = sc.next();
					System.out.print("Enter Price: ");
					String dishprice = sc.next();
					
					alacartecontroller.createAlacarteItem(dishname, dishdesc, dishprice);
					break;
				case 3:
					System.out.print("Enter Old Dish Name: ");
					String olddishname = sc.next();
					System.out.print("Enter New Dish Name: ");
					String newdishname = sc.next();
					System.out.print("Enter New Description: ");
					String newdishdesc = sc.next();
					System.out.print("Enter New Price: ");
					String newdishprice = sc.next();
					
					alacartecontroller.updateAlacarteItem(olddishname, newdishname, newdishdesc, newdishprice);
					break;
				case 4:
					System.out.print("Enter Dish Name: ");
					alacartecontroller.deleteAlacarteItem(sc.next());
					break;
				default:
					break;
			}
			
			System.out.println();
		}while(choice != 0);
		
		sc.close();
	}
	
	public static void runCreateUpdateRemovePromo() {
		int choice;
		Scanner sc = new Scanner(System.in);
		PromoPackageController promopackagecontroller = new PromoPackageController();
		
		do {
			System.out.println("(1) View Promo Item");
			System.out.println("(2) Create Promo Item");
			System.out.println("(3) Update Promo Item");
			System.out.println("(4) Remove Promo Item");
			System.out.println("(0) Back");
			System.out.print("Enter Choice: ");
			choice = sc.nextInt();
			
			switch(choice)
			{
				case 1:
					promopackagecontroller.printPromoMenu();
					break;
				case 2:
					System.out.print("Enter Promo Name: ");
					String dishname = sc.next();
					System.out.print("Enter Description: ");
					String dishdesc = sc.next();
					System.out.print("Enter Price: ");
					String dishprice = sc.next();
					System.out.println("=======================");
					
					//call menuitem controller display
					promopackagecontroller.printAlacarteMenu();
					System.out.print("Add alacarte dish(Separate by comma): ");
					String[] dishlist = sc.next().split(",");
					List<Alacarte> ailist = new ArrayList<>();
					for(String s: dishlist)
					{
						ailist.add(promopackagecontroller.getAlacarteItem(s));
					}
					
					System.out.println("=======================");
					promopackagecontroller.createPromoPackage(dishname, dishdesc, dishprice, ailist);
					break;
				case 3:
					System.out.print("Enter Old Promo Name: ");
					String olddishname = sc.next();
					System.out.print("Enter New Promo Name: ");
					String newdishname = sc.next();
					System.out.print("Enter New Description: ");
					String newdishdesc = sc.next();
					System.out.print("Enter New Price: ");
					String newdishprice = sc.next();
					System.out.println("=======================");
					
					//call menuitem controller display
					promopackagecontroller.printAlacarteMenu();
					System.out.print("Add alacarte dish(Separate by comma): ");
					dishlist = sc.next().split(",");
					ailist = new ArrayList<>();
					for(String s: dishlist)
					{
						ailist.add(promopackagecontroller.getAlacarteItem(s));
					}
					
					System.out.println("=======================");
					
					promopackagecontroller.updatePromoPackage(olddishname, newdishname, newdishdesc, newdishprice, ailist);
					break;
				case 4:
					System.out.print("Enter Dish Name: ");
					promopackagecontroller.deleteAlacarteItem(sc.next());
					break;
				default:
					break;
			}
			
			System.out.println();
		}while(choice != 0);
		
		sc.close();
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

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class RRPSS_App {
	
	static OrderController orderController;
	static StaffController staffController;
	static TableController tableController;
	static CategoryController categoryController;
	static PromoPackageController promopackagecontroller;
	static AlacarteController alacartecontroller;
	static SaleRevenue salerevenue;
	
	static ArrayList<Discount> membershipDiscount = new ArrayList<Discount>();
	static ArrayList<Discount> couponDiscount = new ArrayList<Discount>();
	static ArrayList<Tax> taxList = new ArrayList<Tax>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		orderController = new OrderController();
		staffController = new StaffController();
		tableController = new TableController();
		categoryController = new CategoryController();
		alacartecontroller = new AlacarteController();
		promopackagecontroller = new PromoPackageController();
		salerevenue = new SaleRevenue();
		
		int choice;
		

		System.out.println("Restaurant Reservation and Point of Sale System (RRPSS)");
		System.out.println("========================================================");
		
		ArrayList<Order> orderlist = new ArrayList<Order>();
		Table[] tables = new Table[10];
		for(int i = 0; i < tables.length; i++)
			tables[i] = new Table(i+1);
		int ordernum = 1;
		Scanner sc = new Scanner(System.in);
		
		do{
			System.out.println("(1) View/Create/Update/Remove Menu Item");
			System.out.println("(2) View/Create/Update/Remove Promotion");
			System.out.println("(3) Order");
			System.out.println("(4) Create Reservation Booking");
			System.out.println("(5) Check/Remove Reservation Booking");
			System.out.println("(6) Check Table Availability");
			System.out.println("(7) Print Sale Revenue Report By Period");
			System.out.println("(8) Exit");
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
				orderController.OrderMenu();
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				salerevenue.generateNewSalesReport(sc, orderController.getAllOrders(), alacartecontroller.getAllAlacarteItems(), promopackagecontroller.getAllPromoItems());
				break;
			case 8:
				System.exit(0);
				break;
			default:
				System.out.println("\nInvalid choice.\n");
				break;	
			}
		}while(choice!=0);
	}
	
	public static void runCreateUpdateRemoveAlacarte() {
		int choice;
		Scanner sc = new Scanner(System.in);
		try {
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
					case 0:
						return;
					default:
						break;
				}
				
				System.out.println();
			}while(choice != 0);
		}
		catch(Exception ex){
			System.out.println("Error: Invalid Input. Please Try Again.");
		}

	}
	
	public static void runCreateUpdateRemovePromo() {
		int choice;
		Scanner sc = new Scanner(System.in);
		//try {
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
						System.out.println("Enter Promo Name: ");
						String dishname = sc.next();
						System.out.println("Promo:" + dishname);
						System.out.println("Enter Description: ");
						String dishdesc = sc.next();
						System.out.println("Desc:" + dishdesc);
						
						System.out.println("Enter Price: ");
						String dishprice = sc.next();
						System.out.println("Promo:" + dishprice);
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
					case 0:
						return;
					default:
						break;
				}
				
				System.out.println();
			} while(choice != 0);

		//}
		//catch(Exception ex){
		//	System.out.println("Error: Invalid Input. Please Try Again.");
		//}
	}
	
	private void GenerateTax() {
		Tax gst = new Tax("GST",7);
		Tax serviceCharge = new Tax("Service Charge",15);
		taxList.add(gst);
		taxList.add(serviceCharge);
	}
	private void GenerateDiscount() {
		GenerateMembership();
		GenerateCoupon();
	}
	private void GenerateMembership() {
		Discount weiling = new Membership(0.15,"Regular Membership", "0001", "Weiling Wu");
		Discount delon = new Membership(0.15,"Regular Membership","0002", "Delon Lee");
		Discount eugene = new Membership(0.15,"Regular Membership","0003", "Eugene Sow");
		Discount jengkit = new Membership(0.15,"Regular Membership","0004", "Jeng Kit");
		
		membershipDiscount.add(weiling);
		membershipDiscount.add(delon);
		membershipDiscount.add(eugene);
		membershipDiscount.add(jengkit);
	}
	private void GenerateCoupon() {
		Discount coupon5 = new DiscountCoupon(5,"$5 OFF Coupon",false);
		Discount coupon10 = new DiscountCoupon(10,"$10 OFF Coupon",false);
		Discount coupon15OFF = new DiscountCoupon(0.05,"15% OFF Coupon",true);
		
		couponDiscount.add(coupon5);
		couponDiscount.add(coupon10);
		couponDiscount.add(coupon15OFF);
	}



	

	


}

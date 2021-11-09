import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class RRPSS_App {
	
	static OrderController orderController;
	static StaffController staffController;
	static TableController tableController;
	static MenuItemController menuItemController;
	
	static ReservationController reservationController = new ReservationController();
	static ArrayList<Discount> membershipDiscount = new ArrayList<Discount>();
	static ArrayList<Discount> couponDiscount = new ArrayList<Discount>();
	static ArrayList<Tax> taxList = new ArrayList<Tax>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		orderController = new OrderController();
		staffController = new StaffController();
		tableController = new TableController();
		menuItemController = new MenuItemController(); 
		GenerateDiscount();
		GenerateTax();
		int choice;
		Scanner sc = new Scanner(System.in);

		System.out.println("Restaurant Reservation and Point of Sale System (RRPSS)");
		System.out.println("========================================================");
		int ordernum = 1;
		
		do{
			System.out.println("(1) View/Create/Update/Remove Menu Item");
			System.out.println("(2) View/Create/Update/Remove Promotion");
			System.out.println("(3) Create order");
			System.out.println("(4) View order");
			System.out.println("(5) Add/Remove order item/s to/from order");
			System.out.println("(6) Create Reservation Booking"); //
			System.out.println("(7) Check/Remove Reservation Booking"); //
			System.out.println("(8) Check Table Availability");
			System.out.println("(9) Print order invoice");
			System.out.println("(10) Print Sale Revenue Report By Period");
			System.out.println("(0) Exit");
			System.out.print("Enter Choice: ");
			choice = sc.nextInt();

			switch(choice)
			{
			case 1:
				menuItemController.runCreateUpdateRemoveAlacarte();
				break;
			case 2:
				menuItemController.runCreateUpdateRemovePromo();
				break;
			case 3:	
				orderController.OpenOrder();
				break;
			case 4:
				orderController.ViewOrder();
				break;
			case 5:
				orderController.UpdateOrder();
				break;
			case 6:
				reservationController.getDetails(tableController);
				break;
			case 7:
				reservationController.checkRemoveReservation();
				break;
			case 8:
				tableController.ViewTable();
				break;
			case 9:
				orderController.CloseOrder(taxList, membershipDiscount, couponDiscount);
				break;
			case 10:
				break;
			case 0:
				System.exit(0);
				break;
			default:
				System.out.println("\nInvalid choice.\n");
				break;	
			}
		}while(choice!=0);
	}
	
	
	
	private static void GenerateTax() {
		Tax gst = new Tax("GST",7);
		Tax serviceCharge = new Tax("Svr Chrg",15);
		taxList.add(gst);
		taxList.add(serviceCharge);
	}
	private static void GenerateDiscount() {
		GenerateMembership();
		GenerateCoupon();
	}
	private static void GenerateMembership() {
		Discount weiling = new Membership(0.15,"Regular Membership", "0001", "Weiling Wu");
		Discount delon = new Membership(0.15,"Regular Membership","0002", "Delon Lee");
		Discount eugene = new Membership(0.15,"Regular Membership","0003", "Eugene Sow");
		Discount jengkit = new Membership(0.15,"Regular Membership","0004", "Jeng Kit");
		
		membershipDiscount.add(weiling);
		membershipDiscount.add(delon);
		membershipDiscount.add(eugene);
		membershipDiscount.add(jengkit);
	}
	private static void GenerateCoupon() {
		Discount coupon5 = new DiscountCoupon(5,"$5 OFF",false);
		Discount coupon10 = new DiscountCoupon(10,"$10 OFF",false);
		Discount coupon15OFF = new DiscountCoupon(0.05,"5% OFF",true);
		
		couponDiscount.add(coupon5);
		couponDiscount.add(coupon10);
		couponDiscount.add(coupon15OFF);
	}



	

	


}

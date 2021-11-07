import java.time.LocalDateTime;
import java.util.*;

public class OrderController {
	static Map<Integer, Order> orderMap = new HashMap<Integer, Order>();
	static ViewOrderController viewOC;
	static UpdateOrderController updateOC;
	static ClosedOrderController closedOC;
	static OpenOrderController openOC;
	
	ArrayList<Discount> membershipDiscount = new ArrayList<Discount>();
	ArrayList<Discount> couponDiscount = new ArrayList<Discount>();
	ArrayList<Tax> taxList = new ArrayList<Tax>();
	
	public OrderController() 
	{
		viewOC = new ViewOrderController();
		updateOC = new UpdateOrderController();
		closedOC = new ClosedOrderController();
		openOC = new OpenOrderController();
	}	
	public static void PrintInvoice(Order order)
	{
		Invoice invoice = new Invoice(order);
		invoice.printInvoice();
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

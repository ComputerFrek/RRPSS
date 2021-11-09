import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice {
	
	public static void PrintInvoice(Order order)
	{
		System.out.println();
		System.out.println("----------------------------------------");
		printHeader(order);
		System.out.println("----------------------------------------");
		System.out.println("QTY \tITEM \t\t\tAMT ");
		System.out.println("----------------------------------------");
		printMenuItem(order);
		System.out.println("----------------------------------------");
		printSubTotal(order);
		System.out.println("----------------------------------------");
		printTotal(order);
		System.out.println();
		
	}
	private static void printHeader(Order order){
		System.out.printf("Server: %s\t\t Date:%s \r\n",order.getStaff().getName() ,ProduceDate(order.getEndTimeStamp()));
		System.out.printf("Table: %d\t\t Time:%s \r\n",order.getTable().getTableID() ,ProduceTime(order.getEndTimeStamp()));
	}
	private static void printMenuItem(Order order) {
		for (String item : order.getOrderItems().keySet())
        {
			String menuItem = item;
			int qty = order.getOrderItem(menuItem).getQuantity();
            double itemSubTotal = order.getOrderItem(menuItem).getSubTotal();
            System.out.printf("%d \t%-15s \t%.2f \r\n",qty, menuItem, itemSubTotal);
        }
	}
	private static void printSubTotal(Order order) {
		System.out.printf("  SUB-TOTAL:\t\t\t%.2f \r\n", order.getSubtotal());
		printTaxes(order);
		printDiscount(order);
	}
	private static void printTotal(Order order) {
		System.out.printf("  TOTAL:\t\t\t%.2f \r\n",order.getTotal());
	}
	private static void printTaxes(Order order) {
		for(TaxOrder tO : order.getTaxOrders())
		{
			Tax t = tO.getTax();
			double taxPrice = tO.getTaxPrice();
			System.out.printf("  %d %s:\t\t\t%.2f \r\n",(int)t.taxPercentage, t.taxName, taxPrice);
		}
	}
	private static void printDiscount(Order order) {
		if(order.getDiscount() == null)
			return;
		DiscountOrder dO = order.getDiscount();
		Discount d = dO.getDiscount();
		double discountPrice = dO.getDiscountPrice();
		
		if (d instanceof Membership)
		{
			Membership m = (Membership)d;
			String name = m.membershipName;
			System.out.printf("  MEMBER:\t\t\t%s \r\n", name);
		}
		else if(d instanceof DiscountCoupon)
		{
			DiscountCoupon c = (DiscountCoupon)d;
			String couponName = c.getDescription();
			System.out.printf("  COUPON:\t\t\t%s \r\n", couponName);
		}
		System.out.printf("  DISCOUNT:\t\t\t-%.2f \r\n", discountPrice);
	}

	private static String ProduceDate(LocalDateTime timeStamp) {
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyy");
		return timeStamp.format(formatDate);
	}
	private static String ProduceTime(LocalDateTime timeStamp) {
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("HH:mm");
		return timeStamp.format(formatDate);
	}

}

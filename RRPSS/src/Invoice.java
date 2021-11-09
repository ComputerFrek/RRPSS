import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice {
	
	private Order order;
	

	public Invoice(Order order) {
		this.order = order;
	}
	
	
	public void printInvoice()
	{
		System.out.println();
		System.out.println("----------------------------------------");
		printHeader();
		System.out.println("----------------------------------------");
		System.out.println("QTY \tITEM \t\t\tAMT ");
		System.out.println("----------------------------------------");
		printMenuItem();
		System.out.println("----------------------------------------");
		printSubTotal();
		System.out.println("----------------------------------------");
		printTotal();
		System.out.println();
		
	}
	private void printHeader(){
		System.out.printf("Server: %s\t\t Date:%s \r\n",order.getStaff().getName() ,ProduceDate(order.getEndTimeStamp()));
		System.out.printf("Table: %d\t\t Time:%s \r\n",order.getTable().getTableID() ,ProduceTime(order.getEndTimeStamp()));
	}
	private void printMenuItem() {
		for (String item : order.getOrderItems().keySet())
        {
			String menuItem = item;
			int qty = order.getOrderItem(menuItem).getQuantity();
            double itemSubTotal = order.getOrderItem(menuItem).getSubTotal();
            System.out.printf("%d \t%-15s \t%.2f \r\n",qty, menuItem, itemSubTotal);
        }
	}
	private void printSubTotal() {
		System.out.printf("  SUB-TOTAL:\t\t\t%.2f \r\n", order.getSubtotal());
		printTaxes();
		printDiscount();
	}
	private void printTotal() {
		System.out.printf("  TOTAL:\t\t\t%.2f \r\n",order.getTotal());
	}
	private void printTaxes() {
		for(TaxOrder tO : order.getTaxOrders())
		{
			Tax t = tO.getTax();
			double taxPrice = tO.getTaxPrice();
			System.out.printf("  %d %s:\t\t\t%.2f \r\n",(int)t.taxPercentage, t.taxName, taxPrice);
		}
	}
	private void printDiscount() {
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

	private String ProduceDate(LocalDateTime timeStamp) {
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyy");
		return timeStamp.format(formatDate);
	}
	private String ProduceTime(LocalDateTime timeStamp) {
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("HH:mm");
		return timeStamp.format(formatDate);
	}

}

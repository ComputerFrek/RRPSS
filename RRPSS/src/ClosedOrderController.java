import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ClosedOrderController implements iClosedOrder, iDiscount{

	private OrderController oC;

	public ClosedOrderController(OrderController oC) {
		this.oC = oC;
	}
	
	@Override
	public boolean CloseOrder(Order order, ArrayList<Tax> taxList, ArrayList<Discount> membershipList, ArrayList<Discount> couponList) {
		// TODO Auto-generated method stub	
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.print("Are You Sure? (Y/N): ");
		String choice = sc.next();
		
		System.out.println();
		if(!choice.trim().toLowerCase().equals("y"))
			return false;;

		order.setEndTimeStamp(LocalDateTime.now()); // Closed Order with DateTime
		if(order.getEndTimeStamp() != null)
		{
			order.setSubTotal(CalculateSubTotal(order)); // Update Order Bill SubTotal
			double discountTotal = CalculateDiscount(order, membershipList, couponList);
			double taxTotal = CalculateTax(order, taxList);
			
			double total = order.getSubtotal() - discountTotal + taxTotal;
			if(total < 0)
				total = 0;
			order.setTotal(total);
			order.getTable().setOccupied(false);
			System.out.printf("Order %03d Closed @ %s \r\n", order.getOrderID(), ConvertLocalToString(order.getEndTimeStamp()));
			return true;
		}
		else
		{
			System.out.printf("Order %03d was not closed, Please Try Again \r\n");
			return false;
		}
	}
	private String ConvertLocalToString(LocalDateTime timeStamp) {
		if(timeStamp == null)
			return "NIL";
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm");
		return timeStamp.format(formatDate);
	}

	@Override
	public double CalculateTax(Order order, ArrayList<Tax> taxList) {
		double totalTax = 0;
		for(int i = 0; i < taxList.size(); i++)
		{
			Tax tax = taxList.get(i);
			double taxPrice = tax.calculateTax(order);
			order.addTaxOrder(tax, taxPrice);
			totalTax += taxPrice;
		}
		return totalTax;
	}

	@Override
	public double CalculateSubTotal(Order order) {
		// TODO Auto-generated method stub
		double subTotalBill = 0;
		// Calculate Items SubTotal
		for (String item : order.getOrderItems().keySet())
        {
			String keyItem = item;
			MenuItem menuItem = order.getOrderItem(keyItem).getMenuItem();
			int qty = order.getOrderItem(keyItem).getQuantity();
			
            double subTotalItem = menuItem.getPrice() * qty;
            order.getOrderItem(keyItem).setSubTotal(subTotalItem);
            subTotalBill += subTotalItem;
        }
		return subTotalBill;
	}

	@Override
	public double CalculateDiscount(Order order, ArrayList<Discount> membershipList, ArrayList<Discount> couponList) {
		Scanner sc = new Scanner(System.in);
		Discount discount = null;
		
		System.out.print("Apply Discount?(Y/N): ");
		String isApply = sc.nextLine();
		if(isApply.toLowerCase().equals("n"))
			return 0;
		
		PrintDiscountOption();
		// Retrieve the Discount Details(Membership or Coupon)
		System.out.print("Enter Choice: ");
		int choice = sc.nextInt();
		switch(choice) {
			case 1:
				discount = CalculateMembershipDiscount(membershipList);
				break;
			case 2:
				discount = CalculateCouponDiscount(couponList);
				break;
			default:
				System.out.println("Invalid Discount Choice. Discount was not Applied");
				return -1;
		}
		if(discount == null)
		{
			System.out.println("Discount was not apply");
			return 0;
		}
		System.out.printf("%s Discount Applied \r\n", discount.getDescription());
		DiscountOrder discountOrder = new DiscountOrder();
		discountOrder.setDiscount(discount);
		discountOrder.setDiscountPrice(discount.CalulcateDiscount(order.getSubtotal()));
		order.setDiscount(discountOrder);
		
		return discountOrder.getDiscountPrice();
	}
	
	private void PrintDiscountOption() {
		System.out.println("Discount Type: ");
		System.out.println("(1) Membership: ");
		System.out.println("(2) Coupon: ");
		System.out.println("(3) Exit ");
	}

	@Override
	public Discount CalculateMembershipDiscount(ArrayList<Discount> membershipDiscount) {
		Scanner sc = new Scanner(System.in);
		String membershipID = "";
		Discount discount = null;
		
		while(discount == null)
		{
			System.out.print("Enter MembershipID: ");
			membershipID = sc.nextLine();
			discount = CheckMembershipStatus(membershipID, membershipDiscount);
			
			if(discount == null)
			{
				System.out.println("Could not find Membership Details.");
				System.out.println("Do You want To Retype MembershipID?:(Y/N)");
				if(sc.nextLine().trim().toLowerCase().equals("y"))
					continue;
				return null;
			}
		}
		return discount;
	}

	@Override
	public Discount CheckMembershipStatus(String membershipID, ArrayList<Discount> membershipDiscount) {
		// TODO Auto-generated method stub
		for(int i = 0; i < membershipDiscount.size(); i++)
		{
			if (membershipDiscount.get(i) instanceof Membership)
			{
				Membership m = (Membership)membershipDiscount.get(i);
				if(m.membershipID.equals(membershipID))
					return membershipDiscount.get(i);
			}
		}
		return null;
	}

	@Override
	public Discount CalculateCouponDiscount(ArrayList<Discount> couponDiscount) {
		Scanner sc = new Scanner(System.in);
		Discount discount = null;
		int couponIndex = -1;
		DisplayCoupons(couponDiscount);

		while(discount == null)
		{
			System.out.print("Choose Coupon: ");
			couponIndex = sc.nextInt();
			if(couponIndex <= 0 || couponIndex > couponDiscount.size())
			{
				System.out.println("Could not find Coupon Details.");
				System.out.println("Retype Coupon?: ");
				String retyp =  sc.next();
				if(retyp.trim().toLowerCase().equals("y"))
					continue;
				return null;
			}
			discount = couponDiscount.get(couponIndex - 1);
		}
		return discount;
	}

	@Override
	public void DisplayCoupons(ArrayList<Discount> couponDiscount) {
		System.out.println("No. \tDescription");
		for(int i = 0; i < couponDiscount.size(); i++)
		{
			if (couponDiscount.get(i) instanceof DiscountCoupon)
			{
				DiscountCoupon c = (DiscountCoupon)couponDiscount.get(i);
				System.out.printf("%d. \t%s \r\n",i+1,c.getDescription());
			}
			
		}
	}

}

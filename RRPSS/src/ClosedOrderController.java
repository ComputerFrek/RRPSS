import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ClosedOrderController implements iClosedOrder, iDiscount{

	@Override
	public void CloseOrder(Order order, ArrayList<Tax> taxList, ArrayList<Discount> membershipDiscount) {
		// TODO Auto-generated method stub
		order.setEndTimeStamp(LocalDateTime.now()); // Closed Order with DateTime
		order.setSubTotal(CalculateSubTotal(order)); // Update Order Bill SubTotal
		double discountTotal = CalculateDiscount(order, membershipDiscount);
		double taxTotal = CalculateTax(order, taxList);
		
		double total = order.getSubtotal() - discountTotal + taxTotal;
		order.setTotal(total);
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
	public double CalculateDiscount(Order order, ArrayList<Discount> membershipDiscount) {
		Scanner sc = new Scanner(System.in);
		Discount discount = null;
		
		System.out.println("Apply Discount?(Y/N): ");
		String isApply = sc.nextLine();
		if(isApply.equalsIgnoreCase("N"))
			return 0;
		
		PrintDiscountOption();
		// Retrieve the Discount Details(Membership or Coupon)
		int choice = sc.nextInt();
		switch(choice) {
			case 1:
				discount = CalculateMembershipDiscount(membershipDiscount);
				break;
			case 2:
				discount = CalculateCouponDiscount(membershipDiscount);
				break;
			default:
				System.out.println("Invalid Discount Choice. Discount was not Applied");
				return -1;
		}

		DiscountOrder discountOrder = new DiscountOrder();
		discountOrder.setDiscount(discount);
		discountOrder.setDiscountPrice(discount.CalulcateDiscount(order.getSubtotal()));
		order.setDiscount(discountOrder);
		
		return discountOrder.getDiscountPrice();
	}
	
	private void PrintDiscountOption() {
		System.out.println("Select Discount Type: ");
		System.out.println("1) Membership: ");
		System.out.println("2) Coupon: ");
		System.out.println("3) Exit ");
	}

	@Override
	public Discount CalculateMembershipDiscount(ArrayList<Discount> membershipDiscount) {
		Scanner sc = new Scanner(System.in);
		String membershipID = "";
		Discount discount = null;
		
		while(discount == null)
		{
			System.out.println("Enter MembershipID: ");
			membershipID = sc.nextLine();
			discount = CheckMembershipStatus(membershipID, membershipDiscount);
			
			if(discount == null)
			{
				System.out.println("Could not find Membership Details.");
				System.out.println("Retype MembershipID?: ");
				if(sc.nextLine() == "Y")
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
			Membership m = (Membership)membershipDiscount.get(i);
			if(m.membershipID == membershipID)
				return membershipDiscount.get(i);
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
			System.out.println("Choose Coupon: ");
			couponIndex = sc.nextInt();
			if(couponIndex < 0 || couponDiscount.size() > couponIndex)
			{
				System.out.println("Could not find Membership Details.");
				System.out.println("Retype Coupon?: ");
				if(sc.nextLine() == "Y")
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
			DiscountCoupon c = (DiscountCoupon)couponDiscount.get(i);
			System.out.printf("%d. \t%s",i,c.getDescription());
		}
	}

}

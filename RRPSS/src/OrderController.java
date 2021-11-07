import java.time.LocalDateTime;
import java.util.*;

public class OrderController {
	static Map<Integer, Order> orderMap = new HashMap<Integer, Order>();
	public OrderController() 
	{
		
	}
	
	public void ViewOrder()
	{
		
	}
	public void ViewAllOrder()
	{
		int count = 1;
		System.out.println("Order List: ");
		System.out.println("No. \tOrder ID \tStaff By");
        for (int orderID : orderMap.keySet())
        {
            Order order = orderMap.get(orderID);
            System.out.printf("%d \t%s \t%s",count, orderID, order.getStaff());
            count++;
        }

	}
		
	public void ViewCloseOrder()
	{
		int count = 1;
		System.out.println("Order List: ");
		System.out.println("No. \tOrder ID \tStaff By");
        for (int orderID : orderMap.keySet())
        {
            Order order = orderMap.get(orderID);
            if(order.getEndTimeStamp() != null)
            {
            	System.out.printf("%d \t%s \t%s",count, orderID, order.getStaff());
                count++;
            }
        }

		
	}
	public void ViewOpenOrder()
	{
		int count = 1;
		System.out.println("Order List: ");
		System.out.println("No. \tOrder ID \tStaff By");
        for (int orderID : orderMap.keySet())
        {
            Order order = orderMap.get(orderID);
            if(order.getEndTimeStamp() == null)
            {
            	System.out.printf("%d \t%s \t%s",count, orderID, order.getStaff());
                count++;
            }
        }
	}
	public void ViewMoreDetail(String orderID) {
		Scanner sc = new Scanner(System.in);
		
		Order order = orderMap.get(orderID);
		System.out.printf("Order Details: ");
		System.out.printf("Order ID: /t%d", order.getOrderID());
		System.out.printf("Table ID: /t%d", order.getTable().getTableID());
		System.out.printf("Staff Name: /t%d", order.getStaff().getName());
		System.out.printf("Start Date/Time: /t%s", order.getStartTimeStamp());
		System.out.printf("End Date/Time: /t%s", order.getEndTimeStamp());
		System.out.printf("Number of Pax: /t%s", order.getNoOfPax());
		System.out.printf("View Order Items(Y/N) ?");
		if(sc.nextLine() == "Y")
			ShowAllOrderItems(order);
	}
	
	public static void CreateOrder() {
		Scanner sc = new Scanner(System.in);
		int noOfPax, tableSelected;
		Staff staff;
		Table table = null;

		RRPSS_App.staffController.ViewStaff();
		while(staff == null) {
			staff = RRPSS_App.staffController.SelectStaff();
		}
		
		System.out.println("No of Pax?: ");
		noOfPax = sc.nextInt();
		System.out.println("Choose A Table: ");
		RRPSS_App.tableController.ShowRecommendTable(noOfPax);
		RRPSS_App.tableController.ViewTable();
		
		while(table == null)
		{
			System.out.println("Select One Of The Table Above: ");
			tableSelected = sc.nextInt();
			table = RRPSS_App.tableController.SelectTable(tableSelected);
		}

		
		Order newOrder = addOrder(orderMap.size(), noOfPax, table, staff);
		if(newOrder != null)
		{
			// Show Message
		}
	}
	public static Order addOrder(int orderID, int noOfPax, Table selectedTable, Staff createdStaff) {
		Order newOrder = new Order(orderID, noOfPax, selectedTable, createdStaff);
		return orderMap.put(orderID, newOrder);
	}
	
	public static void AddOrderItem(Order order) {
		Scanner sc = new Scanner(System.in);
		String categoryInput = "";
		String menuInput = "";
		Category category;
		MenuItem menuItem;
		OrderItems orderItem; 
		while((category = CategoryController.GetCategory(categoryInput)) == null)
		{
			System.out.println("Choose a Category: ");
			RRPSS_App.categoryController.ViewCategory();
			System.out.println("Select a Category: ");
			categoryInput = sc.nextLine();
			if(categoryInput == "exit")
				return;
		}
		while((menuItem = CategoryController.GetMenuItems(category, menuInput)) == null)
		{
			System.out.printf("%s:Menu Item: ", categoryInput);
			CategoryController.ViewMenuList(categoryInput);
			System.out.println("Select an Item: ");
			menuInput = sc.nextLine();
			if(menuInput == "exit")
				return;
		}
		// Show Menu Item Description and Price
		System.out.printf("Menu Item Selected:\n"
				+ "%s \n"
				+ "%s \n"
				+ "%.2f", 
				menuItem.getItemName(), menuItem.getDescription(), menuItem.getPrice());

		if((orderItem = order.getOrderItem(menuItem.getItemName())) != null)
			System.out.printf("Existing Quantity: %d", orderItem.getQuantity());
		System.out.println("Enter Desired Quantity: ");	// Do You want do it by addition or replace the value
		orderItem.setQuantity(sc.nextInt());
		
		System.out.printf("Item: %s was added into Order List - QTY: %d", menuItem.getItemName(), orderItem.getQuantity());	
	}
	public static void RemoveOrderItem(Order order) {
		Scanner sc = new Scanner(System.in);
		OrderItems orderItem;
		String itemInput;
		int quantityRemove, newQuantity;
				
		ShowAllOrderItems(order);
		System.out.println("Select an Item: ");
		itemInput = sc.nextLine();
		orderItem = order.getOrderItem(itemInput);
		if(orderItem == null)
			return;

		// Show Item Selected
		System.out.printf("Item Selected:\n"
				+ "Name: %s \n"
				+ "Quantity: %d \n", 
				orderItem.getMenuItem().getItemName(), orderItem.getQuantity());
		
		// Check Quantity
		quantityRemove = sc.nextInt();
		newQuantity = orderItem.getQuantity() - quantityRemove;
		// Remove Item
		if(newQuantity <= 0)
		{
			order.removeOrderItems(itemInput);
			System.out.printf("Item: %s has been removered from Order List", itemInput);
		}
		else
		{
			orderItem.setQuantity(newQuantity);
			System.out.printf("%d has been removed from %s", quantityRemove, itemInput);
		}
	}
	public static void ShowAllOrderItems(Order order)
	{
		int count = 1;
        for (String menuItem : order.getOrderItems().keySet())
        {
            OrderItems orderItem = order.getOrderItems().get(menuItem);
            System.out.printf("%d | %s",count, orderItem.getMenuItem().getItemName());
            count++;
        }
	}
	
	public void CloseOrder(Order order) {
		order.setEndTimeStamp(LocalDateTime.now()); // Closed Order with DateTime
		order.setSubTotal(CalculateSubTotal(order)); // Update Order Bill SubTotal
		double discountTotal = CalculateDiscount(order);
		double taxTotal = CalculateTax(order);
		
		double total = order.getSubtotal() - discountTotal + taxTotal;
		order.setTotal(total);
		
	}
	private double CalculateTax(Order order) 
	{
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
	ArrayList<Tax> taxList = new ArrayList<Tax>();
	private void GenerateTax() {
		Tax gst = new Tax("GST",7);
		Tax serviceCharge = new Tax("Service Charge",15);
		taxList.add(gst);
		taxList.add(serviceCharge);
	}
	private double CalculateDiscount(Order order) {
		Scanner sc = new Scanner(System.in);
		Discount discount = null;
		
		System.out.println("Apply Discount?(Y/N): ");
		String isApply = sc.nextLine();
		if(isApply == "N")
			return 0;
		
		PrintDiscountOption();
		// Retrieve the Discount Details(Membership or Coupon)
		int choice = sc.nextInt();
		switch(choice) {
			case 1:
				discount = MembershipDiscount();
				break;
			case 2:
				discount = CouponDiscount();
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
	
	
	ArrayList<Discount> membershipDiscount = new ArrayList<Discount>();
	ArrayList<Discount> couponDiscount = new ArrayList<Discount>();
	
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
	private void PrintDiscountOption() {
		System.out.println("Select Discount Type: ");
		System.out.println("1) Membership: ");
		System.out.println("2) Coupon: ");
		System.out.println("3) Exit ");
	}
	private Discount MembershipDiscount() {
		Scanner sc = new Scanner(System.in);
		String membershipID = "";
		Discount discount = null;
		
		while(discount == null)
		{
			System.out.println("Enter MembershipID: ");
			membershipID = sc.nextLine();
			discount = CheckMembershipStatus(membershipID);
			
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
	private Discount CheckMembershipStatus(String membershipID){
		for(int i = 0; i < membershipDiscount.size(); i++)
		{
			Membership m = (Membership)membershipDiscount.get(i);
			if(m.membershipID == membershipID)
				return membershipDiscount.get(i);
		}
		return null;
	}
	
	private Discount CouponDiscount() {
		Scanner sc = new Scanner(System.in);
		Discount discount = null;
		int couponIndex = -1;
		DisplayCoupon();

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
	private void DisplayCoupon() {
		System.out.println("No. \tDescription");
		for(int i = 0; i < couponDiscount.size(); i++)
		{
			DiscountCoupon c = (DiscountCoupon)couponDiscount.get(i);
			System.out.printf("%d. \t%s",i,c.getDescription());
		}
	}

	private double CalculateSubTotal(Order order) {
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
	
	public static void printInvoice(Order order)
	{
		Invoice invoice = new Invoice(order);
		invoice.printInvoice();
	}
	

	

}

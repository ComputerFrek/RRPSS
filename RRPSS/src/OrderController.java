import java.time.LocalDateTime;
import java.util.*;

public class OrderController {
	Map<Integer, Order> orderMap = new HashMap<Integer, Order>();
	ViewOrderController viewOC;
	UpdateOrderController updateOC;
	ClosedOrderController closedOC;
	OpenOrderController openOC;
	
	public OrderController() 
	{
		viewOC = new ViewOrderController(this);
		updateOC = new UpdateOrderController(this);
		closedOC = new ClosedOrderController(this);
		openOC = new OpenOrderController(this);
	}	
	
	public void OrderMenu() {

		Scanner sc = new Scanner(System.in);
		int choice = -1;
		
		try {
			
			do
			{
				System.out.println();
				
				System.out.println("(1) View Orders");
				System.out.println("(2) Open Order");
				System.out.println("(3) Close Order");
				System.out.println("(4) Add/Remove Order Items");
				System.out.println("(0) Exit");
				System.out.print("Enter Choice: ");
				choice = sc.nextInt();
				
				switch(choice) {
					case 1:
						viewOC.ViewOrderMenu(orderMap);
						break;
					case 2:
						OpenOrder();
						break;
					case 3:
						CloseOrder(RRPSS_App.taxList, RRPSS_App.membershipDiscount, RRPSS_App.couponDiscount);
						break;
					case 4:
						UpdateOrder();
						break;
					case 0:
						break;
					default:
						break;
				}
			} while(choice != 0);
		}
		catch(Exception ex)
		{
			System.out.println("Error: Invalid Input. Please Try Again.");
		}
	}
	
	public void PrintInvoice(Order order)
	{
		Scanner sc = new Scanner(System.in);
		String choice = "";
		System.out.println();
		System.out.print("Print Invoice? (Y/N): ");
		choice = sc.next();
		if(!choice.trim().toLowerCase().equals("y"))
			return;
		
		Invoice invoice = new Invoice(order);
		invoice.printInvoice();
	}
	public Order SelectOrder(int orderID) {
		if(!orderMap.containsKey(orderID))
			return null;
		return orderMap.get(orderID);
	}
	
	public void UpdateOrder() {
		
		Order order = SelectionOrder();
		if(order == null)
			return;
		updateOC.UpdateOrderMenu(order);
	}
	public void OpenOrder() {
		openOC.OpenOrder(orderMap);
	}
	public void CloseOrder(ArrayList<Tax> taxList, ArrayList<Discount> membershipList, ArrayList<Discount> couponList) {
		Order order = SelectionOrder();
		
		if(order == null)
			return;
		if(closedOC.CloseOrder(order, taxList, membershipList,couponList))
			PrintInvoice(order);
	}
	public Order SelectionOrder() {
		
		int orderCount = viewOC.ViewExistingOrder(orderMap);
		if(orderCount <=1)
			return null;
		
		Order order = viewOC.SelectOrder();
		
		if(order == null || order.getEndTimeStamp() != null)
		{	
			System.out.println();
			System.out.println("Invalid Input: Either Order has been closed or it does not exist.");
			return null;
		}
		return order;
	}


	

	

}

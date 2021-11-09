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
		closedOC = new ClosedOrderController();
		openOC = new OpenOrderController();
	}	
	
	public void OrderMenu() {

		Scanner sc = new Scanner(System.in);
		int choice = -1;
		
		try {
			
			do
			{
				System.out.println("(1) View Orders");
				System.out.println("(2) Open Order");
				System.out.println("(3) Close Order");
				System.out.println("(4) Add/Remove Order Items");
				System.out.println("(0) Exit");
				
				choice = sc.nextInt();
				
				switch(choice) {
					case 1:
						viewOC.ViewOrderMenu(orderMap);
						break;
					case 2:
						OpenOrder();
						break;
					case 3:
						CloseOrder(RRPSS_App.taxList, RRPSS_App.membershipDiscount);
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
		Invoice invoice = new Invoice(order);
		invoice.printInvoice();
	}
	public Order SelectOrder(int orderID) {
		if(!orderMap.containsKey(orderID))
			return null;
		return orderMap.get(orderID);
	}
	
	public void UpdateOrder() {
		viewOC.ViewExistingOrder(orderMap);
		Order order = viewOC.SelectOrder();
		updateOC.UpdateOrderMenu(order);
	}
	public void OpenOrder() {
		openOC.OpenOrder(orderMap);
	}
	public void CloseOrder(ArrayList<Tax> taxList, ArrayList<Discount> membershipDiscount) {
		viewOC.ViewExistingOrder(orderMap);
		Order order = viewOC.SelectOrder();
		closedOC.CloseOrder(order, taxList, membershipDiscount);
	}

	public Map<Integer, Order> getAllOrders()
	{
		return this.orderMap;
	}
	
}

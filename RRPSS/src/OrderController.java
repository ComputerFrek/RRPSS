import java.time.LocalDateTime;
import java.util.*;

public class OrderController {
	static Map<Integer, Order> orderMap = new HashMap<Integer, Order>();
	static ViewOrderController viewOC;
	static UpdateOrderController updateOC;
	static ClosedOrderController closedOC;
	static OpenOrderController openOC;
	
	public OrderController() 
	{
		viewOC = new ViewOrderController(this);
		updateOC = new UpdateOrderController();
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
						break;
					case 3:
						break;
					case 4:
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
	public void ViewAllOrder() {
		viewOC.ViewAllOrder(orderMap);
	}
	public void ViewExistingOrder() {
		viewOC.ViewExistingOrder(orderMap);
	}
	public void ViewClosedOrder() {
		viewOC.ViewClosedOrder(orderMap);
	}
	public Order SelectOrder(int orderID) {
		if(!orderMap.containsKey(orderID))
			return null;
		return orderMap.get(orderID);
	}
	public void AddOrderItem(Order order) {
		updateOC.AddOrderItem(order);
	}
	public void RemoveOrderItem(Order order) {
		updateOC.RemoveOrderItem(order);
	}
	public void OpenOrder() {
		openOC.OpenOrder(orderMap);
	}
	public void CloseOrder(Order order, ArrayList<Tax> taxList, ArrayList<Discount> membershipDiscount) {
		closedOC.CloseOrder(order, taxList, membershipDiscount);
	}


	

	

}

import java.util.Map;
import java.util.Scanner;

public class OpenOrderController implements iOpenOrder{

	private OrderController oC;

	public OpenOrderController(OrderController oC)
	{
		this.oC = oC;
	}
	
	@Override
	public void OpenOrder(Map<Integer, Order> orderMap) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int noOfPax;
		Staff staff = null;
		Table table = null;
		
		staff = StaffSelection();
		noOfPax = NoOfPaxClarification();
		table = TableSelection(noOfPax);
		
		Order newOrder = addOrder(orderMap.size()+1, noOfPax, table, staff, orderMap);
		System.out.println();
		if(newOrder == null)
		{
			System.out.println("Error: Order was not Created");
			return;
		}
		newOrder.getTable().setOccupied(true);
		System.out.printf("Order %03d has been created. Seated at Table %02d \r\n", newOrder.getOrderID(), newOrder.getTable().getTableID());
		
	}
	private Staff StaffSelection() {
		Scanner sc = new Scanner(System.in);
		Staff staff = null;
		System.out.println();
		RRPSS_App.staffController.ViewStaff();
		while(staff == null) {
			System.out.print("Select Your Emp ID: ");
			int staffID = sc.nextInt();
			staff = RRPSS_App.staffController.SelectStaff(staffID);
		}
		return staff;
	}
	private Table TableSelection(int noOfPax) {
		System.out.println();
		System.out.println("Searching for Table...");
		int tableID = RRPSS_App.tableController.CheckTable(noOfPax);
		
		if(tableID == -1)
		{
			System.out.println("No tables currently available.");
			return null;
		}
		Table table = RRPSS_App.tableController.SelectTable(tableID); //TableSelection();
		return table;
	}
	private int NoOfPaxClarification() {
		Scanner sc = new Scanner(System.in);
		int noOfPax = 0;
		
		System.out.println();
		while(noOfPax < 1 || noOfPax > 10)
		{
			System.out.print("No of Pax?: ");
			noOfPax = sc.nextInt();
			if(noOfPax < 1 || noOfPax > 10)
				System.out.println("Invalid No Of Pax - Min: 1 pax, Max: 10 pax");
		}

		return noOfPax;
	}

	@Override
	public Order addOrder(int orderID, int noOfPax, Table selectedTable, Staff createdStaff, Map<Integer, Order> orderMap) {
		Order newOrder = new Order(orderID, noOfPax, selectedTable, createdStaff);
		orderMap.put(orderID, newOrder);
		return newOrder;
	}

}

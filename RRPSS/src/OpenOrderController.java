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
		table = TableSelection();
		
		Order newOrder = addOrder(orderMap.size()+1, noOfPax, table, staff, orderMap);
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
		
		RRPSS_App.staffController.ViewStaff();
		while(staff == null) {
			System.out.print("Select Your Emp ID: ");
			int staffID = sc.nextInt();
			staff = RRPSS_App.staffController.SelectStaff(staffID);
		}
		return staff;
	}
	private Table TableSelection() {
		Scanner sc = new Scanner(System.in);
		Table table = null;
		int tableSelected;
		
		RRPSS_App.tableController.ViewTable();
		System.out.println("Table Selections: ");
		while(table == null)
		{
			System.out.print("Please Select a Table: ");
			tableSelected = sc.nextInt();
			table = RRPSS_App.tableController.SelectTable(tableSelected);
			if(table == null)
				System.out.println("Invalid Table Selection");
		}
		return table;
	}
	private int NoOfPaxClarification() {
		Scanner sc = new Scanner(System.in);
		int noOfPax = 0;
		
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

import java.util.Map;
import java.util.Scanner;

public class OpenOrderController implements iOpenOrder{

	@Override
	public void OpenOrder(Map<Integer, Order> orderMap) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int noOfPax, tableSelected;
		Staff staff = null;
		Table table = null;

		RRPSS_App.staffController.ViewStaff();
		while(staff == null) {
			staff = RRPSS_App.staffController.SelectStaff();
		}
		
		System.out.println("No of Pax?: ");
		noOfPax = sc.nextInt();
		System.out.println("Choose A Table: ");
		
		RRPSS_App.tableController.ViewTable();
		
		while(table == null)
		{
			System.out.println("Select a Table: ");
			tableSelected = sc.nextInt();
			table = RRPSS_App.tableController.SelectTable(tableSelected);
		}

		Order newOrder = addOrder(orderMap.size(), noOfPax, table, staff, orderMap);
		if(newOrder != null)
		{
			System.out.println("Error: Order was not Created");
		}
	}

	@Override
	public Order addOrder(int orderID, int noOfPax, Table selectedTable, Staff createdStaff, Map<Integer, Order> orderMap) {
		Order newOrder = new Order(orderID, noOfPax, selectedTable, createdStaff);
		return orderMap.put(orderID, newOrder);
	}

}

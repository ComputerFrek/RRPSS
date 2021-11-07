

import java.util.Map;
import java.util.Scanner;

public class ViewOrderController implements iViewOrderController{

	@Override
	public void ViewExistingOrder(Map<Integer, Order> orderMap) {
		// TODO Auto-generated method stub
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
	
	@Override
	public void ViewMoreDetails(String orderID, Map<Integer, Order> orderMap) {
		// TODO Auto-generated method stub
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

	@Override
	public void ViewClosedOrder(Map<Integer, Order> orderMap) {
		// TODO Auto-generated method stub
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

	@Override
	public void ViewAllOrder(Map<Integer, Order> orderMap) {
		// TODO Auto-generated method stub
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

	@Override
	public void ShowAllOrderItems(Order order) {
		// TODO Auto-generated method stub
		int count = 1;
        for (String menuItem : order.getOrderItems().keySet())
        {
            OrderItems orderItem = order.getOrderItems().get(menuItem);
            System.out.printf("%d | %s",count, orderItem.getMenuItem().getItemName());
            count++;
        }
	}

}



import java.util.Map;

public interface iViewOrderController {
	void ViewExistingOrder(Map<Integer, Order> orderMap);
	void ViewClosedOrder(Map<Integer, Order> orderMap);
	void ViewAllOrder(Map<Integer, Order> orderMap);
	void ViewMoreDetails(String orderID, Map<Integer, Order> orderMap);
	void ShowAllOrderItems(Order order);
}

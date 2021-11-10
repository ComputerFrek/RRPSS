

import java.util.Map;

public interface iViewOrderController {
	int ViewExistingOrder(Map<Integer, Order> orderMap);
	int ViewClosedOrder(Map<Integer, Order> orderMap);
	int ViewAllOrder(Map<Integer, Order> orderMap);
	void ShowAllOrderItems(Order order);
	void ViewMoreDetails(Order order);
}

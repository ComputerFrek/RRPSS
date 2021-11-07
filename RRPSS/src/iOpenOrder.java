import java.util.Map;

public interface iOpenOrder {
	void OpenOrder(Map<Integer, Order> orderMap);
	Order addOrder(int orderID, int noOfPax, Table selectedTable, Staff createdStaff, Map<Integer, Order> orderMap);
}

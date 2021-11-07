import java.util.ArrayList;

public interface iClosedOrder extends iTaxCalculator{
	void CloseOrder(Order order, ArrayList<Tax> taxList, ArrayList<Discount> membershipDiscount);
	double CalculateSubTotal(Order order);
	
}

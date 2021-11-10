import java.util.ArrayList;

public interface iClosedOrder extends iTaxCalculator{
	boolean CloseOrder(Order order, ArrayList<Tax> taxList, ArrayList<Discount> membershipList, ArrayList<Discount> couponList);
	double CalculateSubTotal(Order order);
	
}

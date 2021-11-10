import java.util.ArrayList;

public interface iDiscountCalculator {
	double CalculateDiscount(Order order, ArrayList<Discount> membershipList, ArrayList<Discount> couponList);
}

import java.util.ArrayList;

public interface iDiscountCalculator {
	double CalculateDiscount(Order order, ArrayList<Discount> membershipDiscount);
}

import java.util.ArrayList;

public interface iTaxCalculator{
	double CalculateTax(Order order, ArrayList<Tax> taxList);
}

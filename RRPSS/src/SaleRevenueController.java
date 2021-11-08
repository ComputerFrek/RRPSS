import java.util.Scanner;

public class SaleRevenueController {
	private SaleRevenue sr;
	private OrderController oc;
	private AlacarteController ac;
	private PromoPackageController ppc;
	
	
	public SaleRevenueController(OrderController oc, AlacarteController ac, PromoPackageController ppc)
	{
		this.oc = oc;
		this.ac = ac;
		this.ppc = ppc;
		
	}
	
	public SaleRevenue createNewSalesReport()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Report ID: ");
		int reportid = sc.nextInt();
		
		System.out.println("Enter Report Name: ");
		String reportname = sc.next();
		
		System.out.println("Generate report by day or month?");
		System.out.println("D - Day, M - Month");
		String dayormonth = sc.next();
		
		boolean booldayormonth = false;
		
		if(dayormonth.equalsIgnoreCase("D"))
		{
			booldayormonth = true;
		} else {
			booldayormonth = false;
		}
		
		sr = new SaleRevenue(reportid, reportname, booldayormonth);
		
		sc.close();
		
		return sr;
	}
	
	
}

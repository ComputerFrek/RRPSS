import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class SaleRevenue {
	private int reportID;
	private String reportName;
	private boolean periodByDay;
	private double totalRevenue;
	private Order[] orderList;
	
	public int getReportID()
	{
		return reportID;
	}
	
	public void setReportID(int reportID)
	{
		this.reportID = reportID;
	}
	
	public String getReportName()
	{
		return reportName;
	}
	
	public void setReportName(String reportName)
	{
		this.reportName = reportName;
	}
	
	public boolean getPeriodByDay()
	{
		return periodByDay;
	}
	
	public void setPeriodByDay(boolean periodByDay)
	{
		this.periodByDay = periodByDay;
	}
	
	public double getTotalRevenue()
	{
		return totalRevenue;
	}
	
	public void setTotalRevenue(double totalRevenue)
	{
		this.totalRevenue = totalRevenue;
	}
	
	public Order[] getOrderList()
	{
		return orderList;
	}
	
	public void setOrderList(Order[] orderList)
	{
		this.orderList = orderList;
	}
	
	public void generateNewSalesReport(Map<Integer, Order> orders, List<Alacarte> alacarteitem, List<PromoPackage> pp)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Report ID: ");
		this.reportID = sc.nextInt();
		
		System.out.println("Enter Report Name: ");
		this.reportName = sc.next();
		
		System.out.println("Generate report by day or month?");
		System.out.println("D - Day, M - Month");
		String dayormonth = sc.next();
		
		String inputdate = "";
		DateTimeFormatter bydaydtf = DateTimeFormatter.ofPattern("dd-MMM");
		DateTimeFormatter bymondtf = DateTimeFormatter.ofPattern("MMM");
		
		if(dayormonth.equalsIgnoreCase("D"))
		{
			this.periodByDay = true;
			System.out.println("Enter Date(E.g. 02-Dec): ");
		} else {
			this.periodByDay = false;
			System.out.println("Enter Month(E.g. Dec): ");
		}
		inputdate = sc.next();
		
		sc.close();
		
		for(Alacarte al: alacarteitem)
		{
			double itemrevenue = 0;
			for(Map.Entry<Integer, Order> e : orders.entrySet())
			{
				Order currentorder = e.getValue();
				
				if(currentorder.getOrderItem(al.getItemName()) != null) {
					if(periodByDay == true)
					{
						String orderenddate = currentorder.getEndTimeStamp().format(bydaydtf);
						if(currentorder.isOrderClosed() == true && orderenddate.equalsIgnoreCase(inputdate))
						{
							itemrevenue += currentorder.getOrderItem(al.getItemName()).getSubTotal();
						}
					} else {
						String orderenddate = currentorder.getEndTimeStamp().format(bymondtf);
						if(currentorder.isOrderClosed() == true && orderenddate.equalsIgnoreCase(inputdate))
						{
							itemrevenue += currentorder.getOrderItem(al.getItemName()).getSubTotal();
						}
					}
				}
			}
		}
	}
}

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
	
	public void generateNewSalesReport(Scanner sc, Map<Integer, Order> orders, List<Alacarte> alacarteitem, List<PromoPackage> pp)
	{
		System.out.println("Enter Report ID: ");
		this.reportID = sc.nextInt();
		
		System.out.println("Enter Report Name: ");
		this.reportName = sc.next();
		
		System.out.println("Generate report by day or month?");
		System.out.println("D - Day, M - Month");
		String dayormonth = sc.next();
		
		String inputdate = "";
		
		if(dayormonth.equalsIgnoreCase("D"))
		{
			this.periodByDay = true;
			System.out.println("Enter Date(E.g. 02-Dec): ");
		} else {
			this.periodByDay = false;
			System.out.println("Enter Month(E.g. Dec): ");
		}
		inputdate = sc.next();
		
		double totalrevenue = 0;
		
		System.out.println("=====================================");
		System.out.printf("Report ID: %d\n", this.reportID);
		System.out.printf("Report Name: %s\n", this.reportName);
		System.out.printf("Report Date: %s\n",  inputdate);
		System.out.println("=====================================");
		
		System.out.println("Alacarte Name\t\tRevenue($)");
		System.out.println("-------------------------------------");
		double alrev = 0;
		for(Alacarte al: alacarteitem)
		{
			double itemrev = getItemRevenue(orders, inputdate, al);
			totalrevenue += itemrev;
			alrev += itemrev;
			System.out.printf("%-16s\t%.2f\n",al.getItemName(),itemrev);
		}
		
		System.out.println("-------------------------------------");
		System.out.println("Alacarte Total: " + alrev);
		System.out.println("=====================================");
		System.out.println("Promo Name\t\tRevenue($)");
		System.out.println("-------------------------------------");
		double promorev = 0;
		for(PromoPackage promo: pp)
		{
			double itemrev = getItemRevenue(orders, inputdate, promo);
			totalrevenue += itemrev;
			promorev += itemrev;
			System.out.printf("%-16s\t%.2f\n",promo.getItemName(),itemrev);
		}
		System.out.println("-------------------------------------");
		System.out.println("Promo Total: " + promorev);
		System.out.println("=====================================");
		
		System.out.println("Total Revenue: " + totalrevenue);
		System.out.println();
	}
	
	private double getItemRevenue(Map<Integer, Order> orders, String inputdate, MenuItem mi)
	{
		DateTimeFormatter bydaydtf = DateTimeFormatter.ofPattern("dd-MMM");
		DateTimeFormatter bymondtf = DateTimeFormatter.ofPattern("MMM");
		double itemrevenue = 0;
		String orderenddate = "";
		
		for(Map.Entry<Integer, Order> e : orders.entrySet())
		{
			Order currentorder = e.getValue();
			
			if(currentorder.getOrderItem(mi.getItemName()) != null && currentorder.isOrderClosed() == true)
			{
				if(this.periodByDay == true)
				{
					orderenddate = currentorder.getEndTimeStamp().format(bydaydtf);
				} else {
					orderenddate = currentorder.getEndTimeStamp().format(bymondtf);
				}
				
				if(currentorder.isOrderClosed() == true && orderenddate.equalsIgnoreCase(inputdate))
				{
					itemrevenue += currentorder.getOrderItem(mi.getItemName()).getSubTotal();
				}
			}
		}
		
		return itemrevenue;
	}
}

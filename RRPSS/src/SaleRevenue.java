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
	
	
}
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {
	private int orderID;
	private LocalDateTime timeStamp = LocalDateTime.now();
	private double subtotal;
	private double total;
	private int noOfPax;
	private Table table;
	private Staff staff;
	private static Map<MenuItem, Integer> menuQuantity = new HashMap<MenuItem, Integer>();
	
	public Order(int orderID, int noOfPax, Table table, Staff staff)
	{
		this.orderID = orderID;
		this.noOfPax = noOfPax;
		this.table = table;
		this.staff = staff;
	}
	
	public int getOrderID()
	{
		return orderID;
	}
	
	public void setNoOfPax(int noOfPax)
	{
		this.noOfPax = noOfPax;
	}
	public int getNoOfPax()
	{
		return noOfPax;
	}
	
	public void setOrderID(int orderID)
	{
		this.orderID = orderID;
	}
	
	public String getTimeStamp()
	{
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");
		//System.out.print(timeStamp.format(formatDate));
		//return timeStamp;
		return timeStamp.format(formatDate);
	}
	
	public void setTimeStamp(LocalDateTime timeStamp)
	{
		timeStamp = LocalDateTime.now();
		this.timeStamp = timeStamp;
	}
	
	public double getSubtotal()
	{
		return subtotal;
	}
	
	public void setSubTotal(double subtotal)
	{
		this.subtotal = subtotal;
	}
	
	public double getTotal()
	{
		return total;
	}
	
	public void setTotal(double total)
	{
		this.total = total;
	}
	
	public Table getTable()
	{
		return table;
	}
	
	public void setTable(Table table)
	{
		this.table = table;
	}
	
	public Staff getStaff()
	{
		return staff;
	}
	
	public void setStaff(Staff staff)
	{
		this.staff = staff;
	}
	
	public int getMenuQuantity(MenuItem menuItem) {
		if(this.menuQuantity.containsKey(menuItem))
			return this.menuQuantity.get(menuItem);
		return 0;
	}
	public int setMenuQuantity(MenuItem menuItem, int quantity)
	{
		if(this.menuQuantity.containsKey(menuItem))
			return this.menuQuantity.replace(menuItem, quantity);
		return this.menuQuantity.put(menuItem, quantity);
	}
	
}

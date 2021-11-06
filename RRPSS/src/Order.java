import java.time.*;
import java.time.format.DateTimeFormatter;

public class Order {
	private int orderID;
	private LocalDateTime timeStamp = LocalDateTime.now();
	private double subtotal;
	private double total;
	private Table table;
	private Staff staff;
	private Customer customer;
	
	public Order(int orderID)
	{
		this.orderID = orderID;
	}
	
	public int getOrderID()
	{
		return orderID;
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
	
	public Customer getCustomer()
	{
		return customer;
	}
	
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
}

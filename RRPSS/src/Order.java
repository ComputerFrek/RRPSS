import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Order {
	private int orderID;
	private LocalDateTime startTimeStamp;
	private LocalDateTime endTimeStamp;
	private double subtotal;
	private double total;
	private int noOfPax;
	private Table table;
	private Staff staff;
	private DiscountOrder discountOrder;
	private ArrayList<TaxOrder> taxOrderArray;
	private Map<String, OrderItems> orderItems;
	
	public Order(int orderID, int noOfPax, Table table, Staff staff)
	{
		this.orderID = orderID;
		this.noOfPax = noOfPax;
		this.table = table;
		this.staff = staff;
		orderItems = new HashMap<String, OrderItems>();
		startTimeStamp = LocalDateTime.now();
		taxOrderArray = new ArrayList<TaxOrder>();
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
	
	public LocalDateTime getStartTimeStamp()
	{
		return this.startTimeStamp;
	}
	public LocalDateTime getEndTimeStamp()
	{
		return this.endTimeStamp;

		//DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");
		//System.out.print(timeStamp.format(formatDate));
		//return timeStamp;
		//return timeStamp.format(formatDate);
	}
	
	public void setEndTimeStamp(LocalDateTime dateTime)
	{
		this.endTimeStamp = dateTime;
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
	
	public OrderItems getOrderItem(String item) {
		if(this.orderItems.containsKey(item))
			return this.orderItems.get(item);
		return null;
	}
	public Map<String, OrderItems> getOrderItems() {
		return this.orderItems;
	}
	public void removeOrderItems(String item) {
		this.orderItems.remove(item);
	}
	public DiscountOrder getDiscount() {
		return this.discountOrder;
	}
	public void setDiscount(DiscountOrder discountOrder) {
		this.discountOrder = discountOrder;;
	}
	public ArrayList<TaxOrder> getTaxOrders() {
		return taxOrderArray;
	}
	public TaxOrder getTaxOrder(String taxName) {
		for(int i = 0; i < this.taxOrderArray.size(); i++)
		{
			if(taxOrderArray.get(i).getTax().taxName == taxName)
				return taxOrderArray.get(i);
		}
		return null;
	}
	public void addTaxOrder(Tax tax, double taxPrice)
	{
		for(int i = 0; i < this.taxOrderArray.size(); i++)
		{
			if(taxOrderArray.get(i).getTax().taxName == tax.taxName)
				return;
		}
		TaxOrder newTaxOrder = new TaxOrder();
		newTaxOrder.setTax(tax);
		newTaxOrder.setTaxPrice(taxPrice);
		taxOrderArray.add(newTaxOrder);
	}
	
	private boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }
	
	
}

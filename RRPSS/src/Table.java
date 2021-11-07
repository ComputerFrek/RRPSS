
public class Table {
	private int tableID;
	private int capacity;
	private int noOfPax;
	private boolean reserved;
	
	public Table(int tableID)
	{
		this.tableID = tableID;
	}
	
	public int getTableID()
	{
		return tableID;
	}
	
	public void setTableID(int tableID)
	{
		this.tableID = tableID;
	}
	
	public int getCapacity()
	{
		return capacity;
	}
	
	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}
	
	public int getNoOfPax()
	{
		return noOfPax;
	}
	
	public void setNoOfPax(int noOfPax)
	{
		this.noOfPax = noOfPax;
	}
	
	public boolean getReserved()
	{
		return reserved;
	}
	
	public void setReserved(boolean reserved)
	{
		this.reserved = reserved;
	}
}


public class Table {
	private int tableID;
	private int capacity;
	private int noOfPax;
	private boolean reserved;
	private boolean occupied;
	
	public Table(int tableID, int capacity)
	{
		this.tableID = tableID;
		this.capacity = capacity;
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
	
	public boolean getOccupied()
	{
		return occupied;
	}
	
	public void setOccupied(boolean occupied)
	{
		this.occupied = occupied;
	}
}

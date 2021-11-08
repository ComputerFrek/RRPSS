
public class Table {
	private int tableID;
	private int noOfPax;
	private boolean reserved;
	private boolean occupied;
	
	public Table(int tableID, int noOfPax)
	{
		this.tableID = tableID;
		this.noOfPax = noOfPax;
	}
	
	public int getTableID()
	{
		return tableID;
	}
	
	public void setTableID(int tableID)
	{
		this.tableID = tableID;
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

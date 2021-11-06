
public class Table {
	private int tableID;
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

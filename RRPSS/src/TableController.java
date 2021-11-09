
public class TableController {
	Table[] tables;
	
	public TableController() {
		int[] size = {2,2,4,4,4,4,8,8,8,10};
		tables = new Table[10];
		for(int i = 0; i < tables.length; i++)
			tables[i] = new Table(i+1, size[i]);
	}
	public void ViewTable() {
		System.out.println("All Tables:");
		System.out.println("Table ID \tCapacity \tNo Of Pax\tisOccupied? \tisReserved?");
		for(Table t: tables)
		{
			String isOccupied = ((t.getOccupied() == true) ? Integer.toString(t.getOrder().getOrderID()) : "Vacant");
			String isReserved = ((t.getReserved() == true) ? "Reserved" : "NIL");
			String noOfPax = ((t.getNoOfPax() == 0) ? "-" : Integer.toString(t.getNoOfPax()));
			System.out.printf("%02d \t\t%02d \t\t%s\t\t%s \t\t%s\r\n", 
					t.getTableID(), t.getCapacity(), noOfPax, isOccupied, isReserved);
		}
		System.out.println();
	} 
	public void ViewAvailableTable() {
		System.out.println("Tables Available:");
		for(Table t: tables)
		{
			if( !t.getReserved() && !t.getOccupied())
				System.out.println("TableID " + t.getTableID());
		}
		System.out.println();
	} 
	public Table SelectTable(int tableID) {
		if(tables[tableID-1].getReserved() == true || tables[tableID-1].getOccupied() == true)
			return null;
		return tables[tableID-1];
	}
	
	public int CheckTable(int pax)
	{
		for(Table t : tables)
		{
			if(!t.getReserved() && !t.getOccupied())
			{
				if(t.getCapacity() >= pax)
				{
					int tableID = t.getTableID();
					return tableID;
				}
			}
		}
		return -1;
	}
}

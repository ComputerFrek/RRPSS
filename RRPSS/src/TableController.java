
public class TableController {
	Table[] tables;
	
	public TableController() {
		tables = new Table[10];
		for(int i = 0; i < tables.length; i++)
			tables[i] = new Table(i+1);
	}
	public void ViewTable() {
		System.out.println("All Tables:");
		for(Table t: tables)
		{
			System.out.println("TableID " + t.getTableID());
		}
		System.out.println();
	} 
	public void ViewAvailableTable() {
		System.out.println("Tables Available:");
		for(Table t: tables)
		{
			if(!t.getReserved() && !t.getOccupied())
				System.out.println("TableID " + t.getTableID());
		}
		System.out.println();
	} 
	public Table SelectTable(int tableID) {
		if(tables[tableID-1].getReserved() == true || tables[tableID-1].getOccupied() == true)
			return null;
		return tables[tableID-1];
	}
}

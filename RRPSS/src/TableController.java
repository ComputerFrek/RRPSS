
public class TableController {
	Table[] tables;
	
	public TableController() {
		tables = new Table[10];
		for(int i = 0; i < tables.length; i++)
			tables[i] = new Table(i+1);
	}
	public void ViewTable() {
		System.out.println("Tables Available:");
		for(Table t: tables)
		{
			if(!t.getReserved() && !t.getOccupied())
				System.out.println("TableID " + t.getTableID());
		}
		System.out.println();
	}
	public void ShowRecommendTable(int noOfPax) {
		
	}
	public Table SelectTable(int tableID) {
		if(tables[tableID].getReserved() == true || tables[tableID].getOccupied() == true)
			return null;
		return tables[tableID];
	}
}

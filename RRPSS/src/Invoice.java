
public class Invoice {
	
	private Order order;
	

	public Invoice(Order order) {
		this.order = order;
	}
	
	
	public void printInvoice(Order order)
	{
		
		System.out.println("Server: Test\t\t Date:12/06/2011");
		System.out.println("Table: 11\t\t Time:21:26");
		System.out.println("\t     Client: 2");
		System.out.println("----------------------------------------");
		System.out.println("QTY \tITEM \t\tAMT ");
		System.out.println("----------------------------------------");
		System.out.println();
		printMenuItem();
		System.out.println("----------------------------------------");
		System.out.println("\t\tSUB-TOTAL:\t6.00");
		System.out.println("\t\t    Taxes:\t2.00");
		System.out.println("----------------------------------------");
		System.out.println("\t\t    TOTAL:\t8.00");
		System.out.println();
		
	}
	private void printHeader(){
		
	}
	private void printMenuItem() {
		for (String item : order.getOrderItems().keySet())
        {
			String menuItem = item;
			int qty = order.getOrderItem(menuItem).getQuantity();
            double itemSubTotal = order.getOrderItem(menuItem).getSubTotal();
            System.out.printf("%d \t%s \t\t%2.f",qty, menuItem, itemSubTotal);
        }
	}
}

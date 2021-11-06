
public class OrderItems {
	private MenuItem menuItem;
	private int quantity;

	public OrderItems(MenuItem menuItem, int quantity)
	{
		this.menuItem = menuItem;
		this.quantity = quantity;
	}
	public MenuItem getMenuItem() {
		return this.menuItem;
	}
	public int getQuantity() {
		return this.quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

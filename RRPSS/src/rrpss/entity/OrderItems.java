package rrpss.entity;

public class OrderItems {
	private MenuItem menuItem;
	private int quantity;
	private double subTotal = 0;

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
	public double getSubTotal() {
		return this.subTotal;
	}
	public void setSubTotal(double subtotal2) {
		this.subTotal = subtotal2;
	}
}

package rrpss.entity;

import rrpss.service.iMenuItem;

public class OrderItems {
	private iMenuItem menuItem;
	private int quantity;
	private double subTotal;

	public OrderItems(iMenuItem menuItem, int quantity)
	{
		this.menuItem = menuItem;
		this.quantity = quantity;
		this.subTotal = 0;
	}
	public iMenuItem getMenuItem() {
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
	public void setSubTotal(double subtotal) {
		this.subTotal = subtotal;
	}
}

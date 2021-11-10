package rrpss.entity;

import rrpss.service.iMenuItem;

public class Alacarte implements iMenuItem{
	private String itemName;
	private String description;
	private double price;
	
	public Alacarte(String itemName, String description, String price)
	{
		this.itemName = itemName;
		this.description = description;
		this.price = Double.parseDouble(price);
	}
	
	public String getItemName() 
	{
		return itemName;
	}
	
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(String price)
	{
		this.price = Double.parseDouble(price);
	}
}
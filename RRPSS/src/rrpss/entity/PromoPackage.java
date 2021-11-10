package rrpss.entity;
import java.util.ArrayList;
import java.util.List;

import rrpss.service.iMenuItem;

public class PromoPackage implements iMenuItem{
	private String promoname;
	private String promodesc;
	private double promoprice;
	private List<iMenuItem> menuitems;
	
	public PromoPackage(String name, String description, String price)
	{
		menuitems = new ArrayList<>();
		this.promodesc = description;
		this.promoname = name;
		this.promoprice = Double.parseDouble(price);
	}
	
	public String getItemName()
	{
		return promoname;
	}
	
	public void setItemName(String name)
	{
		this.promoname = name;
	}
	
	public String getDescription()
	{
		return promodesc;
	}
	
	public void setDescription(String description)
	{
		this.promodesc = description;
	}
	
	public double getPrice()
	{
		return promoprice;
	}
	
	public void setPrice(String price)
	{
		this.promoprice = Double.parseDouble(price);
	}
	
	public List<iMenuItem> getMenuItem()
	{
		return menuitems;
	}
	
	public void clearMenuItem()
	{
		menuitems.clear();
	}
	
	public void addMenuItem(iMenuItem item)
	{
		menuitems.add(item);
	}
}
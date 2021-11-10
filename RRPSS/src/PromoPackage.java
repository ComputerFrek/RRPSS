import java.util.ArrayList;
import java.util.List;

public class PromoPackage implements MenuItem{
	private String promoname;
	private String promodesc;
	private double promoprice;
	private List<MenuItem> menuitems;
	
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
	
	public List<MenuItem> getMenuItem()
	{
		return menuitems;
	}
	
	public void clearMenuItem()
	{
		menuitems.clear();
	}
	
	public void addMenuItem(MenuItem item)
	{
		menuitems.add(item);
	}
}
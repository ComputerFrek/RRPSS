import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class Category {
	private int categoryID;
	private String type;
	private Map<String, MenuItem> menuItems = new HashMap<String, MenuItem>();
	
	public Category(int categoryID, String type)
	{
		this.categoryID = categoryID;
		this.type = type;
	}
	
	public int getCategoryID()
	{
		return categoryID;
	}
	
	public void setCategory(int CategoryID)
	{
		this.categoryID = CategoryID;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	public Map<String, MenuItem> getMenuItems() {
		return menuItems;
	}
}

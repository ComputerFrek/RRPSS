import java.util.ArrayList;
import java.util.List;

public class PromoPackage{
	private String promoname;
	private List<MenuItem> menu;
	
	public PromoPackage(String name)
	{
		menu = new ArrayList<>();
		promoname = name;
	}
	
	public String getPromoName()
	{
		return promoname;
	}
	
	public List<MenuItem> getMenuItem()
	{
		return menu;
	}
	
	public void addMenuItem(MenuItem item)
	{
		menu.add(item);
	}
}


public class PromoPackage extends MenuItem{
	public PromoPackage(String itemName, String description, String price) {
		super(itemName, description, price);
		// TODO Auto-generated constructor stub
	}

	private MenuItem[] alaCarteItem;
	
	public MenuItem[] getAlaCarteItem()
	{
		return alaCarteItem;
	}
	
	public void setAlaCarteItem(MenuItem[] alaCarteItem)
	{
		this.alaCarteItem = alaCarteItem;
	}
}

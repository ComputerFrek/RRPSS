import java.util.HashMap;
import java.util.Map;

public class CategoryController {
	static Map<String, Category> categoryDictionary = new HashMap<String, Category>();
	public CategoryController(){
		
	}
	public void ViewCategory() {
		for(String categoryName : categoryDictionary.keySet())
			System.out.println(categoryName);
	}
	public static Category GetCategory(String category) {
		if(categoryDictionary.containsKey(category))
			return categoryDictionary.get(category);
		System.out.println("Invalid Input: Category");
		return null;
	}
	public static void ViewMenuList(String category) {
		int count = 1;
		for(MenuItem itemName : categoryDictionary.get(category).getMenuItems().values())
		{
			System.out.println(count + "| " + itemName.getItemName());
			count++;
		}
			
	}
	public static MenuItem GetMenuItems(Category category, String menuItem) {
		if(isNumeric(menuItem) && category.getMenuItems().size() <= Integer.valueOf(menuItem))
		{
			MenuItem[] item = category.getMenuItems().values().toArray(new MenuItem[0]); // Convert To Array
			return item[Integer.valueOf(menuItem) - 1];
		}
		if(categoryDictionary.get(menuItem).getMenuItems().containsKey(menuItem))
			return categoryDictionary.get(menuItem).getMenuItems().get(menuItem);
		System.out.println("Invalid Input: MenuItem");
		return null;
	}
	private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }
}

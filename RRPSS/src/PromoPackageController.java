import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PromoPackageController {
	private String promopackagename = "promo.txt";
	private File promofile;
	private List<MenuItem> promoitems;
	
	public PromoPackageController(List<MenuItem> menuitems) {
		promofile = new File(promopackagename);
		
		try {
			//if exists load menu in file into menu item arraylist
			Scanner sc = new Scanner(promofile);
			while(sc.hasNextLine()) {
				String[] fields = sc.nextLine().split("\\|");
				String[] stringitems = fields[2].split(",");
				
				promoitems = new ArrayList<>();
				for(String stringname: stringitems)
				{
					promoitems.add(getMenuItem(menuitems, stringname));
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			try {
				promofile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static MenuItem getMenuItem(List<MenuItem> menuitems, String name)
	{
		for(MenuItem mi: menuitems)
		{
			if(mi.getItemName().equalsIgnoreCase(name))
			{
				return mi;
			}
		}
		
		return null;
	}
	

}

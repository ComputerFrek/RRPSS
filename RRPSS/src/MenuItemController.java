import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuItemController {
	private String menufilename = "menuItem.txt";
	private File menufile;
	private List<MenuItem> menu;
	
	
	public MenuItemController() {
		menu = new ArrayList<>();
		menufile = new File(menufilename);
		
		try {
			//if exists load menu in file into menu item arraylist
			Scanner sc = new Scanner(menufile);
			while(sc.hasNextLine()) {
				String[] fields = sc.nextLine().split("\\|");
				MenuItem m = new MenuItem(fields[0].trim(),fields[1].trim(),fields[2].trim());
				menu.add(m);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			try {
				menufile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public List<MenuItem> getMenuItems()
	{
		return menu;
	}
	
	//display
	public void printMenu() {
		System.out.println("Name,Category,Price");
		System.out.println("===================");
		for(MenuItem me : menu)
		{
			System.out.printf("%s,%s,%.2f\n",me.getItemName(),me.getDescription(),me.getPrice());
		}
		System.out.println();
	}
	
	//create
	public boolean createMenuItem(String name, String desc, String price) {
		MenuItem m = new MenuItem(name, desc, price);
		menu.add(m);
		
		return writeToFile(name, desc, price);
	}
	
	//update
	public boolean updateMenuItem(String oldname, String newname, String olddesc, String newdesc, String oldprice, String newprice) {
		for(MenuItem me: menu)
		{
			if(me.getItemName().equalsIgnoreCase(oldname))
			{
				if(!newname.isEmpty())
				{
					me.setItemName(newname);
				}
				
				if(!newdesc.isEmpty())
				{
					me.setDescription(newdesc);
				}
				
				if(!newprice.isEmpty())
				{
					me.setPrice(newprice);
				}
				
			}
		}
		
		menufile.delete();
		
		for(MenuItem me: menu)
		{
			boolean status = writeToFile(me.getItemName(), me.getDescription(), Double.toString(me.getPrice()));
			if(status)
			{
				System.out.println("Error saving: " + me.getItemName());
			}
		}
		
		return false;
	}
	
	//delete
	public boolean deleteMenuItem(String name) {
		for(MenuItem me: menu)
		{
			if(me.getItemName().equalsIgnoreCase(name))
			{
				menu.remove(me);
			}
		}
		
		menufile.delete();
		
		for(MenuItem me: menu)
		{
			boolean status = writeToFile(me.getItemName(), me.getDescription(), Double.toString(me.getPrice()));
			if(status)
			{
				System.out.println("Error saving: " + me.getItemName());
			}
		}
		
		return false;
	}
	
	private boolean writeToFile(String name, String desc, String price) {
		FileWriter fw;
		try {
			fw = new FileWriter(menufilename);
			fw.write(name + "|" + desc + "|" + price);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		return false;
	}
}

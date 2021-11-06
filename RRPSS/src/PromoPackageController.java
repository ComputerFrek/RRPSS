import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PromoPackageController {
	private String promopackagefilename = "promo.txt";
	private File promofile;
	private List<Alacarte> promoitems;
	private AlacarteController alacartecontroller;
	
	public PromoPackageController() {
		alacartecontroller = new AlacarteController();
		promofile = new File(promopackagefilename);
		
		try {
			//if exists load menu in file into menu item arraylist
			Scanner sc = new Scanner(promofile);
			while(sc.hasNextLine()) {
				String[] fields = sc.nextLine().split("\\|");
				String[] stringitems = fields[2].split(",");
				
				promoitems = new ArrayList<>();
				for(String stringname: stringitems)
				{
					promoitems.add(alacartecontroller.getAlacarteItem(stringname));
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
	
	public List<Alacarte> getAllPromoItems()
	{
		return promoitems;
	}
	
	public void printAlacarteMenu()
	{
		alacartecontroller.printAlacarteMenu();
	}

	public boolean createPromoPackage(String name, String desc, String price, List<Alacarte> itemstoadd)
	{
		PromoPackage pp = new PromoPackage(name, desc, price);
		for(Alacarte ai: itemstoadd)
		{
			pp.addMenuItem(ai);
		}
		return writeToFile(name, desc, price);
	}
	
	private boolean writeToFile(String name, String desc, String price) {
		FileWriter fw;
		try {
			fw = new FileWriter(promopackagefilename, true);
			fw.write(name + "|" + desc + "|" + price + "|");
			for(Alacarte mi: promoitems)
			{
				fw.write(mi.getItemName() + ",");
			}
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		return false;
	}
}

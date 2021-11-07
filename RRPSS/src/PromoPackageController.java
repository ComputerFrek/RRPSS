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
	private List<PromoPackage> promopackages;
	private AlacarteController alacartecontroller;
	
	public PromoPackageController() {
		alacartecontroller = new AlacarteController();
		promofile = new File(promopackagefilename);
		promopackages = new ArrayList<>();
		PromoPackage pp;
		
		try {
			//if exists load menu in file into menu item arraylist
			Scanner sc = new Scanner(promofile);
			while(sc.hasNextLine()) {
				String[] fields = sc.nextLine().split("\\|");
				String[] stringitems = fields[3].split(",");
				
				pp = new PromoPackage(fields[0], fields[1], fields[2]);
				
				for(String stringname: stringitems)
				{
					pp.addMenuItem(alacartecontroller.getAlacarteItem(stringname));
				}
				
				promopackages.add(pp);
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
	
	public List<PromoPackage> getAllPromoItems()
	{
		return promopackages;
	}
	
	public Alacarte getAlacarteItem(String name)
	{
		return alacartecontroller.getAlacarteItem(name);
	}
	
	public void printAlacarteMenu()
	{
		alacartecontroller.printAlacarteMenu();
	}
	
	public void printPromoMenu() {
		System.out.println("Name,Category,Price");
		System.out.println("===================");
		for(PromoPackage pp : promopackages)
		{
			System.out.printf("%s,%s,%.2f,",pp.getItemName(),pp.getDescription(),pp.getPrice());
			for(MenuItem mi : pp.getMenuItem())
			{
				System.out.print(mi.getItemName() + ",");
			}
			System.out.println();
		}
	}

	public boolean createPromoPackage(String name, String desc, String price, List<Alacarte> itemstoadd)
	{
		PromoPackage pp = new PromoPackage(name, desc, price);
		for(Alacarte ai: itemstoadd)
		{
			pp.addMenuItem(ai);
		}
		return writeToFile(name, desc, price, pp);
	}
	
	public boolean updatePromoPackage(String oldname, String newname, String newdesc, String newprice, List<Alacarte> newitemstoadd) {
		for(PromoPackage pp: promopackages)
		{
			if(pp.getItemName().equalsIgnoreCase(oldname))
			{
				if(!newname.isEmpty())
				{
					pp.setItemName(newname);
				}
				
				if(!newdesc.isEmpty())
				{
					pp.setDescription(newdesc);
				}
				
				if(!newprice.isEmpty())
				{
					pp.setPrice(newprice);
				}
				
				if(!newitemstoadd.isEmpty())
				{
					pp.clearMenuItem();
					for(Alacarte al: newitemstoadd)
					{
						pp.addMenuItem(al);
					}
				}
				
			}
		}
		
		promofile.delete();
		
		for(PromoPackage pp: promopackages)
		{
			boolean status = writeToFile(pp.getItemName(), pp.getDescription(), Double.toString(pp.getPrice()), pp);
			if(status)
			{
				System.out.println("Error saving: " + pp.getItemName());
			}
		}
		
		return false;
	}
	
	//delete
	public boolean deleteAlacarteItem(String name) {
		List<PromoPackage> promotoremove = new ArrayList<>();
		
		for(PromoPackage pp: promopackages)
		{
			if(pp.getItemName().equalsIgnoreCase(name))
			{
				promotoremove.add(pp);
			}
		}
		
		promopackages.removeAll(promotoremove);
		
		promofile.delete();
		
		for(PromoPackage pp: promopackages)
		{
			boolean status = writeToFile(pp.getItemName(), pp.getDescription(), Double.toString(pp.getPrice()), pp);
			if(status)
			{
				System.out.println("Error saving: " + pp.getItemName());
			}
		}
		
		return false;
	}
	
	private boolean writeToFile(String name, String desc, String price, PromoPackage pp) {
		FileWriter fw;
		try {
			fw = new FileWriter(promopackagefilename, true);
			fw.write(name + "|" + desc + "|" + price + "|");
			for(MenuItem mi: pp.getMenuItem())
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

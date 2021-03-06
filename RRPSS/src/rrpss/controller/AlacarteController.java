package rrpss.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import rrpss.entity.Alacarte;

public class AlacarteController {
	private String alacartefilename = "alacarteItem.txt";
	private File alacartefile;
	private List<Alacarte> alacarteitems;
	
	public AlacarteController() {
		alacarteitems = new ArrayList<>();
		alacartefile = new File(alacartefilename);
		
		try {
			//if exists load menu in file into menu item arraylist
			Scanner sc = new Scanner(alacartefile);
			while(sc.hasNextLine()) {
				String[] fields = sc.nextLine().split("\\|");
				Alacarte m = new Alacarte(fields[0].trim(),fields[1].trim(),fields[2].trim(),fields[3].trim());
				alacarteitems.add(m);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			try {
				alacartefile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public List<Alacarte> getAllAlacarteItems()
	{
		return alacarteitems;
	}
	
	public Alacarte getAlacarteItem(String name)
	{
		for(Alacarte ai: alacarteitems)
		{
			if(ai.getItemName().equalsIgnoreCase(name))
			{
				return ai;
			}
		}
		
		return null;
	}
	
	//display
	public void printAlacarteMenu() {
		int count = 1;
		//System.out.println(" No. \tName \t\t\tDescription \t\t\tCategory \tPrice");
		
		//System.out.println("==================================================================================================");
		System.out.println(String.format("%s %3s %12s %12s %25s %20s %12s %12s %10s", "No.", "|", "Name", "|", "Description", "|", "Category", "|", "Price($)"));
        System.out.println(String.format("%s", "-------------------------------------------------------------------------------------------------------------------------"));
		
		for(Alacarte me : alacarteitems)
		{
			System.out.format(" %d \t%15s \t\t%20s \t\t%20s \t%15.2f\r\n", count,me.getItemName(),me.getDescription(),me.getCategory(),me.getPrice());
			//System.out.printf(me.getItemName(),me.getDescription(),me.getCategory(),me.getPrice());
			//System.out.printf("  %d \t%-15s \t%-10s \t%.2f\n",me.getCategory());
			//System.out.printf("  %d \t%-15s \t%-10s \t%.-5s \t%.2f\n",count ,me.getItemName(),me.getDescription(),me.getCategory(),me.getPrice());
			count++;
		}
	}
	
	//create
	public boolean createAlacarteItem(String name, String desc, String cate, String price) {
		Alacarte m = new Alacarte(name, desc, cate, price);
		if(getAlacarteItem(name) != null)
		{
			System.out.println("Item was not added, Items Exist.");
			return false;
		}
		alacarteitems.add(m);
		
		return writeToFile(name, desc, cate, price);
	}
	
	//update
	public boolean updateAlacarteItem(String oldname, String newname, String newdesc, String newcate,String newprice) {
		for(Alacarte me: alacarteitems)
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
				
				if(!newcate.isEmpty())
				{
					me.setCategory(newcate);
				}
				
				if(!newprice.isEmpty())
				{
					me.setPrice(newprice);
				}
				
			}
		}
		
		alacartefile.delete();
		for(Alacarte me: alacarteitems)
		{
			boolean status = writeToFile(me.getItemName(), me.getDescription(), me.getCategory(), Double.toString(me.getPrice()));
			//boolean status = writeToFile(me.getItemName(), me.getDescription(), me.getCategory(), me.getPrice());
			if(!status)
			{
				System.out.println("Error saving: " + me.getItemName());
			}
		}
		return true;
	}
	
	//delete
	public boolean deleteAlacarteItem(String name) {
		List<Alacarte> alacartetoremove = new ArrayList<>();
		
		for(Alacarte me: alacarteitems)
		{
			if(me.getItemName().equalsIgnoreCase(name))
			{
				alacartetoremove.add(me);
			}
		}
		
		alacarteitems.removeAll(alacartetoremove);
		
		alacartefile.delete();
		for(Alacarte me: alacarteitems)
		{
			boolean status = writeToFile(me.getItemName(), me.getDescription(), me.getCategory(), Double.toString(me.getPrice()));
			if(!status)
			{
				System.out.println("Error saving: " + me.getItemName());
			}
		}
		return true;
	}
	
	private boolean writeToFile(String name, String desc, String cate, String price) {
		FileWriter fw;
		try {
			fw = new FileWriter(alacartefile, true);
			fw.write(name + "|" + desc + "|" + cate + "|" + price + "\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
package rrpss.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import rrpss.entity.Alacarte;

public class MenuItemController {

	AlacarteController acC;
	PromoPackageController ppC;

	public MenuItemController() {
		acC = new AlacarteController();
		ppC = new PromoPackageController();
	}

	public void runCreateUpdateRemoveAlacarte() {
		int choice;
		Scanner sc = new Scanner(System.in);
		try {
			do {
				// call menuitem controller display
				System.out.println("(1) View Menu Item");
				System.out.println("(2) Create Menu Item");
				System.out.println("(3) Update Menu Item");
				System.out.println("(4) Remove Menu Item");
				System.out.println("(0) Back");
				System.out.print("Enter Choice: ");
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:
					acC.printAlacarteMenu();
					break;
				case 2:
					CreateNewMenuItem();
					break;
				case 3:
					UpdateMenuItem();
					break;
				case 4:
					RemoveMenuItem();
					break;
				case 0:
					return;
				default:
					break;
				}

				System.out.println();
			} while (choice != 0);
		} catch (Exception ex) {
			System.out.println("Error: Invalid Input. Please Try Again.");
		}

	}
	public void CreateNewMenuItem() {
		Scanner sc = new Scanner(System.in);
		String dishname;
		String dishdesc;
		String dishcat;
		String dishprice="";
		
		while(1==1)
		{
			System.out.print("Enter Dish Name: ");
			dishname = sc.nextLine();
			if(acC.getAlacarteItem(dishname) != null)
			{
				System.out.println("Item already Exist.");
				System.out.print("Do you want to change the name for the item?(Y/N).");
				if(sc.nextLine().toLowerCase().equals("y"))
				{
					continue;
				}	
				else
					return;
			}
			break;
		}
		System.out.print("Enter Description: "); 
		dishdesc = sc.nextLine();
		System.out.print("Enter Category: ");
		dishcat = sc.nextLine();
		do
		{
			System.out.print("Enter Price: ");
			dishprice = sc.nextLine();
			if(isNumeric(dishprice) == false)
			{
				System.out.println("Invalid input please input a valid price..");
				continue;
			}
				
		} while(isNumeric(dishprice) == false);
		

		if(acC.createAlacarteItem(dishname, dishdesc, dishcat, dishprice))
			System.out.println("\nNew item added successfully!");
		else
			System.out.println("\nItem was not added!");
	}
	public void UpdateMenuItem() {
		Scanner sc = new Scanner(System.in);
		acC.printAlacarteMenu();
		String olddishname;
		int itemNo;
		while(1==1)
		{
			System.out.print("Enter Item ID: ");
			itemNo = sc.nextInt();
			sc.nextLine();
			if(itemNo < 1 || itemNo>acC.getAllAlacarteItems().size())
			{
				System.out.println("Item ID does not exist.");
				System.out.print("Do you want to change the name for the item?(Y/N).");
				if(sc.nextLine().toLowerCase().equals("y"))
				{
					continue;
				}	
				else
					return;
			}
			break;
		}
		
		olddishname = acC.getAllAlacarteItems().get(itemNo-1).getItemName();
		System.out.print("Enter New Dish Name: ");
		String newdishname = sc.nextLine();
		System.out.print("Enter New Description: ");
		String newdishdesc = sc.nextLine();
		System.out.print("Enter New Category: ");
		String newdishcate = sc.nextLine();
		String newdishprice;
		do
		{
			System.out.print("Enter New Price: ");
			newdishprice = sc.nextLine();
			if(isNumeric(newdishprice) == false)
			{
				System.out.println("Invalid input please input a valid price..");
				continue;
			}
				
		} while(isNumeric(newdishprice) == false);

		if(acC.updateAlacarteItem(olddishname, newdishname, newdishdesc, newdishcate, newdishprice))
			System.out.println("\nItem updated successfully!");
		else
			System.out.println("\nItem was not updated!");
	}
	public void RemoveMenuItem() {
		Scanner sc = new Scanner(System.in);
		
		String dishname="";
		int itemNo;
		while(1==1)
		{
			acC.printAlacarteMenu();
			System.out.print("Enter Item ID: ");
			itemNo = sc.nextInt();
			sc.nextLine();
			if(itemNo < 1 || itemNo>acC.getAllAlacarteItems().size())
			{
				System.out.println("Item ID does not exist.");
				System.out.print("Do you want to continue removing an item?(Y/N).");
				if(sc.nextLine().toLowerCase().equals("y"))
				{
					continue;
				}	
				else
					return;
			}
			break;
		}
		dishname = acC.getAllAlacarteItems().get(itemNo-1).getItemName();
		if(acC.deleteAlacarteItem(dishname))
			System.out.println("\nItem removed successfully!");
		else
			System.out.println("\nItem was not removed!");
	}
	public void runCreateUpdateRemovePromo() {
		int choice;
		Scanner sc = new Scanner(System.in);
		try {
			do {
				System.out.println("(1) View Promo Item");
				System.out.println("(2) Create Promo Item");
				System.out.println("(3) Update Promo Item");
				System.out.println("(4) Remove Promo Item");
				System.out.println("(0) Back");
				System.out.print("Enter Choice: ");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					ppC.printPromoMenu();
					break;
				case 2:
					CreatePromo();
					break;
				case 3:
					UpdatePromo();
					break;
				case 4:
					RemovePromo();
					break;
				case 0:
					return;
				default:
					break;
				}

				System.out.println();
			} while (choice != 0);
		} catch (Exception ex) {
			System.out.println("Error: Invalid Input. Please Try Again.");
		}
	}
	public void CreatePromo() {
		Scanner sc = new Scanner(System.in);
		
		String dishname;
		String dishdesc;
		String dishcat;
		String dishprice="";
		
		while(1==1)
		{
			System.out.print("Enter Promo Name: ");
			dishname = sc.nextLine();
			if(ppC.getPromoPackage(dishname) != null)
			{
				System.out.println("Promo Package already Exist.");
				System.out.print("Do you want to change the name for the item?(Y/N).");
				if(sc.nextLine().toLowerCase().equals("y"))
				{
					continue;
				}	
				else
					return;
			}
			break;
		}
		
		System.out.println("Promo:" + dishname);
		System.out.print("Enter Description: ");
		dishdesc = sc.nextLine();
		System.out.println("Desc:" + dishdesc);

		do
		{
			System.out.print("Enter Price: ");
			dishprice = sc.nextLine();
			if(isNumeric(dishprice) == false)
			{
				System.out.println("Invalid input please input a valid price..");
				continue;
			}
				
		} while(isNumeric(dishprice) == false);
		
		System.out.println("Promo:" + dishprice);
		System.out.println("=======================");

		// call menuitem controller display
		ppC.printAlacarteMenu();
		ArrayList<Alacarte> ailist = new ArrayList<Alacarte>();
		do {
			System.out.print("Add alacarte dish, Enter Item ID: ");
			int itemNo = sc.nextInt();
			sc.nextLine();
			if(itemNo < 1 || itemNo>acC.getAllAlacarteItems().size())
			{
				System.out.println("Item ID does not exist.");
				System.out.print("Do you want to add other menu item?(Y/N).");
				if(sc.nextLine().toLowerCase().equals("y"))
				{
					continue;
				}	
				else
					return;
			}
			ailist.add(acC.getAllAlacarteItems().get(itemNo));
			System.out.println("Item added.");
			System.out.print("Do you want to add other menu item?(Y/N).");
			if(sc.nextLine().toLowerCase().equals("y"))
				continue;
			else
				break;
		}while(1==1);


		System.out.println("=======================");
		if(ppC.createPromoPackage(dishname, dishdesc, dishprice, ailist))
			System.out.println("\nNew Promo added successfully!");
		else
			System.out.println("\nPromo was not added!");
	}
	public void UpdatePromo() {
		Scanner sc = new Scanner(System.in);
		String olddishname;
		while(1==1)
		{
			ppC.printPromoMenu();
			System.out.print("Enter Old Promo Name: ");
			olddishname = sc.nextLine();
			if(ppC.getPromoPackage(olddishname) == null)
			{
				System.out.println("Promo Package does not exist.");
				System.out.print("Do you want to search for a another Promo name?(Y/N).");
				if(sc.nextLine().toLowerCase().equals("y"))
				{
					continue;
				}	
				else
					return;
			}
			break;
		}

		System.out.print("Enter New Promo Name: ");
		String newdishname = sc.nextLine();

		System.out.print("Enter New Description: ");
		String newdishdesc = sc.nextLine();
		String newdishprice;
		do
		{
			System.out.print("Enter New Price: ");
			newdishprice = sc.nextLine();
			if(isNumeric(newdishprice) == false)
			{
				System.out.println("Invalid input please input a valid price..");
				continue;
			}
				
		} while(isNumeric(newdishprice) == false);
		
		System.out.println("=======================");

		// call menuitem controller display
		ppC.printAlacarteMenu();
		ArrayList<Alacarte> ailist = new ArrayList<Alacarte>();
		do {
			System.out.print("Add alacarte dish, Enter Item ID: ");
			int itemNo = sc.nextInt();
			sc.nextLine();
			if(itemNo < 1 || itemNo>acC.getAllAlacarteItems().size())
			{
				System.out.println("Item ID does not exist.");
				System.out.print("Do you want to add other menu item?(Y/N).");
				if(sc.nextLine().toLowerCase().equals("y"))
				{
					continue;
				}	
				else
					return;
			}
			ailist.add(acC.getAllAlacarteItems().get(itemNo-1));
			System.out.println("Item added.");
			System.out.print("Do you want to add other menu item?(Y/N).");
			if(sc.nextLine().toLowerCase().equals("y"))
				continue;
			else
				break;
		}while(1==1);

		System.out.println("=======================");

		if(ppC.updatePromoPackage(olddishname, newdishname, newdishdesc, newdishprice, ailist))
			System.out.println("\nPromo updated successfully!");
		else
			System.out.println("\nPromo was not updated!");
	}
	public void RemovePromo() {
		Scanner sc = new Scanner(System.in);
		String promoname;
		while(1==1)
		{
			ppC.printPromoMenu();
			System.out.print("Enter Promo Name to remove: ");
			promoname = sc.nextLine();
			if(ppC.getPromoPackage(promoname) == null)
			{
				System.out.println("Promo Package does not exist.");
				System.out.print("Do you want to remove another Promo?(Y/N).");
				if(sc.nextLine().toLowerCase().equals("y"))
				{
					continue;
				}	
				else
					return;
			}
			break;
		}
		if(ppC.deleteAlacarteItem(promoname))
			System.out.println("\nPromo remove successfully!");
		else
			System.out.println("\nPromo was not removed!");
	}
	public boolean isNumeric(String str) {
		  ParsePosition pos = new ParsePosition(0);
		  NumberFormat.getInstance().parse(str, pos);
		  return str.length() == pos.getIndex();
	}
	public AlacarteController getAlaCarteController() {
		return this.acC;
	}
	public PromoPackageController getPromoPackageController() {
		return this.ppC;
	}

}

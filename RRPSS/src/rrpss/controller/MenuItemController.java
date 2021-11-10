package rrpss.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import rrpss.entity.Alacarte;

public class MenuItemController {
	
	public AlacarteController acC;
	public PromoPackageController ppC;
	
	public MenuItemController() {
		acC = new AlacarteController();
		ppC = new PromoPackageController();
	}
	
	public void runCreateUpdateRemoveAlacarte() {
		int choice;
		Scanner sc = new Scanner(System.in);
		try {
			do {
				//call menuitem controller display
				System.out.println("(1) View Menu Item");
				System.out.println("(2) Create Menu Item");
				System.out.println("(3) Update Menu Item");
				System.out.println("(4) Remove Menu Item");
				System.out.println("(0) Back");
				System.out.print("Enter Choice: ");
				choice = sc.nextInt();
				
				switch(choice)
				{
					case 1:
						acC.printAlacarteMenu();
						break;
					case 2:
						System.out.print("Enter Dish Name: ");
						String dishname = sc.next();
						System.out.print("Enter Description: ");
						String dishdesc = sc.next();
						System.out.print("Enter Price: ");
						String dishprice = sc.next();
						
						acC.createAlacarteItem(dishname, dishdesc, dishprice);
						break;
					case 3:
						System.out.print("Enter Old Dish Name: ");
						String olddishname = sc.next();
						System.out.print("Enter New Dish Name: ");
						String newdishname = sc.next();
						System.out.print("Enter New Description: ");
						String newdishdesc = sc.next();
						System.out.print("Enter New Price: ");
						String newdishprice = sc.next();
						
						acC.updateAlacarteItem(olddishname, newdishname, newdishdesc, newdishprice);
						break;
					case 4:
						System.out.print("Enter Dish Name: ");
						acC.deleteAlacarteItem(sc.next());
						break;
					case 0:
						return;
					default:
						break;
				}
				
				System.out.println();
			}while(choice != 0);
		}
		catch(Exception ex){
			System.out.println("Error: Invalid Input. Please Try Again.");
		}

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
				
				switch(choice)
				{
					case 1:
						ppC.printPromoMenu();
						break;
					case 2:
						System.out.println("Enter Promo Name: ");
						String dishname = sc.next();
						System.out.println("Promo:" + dishname);
						System.out.println("Enter Description: ");
						String dishdesc = sc.next();
						System.out.println("Desc:" + dishdesc);
						
						System.out.println("Enter Price: ");
						String dishprice = sc.next();
						System.out.println("Promo:" + dishprice);
						System.out.println("=======================");
						
						//call menuitem controller display
						acC.printAlacarteMenu();
						System.out.print("Add alacarte dish(Separate by comma): ");
						String[] dishlist = sc.next().split(",");
						List<Alacarte> ailist = new ArrayList<>();
						for(String s: dishlist)
						{
							ailist.add(acC.getAlacarteItem(s));
						}
						
						System.out.println("=======================");
						ppC.createPromoPackage(dishname, dishdesc, dishprice, ailist);
						break;
					case 3:
						System.out.print("Enter Old Promo Name: ");
						String olddishname = sc.next();
						System.out.print("Enter New Promo Name: ");
						String newdishname = sc.next();
						System.out.print("Enter New Description: ");
						String newdishdesc = sc.next();
						System.out.print("Enter New Price: ");
						String newdishprice = sc.next();
						System.out.println("=======================");
						
						//call menuitem controller display
						acC.printAlacarteMenu();
						System.out.print("Add alacarte dish(Separate by comma): ");
						dishlist = sc.next().split(",");
						ailist = new ArrayList<>();
						for(String s: dishlist)
						{
							ailist.add(acC.getAlacarteItem(s));
						}
						
						System.out.println("=======================");
						
						ppC.updatePromoPackage(olddishname, newdishname, newdishdesc, newdishprice, ailist);
						break;
					case 4:
						System.out.print("Enter Dish Name: ");
						ppC.deleteAlacarteItem(sc.next());
						break;
					case 0:
						return;
					default:
						break;
				}
				
				System.out.println();
			} while(choice != 0);
		}
		catch(Exception ex){
			System.out.println("Error: Invalid Input. Please Try Again.");
		}
	}

}
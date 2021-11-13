package rrpss.controller;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import rrpss.entity.Alacarte;
import rrpss.entity.Order;
import rrpss.entity.PromoPackage;
import rrpss.entity.SaleRevenue;
import rrpss.service.iMenuItem;

public class SaleRevenueController {
	
	public void generateNewSalesReport(OrderController oC, AlacarteController acC, PromoPackageController ppC)
	{
		SaleRevenue newSalesReport = new SaleRevenue();
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Report ID: ");
		newSalesReport.setReportID(sc.nextInt());
		sc.nextLine();
		System.out.print("Enter Report Name: ");
		newSalesReport.setReportName(sc.nextLine());
		
		System.out.println("Generate report by day or month?");
		System.out.print("D - Day, M - Month:");
		String dayormonth = sc.next();
		
		String inputdate = "";
		
		while(true)
		{
			if(dayormonth.equalsIgnoreCase("D"))
			{
				newSalesReport.setPeriodByDay(true);
				System.out.println("Enter Date(E.g. 02-Dec): ");
			} 
			else if(dayormonth.equalsIgnoreCase("M"))
			{
				newSalesReport.setPeriodByDay(false);
				System.out.println("Enter Month(E.g. Dec): ");
			}
			else
			{
				System.out.println("Invalid Input: ");
				System.out.println("Generate report by day or month?");
				System.out.print("D - Day, M - Month:");
				dayormonth = sc.next();
				continue;
			}
			break;
		}

		inputdate = sc.next();
		
		double totalrevenue = 0;
		
		System.out.println("=====================================");
		System.out.printf("Report ID: %d\n", newSalesReport.getReportID());
		System.out.printf("Report Name: %s\n", newSalesReport.getReportName());
		System.out.printf("Report Date: %s\n",  inputdate);
		System.out.println("=====================================");
		
		System.out.println("Alacarte Name\t\tRevenue($)");
		System.out.println("-------------------------------------");
		double alrev = 0;
		for(Alacarte al: acC.getAllAlacarteItems())
		{
			double itemrev = getItemRevenue(newSalesReport, oC, inputdate, al);
			totalrevenue += itemrev;
			alrev += itemrev;
			System.out.printf("%-16s\t%.2f\n",al.getItemName(),itemrev);
		}
		
		System.out.println("-------------------------------------");
		System.out.println("Alacarte Total: " + alrev);
		System.out.println("=====================================");
		System.out.println("Promo Name\t\tRevenue($)");
		System.out.println("-------------------------------------");
		double promorev = 0;
		for(PromoPackage promo: ppC.getAllPromoItems())
		{
			double itemrev = getItemRevenue(newSalesReport, oC, inputdate, promo);
			totalrevenue += itemrev;
			promorev += itemrev;
			System.out.printf("%-16s\t%.2f\n",promo.getItemName(),itemrev);
		}
		System.out.println("-------------------------------------");
		System.out.println("Promo Total: " + promorev);
		System.out.println("=====================================");
		
		System.out.println("Total Revenue: " + totalrevenue);
		System.out.println();
	}
	
	private double getItemRevenue(SaleRevenue salesReport, OrderController oC, String inputdate, iMenuItem mi)
	{
		DateTimeFormatter bydaydtf = DateTimeFormatter.ofPattern("dd-MMM");
		DateTimeFormatter bymondtf = DateTimeFormatter.ofPattern("MMM");
		double itemrevenue = 0;
		String orderenddate = "";
		
		for(Map.Entry<Integer, Order> e : oC.getOrderMap().entrySet())
		{
			Order currentorder = e.getValue();
			
			if(currentorder.getOrderItem(mi.getItemName()) != null && currentorder.isOrderClosed() == true)
			{
				if(salesReport.getPeriodByDay() == true)
				{
					orderenddate = currentorder.getEndTimeStamp().format(bydaydtf);
				} else {
					orderenddate = currentorder.getEndTimeStamp().format(bymondtf);
				}
				
				if(currentorder.isOrderClosed() == true && orderenddate.equalsIgnoreCase(inputdate))
				{
					itemrevenue += currentorder.getOrderItem(mi.getItemName()).getSubTotal();
				}
			}
		}
		return itemrevenue;
	}
}
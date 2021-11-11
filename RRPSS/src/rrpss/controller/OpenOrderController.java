package rrpss.controller;
import java.util.Map;
import java.util.Scanner;

import rrpss.entity.Order;
import rrpss.entity.Reservation;
import rrpss.entity.Staff;
import rrpss.entity.Table;
import rrpss.main.RRPSS_App;
import rrpss.service.iOpenOrder;

public class OpenOrderController implements iOpenOrder{

	private OrderController oC;

	public OpenOrderController(OrderController oC)
	{
		this.oC = oC;
	}
	
	@Override
	public void OpenOrder() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int noOfPax;
		Staff staff = null;
		Table table = null;
		
		staff = StaffSelection();
		if(staff == null)
			return;
		noOfPax = NoOfPaxClarification();
		if(noOfPax == 0)
			return;
		table = TableSelection(noOfPax);
		if(table == null)
			return;
		
		try 
		{
			Order newOrder = addOrder(oC.getOrderMap().size()+1, noOfPax, table, staff);
			System.out.println();
			if(newOrder == null)
			{
				System.out.println("Error: Order was not Created");
				return;
			}
			newOrder.setNoOfPax(noOfPax);
			newOrder.getTable().setOccupied(true);
			newOrder.getTable().setNoOfPax(noOfPax);
			newOrder.getTable().setOrder(newOrder);
			System.out.printf("Order %03d has been created. Seated at Table %02d \r\n", newOrder.getOrderID(), newOrder.getTable().getTableID());
			
		}
		catch(Exception ex){
			System.out.println("Error: Order was not Created");
		}
		
	}
	
	public void OpenOrderByReservation() {
		Scanner sc = new Scanner(System.in);
		int noOfPax;
		Staff staff = null;
		Table table = null;
		
		//Choose Reservation
		RRPSS_App.reservationController.printReservation();
		
		System.out.print("\nEnter reservation ID to Check In: ");
		int reservationID = sc.nextInt();

		if(!RRPSS_App.reservationController.checkReservation(reservationID))
		{
			System.out.print("Reservation ID not found.\n");
			return;
		}
		Reservation reservation = RRPSS_App.reservationController.getReservations().get(reservationID-1);
		staff = StaffSelection();
		noOfPax = reservation.getNoOfPax();
		table = reservation.getTableReserved();
		
		try 
		{
			Order newOrder = addOrder(oC.getOrderMap().size()+1, noOfPax, table, staff);
			System.out.println();
			if(newOrder == null)
			{
				System.out.println("Error: Order was not Created");
				return;
			}
			newOrder.setNoOfPax(noOfPax);
			newOrder.getTable().setOccupied(true);
			newOrder.getTable().setReserved(false);
			newOrder.getTable().setNoOfPax(noOfPax);
			newOrder.getTable().setOrder(newOrder);
			System.out.printf("Order %03d has been created. Seated at Table %02d \r\n", newOrder.getOrderID(), newOrder.getTable().getTableID());
			
		}
		catch(Exception ex){
			System.out.println("Error: Order was not Created");
		}
		
	}
	private Staff StaffSelection() {
		Scanner sc = new Scanner(System.in);
		Staff staff = null;
		System.out.println();
		RRPSS_App.staffController.ViewStaff();
		try {
			while(staff == null) {
				System.out.print("Select Your Emp ID: ");
				int staffID = sc.nextInt();
				staff = RRPSS_App.staffController.SelectStaff(staffID);
			}
			return staff;
		}
		catch(Exception ex){
			System.out.println("Error: Staff was not selected");
			return null;
		}

	}
	private Table TableSelection(int noOfPax) {
		System.out.println();
		System.out.println("Searching for Table...");
		
		int tableID = RRPSS_App.tableController.CheckTable(noOfPax);
		
		if(tableID == -1)
		{
			System.out.println("No tables currently available.");
			return null;
		}
		Table table = RRPSS_App.tableController.SelectTable(tableID); //TableSelection();
		return table;
	}
	private int NoOfPaxClarification() {
		Scanner sc = new Scanner(System.in);
		int noOfPax = 0;
		
		System.out.println();
		try {
			while(noOfPax < 1 || noOfPax > 10)
			{
				System.out.print("No of Pax?: ");
				noOfPax = sc.nextInt();
				if(noOfPax < 1 || noOfPax > 10)
					System.out.println("Invalid No Of Pax - Min: 1 pax, Max: 10 pax");
			}
			return noOfPax;
		}
		catch (Exception ex)
		{
			System.out.println("Error: Input No of Pax Error");
			return 0;
		}

	}

	@Override
	public Order addOrder(int orderID, int noOfPax, Table selectedTable, Staff createdStaff) {
		try {
			Order newOrder = new Order(orderID, noOfPax, selectedTable, createdStaff);
			oC.addOrderMap(newOrder);
			return newOrder;
		}
		catch(Exception ex) 
		{
			return null;
		}

	}

}

package rrpss.controller;
import java.util.*;

import rrpss.entity.Customer;
import rrpss.entity.Reservation;
import rrpss.main.RRPSS_App;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.time.*;
import java.time.format.DateTimeParseException;

public class ReservationController {
	
	private List<Reservation> reservation = new ArrayList<>();
	private int tableID;
	private int reservationID = 1;
	
	
	public void getDetails() {
		
		Scanner sc = new Scanner(System.in);
		checkPeriodExpiry();
		//date, time, #pax, name, contact, etc. 
		System.out.println("Enter the following details for reservation:");
		System.out.println("-----------------------------------------------");
		
		System.out.print("Date (YYYY-MM-DD) : "); //date
		String date = sc.next();
		while(!checkDate(date))
		{
			System.out.println("Invalid date format!");
			System.out.print("Date (YYYY-MM-DD) : ");
			date = sc.nextLine();
		}
		
		System.out.print("Time (HH:MM) : "); //time
		String time = sc.next();
		while(!checkTime(time))
		{
			System.out.println("Invalid time format!");
			System.out.print("Time (HH:MM) : ");
			time = sc.nextLine();
		}

		
		int pax = 0;
		
		while(pax < 1 || pax > 10)
		{
			System.out.print("Number of Pax: ");
			String paxString = sc.next();
			if(isNumeric(paxString))
				pax = Integer.parseInt(paxString);
			else
			{
				System.out.println("Invalid pax input.");
				continue;
			}
			if(pax < 1 || pax > 10)
			{
				System.out.println("Invalid No Of Pax - Min: 1 pax, Max: 10 pax");
				continue;
			}
			pax = Integer.parseInt(paxString);	
		}
		
		
		tableID = RRPSS_App.tableController.CheckTable(pax);
		if(tableID == -1)
		{
			System.out.println("No tables currently available.");
		}
		else
		{	
			sc.nextLine();
			System.out.print("Customer's Name: "); //name
			String name = sc.nextLine();
			
			Customer customer = new Customer(name);
			
			Reservation reserve = new Reservation(
					reservationID,LocalDateTime.parse(date+"T"+time+":00"),pax,customer,RRPSS_App.tableController.tables[tableID-1]);
			
			reservation.add(reserve);
			
			RRPSS_App.tableController.tables[tableID-1].setReserved(true);
			System.out.printf("Reservation has been created. \nReservation ID is %d. Table ID is %d.\n",reservationID,tableID);
			System.out.println();
			
			reservationID++;
						
		}	
	}
	
	public List<Reservation> getReservations()
	{
		return reservation;
	}
	
	public void printReservation()
	{
		checkPeriodExpiry();
		
		System.out.println("\n\t\t  Reservations ");
		System.out.println("----------------------------------------------------");
		System.out.println(" ID |  Reserved Time   | Table ID | Pax | Name ");
		System.out.println("----------------------------------------------------");
		if(reservation.isEmpty())
			System.out.println("\t    There are no reservations.\n");
		else
		{
			for(Reservation r : reservation)
			{
				//[ReservationID]: [DateTime], Table [TableID], [noOfPax] pax, [customer name]
				System.out.printf("(%d): [%s], Table %d, %d pax, %s"
						,r.getReservationID(),r.getDateTime(),
						r.getTableReserved().getTableID(),r.getNoOfPax(),r.getCustomerBooked().getName());
				System.out.println();
			}
			System.out.println();
		}
	}
	
	private void remove(int reservationID)
	{
		for(int i = 0; i < reservation.size(); i++)
		{
			if(reservation.get(i).getReservationID() == reservationID)
			{
				reservation.get(i).getTableReserved().setReserved(false);
				reservation.remove(i);
			}
		}
	}
	public int getReservationIndex(int reservationID) {
		for(int i = 0; i < reservation.size(); i++)
		{
			if(reservation.get(i).getReservationID() == reservationID)
			{
				return i;
			}
		}
		return -1;
		
	}
	
	private void removeReservation()
	{
		if(reservation.isEmpty())
		{
			System.out.println("\nThere are no reservations to remove.\n");
			return;
		}

		System.out.print("\nEnter reservation ID to remove: ");
		Scanner sc = new Scanner(System.in);
		int reservationID = sc.nextInt();

		if(!checkReservation(reservationID))
		{
			System.out.print("Reservation ID not found.\n");
			return;
		}
		remove(reservationID);

		System.out.printf("ReservationID %d is removed.\n\n",reservationID);
	}
	
	public boolean checkReservation(int reservationID)
	{
		for(int i = 0; i < reservation.size(); i++)
		{
			if(reservation.get(i).getReservationID() == reservationID)
			{
				return true;
			}			
		}
		return false;
	}
	
	public void checkRemoveReservation() 
	{
		int choice;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("(1) Check Reservation(s)");
			System.out.println("(2) Remove Reservation(s)");
			System.out.println("(3) Exit");
			System.out.print("Enter choice: ");
			
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:	printReservation();
			break;
			case 2: removeReservation();
			break;
			case 3: System.out.println(); 
				return;

			}
		}while(choice != -1);	
		
	}
	
	private boolean checkDate(String date) {		
		try {
			LocalDate.parse(date);	
		}catch(DateTimeParseException e) {
			return false;
		}
		return true;
	}
	
	private boolean checkTime(String time)
	{
		try {
			LocalTime.parse(time);	
		}catch(DateTimeParseException e) {
			return false;
		}
		return true;
	}
	
	private void checkPeriodExpiry()
	{
		LocalDateTime current = LocalDateTime.now();
		if(reservation.isEmpty())
			return;
		
		for(int i = 0; i < reservation.size(); i++)
		{
			if(current.isAfter(reservation.get(i).getExpiryTime())) 
			{
				remove(reservation.get(i).getReservationID());
			}
		}
		
	}
	public boolean isNumeric(String str) {
		  ParsePosition pos = new ParsePosition(0);
		  NumberFormat.getInstance().parse(str, pos);
		  return str.length() == pos.getIndex();
	}
	
}
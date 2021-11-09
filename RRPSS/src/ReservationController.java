
import java.util.*;
import java.time.*;
import java.time.format.DateTimeParseException;

public class ReservationController {
	
	private List<Reservation> reservation = new ArrayList<>();
	private int tableID;
	private int reservationID = 1;
	
	public void getDetails(TableController table_control) {
		
		Scanner sc = new Scanner(System.in);
		
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
		
		System.out.print("Number of pax: ");
		int pax = sc.nextInt();
		
		tableID = table_control.CheckTable(pax);
		if(tableID == -1)
		{
			System.out.println("No tables currently available.");
		}
		else
		{	
			System.out.print("Customer's Name: "); //name
			String name = sc.next();
			
			Customer customer = new Customer();
			customer.setName(name);
			
			Reservation reserve = new Reservation(
					reservationID,LocalDateTime.parse(date+"T"+time+":00"),pax,customer,table_control.tables[tableID-1]);
			
			reservation.add(reserve);
			
			table_control.tables[tableID-1].setReserved(true);
			System.out.printf("Reservation has been created. \nReservation ID is %d. Table ID is %d.\n",reservationID,tableID);
			System.out.println();
			
			reservationID++;
						
		}	
	}
	
	public List<Reservation> getReservations()
	{
		return reservation;
	}
	
	private void printReservation()
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
	
//	private void remove(Table[] tables, int reservationID, boolean occupy)
//	{
//		for(int i = 0; i < reservation.size(); i++)
//		{
//			if(reservation.get(i).getReservationID() == reservationID)
//			{
//				tables[reservation.get(i).getTableReserved().getTableID() - 1].setReserved(false);
//				tables[reservation.get(i).getTableReserved().getTableID() - 1].setOccupied(occupy);;
//				reservation.remove(i);
//				System.out.printf("ReservationID %d is removed.\n\n",reservationID);
//			}			
//		}
//	}
	private void remove(int reservationID, boolean occupy)
	{
		for(int i = 0; i < reservation.size(); i++)
		{
			if(reservation.get(i).getReservationID() == reservationID)
			{
				reservation.get(i).getTableReserved().setReserved(false);
				reservation.get(i).getTableReserved().setOccupied(occupy);
				reservation.remove(i);
			}
		}
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
		
		System.out.print("Did the customer(s) arrive? (Y/N): ");
		String c = sc.next();
		
		if(c.trim().toLowerCase().equals("y"))
			remove(reservationID,true);//remove(tables,reservationID,true);
		else if(c.trim().toLowerCase().endsWith("n"))
			remove(reservationID,false);//remove(tables,reservationID,false);
		else
		{
			System.out.println("Invalid input. Please try again.");
			return;
		}
		System.out.printf("ReservationID %d is removed.\n\n",reservationID);
	}
	
	private boolean checkReservation(int reservationID)
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
				remove(reservation.get(i).getReservationID(),false);
			}
		}
		
	}
	
}

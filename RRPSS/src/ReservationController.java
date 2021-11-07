import java.util.*;
import java.time.*;
import java.time.format.DateTimeParseException;

public class ReservationController {
	
	private List<Reservation> reservation = new ArrayList<>();
	private int tableID;
	private int reservationID = 1;
	
	public void getDetails(Table[] tables) {
		
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
		if(!checkTable(tables, pax))
		{
			System.out.println("No tables currently available.");
		}
		else
		{	
			System.out.print("Customer's Name: "); //name
			String name = sc.next();
			
//			System.out.print("Contact Number: "); //contact
//			String number = sc.nextLine();
			
			Customer customer = new Customer();
			customer.setName(name);
			
			Reservation reserve = new Reservation(
					reservationID,LocalDateTime.parse(date+"T"+time+":00"),pax,customer,tables[tableID-1]);
			
			reservation.add(reserve);
			tables[tableID-1].setReserved(true);
			System.out.println("Reservation has been created. Reservation ID is "+reservationID);
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
		System.out.println("Reservations: ");
		System.out.println("--------------------------------");
		if(reservation.isEmpty())
			System.out.println("There are no reservations.\n");
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
	
	private boolean checkTable(Table[] tables, int pax)
	{
		for(Table t : tables)
		{
			if(!t.getReserved())
			{
				if(t.getCapacity() >= pax)
				{
					tableID = t.getTableID();
					return true;
				}
			}
		}
		return false;
	}
}

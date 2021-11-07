import java.util.*;
import java.time.*;
import java.time.format.DateTimeParseException;

public class ReservationController {

	private boolean reservationstatus;
	private List<Reservation> reservation = new ArrayList<>();
	
	
	public boolean getDetails(Table[] tables) {
		
		Scanner sc = new Scanner(System.in);
		
		//date, time, #pax, name, contact, etc. 
		System.out.println("Enter the following details for reservation:");
		System.out.println("-----------------------------------------------");
		
		System.out.print("Date (YYYY-MM-DD) : "); //date
		String date = sc.nextLine();
		while(!checkDate(date))
		{
			System.out.println("Invalid date format!");
			System.out.print("Date (YYYY-MM-DD) : ");
			date = sc.nextLine();
		}
		
		System.out.print("Time (HH:MM) : "); //time
		String time = sc.nextLine();
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
			String name = sc.nextLine();
			
			System.out.print("Contact Number: "); //contact
			String number = sc.nextLine();
			
			Customer customer = new Customer();
			customer.setName(name);
			
		}
		

		return reservationstatus;
	}
	
	public List<Reservation> getReservations()
	{
		return reservation;
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
					return true;
			}
		}
		return false;
	}
}

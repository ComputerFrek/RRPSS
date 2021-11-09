import java.time.*;

public class Reservation {
	private int reservationID;
	//private String date_time;
	private LocalDateTime datetime;
	private LocalDateTime expirytime;
	private int noOfPax;
	private Customer customerBooked;
	private Table tableReserved;
	
	public Reservation(int reservationID, LocalDateTime datetime,int noOfPax, Customer customerBooked, Table tableReserved) {
		this.reservationID = reservationID;
		this.datetime = datetime;
		this.expirytime = this.datetime.plusMinutes(30);
		this.noOfPax = noOfPax;
		this.customerBooked = customerBooked;
		this.tableReserved = tableReserved;
	}
	
	public int getReservationID()
	{
		return reservationID;
	}
	
	public void setReservationID(int reservationID)
	{
		this.reservationID = reservationID;
	}
	
	public LocalDateTime getDateTime()
	{
		return datetime;
	}
	
	public void setDateTime(LocalDateTime datetime)
	{
		this.datetime = datetime; //idk man
	}
	
	public LocalDateTime getExpiryTime()
	{
		return expirytime;
	}
	
	public void setExpiryTime(LocalDateTime expirytime)
	{
		this.expirytime = expirytime;
	}
	
	public int getNoOfPax()
	{
		return noOfPax;
	}
	
	public void setNoOfPax(int noOfPax)
	{
		this.noOfPax = noOfPax;
	}
	
	public Customer getCustomerBooked()
	{
		return customerBooked;
	}
	
	public void setCustomerBooked(Customer customerBooked)
	{
		this.customerBooked = customerBooked;
	}
	
	public Table getTableReserved()
	{
		return tableReserved;
	}
	
	public void setTableReserved(Table tableReserved)
	{
		this.tableReserved = tableReserved;
	}
}

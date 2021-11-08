
public class Staff extends Person{
	private int employeeID;
	private String jobtitle;
	private char gender;
	
	
	public Staff(String name, int employeeID, String jobtitle, char gender) {
		super(name);
		this.employeeID = employeeID;
		this.jobtitle = jobtitle;
		this.gender = gender;
	}
	
	public int getEmployeeID()
	{
		return employeeID;
	}
	
	public void setEmployeeID(int employeeID)
	{
		this.employeeID = employeeID;
	}
	
	public String getJobtitl()
	{
		return jobtitle;
	}
	
	public void setJobtitle(String jobtitle)
	{
		this.jobtitle = jobtitle;
	}
	
	public char getGender()
	{
		return gender;
	}
	
	public void setGender(char gender)
	{
		this.gender = gender;
	}
}

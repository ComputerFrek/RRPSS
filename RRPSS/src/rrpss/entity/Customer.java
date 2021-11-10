package rrpss.entity;

public class Customer extends Person{
	public Customer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private int customerID;
	private boolean membership;
	
	public int getCustomerID()
	{
		return customerID;
	}
	
	public void setCustomerID(int customerID)
	{
		this.customerID = customerID;
	}
	
	public boolean getMembership()
	{
		return membership;
	}
	
	public void setMembership(boolean membership)
	{
		this.membership = membership;
	}
}

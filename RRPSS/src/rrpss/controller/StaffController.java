package rrpss.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import rrpss.entity.Staff;

public class StaffController {
	static Map<Integer, Staff> staffDictionary = new HashMap<Integer, Staff>();;;
	public StaffController(){
		GenerateStaff();
	}
	public Staff SelectStaff(int staffID) {
		if(!staffDictionary.containsKey(staffID))
			return null;
		return staffDictionary.get(staffID);
	}
	public void ViewStaff() {
		System.out.println("View All Staff:");
		System.out.println("Emp ID \tEmp Name \tJob Title");
		for(Staff s: new ArrayList<Staff>(staffDictionary.values()))
		{
			System.out.printf("%d \t%s \t%s \r\n",s.getEmployeeID(),s.getName(),s.getJobtitl());
		}
	}
	public void GenerateStaff() {
		Staff staff1 = new Staff("Steve", 1, "Server", 'M');
		Staff staff2 = new Staff("Mandy", 2, "Server", 'F');
		Staff staff3 = new Staff("Wu Sen", 3, "Server", 'M');
		Staff staff4 = new Staff("Steven", 4, "Server", 'M');
		
		staffDictionary.put(1,staff1);
		staffDictionary.put(2,staff2);
		staffDictionary.put(3,staff3);
		staffDictionary.put(4,staff4);
	}
}

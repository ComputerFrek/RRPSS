import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StaffController {
	static Map<Integer, Staff> staffDictionary = new HashMap<Integer, Staff>();;;
	public StaffController(){
		
	}
	public Staff SelectStaff() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Select Staff: ");
		int staffID = sc.nextInt();
		return staffDictionary.get(staffID);
	}
	public void ViewStaff() {
		for(Staff s: new ArrayList<Staff>(staffDictionary.values()))
		{
			System.out.printf("%d,%s,%s",s.getEmployeeID(),s.getName(),s.getJobtitl());
		}
	}
}

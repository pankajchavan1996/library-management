package UserArea;

import java.util.Scanner;

import dao.IssueDao;
import utils.BookUtil;
import utils.CopyUtil;
import utils.UserUtil;

public class Member {
	static Scanner sc = new Scanner(System.in);
	private static IssueDao dao=new IssueDao();

	private static int menuList() {
		System.out.println("0. Sign Out");
		System.out.println("1. Edit Profile");
		System.out.println("2. Change Password");
		System.out.println("3. Book Availability");
		System.out.println("4. Find a Book");
		System.out.println("5. Check Status");
		System.out.print("Enter Choice : ");
		return sc.nextInt();
	}

	public static void memberArea(String email) {
		System.out.println("MEMEBR AREA");
		int choice;
		while ((choice = Member.menuList()) != 0) {
			switch (choice) {
			case 1: // Edit Profile
				UserUtil.editProfile(email);
				break;
			case 2: // Change Password
				UserUtil.changePassword(email);
				break;
			case 3: // Book availability
				CopyUtil.checkAvai();
				break;
			case 4: // Find a Book
				BookUtil.findBook();
				break;
			case 5: //  Check Status
				dao.checkStatus();
				break;

			}
		}
	}

}

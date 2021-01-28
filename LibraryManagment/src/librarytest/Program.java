package librarytest;

import java.util.Scanner;
import utils.UserUtil;

public class Program {
	static Scanner sc = new Scanner(System.in);
	private static int menuList() {
		System.out.println("0. EXIT");
		System.out.println("1. Sign In");
		System.out.println("2. Sign Up");
		System.out.println("Enter Choice : ");
		return sc.nextInt();
	}

	public static void main(String[] args) {
		int choice;
		while ((choice = Program.menuList()) != 0) {
			switch (choice) {
			case 1: // sign in
				UserUtil.signIn();
				break;
			case 2: // sign up
				UserUtil.signUp();
				break;
			}
		}
	}

}

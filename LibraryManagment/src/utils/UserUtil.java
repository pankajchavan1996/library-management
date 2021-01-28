package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import UserArea.Librarian;
import UserArea.Member;
import UserArea.Owner;
import dao.UserDao;
import pojo.User;

public class UserUtil {
	static Scanner sc = new Scanner(System.in);
	public final static String OWNEREMAIL = "owner@gmail.com";

	public static void signUp() {
		try (UserDao user = new UserDao();) {
			User u = UserUtil.acceptUser();
			int res = user.insert(u);
			System.out.println(res + " inserted");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void signIn() {
		String email, password;
		System.out.print("Enter Email : ");
		email = sc.nextLine();
		System.out.print("Enter password : ");
		password = sc.nextLine();
		String role = null;

		if ((role = UserUtil.userFind(email, password)) != null) {

			if ((email.equals(OWNEREMAIL)))
				Owner.ownerArea(email);
			else if (role.equals("Member"))
				Member.memberArea(email);
			else if (role.equals("Librarian"))
				Librarian.librarianArea(email);

		} else
			System.out.println("User not Found.");

	}

	public static User acceptUser() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Name : ");
		String name = sc.nextLine();
		System.out.print("Enter Email ID : ");
		String email = sc.nextLine();
		System.out.print("Enter Phone number : ");
		String phone = sc.nextLine();
		System.out.print("Enter Password : ");
		String password = sc.nextLine();
		if (email.compareTo(UserUtil.OWNEREMAIL) == 0)
			return new User(name, email, phone, password, "Owner");
		else
			return new User(name, email, phone, password, "Member");
	}

	public static String userFind(String email, String password) {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement stmtFind = connection.prepareStatement("Select * from users where email = ?");) {
			String role = null;
			stmtFind.setString(1, email);
			ResultSet rs = stmtFind.executeQuery();
			String passwordToMatch = null;
			while (rs.next()) {
				passwordToMatch = rs.getString(5);
				if (password.equals(passwordToMatch)) {
					role = rs.getString(6);
					// System.out.println(role);
				}
			}
			return role;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static User editUserFind(String email) {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement stmtFind = connection.prepareStatement("Select * from users where email = ?");) {
			stmtFind.setString(1, email);
			ResultSet rs = stmtFind.executeQuery();
			// String passwordToMatch = null;
			User user = null;
			while (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
			}
			rs.close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static int editMenuList() {
		System.out.println("0. Exit");
		System.out.println("1. Change Name");
		System.out.println("2. Change Phone");
		System.out.print("Enter choice : ");
		return sc.nextInt();
	}

	public static void editProfile(String email) {

		// System.out.println(email);

		try (UserDao dao = new UserDao()) {
			User u = UserUtil.editUserFind(email);
			int choice = 0;
			while ((choice = editMenuList()) != 0) {
				switch (choice) {
				case 1: // name
					sc.nextLine();
					System.out.print("Enter New Name : ");
					u.setName(sc.nextLine());
					dao.updateName(u);
					break;
				case 2:
					sc.nextLine();
					System.out.print("Enter New Phone : ");
					u.setPhone(sc.nextLine());
					dao.updatePhone(u);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void changePassword(String email) {
		try (UserDao dao = new UserDao()) {
			User u = UserUtil.editUserFind(email);
			sc.nextLine();
			System.out.print("Enter New Password : ");
			u.setPassword(sc.nextLine());
			dao.updatePassword(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

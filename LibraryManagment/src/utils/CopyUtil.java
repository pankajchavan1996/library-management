package utils;

import java.util.Scanner;

import dao.CopyDao;
import pojo.BookCopy;

public class CopyUtil {
	static public Scanner sc = new Scanner(System.in);
	private static CopyDao dao = new CopyDao();

	public static void acceptCopies() {
		BookCopy copy = new BookCopy();

		System.out.print("Enter book id : ");
		copy.setBookId(sc.nextInt());
		sc.nextLine();
		System.out.print("Enter rack no : ");
		copy.setRack(sc.nextLine());
		copy.setStatus("available");
		dao.insertCopy(copy);
	}

	public static void checkAvai() {
		System.out.print("Enter Book id ");
		System.out.println(dao.checkBook(sc.nextInt())+" copies are available.");

	}

	public static void changeRack() {
		System.out.print("Enter copy id : ");
		int num = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter new rack : ");
		String rack = sc.nextLine();
		dao.changeRack(num, rack);
	}

}

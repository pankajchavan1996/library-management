package utils;

import java.time.LocalDate;
import java.sql.Date;
import java.util.Scanner;

import dao.CopyDao;
import dao.IssueDao;
import dao.PaymentDao;
import pojo.IssueRecord;

public class IssueRecordUtil {
	private static Scanner sc = new Scanner(System.in);
	private static IssueDao dao = new IssueDao();
	private static CopyDao cdao = new CopyDao();
	private static PaymentDao pdao = new PaymentDao();
	private static IssueRecord issue = new IssueRecord();

	public static void issueBookCopy() {
		System.out.print("Enter copy id      : ");
		issue.setCopyID(sc.nextInt());
		System.out.print("Enter user id      : ");
		issue.setUserID(sc.nextInt());
		if(pdao.paidMember(issue.getUserID())) {
		/* System.out.println("Enter issue date : ");
		 * System.out.print("day   : "); int a=sc.nextInt();
		 * System.out.print("month : "); int b=sc.nextInt();
		 * System.out.print("year  : "); int c=sc.nextInt();
		 */
		
		LocalDate date = LocalDate.now();
		issue.setIssueDate(Date.valueOf(date));
		issue.setReturnDuedate(Date.valueOf(date.plusDays(15)));
		dao.addRecord(issue);
		cdao.changeStatus(issue.getCopyID(), 1);
		}else
			System.out.println("Oops, User Is not Paid !!!");
	}

	public static void returnBookCopy() {
		System.out.print("Enter user ID      : ");
		int id = sc.nextInt();
		System.out.println("Enter return date : ");
		System.out.print("day   : ");
		int day = sc.nextInt();
		System.out.print("month : ");
		int month = sc.nextInt();
		System.out.print("year  : ");
		int year = sc.nextInt();
		dao.returnBook(id, Date.valueOf(LocalDate.of(year, month, day)));

	}

}

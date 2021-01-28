package utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
import dao.IssueDao;
import dao.PaymentDao;
import pojo.IssueRecord;
import pojo.Payments;

public class PaymentUtil {
	private static PaymentDao pdao = new PaymentDao();
	private static Scanner sc = new Scanner(System.in);
	private static IssueDao idao = new IssueDao();

	public static void acceptPayment() {
		Payments payment = new Payments();
		System.out.print("Enter user id : ");
		payment.setUserId(sc.nextInt());
		payment.setType("fees");
		payment.setAmount(500.00);
		pdao.acceptFees(payment);
	}

	public static void fineReceive(int userid, int issueid, Date dt) {

		IssueRecord issue = idao.getRecord(issueid, dt);
		LocalDate issueDate = issue.getIssueDate().toLocalDate();
		LocalDate returnDate = dt.toLocalDate();

		int diffdays = Period.between(issueDate, returnDate).getDays();
		System.out.println("diff days :" + diffdays);
		if (diffdays > 0) {
			issue.setFine(diffdays * 10);
			System.out.println("fine" + issue.getFine());
			pdao.fineAccept(issue);
		}

	}

	public static void paymentHistory() {
		pdao.paymentHistory();
	}
}

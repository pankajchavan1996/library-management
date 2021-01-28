package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import pojo.BookCopy;
import pojo.IssueRecord;
import utils.DBUtil;
import utils.PaymentUtil;

public class IssueDao implements AutoCloseable {
	private CopyDao cdao = new CopyDao();
	private Connection connection;
	private Statement stm;
	private PreparedStatement stm1;
	private PreparedStatement stm2;
	private PreparedStatement stm3;
	private PreparedStatement stm4;
	private PreparedStatement stm5;

	private static Scanner sc = new Scanner(System.in);

	public IssueDao() {

		try {
			connection = DBUtil.getConnection();
			stm = connection.createStatement();
			stm1 = connection.prepareStatement("insert into issuerecords values(?,?,?,?,?,?,?)");
			stm2 = connection.prepareStatement("update issuerecords set return_date=? where issue_id=?");
			stm3 = connection.prepareStatement("SELECT * FROM issuerecords where user_id=? AND return_date is NULL ");
			stm4 = connection.prepareStatement("select *from issuerecords where user_id=?");
			stm5 = connection.prepareStatement("select *from issuerecords where issue_id=?");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addRecord(IssueRecord issue) {

		CopyDao dao = new CopyDao();
		BookCopy copy = new BookCopy();
		copy = dao.getBook(issue.getCopyID());
		if (copy.getStatus().contentEquals("available")) {

			try {
				ResultSet rs = stm.executeQuery("SELECT MAX(issue_id) FROM issuerecords");
				int num = 1;
				while (rs.next())
					num = rs.getInt(1) + 1;
				stm1.setInt(1, num);
				stm1.setInt(2, issue.getCopyID());
				stm1.setInt(3, issue.getUserID());
				stm1.setDate(4, issue.getIssueDate());
				stm1.setDate(5, issue.getReturnDuedate());
				stm1.setDate(6, issue.getReturnDate());
				stm1.setDouble(7, issue.getFine());
				stm1.executeUpdate();
				System.out.println("record added !!! ");
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else
			System.out.println("Book Is alredy Issued.");
	}

	public void returnBook(int userid, Date dt) {

		try {
			stm3.setInt(1, userid);
			ResultSet res = stm3.executeQuery();
			System.out.println();
			ArrayList<IssueRecord> records = new ArrayList<>();
			while (res.next()) {
				records.add(new IssueRecord(res.getInt(1), res.getInt(2), res.getInt(3), res.getDate(4), res.getDate(5),
						res.getDate(6), res.getDouble(7)));
			}
			res.close();
			System.out.println();
			for (IssueRecord ref : records) {
				System.out.println(ref.toString());
			}
			System.out.print("Enter issue Id to be returned : ");
			int num = sc.nextInt();
			stm2.setDate(1, dt);
			stm2.setInt(2, num);
			stm2.executeUpdate();
			int copyID = 0;
			for (IssueRecord ref : records) {
				if (ref.getIssueId() == num) {
					copyID = ref.getCopyID();
					PaymentUtil.fineReceive(userid,copyID,dt);
				}

			}
			cdao.changeStatus(copyID, 2);
			System.out.println("retun book record updated ");

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
	}

	public void checkStatus() {
		try {
			System.out.println("Enter your User ID: ");
			int id = sc.nextInt();
			stm4.setInt(1, id);
			ResultSet rs = stm4.executeQuery();
			ArrayList<IssueRecord> records = new ArrayList<>();
			while (rs.next()) {
				records.add(new IssueRecord(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getDate(5),
						rs.getDate(6), rs.getDouble(7)));
			}
			rs.close();
			System.out.println();
			for (IssueRecord ref : records) {
				System.out.println(ref.toString());
			}
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void close() throws IOException {

		try {
			connection.close();
			stm.close();
			stm1.close();
			stm2.close();
			stm3.close();
			stm4.close();
			stm5.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public IssueRecord getRecord(int issueid, Date dt) {
		IssueRecord issue = new IssueRecord();
		try {

			stm5.setInt(1, issueid);
			ResultSet rs = stm5.executeQuery();
			while (rs.next()) {
				issue.setCopyID(rs.getInt(2));
				issue.setUserID(rs.getInt(3));
				issue.setIssueDate(rs.getDate(4));
				issue.setReturnDuedate(rs.getDate(5));
				issue.setReturnDate(dt);
			}
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return issue;

	}

}

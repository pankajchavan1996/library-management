package dao;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pojo.BookCopy;
import utils.DBUtil;

public class CopyDao implements Closeable {
	private Connection connection;
	private Statement stmInsert;
	private PreparedStatement stm;
	private PreparedStatement stmSelect;
	private PreparedStatement stmUpdate;
	private PreparedStatement stmUpdateStatus;
	private PreparedStatement stmSelectAll;

	public CopyDao() {

		try {
			connection = DBUtil.getConnection();
			stm = connection.prepareStatement("INSERT INTO copies VALUES(?,?,?,?)");
			stmSelect = connection.prepareStatement("SELECT copy_id, rack FROM copies WHERE book_id=? and status = 'available' ");
			stmInsert = connection.createStatement();
			stmUpdate = connection.prepareStatement("UPDATE copies SET rack=? WHERE copy_id=?");
			stmUpdateStatus = connection.prepareStatement("UPDATE copies SET status=? WHERE copy_id=?");
			stmSelectAll = connection.prepareCall("SELECT * FROM copies WHERE copy_id=?");
		} catch (Exception cause) {
			throw new RuntimeException(cause);
		}
	}

	public void insertCopy(BookCopy copy) {

		try {
			ResultSet rs = stmInsert.executeQuery("SELECT MAX(copy_id) FROM copies");
			int num = 1;
			while (rs.next())
				num = rs.getInt(1) + 1;
			stm.setInt(1, num);
			stm.setInt(2, copy.getBookId());
			stm.setString(3, copy.getRack());
			stm.setString(4, copy.getStatus());
			stm.executeUpdate();
			System.out.println("Copy is added. ");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int checkBook(int num) {
		int count = 0;
		try {
			stmSelect.setInt(1, num);
			ResultSet rs = stmSelect.executeQuery();
			System.out.println();
			System.out.println("Available book copies are : ");
			System.out.printf("%-7s%-7s\n","CopyId","Rack");
			while (rs.next()) {
				System.out.printf("%-7d%-7s\n", rs.getInt(1), rs.getString(2));
				++count;
			}
			System.out.println();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;

	}

	public BookCopy getBook(int num) {
		BookCopy copy = new BookCopy();
		try {

			stmSelectAll.setInt(1, num);
			ResultSet rs = stmSelectAll.executeQuery();
			while (rs.next()) {
				copy.setCopyId(1);
				copy.setBookId(rs.getInt(1));
				copy.setRack(rs.getString(3));
				copy.setStatus(rs.getString(4));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return copy;
	}

	@Override
	public void close() throws IOException {
		try {
			connection.close();
			stm.close();
			stmInsert.close();
			stmSelect.close();
			stmUpdate.close();
			stmUpdate.close();
			stmSelectAll.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void changeRack(int num, String rack) {
		try {
			stmUpdate.setString(1, rack);
			stmUpdate.setInt(2, num);
			stmUpdate.executeUpdate();
			System.out.println("Rack changed !!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void changeStatus(int copyId, int i) {
		try {
			if (i == 1)
				stmUpdateStatus.setString(1, "issued");
			else if (i == 2)
				stmUpdateStatus.setString(1, "available");
			stmUpdateStatus.setInt(2, copyId);
			stmUpdateStatus.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}

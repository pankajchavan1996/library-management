package librarytest;

import java.sql.*;
import utils.DBUtil;
public class CreateTable {

	public static void main(String[] args) {
		
		try (Connection con = DBUtil.getConnection(); Statement stmt = con.createStatement();){
			
			String sql = "CREATE TABLE users " +
						"( user_id INT," +
						"name VARCHAR(255)," +
						"email VARCHAR(255)," +
						"phone VARCHAR(15)," +
						"password VARCHAR(20),"+
						"role VARCHAR(10)," +
						" PRIMARY KEY (user_id))";
			String sql2 = "CREATE TABLE books" +
					"(book_id INT, "+
					"name VARCHAR(255),"+
					"author VARCHAR(255),"+
					"subject VARCHAR(255),"+
					"price DOUBLE,"+
					"isbn VARCHAR(50),"+
					"PRIMARY KEY (book_id))";
			String sql3 = "CREATE TABLE copies" +
					"(copy_id INT,"+
					"book_id INT, "+
					"rack VARCHAR(50),"+
					"status VARCHAR(15),"+
					"PRIMARY KEY (copy_id),"+
					"FOREIGN KEY (book_id) REFERENCES books(book_id))";

			String sql4 = "CREATE TABLE issuerecords" +
					"(issue_id INT," +
					"copy_id INT,"+
					"user_id INT,"+
					"issue_date DATETIME,"+
					"return_duedate DATETIME,"+
					"return_date DATETIME,"+
					"fine DOUBLE,"+
					"PRIMARY KEY (issue_id),"+
					"FOREIGN KEY (user_id) REFERENCES users(user_id),"+
					"FOREIGN KEY (copy_id) REFERENCES copies(copy_id))";

			String sql5 = "CREATE TABLE payments" +
					"(payment_id INT, "+
					"user_id INT,"+
					"amount DOUBLE,"+
					"type VARCHAR(20),"+
					"transanction_time DATETIME,"+
					"nextpayement_duedate DATETIME,"+
					"PRIMARY KEY (payment_id),"+
					"FOREIGN KEY (user_id) REFERENCES users(user_id))";
					
			stmt.executeUpdate(sql);
			System.out.println("Table USERs Created");			
			stmt.executeUpdate(sql2);
			System.out.println("Table BOOKs Created");	
			stmt.executeUpdate(sql3);
			System.out.println("Table COPIEs Created");
			stmt.executeUpdate(sql4);
			System.out.println("Table ISSUERECORDs Created");
			stmt.executeUpdate(sql5);
			System.out.println("Table PAYMENTs Created");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

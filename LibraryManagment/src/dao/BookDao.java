package dao;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.Book;
import utils.DBUtil;

public class BookDao implements Closeable {
	private Connection connection;
	private PreparedStatement stmtInsert;
	private PreparedStatement stmtUpdate;
	private PreparedStatement stmtDelete;
	private PreparedStatement stmtSelect;
	private PreparedStatement stmtSearch;
	private PreparedStatement stmtEditBook;

	public BookDao() throws Exception {
		this.connection = DBUtil.getConnection();
		this.stmtInsert = this.connection.prepareStatement("INSERT INTO books VALUES(?,?,?,?,?,?)");
		this.stmtUpdate = this.connection.prepareStatement("UPDATE books SET name=?,author=?,subject=?,price=?,isbn=? where book_id=?");
		this.stmtDelete = this.connection.prepareStatement("DELETE FROM books WHERE book_id=?");
		this.stmtSelect = this.connection.prepareStatement("SELECT * FROM books");
		this.stmtSearch = this.connection.prepareStatement("SELECT * FROM books WHERE LOCATE(?,name) != 0");
		this.stmtEditBook=this.connection.prepareStatement("SELECT * FROM books WHERE book_id=?");
	}

	// insert
	public int insert(Book book) throws Exception {
		String query = "Select max(book_id) as max from books ";
		PreparedStatement pst = connection.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		int num = 0;
		while (rs.next())
			num = rs.getInt("max") + 1;
		book.setBookId(num);
		this.stmtInsert.setInt(1, book.getBookId());
		this.stmtInsert.setString(2, book.getName());
		this.stmtInsert.setString(3, book.getAuthor());
		this.stmtInsert.setString(4, book.getSubject());
		this.stmtInsert.setDouble(5, book.getPrice());
		this.stmtInsert.setString(6, book.getIsbn());
		rs.close();
		return this.stmtInsert.executeUpdate();
	}

	// update
	public void update(Book book){

			try {
				this.stmtUpdate.setString(1, book.getName());
				this.stmtUpdate.setString(2, book.getAuthor());
				this.stmtUpdate.setString(3, book.getSubject());
				this.stmtUpdate.setDouble(4, book.getPrice());
				this.stmtUpdate.setString(5,book.getIsbn());
				this.stmtUpdate.setInt(6,book.getBookId());
				
				this.stmtUpdate.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Records of Book are updated !!!");
	}

	// delete
	public int delete(int bookId) throws Exception {
		this.stmtDelete.setInt(1, bookId);
		return this.stmtDelete.executeUpdate();
	}

	// getBooks
	public List<Book> getBooks() throws Exception {
		List<Book> bookList = new ArrayList<>();
		try (ResultSet rs = this.stmtSelect.executeQuery();) {
			while (rs.next())
				bookList.add(new Book(rs.getInt("book_id"), rs.getString("name"), rs.getString("author"),
						rs.getString("subject"), rs.getDouble("price"), rs.getString("isbn")));
			bookList.forEach(System.out::println);
		}
		return bookList;
	}
	
	public List<Book> search(String bookName) throws Exception {
		this.stmtSearch.setString(1, bookName);
		List<Book> bookFind = new ArrayList<>();
		try(ResultSet rs = this.stmtSearch.executeQuery();) {
			while (rs.next())
				bookFind.add(new Book(rs.getInt("book_id"), rs.getString("name"), rs.getString("author"),
						rs.getString("subject"), rs.getDouble("price"), rs.getString("isbn")));
			bookFind.forEach(System.out::println);
		}
		return bookFind;
		}
	
	@Override
	public void close() throws IOException {
		try {
			this.stmtInsert.close();
			this.stmtUpdate.close();
			this.stmtDelete.close();
			this.stmtSelect.close();
			this.connection.close();
		} catch (SQLException cause) {
			throw new IOException(cause); // Exception Chaining
		}
	}

	public Book getBookByID(int nextInt) {
		try {
			this.stmtEditBook.setInt(1, nextInt);
			ResultSet rs = stmtEditBook.executeQuery();
			Book book = new Book();
			while (rs.next()) {
				book.setBookId(rs.getInt(1));
				book.setName(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setSubject(rs.getString(4));
				book.setPrice(rs.getDouble(5));
				book.setIsbn(rs.getString(6));
			}
			rs.close();
			return book;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}

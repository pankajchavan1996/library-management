package pojo;

public class Book {
	private int bookId;
	private String name;
	private String author;
	private String subject;
	private Double price;
	private String isbn;
	
	public Book() {}
	public Book(String name, String author, String subject, Double price, String isbn) {
		this.name = name;
		this.author = author;
		this.subject = subject;
		this.price = price;
		this.isbn = isbn;
	}
	public Book(int bookId, String name, String author, String subject, Double price, String isbn) {
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.subject = subject;
		this.price = price;
		this.isbn = isbn;
	}
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	@Override
	public String toString() {
		return String.format("%-6d%-10s%-25s%-12s%-10.2f%-10s", this.bookId, this.name, this.author, this.subject, this.price, this.isbn);
	}
	
}

package pojo;

public class BookCopy {
	private int copyId;
	private int bookId;
	private String rack;
	private String status;
	
	public BookCopy(int copyId, int bookId, String rack, String status) {
		this.copyId = copyId;
		this.bookId = bookId;
		this.rack = rack;
		this.status = status;
	}
	public BookCopy() {	}
	public int getCopyId() {
		return copyId;
	}
	public void setCopyId(int copyId) {
		this.copyId = copyId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getRack() {
		return rack;
	}
	public void setRack(String rack) {
		this.rack = rack;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return String.format("%-6d%-6d%-7s%-10s%", this.copyId, this.bookId, this.rack, this.status);
	}
	
	
}

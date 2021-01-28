package utils;

import java.io.IOException;
import java.util.Scanner;

import dao.BookDao;
import pojo.Book;

public class BookUtil {
	static Scanner sc = new Scanner(System.in);

	public static void addBook() {
		try (BookDao dao = new BookDao();) {
			Book book = BookUtil.acceptBook();
			int res = dao.insert(book);
			System.out.println(res + " book inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Book acceptBook() {

		System.out.print("Enter Book Name : ");
		String name = sc.nextLine();
		System.out.print("Enter Author Name : ");
		String author = sc.nextLine();
		System.out.print("Enter Subject Name : ");
		String subject = sc.nextLine();
		System.out.print("Enter Price : ");
		Double price = sc.nextDouble();
		sc.nextLine();
		System.out.print("Enter ISBN : ");
		String isbn = sc.nextLine();
		return new Book(name, author, subject, price, isbn);

	}

	public static void findBook() {
		try (BookDao dao = new BookDao();) {
			System.out.print("Enter Book Name : ");
			sc.nextLine();
			String bookName = sc.nextLine();
			dao.search(bookName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static int editBookMenu() {
		System.out.println("0. Exit");	
		System.out.println("1. Update Name  ");
		System.out.println("2. Update Author  ");
		System.out.println("3. Update Subject ");
		System.out.println("4. Update Price ");
		System.out.println("5. Update ISBN ");
		System.out.print("Enter Choice : ");
		return sc.nextInt();
	}
	public static void editBook() throws Exception {
		System.out.print("Enter Book Id to edit the book : ");
		try(BookDao dao = new BookDao();){
		Book book = dao.getBookByID(sc.nextInt());	
		int choice;
		while((choice=BookUtil.editBookMenu())!=0) {			
			 switch(choice) { 
		        
		        case 1:  //Name
		        	sc.nextLine();
	        	    System.out.print("Enter new name : ");
	        	    book.setName(sc.nextLine());
		        	break;
		        case 2:
		        	System.out.print("Enter new author : ");
		        	book.setAuthor(sc.nextLine());		        	
		        	break;
		        	
		        case 3:
		        	System.out.print("Enter new subject : ");
		        	book.setSubject(sc.nextLine());		        	
		        	break;
		        	
		        case 4:
		        	System.out.print("Enter new price : ");
		        	book.setPrice(sc.nextDouble());		             	
		        	break;
		        	
		        case 5:
		        	sc.nextLine();
		        	System.out.print("Enter new ISBN : ");
		        	book.setIsbn(sc.nextLine());		             	
		        	break;
			}
		}
		dao.update(book);
		}
	}

}

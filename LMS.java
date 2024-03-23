/*
202420-CEN-3024C-24668
Albert Gonzalez
LMS Class
This is the code that the user interacts with. They are first prompted with a welcome
message and some basic instructions. They are then in a while loop waiting for them to quit.
They have the options of adding or removing a book, viewing the collection of books, or close
the application. I use a switch statement to determine the applications response to the user.
This needs to be broken down some more.
*/
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;

public class LMS {
    public static void main(String[] args) throws IOException, SQLException {

        DatabaseService db = new DatabaseService("books.db");
        BookCollection bookCollection = new BookCollection();

        String welcomeMessage = "\n \nWelcome to the Orange County LMS.\n \n";
        String addBookMessage = "Please type in data file location:  ";
        String removeBookByIDMessage = "Enter book Barcode to remove Book: ";
        String removeBookByTitleMessage = "Enter book title to remove Book: ";
        String checkOutBookMessage = "Enter the book title to check Out";
        String checkInBookTitleMessage = "Enter the book title to check In";
        String checkInBookAuthorMessage = "Enter the book author to check In";
        String invalidResponse = "This response is invalid.\n";

        boolean appRunning = true;
        System.out.println(welcomeMessage);

        while (appRunning) {
            Scanner sc = new Scanner(System.in);
            System.out.println(addBookMessage);
            String userResponse = sc.nextLine();
            bookCollection.collectBooks(userResponse);
            bookCollection.printBookList();
            System.out.println(removeBookByIDMessage);
            int barcode = Integer.parseInt(sc.nextLine());
            bookCollection.dropBook(barcode);
            bookCollection.printBookList();
            System.out.println(removeBookByTitleMessage);
            String bookTitle = sc.nextLine();
            bookCollection.dropBook(bookTitle);
            bookCollection.printBookList();
            System.out.println(checkOutBookMessage);
            bookTitle = sc.nextLine();
            bookCollection.checkOut(bookTitle);
            bookCollection.printBookList();
            System.out.println(checkInBookTitleMessage);
            bookTitle = sc.nextLine();
            System.out.println(checkInBookAuthorMessage);
            String bookAuthor = sc.nextLine();
            bookCollection.checkIn(bookTitle, bookAuthor);
            bookCollection.printBookList();
            appRunning = false;


        }
        System.out.println("GoodBye. :)");
    }
}
import java.util.Date;

/*
202420-CEN-3024C-24668
Albert Gonzalez
Book Class
This defines what a book is at and holds all data needed for the database.
*/
public class Book {
    int id;
    String title;
    String author;
    String checkedOut;
    Date returnDate;

    
    Book(int id, String title, String author, String checkedOut, Date returnDate)
    {
        this.id = id;
        this.title = title;
        this.author = author;
        this.checkedOut = checkedOut;
        this.returnDate = returnDate;
    }
}

/*
202420-CEN-3024C-24668
Albert Gonzalez
Collection Class
This may be doing more than it should. It 'collects' the books from
the collection.txt file and updates it. Converts the file into a
list of 'Books' which is what the user manipulates until it's saved.
*/
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class BookCollection {
    public int lastUsedID = 0;
    private List<Book> bookCollection = new ArrayList<>();

    BookCollection() throws IOException  {
        collectBooks();
        System.out.println(lastUsedID);
    }

    /*
    addBook() takes in the books title and author and assigns it an ID. It is then pushed to the collections list.
    It has no return value, but outputs a response for the user.
     */
    public void addBook(String title, String Author) throws IOException {
        lastUsedID++;
        Book book = new Book(lastUsedID, title, Author);
        bookCollection.add(book);
        System.out.println(book.title + " by " + book.author + " has been added to your collection.");
        saveCollection();
    }

    /*
    dropBook() takes an ID which it uses to find a book from the collections list and remove it.
    It has no return value, but outputs a response for the user.
     */
    public void dropBook(int id) throws IOException {
        for(Book book : bookCollection) {
            if(book.id == id) {
                bookCollection.remove(book);
                System.out.println(book.title + " by " + book.author + " has been removed from your collection.");
            }
        }
        saveCollection();
    }

    /*
    printBookList() displays the collection of books to the user.
    It has no return value, but outputs a response for the user.
     */
    public void printBookList() {
        for(Book book : bookCollection) {
            System.out.println(book.id + ", " + book.title + ", " + book.author);
        }
    }

    /*
    collectBooks() reads the collections.txt file, converts it into a list of books
    assigned to the 'library' variable, and updates the 'lastUsedID' variable.
    It has no return value.
     */
    public void collectBooks() throws IOException {
        BufferedReader saveFile = null;

        try {
            saveFile = new BufferedReader(new FileReader("collection.txt"));
            String line = null;

            while((line = saveFile.readLine()) != null) {
                String[] bookDetails = line.split(",");
                int bookID = Integer.parseInt(bookDetails[0]);

                if(bookID>lastUsedID) lastUsedID = bookID;

                Book newbook = new Book(bookID, bookDetails[1],bookDetails[2]);
                bookCollection.add(newbook);
            }   
        } 
        catch(FileNotFoundException ex) { System.out.println(ex); } 
        finally { if(saveFile != null) {saveFile.close();}
        }
    }

    /*
    saveCollection() overwrites the collection.txt with list of books(library).
    It has no return value.
     */
    public void saveCollection() throws IOException {
        Writer collectionUpdater = null;
        try {
            collectionUpdater = new FileWriter("collection.txt", false);
            for (Book book : bookCollection) {
                collectionUpdater.write(book.id + "," + book.title + "," + book.author + "\n");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            collectionUpdater.close();
        }
    }
}

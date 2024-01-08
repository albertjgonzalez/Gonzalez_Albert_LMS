import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Collection {
    public int lastUsedID = 0;
    private List<Book> bookCollection = new ArrayList<>();

    Collection() throws IOException  {
        collectBooks();
    }

    public void addBook(String title, String Author) throws IOException {
        Book book = new Book(lastUsedID++, title, Author);
        bookCollection.add(book);
        System.out.println(book.title + " by " + book.author + " has been added to your collection.");
        saveCollection();
    }

    public void dropBook(int id) throws IOException {
        for(Book book : bookCollection) {
            if(book.id == id) {
                bookCollection.remove(book);
                System.out.println(book.title + " by " + book.author + " has been removed from your collection.");
            }
        }
        saveCollection();
    }

    public void printBookList() {
        for(Book book : bookCollection) {
            System.out.println(book.getId() + ", " + book.getTitle() + ", " + book.getAuthor());
        }
    }

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

    public void saveCollection() throws IOException {
        try{
            Writer collectionUpdater = new FileWriter("collection.txt", false);
            for (Book book : bookCollection) {
                collectionUpdater.write(book.id + "," + book.title + "," + book.author + "\n");
            }
            collectionUpdater.close();
        }
        catch(Exception ex){  }
        finally{  }

    }
}

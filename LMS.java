/*
202420-CEN-3024C-24668
Albert Gonzalez
LMS Class
This is the code that the user interacts with. They are first prompted with a welcome
message and some basic instructions. They are then in a while loop waiting for them to quit.
They have the options of adding or removing a book, viewing the collection of books, or close
the application. I use a switch statement to determine the applications response to the user.
This needs to broken down some more.
*/
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

public class LMS {
    public static void main(String[] args) throws IOException {

        BookCollection bookCollection = new BookCollection();

        String welcomeMessage = "\n \nWelcome to the Orange County LMS.\n \n";
        String instructions = "Please type in data file location:  ";
        String invalidResponse = "This response is invalid.\n";

        boolean appRunning = true;
        System.out.println(welcomeMessage);

        while (appRunning) {
            Scanner sc = new Scanner(System.in);
            System.out.println(instructions);
            String userResponse = sc.nextLine();
            bookCollection.collectBooks(userResponse);
            
            
        }
        System.out.println("GoodBye. :)");
    }
}
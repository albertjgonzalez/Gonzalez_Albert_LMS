/*
202420-CEN-3024C-24668
Albert Gonzalez
LMS Class
This is the code that the user interacts with. They are first prompted with a welcome
message and some basic instructions. They are then in a while loop waiting for them to quit.
They have the options of adding or removing a book, viewing the collection of books, or close
the application. I use a switch switch statement to determine the applications response to the user.
*/
import java.io.IOException;

public class LMS {
    public static void main(String[] args) throws IOException {

        Collection library = new Collection();

        String welcomeMessage = "\n \nWelcome the Orange County LMS.\n \n";
        String instructions = "Please type in one of the following options:\n \n" +
                            "-Add Book \n-Remove Book \n-View Current Collection\n";
        String invalidResponse = "This response is invalid\n";



    }
}
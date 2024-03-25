/*
202420-CEN-3024C-24668
Albert Gonzalez
LMSGUI Class
This class controls the main form. You can load the collection,
remove a book, and check a book in or out. I need to create a
controller for this to separate some of the functionality.
*/
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LMSGUI extends JFrame {
    private JTextField enterCollectionNameTextField;
    private JButton loadButton;
    private JTextField IDTextField;
    private JTextField titleTextField1;
    private JButton dropButton;
    private JTextField IDTextField1;
    private JTextField titleTextField2;
    private JButton checkOutButton;
    private JTextField checkInBookTextField;
    private JTextField IDTextField2;
    private JTextField titleTextField3;
    private JButton checkInButton;
    private JTable table1;
    private JPanel libraryForm;
    private BookCollection bookCollection;

    public LMSGUI() throws IOException {
        bookCollection = new BookCollection();
        setContentPane(libraryForm);
        setTitle("LMS");
        setSize(450, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Title");
        model.addColumn("Author");
        model.addColumn("CheckedOut");
        model.addColumn("ReturnDate");
        table1.setModel(model);

        /*
        This function is ran when user clicks the load button.
        This finds a collection based on user input and loads
        data to table.
         */
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String collectionLocation = enterCollectionNameTextField.getText();
                model.setRowCount(0);
                try {
                    Files.lines(Paths.get(collectionLocation))
                            .map(line -> line.split(","))
                            .forEach(parts -> {
                                if (parts.length >= 3) {
                                    String id = parts[0];
                                    String title = parts[1];
                                    String author = parts[2];
                                    model.addRow(new Object[]{id, title, author, "", ""});
                                } else {
                                    System.err.println("Invalid line format: " + String.join(", ", parts));
                                }
                            });
                    bookCollection.collectBooks(collectionLocation);
                    updateTableWithNewCollection(model);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } finally {
                    bookCollection.printBookList();
                }
            }
        });

        /*
        This function removes a book from the book
        collection based on user input. It then reloads
        the table.
         */
        dropButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookID = IDTextField.getText().trim();
                String title = titleTextField1.getText().trim();
                if (!bookID.isEmpty()) {
                    try {
                        int id = Integer.parseInt(bookID);
                        bookCollection.dropBook(id);
                        updateTableWithNewCollection(model);
                    } catch (NumberFormatException | IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error: Unable to drop book.");
                    }
                } else if (!title.isEmpty()) {
                    try {
                        bookCollection.dropBook(title);
                        updateTableWithNewCollection(model);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error: Unable to drop book.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter ID or title to drop a book.");
                }
            }
        });

        /*
        This function checks out a book from the book
        collection based on user input. It then reloads
        the table.
         */
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookID = IDTextField1.getText().trim();
                String title = titleTextField2.getText().trim();
                LocalDate today = LocalDate.now();
                LocalDate returnDate = today.plusWeeks(2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                boolean found = false;

                for (int i = 0; i < model.getRowCount(); i++) {
                    String id = model.getValueAt(i, 0).toString();
                    String bookTitle = model.getValueAt(i, 1).toString();
                    if (id.equals(bookID) || bookTitle.equalsIgnoreCase(title)) {
                        model.setValueAt("X", i, 3);
                        model.setValueAt(returnDate.format(formatter), i, 4);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(null, "Book not found.");
                }
            }
        });

        /*
        This function checks in a book from the book
        collection based on user input. It then reloads
        the table.
         */
        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookID = IDTextField2.getText().trim();
                String title = titleTextField3.getText().trim();
                boolean found = false;

                for (int i = 0; i < model.getRowCount(); i++) {
                    String id = model.getValueAt(i, 0).toString();
                    String bookTitle = model.getValueAt(i, 1).toString();
                    if (id.equals(bookID) || bookTitle.equalsIgnoreCase(title)) {
                        model.setValueAt("", i, 3);
                        model.setValueAt("", i, 4);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(null, "Book not found for check-in.");
                }
            }
        });

    }

    /*
    This function is ran after most methods
    to update the table.
     */
    private void updateTableWithNewCollection(DefaultTableModel model) {
        model.setRowCount(0);
        for (Book book : bookCollection.bookCollection) {
            model.addRow(new Object[]{book.id, book.title, book.author, "", ""});
        }
    }

    public static void main(String[] args) throws IOException {
        new LMSGUI();
    }
}

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
    private Connection connection;

    public DatabaseService(String databaseFileName) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseFileName);
            createTablesIfNotExist();
        } catch (ClassNotFoundException ex) {
            System.err.println("SQLite JDBC driver not found");
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) { connection.close(); }
        } catch (SQLException ex) {
            System.err.println("Error closing database connection: " + ex.getMessage());
        } 
    }

    public void createTablesIfNotExist() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS books (" +
                     "id INTEGER PRIMARY KEY," +
                     "title TEXT NOT NULL," +
                     "author TEXT NOT NULL," +
                     "genre TEXT NOT NULL," +
                     "status CHAR NOT NULL," +
                     "dueDate DATETIME NOT NULL)";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }
}

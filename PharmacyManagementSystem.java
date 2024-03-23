import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PharmacyManagementSystem {
    // Database connection parameters
	    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	    static final String DB_URL = "jdbc:mysql://localhost/pharmacydb";

	    static final String USER = "root";
	    static final String PASS = "kruthika@123";


    // JDBC Connection object
    private Connection connection;

    // Constructor to establish connection
    public PharmacyManagementSystem() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert data into the database
    public void insertData(String query) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve data from the database
    public void retrieveData(String query) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                // Process retrieved data
                // Example: int id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update data in the database
    public void updateData(String query) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
            System.out.println("Data updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete data from the database
    public void deleteData(String query) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
            System.out.println("Data deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Close database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PharmacyManagementSystem pharmacyManagementSystem = new PharmacyManagementSystem();

        // Example usage:
        String insertQuery = "INSERT INTO drugs VALUES (15,'saridon', 80, 50.80)";
        pharmacyManagementSystem.insertData(insertQuery);

        String retrieveQuery = "SELECT * FROM drugs";
        pharmacyManagementSystem.retrieveData(retrieveQuery);

        String updateQuery = "UPDATE drugs SET price = 12.00 WHERE name = 'Paracetamol'";
        pharmacyManagementSystem.updateData(updateQuery);

        String deleteQuery = "DELETE FROM drugs WHERE name = 'Paracetamol'";
        pharmacyManagementSystem.deleteData(deleteQuery);

        pharmacyManagementSystem.closeConnection();
    }
}

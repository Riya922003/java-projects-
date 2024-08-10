import java.sql.*;

public class StudentCRUD {
    private static final String URL = "jdbc:mysql://localhost:3306/database_name";
    private static final String USER = user-name;
    private static final String PASSWORD = your password ;

    // JDBC variables for opening and managing connection
    private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established.");
            createTable();
            insertRecord(1, "John Doe", 20);
            insertRecord(2, "Jane Smith", 22);
            insertRecord(3, "Mike Johnson", 19);
            retrieveRecords();
            updateRecord(1, "John Doe", 21);
            retrieveRecords();
            deleteRecord(3);
            retrieveRecords();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS student (" +
                "id INT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "age INT)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table 'students' created.");
        }
    }

    private static void insertRecord(int id, String name, int age) throws SQLException {
        String insertSQL = "INSERT INTO student (id, name, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Record inserted: " + id + ", " + name + ", " + age);
        }
    }

    private static void retrieveRecords() throws SQLException {
        String selectSQL = "SELECT * FROM student";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {
            System.out.println("Students Table:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }
        }
    }

    private static void updateRecord(int id, String name, int age) throws SQLException {
        String s = ", age = 21  WHERE id = 1";
        String updateSQL = "UPDATE student SET name = Riya Gupta, age = 21  WHERE id = 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            System.out.println("Record updated: ID = " + id);
        }
    }

    private static void deleteRecord(int id) throws SQLException {
        String deleteSQL = "DELETE FROM student WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Record deleted: ID = " + id);
        }
    }
}

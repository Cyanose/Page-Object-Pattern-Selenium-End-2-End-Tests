package utilities;

import java.sql.*;

public class DatabaseUtils {
    private static final String SERVER = "localhost";
    private static final int PORT = 13306;
    private static final String DATABASE_NAME = "testData";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    private Connection conn;

    public DatabaseUtils(Connection conn){
        this.conn = conn;
        prepareDatabase();
    }

    public static DatabaseUtils openConnection(){
        return new DatabaseUtils(openConenction(SERVER,PORT,DATABASE_NAME,USERNAME,PASSWORD));
    }

    public void closeConnection() {
        try {

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Object[][] executeSelectQuery(String sqlQuery) {

        Object[][] data = null;

        try {
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery(sqlQuery);
            result.last();

            int rowCount = result.getRow();
            int cellCount = result.getMetaData().getColumnCount();
            data = new Object[rowCount][cellCount];
            result.first();

            int row = 0;
            do {
                String[] parsedData = new String[2];
                parsedData[0] = result.getString("login");
                parsedData[1] = result.getString("password");

                for (int cell = 0; cell < 2; cell++) {
                    data[row][cell] = parsedData[cell];
                }
                row++;
            } while (result.next());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;

    }

    public void insertDataToTable(String login, String password) {

        Statement s = null;
        try {
            s = this.conn.createStatement();
            String query = String.format("INSERT into Users values('%s', '%s');", login, password);
            s.execute(query);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(1);
        }
    }

    private static Connection openConenction(String server, int portNum, String database, String userName, String password) {
        Connection conn = null;
        try {

            String url = String.format("jdbc:mysql://%s:%d/%s", server, portNum, database);

            conn = DriverManager.getConnection(url, userName, password);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void prepareDatabase() {
        Statement s = null;
        try {
            s = this.conn.createStatement();
            String query = "CREATE DATABASE IF NOT EXISTS testData;";
            s.execute(query);
            query = "use testData;";
            s.execute(query);
            query = "CREATE TABLE IF NOT EXISTS Users (login varchar(255) PRIMARY KEY, password varchar(255));";
            s.execute(query);
            query = "INSERT IGNORE INTO Users (login, password) values('test@test.com','Test1!');";
            s.execute(query);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(1);
        }
    }

}

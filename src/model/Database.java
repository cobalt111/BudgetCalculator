package model;

import java.sql.*;
import java.util.prefs.Preferences;

public class Database {

    private Preferences preferences;

    private static final String DATABASE_URL = "jdbc:oracle:thin:@//oracle.csep.umflint.edu:1521/csep",
        DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
    private String username, password;

    private static Database database = new Database();

    private Database() {
        preferences = Preferences.userNodeForPackage(Database.class);
    }

    public static Database getDatabaseInstance() {
        return database;
    }

    public boolean credentialsAreValid(String username, String password) {
        return false;
    }

    public boolean startConnection() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return false;
        }

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, username, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
            return true;
        }
        else {
            System.out.println("Failed to make connection!");
            return false;
        }
    }

    public void setCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

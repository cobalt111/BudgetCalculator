package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class Database {

    private Preferences preferences;

    private static final String DATABASE_URL = "jdbc:oracle:thin:@//oracle.csep.umflint.edu:1521/csep",
        DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
    private static final String DATABASE_USERNAME = "timothco", DATABASE_PASSWORD = "timothco";
    public User user;

    private static Database database = new Database();

    private Database() {
        preferences = Preferences.userNodeForPackage(Database.class);
    }

    public static Database getDatabaseInstance() {
        return database;
    }

    public Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
        }

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
            return connection;
        }
        else {
            System.out.println("Failed to make connection!");
            return null;
        }
    }

    //region Login
    public boolean credentialsAreValid(User user) {

    }

    public void setLoginCredentials(User user) {
        this.user = user;
        private User getLoginInfo() {
            User user = ;



            return user;
        }
    }
    //endregion




}

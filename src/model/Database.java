package model;

import oracle.jdbc.OracleTypes;

import java.sql.*;
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
    public String credentialsAreValid(User user) {
        Connection connection = getConnection();
        String result = null;
        try {
            CallableStatement statement = connection.prepareCall("{ ? = call verifyUser(?, ?)}");
            statement.registerOutParameter(1, Types.VARCHAR);
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.execute();
            result = statement.getString(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void setCredentials(User user) {
        this.user = user;
    }

    public void setupUser(int[] amounts) {
        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall("{call setupUser(?, ?, ?, ?, ?, ?, ?, ?)}");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, amounts[0]);
            statement.setInt(4, amounts[1]);
            statement.setInt(5, amounts[2]);
            statement.setInt(6, amounts[3]);
            statement.setInt(7, amounts[4]);
            statement.setInt(8, amounts[5]);
            statement.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int[] getCurrentBudget() {
        int[] amounts = new int[6];

        Connection connection = getConnection();
        try {
            CallableStatement statement = connection.prepareCall("{call getCurrentAccountsState(?, ?)}");
            statement.setString(1, user.getUsername());
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            System.out.println(statement.execute());
            ResultSet resultSet = (ResultSet) statement.getObject(2);
            while (resultSet.next()) {
                amounts[0] += resultSet.getInt("Accounts");
            }

            statement = connection.prepareCall("{call getCurrentIncomeState(?, ?)}");
            statement.setString(1, user.getUsername());
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            System.out.println(statement.execute());
            resultSet = (ResultSet) statement.getObject(2);
            while (resultSet.next()) {
                amounts[1] += resultSet.getInt("Income");
            }

            statement = connection.prepareCall("{call getCurrentExpensesState(?, ?)}");
            statement.setString(1, user.getUsername());
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            System.out.println(statement.execute());
            resultSet = (ResultSet) statement.getObject(2);
            while (resultSet.next()) {
                amounts[2] += resultSet.getInt("Expenses");
            }

            statement = connection.prepareCall("{call getCurrentSubscriptionsState(?, ?)}");
            statement.setString(1, user.getUsername());
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            System.out.println(statement.execute());
            resultSet = (ResultSet) statement.getObject(2);
            while (resultSet.next()) {
                amounts[3] += resultSet.getInt("Subscriptions");
            }

            statement = connection.prepareCall("{call getCurrentDepositsState(?, ?)}");
            statement.setString(1, user.getUsername());
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            System.out.println(statement.execute());
            resultSet = (ResultSet) statement.getObject(2);
            while (resultSet.next()) {
                amounts[4] += resultSet.getInt("Deposits");
            }

            statement = connection.prepareCall("{call getCurrentWithdrawalsState(?, ?)}");
            statement.setString(1, user.getUsername());
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            System.out.println(statement.execute());
            resultSet = (ResultSet) statement.getObject(2);
            while (resultSet.next()) {
                amounts[5] += resultSet.getInt("Withdrawals");
            }

//            amounts[0] = resultSet.getInt("Accounts");
////            resultSet.next();
//            amounts[1] = resultSet.getInt("Income");
////            resultSet.next();
//            amounts[2] = resultSet.getInt("Expenses");
////            resultSet.next();
//            amounts[3] = resultSet.getInt("Subscriptions");
////            resultSet.next();
//            amounts[4] = resultSet.getInt("Deposits");
////            resultSet.next();
//            amounts[5] = resultSet.getInt("Withdrawals");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        for (int current : amounts) System.out.println(current);

        return amounts;
    }

    public void updateBudget(String tableName, int amount) {
        Connection connection = getConnection();
        CallableStatement statement;
        try {
            switch (tableName) {
                case "Accounts":
                    statement = connection.prepareCall("{call addToAccounts(?, ?)}");
                    statement.setString(1, user.getUsername());
                    statement.setInt(2, amount);
                    statement.executeQuery();
                    break;
                case "Income":
                    statement = connection.prepareCall("{call addToIncome(?, ?)}");
                    statement.setString(1, user.getUsername());
                    statement.setInt(2, amount);
                    statement.executeQuery();
                    break;
                case "Expenses":
                    statement = connection.prepareCall("{call addToExpenses(?, ?)}");
                    statement.setString(1, user.getUsername());
                    statement.setInt(2, amount);
                    statement.executeQuery();
                    break;
                case "Subscriptions":
                    statement = connection.prepareCall("{call addToSubscriptions(?, ?)}");
                    statement.setString(1, user.getUsername());
                    statement.setInt(2, amount);
                    statement.executeQuery();
                    break;
                case "Deposits":
                    statement = connection.prepareCall("{call addToDeposits(?, ?)}");
                    statement.setString(1, user.getUsername());
                    statement.setInt(2, amount);
                    statement.executeQuery();
                    break;
                case "Withdrawals":
                    statement = connection.prepareCall("{call addToWithdrawals(?, ?)}");
                    statement.setString(1, user.getUsername());
                    statement.setInt(2, amount);
                    statement.executeQuery();
                    break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    private User getLoginInfo() {
//        Connection connection = getConnection();
//
//        try {
//            CallableStatement statement = connection.prepareCall("{ ? call = verifyUser(?, ?)}");
//            statement.registerOutParameter(1, );
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//        User user = ;
//
//
//
//        return user;
//    }
    //endregion




}

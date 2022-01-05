package restaurant.model.utilities;

import restaurant.controller.utilities.ColumnType;
import restaurant.controller.utilities.Convert;

import java.sql.*;
import java.util.Map;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Class for SQL operations
 */


public class SQLDatabase implements IDatabase {
    Connection connection;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        connection.close();
    }

    /*
     Method to connect to the database
     @params: String hostName: the name of the host
              String databaseName: the name of the database
              String userName: the username for authentication
              String passWord: the password for authentication
     @return: none
     */

    @Override
    public void connect(String hostName, String databaseName, String userName, String passWord) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + hostName + "/" + databaseName,
                    userName, passWord);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * Method to retrieve data from the database
     * @params: String query: the query string to be executed
     * @return: ResultSet result: the result set of the executed query
     */

    @Override
    public ResultSet retrieve(String query) {
        ResultSet result = null;
        try {
            result = connection.createStatement().executeQuery(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
        return result;
    }

    /*
     Method to update the database
     @params: String query: the query string to be executed
     @return: no return
     */

    @Override
    public void update(String query) {
        try {
            connection.createStatement().executeUpdate(query);;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
    }

    /*
     Method to retrieve results of the query
     @params: String: the query string to be executed
              Map<String, ColumnType>: the table structure of the ResultSet
     @return: Map<Integer, Map<String, Object>>: the result table in a Map format
     */
    @Override
    public Map<Integer, Map<String, Object>> retrieve(String query, Map<String, ColumnType> tableStructure) {
        Map<Integer, Map<String, Object>> table = null; // Table to hold the converted results
        try {
            table = Convert.convertResultSet(connection.createStatement().executeQuery(query), tableStructure);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return table;
    }

    /*
     Method to insert data to the database, and retrieve the ID of the inserted row
     @params: String: the query string to be executed
     @return: int: the ID of the inserted row. Returns -1 if insert fails
     */
    @Override
    public int insertAndGetId(String query) {
        int id = -1;    // The ID to be returned
        try {
            PreparedStatement statement = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next())
                id = rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return id;
    }

    /*
     Method to insert data to the database
     @params: String: the query string to be executed
     @return: int: the number of rows inserted. Returns -1 if insert fails
     */
    @Override
    public int insert(String query) {
        int rows = -1;  // The ID to be returned
        try {
            rows = connection.createStatement().executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rows;
    }
}



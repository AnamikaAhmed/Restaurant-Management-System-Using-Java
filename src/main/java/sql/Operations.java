package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Operations implements IOperations {
  ResultSet resultSet=null;

  /***
   * Wrapper method to select Database
   * @param dbName Name of database to be connected
   * @param statement statement returned from Connection
   * @throws SQLException
   */
  public void selectDB(String dbName,Statement statement) throws SQLException {
    try {
      statement.execute("USE " + dbName + ";");
    } catch (SQLException ex){
      throw new SQLException("Cannot connect to "+dbName+" due to "+ex);
    }
  }

  /***
   * Wrapper Method for SelectQueries
   * @param query SQL query to be executed
   * @param statement statement returned from Connection
   * @return Results from select query
   * @throws SQLException
   */
  public ResultSet SelectQueryExecuteQuery(String query,Statement statement) throws SQLException {
    try {
      resultSet = statement.executeQuery(query);
    } catch (SQLException ex) {
      throw new SQLException("Cannot execute the query due to "+ex);
    }

    return resultSet;
  }

  /***
   * Wrapper Method of Insert or admin queries
   * @param query SQL query to be executed
   * @param statement statement returned from Connection
   * @throws SQLException
   */
  public void insertQueryExecute(String query,Statement statement) throws SQLException {
    try {
      statement.execute(query);
    } catch (SQLException ex) {
      throw new SQLException("Cannot execute the query due to "+ex);
    }
  }
}

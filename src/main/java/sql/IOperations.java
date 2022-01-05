package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface IOperations {
  void selectDB(String dbName, Statement statement) throws SQLException;
  ResultSet SelectQueryExecuteQuery(String query,Statement statement) throws SQLException;
  void insertQueryExecute(String query,Statement statement) throws SQLException;
}


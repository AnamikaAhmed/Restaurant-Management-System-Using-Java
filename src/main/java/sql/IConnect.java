package sql;

import java.sql.SQLException;
import java.sql.Statement;

public interface IConnect {
  Statement openConnection(String domainName, String username, String password) throws SQLException, ClassNotFoundException;
  void closeConnection() throws SQLException;
}

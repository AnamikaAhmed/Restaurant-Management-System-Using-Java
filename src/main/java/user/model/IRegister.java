package user.model;

import java.sql.SQLException;

public interface IRegister {
  Boolean register(String userName, String address,
                          String email, String contactNumber, String password) throws SQLException, ClassNotFoundException;
  Boolean userExist(String email) throws UserExistException, SQLException, ClassNotFoundException;
}

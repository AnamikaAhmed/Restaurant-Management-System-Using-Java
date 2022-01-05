package user.model;

import java.sql.SQLException;

public interface IReset {
  Boolean replacePassword(String email, String oldPassword, String newPassword) throws SQLException;
  Boolean changePassword(String email, String newPassword) throws SQLException;
}

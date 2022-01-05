package user.view;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface IResetView {
  boolean changePassword() throws SQLException, NoSuchAlgorithmException;
  boolean newPassword() throws SQLException, NoSuchAlgorithmException;
}

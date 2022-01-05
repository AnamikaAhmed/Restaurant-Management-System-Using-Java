package user.controller;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface IReset {
  Integer forget(String emailId);
  Boolean newPassword(String emailID,Integer otp, String newPassword) throws SQLException, NoSuchAlgorithmException;
  Boolean changePassword(String email, String oldPassword, String newPassword) throws SQLException, NoSuchAlgorithmException;
}

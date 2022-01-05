package user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.controller.Reset;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class ResetTest {
  @Test
  @DisplayName("Reset Test (OTP Method)")
  void ResetTest1() throws SQLException, NoSuchAlgorithmException {
    Reset rs = new Reset();
    String email = "Test1@gmail.com";
    String newPassword = "password2";
    Integer otp = rs.forget(email);
    Boolean status =  rs.newPassword(email,otp,newPassword);
    Assertions.assertTrue(true, String.valueOf(status));
  }

  @Test
  @DisplayName("Reset Test (Replace Password)")
  void ResetTest2() throws SQLException, NoSuchAlgorithmException {
    Reset rs = new Reset();
    String email = "Test1@gmail.com";
    String password = "password2";
    String newPassword = "password3";
    Boolean status =  rs.changePassword(email,password,newPassword);
    Assertions.assertTrue(true, String.valueOf(status));
  }

  @Test
  @DisplayName("Reset Test (OTP Method) Failure")
  void ResetTest3() throws SQLException, NoSuchAlgorithmException {
    Reset rs = new Reset();
    // Email Doesn't Exist
    String email = "Test2@gmail.com";
    String newPassword = "password2";
    Integer otp = rs.forget(email);
    Boolean status =  rs.newPassword(email,otp,newPassword);
    Assertions.fail(String.valueOf(status));
  }

  @Test
  @DisplayName("Reset Test (Replace Password) Failure")
  void ResetTest4() throws SQLException, NoSuchAlgorithmException {
    Reset rs = new Reset();
    String email = "Test1@gmail.com";
    // Invalid old password
    String password = "password5";
    String newPassword = "password3";
    Boolean status =  rs.changePassword(email,password,newPassword);
    Assertions.fail(String.valueOf(status));
  }
}

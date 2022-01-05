package user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.controller.Login;
import user.model.InvalidCredentialsException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class LoginTest {
  @Test
  @DisplayName("Successful Login Test")
  void LoginTest1() throws NoSuchAlgorithmException, InvalidCredentialsException, SQLException {
    Login login = new Login();
    String email = "Test1@gmail.com";
    String password = "password3";
    boolean userName = login.login(email,password);
    Assertions.assertTrue(true, String.valueOf(userName));
  }

  @Test
  @DisplayName("Failed Login Test")
  void LoginTest2() throws NoSuchAlgorithmException, InvalidCredentialsException, SQLException {
    Login login = new Login();
    String email = "Test1@gmail.com";
    String password = "password5";
    boolean userName = login.login(email,password);
    Assertions.fail(String.valueOf(userName));
  }
}

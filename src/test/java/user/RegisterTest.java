package user;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import user.controller.Register;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class RegisterTest {

  @Test
  @DisplayName("Successful Registration Test")
  void RegisterTest1() throws SQLException, NoSuchAlgorithmException, ClassNotFoundException {
    Register rg = new Register();
    String userName = "Test1";
    String email = "Test12@gmail.com";
    String password = "password";
    String address = "address1";
    String contractNumber = "+1-123456789";
    String status =  rg.register(userName,address,email,contractNumber,password);
    Assertions.assertTrue(true, status);
  }

  @Test
  @DisplayName("Failed Registration Test")
  void RegisterTest2() throws SQLException, NoSuchAlgorithmException, ClassNotFoundException {
    Register rg = new Register();
    String userName = "Test1";
    // same email id
    String email = "Test12@gmail.com";
    String password = "password";
    String address = "address1";
    String contractNumber = "+1-123456789";
    String status =  rg.register(userName,address,email,contractNumber,password);
    Assertions.fail(status);
  }
}

package user.controller;
import user.model.InvalidCredentialsException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
/**
 * Group 1
 * @author: Aditya Arora
 * @description: Controller class for User Login
 */
public class Login {
  /***
   * Controller Login method
   * @param email Email of the user
   * @param password Password provided by the user
   * @return Status if the user logged in
   * @throws NoSuchAlgorithmException
   * @throws InvalidCredentialsException
   * @throws SQLException
   */
  public boolean login(String email, String password) throws NoSuchAlgorithmException, InvalidCredentialsException, SQLException {
    HashPassword hp = new HashPassword();
    user.model.Login login = new user.model.Login();
    String username = login.login(email,hp.hashPassword(password));

    return username != null;
  }
}

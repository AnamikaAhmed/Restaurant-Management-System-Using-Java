package user.view;

import restaurant.view.HomePage;
import user.controller.Login;
import user.model.InvalidCredentialsException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
/**
 * Group 1
 * @author: Aditya Arora
 * @description: View class for User Login
 */

public class LoginView {
  /***
   * Login method for presentation layer
   * @throws InvalidCredentialsException
   * @throws NoSuchAlgorithmException
   * @throws InvalidInputException
   * @throws SQLException
   */
  public LoginView() throws InvalidCredentialsException, NoSuchAlgorithmException, InvalidInputException, SQLException {
    Headings headings = new Headings();
    headings.signInHeading();

    InputViewOperations viewOperations = new InputViewOperations();
    viewOperations.enterLine("E Mail");
    String email = viewOperations.readFromConsole();
    viewOperations.enterLine("Password");
    String password = viewOperations.readFromConsole();

    Login lg = new Login();
    boolean status = lg.login(email,password);

    if(status){
      HomePage hp = new HomePage();
      hp.homePageRestaurant(email);
    } else {
      throw new InvalidCredentialsException("Oh! Your Email and password doesn't seem right");
    }
  }
}

package user.view;

import user.model.InvalidCredentialsException;
import user.model.UserExistException;
import user.controller.Register;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
/**
 * Group 1
 * @author: Aditya Arora
 * @description: View class for User Registration
 */

public class RegisterView {
  /***
   * Register Method for presentation layer
   * @throws SQLException
   * @throws UserExistException
   * @throws NoSuchAlgorithmException
   * @throws ClassNotFoundException
   * @throws InvalidInputException
   * @throws InvalidCredentialsException
   */
  public RegisterView() throws SQLException, UserExistException, NoSuchAlgorithmException, ClassNotFoundException, InvalidInputException, InvalidCredentialsException {
    Headings headings = new Headings();
    headings.registerHeading();

    InputViewOperations viewOperations = new InputViewOperations();
    viewOperations.enterLine("User Name");
    String userName = viewOperations.readFromConsole();
    viewOperations.enterLine("Address");
    String address = viewOperations.readFromConsole();
    viewOperations.enterLine("Contact Number");
    String contactNumber = viewOperations.readFromConsole();
    viewOperations.enterLine("E Mail");
    String email = viewOperations.readFromConsole();
    viewOperations.enterLine("Password");
    String password = viewOperations.readFromConsole();

    Register register = new Register();
    String registeredEmail = register.register(userName,address,email,contactNumber,password);
    if(registeredEmail != null){
      System.out.println("Registered Successfully");
      UserLandingView ulv = new UserLandingView();
      ulv.LandingPage();
    } else
    {
      System.out.println("There was an error while registering you, Please try again!");
    }
  }
}

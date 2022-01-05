package user.view;

import user.model.InvalidCredentialsException;
import user.model.UserExistException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UserLandingView {
  /**
   * This our Landing page of our Application
   * @throws SQLException
   * @throws UserExistException
   * @throws NoSuchAlgorithmException
   * @throws ClassNotFoundException
   * @throws InvalidInputException
   * @throws InvalidCredentialsException
   */
  public void LandingPage() throws SQLException, UserExistException, NoSuchAlgorithmException, ClassNotFoundException, InvalidInputException, InvalidCredentialsException {
    Headings headings = new Headings();
    headings.dashedLine();
    System.out.println("Welcome");
    headings.dashedLine();
    System.out.println("Select the operation to Perform corresponding operations");
    System.out.println("1. Login");
    System.out.println("2. Register");
    System.out.println("3. Reset Password");

    InputViewOperations ivo = new InputViewOperations();
    String option = ivo.readFromConsole();
    switch (option){
      case "1": LoginView lv = new LoginView();
        break;
      case "2":  RegisterView rv = new RegisterView();
        break;
      case "3": ResetView rev = new ResetView();
        break;
      default: throw new InvalidInputException("Invalid Input");
    }
  }

  public static void main(String[] args) throws InvalidInputException, SQLException, InvalidCredentialsException, UserExistException, NoSuchAlgorithmException, ClassNotFoundException {
    UserLandingView ulv = new UserLandingView();
    ulv.LandingPage();
  }
}

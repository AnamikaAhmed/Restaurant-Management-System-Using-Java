package user.view;

import user.controller.Reset;
import user.model.InvalidCredentialsException;
import user.model.UserExistException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
/**
 * Group 1
 * @author: Aditya Arora
 * @description: View class for User Password Reset
 */

public class ResetView implements IResetView {
  public ResetView() throws InvalidInputException, SQLException, NoSuchAlgorithmException, InvalidCredentialsException, UserExistException, ClassNotFoundException {
    Headings headings = new Headings();
    headings.resetHeading();
    System.out.println("\nPlease select operation to perform");
    System.out.println("1. Change Password");
    System.out.println("2. Change forgotten Password");
    InputViewOperations ivo = new InputViewOperations();
    String option = ivo.readFromConsole();
    switch (option){
      case "1": {
        if(changePassword()){
          System.out.println("Password Changed!");
          UserLandingView ulv = new UserLandingView();
          ulv.LandingPage();
        } else {
          throw new InvalidCredentialsException("EMail or password entered doesn't look correct!");
        }
      }
        break;
      case "2": {
        if(newPassword()){
          System.out.println("Password Changed!");
          UserLandingView ulv = new UserLandingView();
          ulv.LandingPage();
        } else {
          throw new InvalidCredentialsException("EMail or password entered doesn't look correct!");
        }
      }
        break;
      default: throw new InvalidInputException("Invalid Input");
    }
  }

  /***
   * changePassword is the presentation layer method to call change password
   * @return Status of operation
   * @throws SQLException
   * @throws NoSuchAlgorithmException
   */
  public boolean changePassword() throws SQLException, NoSuchAlgorithmException {
    Headings headings = new Headings();
    headings.changePassword();

    InputViewOperations viewOperations = new InputViewOperations();
    viewOperations.enterLine("E Mail");
    String email = viewOperations.readFromConsole();
    viewOperations.enterLine("Old Password");
    String oldPassword = viewOperations.readFromConsole();
    viewOperations.enterLine("New Password");
    String newPassword = viewOperations.readFromConsole();

    Reset rs = new Reset();
    return rs.changePassword(email,oldPassword,newPassword);
  }

  /***
   * newPassword is the presentation layer method to replace with new password
   * @return Status of operation
   * @throws SQLException
   * @throws NoSuchAlgorithmException
   */
  public boolean newPassword() throws SQLException, NoSuchAlgorithmException {
    Headings headings = new Headings();
    headings.forgotPassword();

    InputViewOperations viewOperations = new InputViewOperations();
    viewOperations.enterLine("E Mail");
    String email = viewOperations.readFromConsole();

    Reset rs = new Reset();
    Integer otp = rs.forget(email);
    System.out.println("Your OTP is: "+otp);

    viewOperations.enterLine("E Mail");
    email = viewOperations.readFromConsole();
    viewOperations.enterLine("New Password");
    String newPassword = viewOperations.readFromConsole();
    viewOperations.enterLine("OTP");
    otp = Integer.parseInt(viewOperations.readFromConsole());

    return rs.newPassword(email,otp,newPassword);
  }
}

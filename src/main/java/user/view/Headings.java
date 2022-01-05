package user.view;
/**
 * Group 1
 * @author: Aditya Arora
 * @description: View class for CLI Headings
 */

public class Headings implements IHeadings{
  /***
   * This method prints a dashed line on console.
   */
  public void dashedLine(){
    System.out.println("-------------------------------------");
  }

  /***
   * The method prints Sign In Heading
   */
  public void signInHeading(){
    dashedLine();
    System.out.println("--------------SIGN IN----------------");
    dashedLine();
  }

  /***
   * The method prints Registration Heading
   */
  public void registerHeading(){
    dashedLine();
    System.out.println("--------------REGISTER----------------");
    dashedLine();
  }

  /***
   * The method prints Reset password Heading
   */
  public void resetHeading(){
    dashedLine();
    System.out.println("--------------RESET PASSWORD----------------");
    dashedLine();
  }

  /***
   * The method prints Change password Heading
   */
  public void changePassword(){
    dashedLine();
    System.out.println("--------------CHANGE PASSWORD----------------");
    dashedLine();
  }

  /***
   * The method prints Forgot password Heading
   */
  public void forgotPassword(){
    dashedLine();
    System.out.println("--------------FORGOT PASSWORD----------------");
    dashedLine();
  }

  /***
   * The method prints Home Page Heading
   */
  public void homePage(){
    dashedLine();
    System.out.println("--------------HOME PAGE----------------");
    dashedLine();
  }

  /***
   * The method prints Order Page Heading
   */
  public void orderPage(){
    dashedLine();
    System.out.println("--------------ORDER PAGE----------------");
    dashedLine();
  }
}

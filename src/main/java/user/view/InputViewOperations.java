package user.view;

import java.util.Scanner;

public class InputViewOperations implements IInputViewOperations{

  /***
   * enterLine will ask for a specific input from user
   * @param substitute String to be asked
   */
  public void enterLine(String substitute){
    System.out.println("Please enter your "+substitute+": ");
  }

  /***
   * readFromConsole will read the input
   * @return user input
   */
  public String readFromConsole() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }
}

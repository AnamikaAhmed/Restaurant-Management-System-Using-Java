package user.controller;

import java.lang.Math;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
/**
 * Group 1
 * @author: Aditya Arora
 * @description: Controller class for User Password Reset
 */
public class Reset implements IReset{
  private Map<String, Integer> otpMapping= new HashMap<>();

  /***
   * Method that contains the logic of generating the otp
   * @param emailId email ID of forgotten password user
   * @return OTP generated
   */
  public Integer forget(String emailId){
    Integer randomNumber = (int)(Math.random()*10);
    otpMapping.put(emailId,randomNumber);
    return randomNumber;
  }

  /***
   * Method to set new Password for a user
   * @param emailID email ID for which we have to change the password
   * @param otp OTP to verify
   * @param newPassword New password to be replaced with
   * @return Status of execution
   * @throws SQLException
   * @throws NoSuchAlgorithmException
   */
  public Boolean newPassword(String emailID,Integer otp, String newPassword) throws SQLException, NoSuchAlgorithmException {
    if(otpMapping.get(emailID).equals(otp)){
      user.model.Reset rs = new user.model.Reset();
      HashPassword hp = new HashPassword();
      otpMapping.remove(emailID);
      return rs.changePassword(emailID, hp.hashPassword(newPassword));
    }

    return false;
  }

  /***
   * changePassword method will replace old password with new password
   * @param email email ID for which we have to change the password
   * @param oldPassword Old Password to replace
   * @param newPassword New password to be replaced with
   * @return Status of execution
   * @throws SQLException
   * @throws NoSuchAlgorithmException
   */
  public Boolean changePassword(String email, String oldPassword, String newPassword) throws SQLException, NoSuchAlgorithmException {
    user.model.Reset rs = new user.model.Reset();
    HashPassword hp = new HashPassword();
    return rs.replacePassword(email,hp.hashPassword(oldPassword),hp.hashPassword(newPassword));
  }
}

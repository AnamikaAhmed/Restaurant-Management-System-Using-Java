package user.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Group 1
 * @author: Aditya Arora
 * @description: Controller class for Hashing Password
 */

public class HashPassword {
  /***
   * This Method hashes our password using SHA-256 technique of our input password
   * @param password password to be hashed
   * @return Hashed Password
   * @throws NoSuchAlgorithmException
   */
  public String hashPassword(String password) throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    return Arrays.toString(digest.digest(password.getBytes(StandardCharsets.UTF_8)));
  }
}

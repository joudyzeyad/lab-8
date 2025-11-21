/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

import java.security.MessageDigest;

/**
 *
 * @author NEXT STORE
 */
public class PasswordHasher {
    public static String hash(String password) {
        try {
            MessageDigest m = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = m.digest(password.getBytes());
            
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            
            return sb.toString();
        }
        catch (Exception e) {
            return null;
        }
    }
}

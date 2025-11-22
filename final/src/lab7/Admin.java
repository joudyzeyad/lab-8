/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

/**
 *
 * @author farida helal
 */
public class Admin extends User {

    public Admin(int userId, String username, String email, String password) {
        super(userId, username, email, password, "Admin");
    }

    public Admin(int userId, String username, String email, String passwordHash, boolean alreadyHashed) {
        super(userId, username, email, passwordHash, "Admin", alreadyHashed);
    }
}

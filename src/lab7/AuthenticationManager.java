/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author NEXT STORE
 */
public class AuthenticationManager {
    private static User currentUser = null;

    public static User getCurrentUser() {
        return currentUser;
    }
    
    public static User signup(String username, String email, String password, String role) throws IOException {
            ArrayList<User> users = JsonDatabaseManager.loadUsers();
            
            for (int i = 0; i < users.size() ; i++) {
                User u = users.get(i);
                
                if (u.getEmail().equalsIgnoreCase(email.trim())) {
                    return null;
                }
            }
            
            int newId = JsonDatabaseManager.generateNewUserId();
                
            User newUser;
            if (role.equalsIgnoreCase("student")) {
                newUser = new Student(newId, username, email, password);
            }
            else {
                newUser = new Instructor(newId, username, email, password);
            }
            
            users.add(newUser);
            JsonDatabaseManager.saveUser(users);
            
            return newUser;
    }
        
        public static User login(String email, String password) throws IOException {
                ArrayList<User> users = JsonDatabaseManager.loadUsers();
                String hashed = PasswordHasher.hash(password.trim());
                
                for (int i = 0 ; i < users.size() ; i++) {
                    User u = users.get(i);
                    
                    if (u.getEmail().equalsIgnoreCase(email.trim()) && u.getPasswordHash().equals(hashed)) {
                        currentUser = u;
                        return u;
                    }
                }
                return null;
        }
        
        public static void logout() {
            currentUser = null;
        }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

/**
 *
 * @author NEXT STORE
 */
public abstract class User {
    private final int userId;
    private String username;
    private String email;
    private String passwordHash;
    private final String role;
    
    public User(int userId, String username, String email, String password, String role) {
        this.userId = userId;
        setUsername(username);
        setEmail(email);
        setPasswordHash(password);
        this.role = role;
    }
    
    public User(int userId, String username, String email, String passwordHash, String role, boolean alreadyHashed) {
        this.userId = userId;
        setUsername(username);
        setEmail(email);

        if (alreadyHashed) {
            this.passwordHash = passwordHash;  
        } 
        else {
            setPasswordHash(passwordHash);     
        }

        this.role = role;
    }
    
    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty !");
        }
        else {
            this.username = username;
        }
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Invalid email format !");
        }
        else {
            this.email = email;
        }
    }

    public void setPasswordHash(String password) {
        if (password == null || password.length() < 5) {
            throw new IllegalArgumentException("Password is too short ! It must be at least 5 characters");
        }
        else {
            this.passwordHash = PasswordHasher.hash(password);
        }
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
    
    public String getRole() {
        return role;
    }
}

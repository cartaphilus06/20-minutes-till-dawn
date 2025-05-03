package com.tilldawn.Models.User;

import java.util.*;

public class User {
    private static final ArrayList<User> allUsers = new ArrayList<User>();
    private String username;
    private String password;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        allUsers.add(this);
    }
    public static User getUser(String username) {
        for(User user : allUsers) {
            if(user.getUsername().equals(username)) return user;
        }
        return null;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

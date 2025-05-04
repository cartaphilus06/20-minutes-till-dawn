package com.tilldawn.Models.User;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class User {
    private static ArrayList<User> allUsers = new ArrayList<>();
    private String username;
    private String password;
    private Question securityQuestion;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        allUsers.add(this);
        saveUsers();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        saveUsers();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        saveUsers();
    }
    public Question getSecurityQuestion() {
        return securityQuestion;
    }
    public void setSecurityQuestion(Question securityQuestion) {
        this.securityQuestion = securityQuestion;
    }
    public static void saveUsers() {
        if(allUsers.isEmpty()) return;
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("assets/data/users.json");

        try {
            file.getParentFile().mkdirs();
            mapper.writeValue(file, allUsers);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save users to JSON file");
        }
    }

    public static void loadUsers() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("assets/data/users.json");

        try {
            if (file.exists()) {
                allUsers = mapper.readValue(file, new TypeReference<ArrayList<User>>() {});
            } else {
                System.out.println("Users file doesn't exist, starting with empty user list");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load users from JSON file");
            allUsers = new ArrayList<>();
        }
    }
    public static User getUser(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static ArrayList<User> getAllUsers() {
        return new ArrayList<>(allUsers);
    }
}

package com.tilldawn.Models.User;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class User {
    private static ArrayList<User> allUsers = new ArrayList<>();
    private String username;
    private String password;

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
    public static void saveUsers() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("assets/data/users.json");

        try {
            // Create parent directories if they don't exist
            file.getParentFile().mkdirs();
            // Write the allUsers list to JSON file
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
                // Read the JSON file into the allUsers list
                allUsers = mapper.readValue(file, new TypeReference<ArrayList<User>>() {});
            } else {
                System.out.println("Users file doesn't exist, starting with empty user list");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load users from JSON file");
            // Start with empty list if loading fails
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

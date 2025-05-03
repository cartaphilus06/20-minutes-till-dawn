package com.tilldawn.Models.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.ArrayList;

public class User {
    private static ArrayList<User> allUsers = new ArrayList<>();
    private String username;
    private String password;

    // Required no-arg constructor for Jackson
    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        allUsers.add(this);
        saveUsers();
    }

    // Save users to JSON file in assets/data
    public static void saveUsers() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            FileHandle file = Gdx.files.local("data/users.json");

            // Create parent directories if they don't exist
            file.parent().mkdirs();

            // Write to file
            mapper.writerWithDefaultPrettyPrinter().writeValue(file.file(), allUsers);
        } catch (Exception e) {
            Gdx.app.error("User", "Failed to save users", e);
        }
    }

    public static void loadUsers() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            FileHandle file = Gdx.files.local("data/users.json");

            if (file.exists() && file.length() > 0) {  // Check both existence and content
                allUsers = mapper.readValue(file.file(),
                    mapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class));
            } else {
                // Initialize empty list if file is empty/missing
                allUsers = new ArrayList<>();
                Gdx.app.log("User", "No existing user data found, initializing empty list");
            }
        } catch (Exception e) {
            Gdx.app.error("User", "Failed to load users", e);
            // Fallback to empty list
            allUsers = new ArrayList<>();
        }
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

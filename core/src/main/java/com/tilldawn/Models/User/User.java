package com.tilldawn.Models.User;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tilldawn.App;
import com.tilldawn.Models.Enums.Avatar;
import com.tilldawn.Models.Enums.Weapon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class User {
    private static ArrayList<User> allUsers = new ArrayList<>();
    private String username;
    private String password;
    private Question securityQuestion;
    private Character character;
    private Avatar avatar;
    private boolean stayLoggedIn;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        avatar=Avatar.values()[(new Random()).nextInt(Avatar.values().length)];
        allUsers.add(this);
        saveUsers();
    }
    public String getUsername() {
        return username;
    }

    public void deleteUser(){
        allUsers.remove(this);
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
        saveUsers();
    }
    public Character getCharacter() {
        return character;
    }
    public void setCharacter(Character character) {
        this.character = character;
        saveUsers();
    }
    public Avatar getAvatar() {
        return avatar;
    }
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
        saveUsers();
    }
    public boolean getStayLoggedIn() {
        return stayLoggedIn;
    }
    public void setStayLoggedIn(boolean stayLoggedIn) {
        this.stayLoggedIn = stayLoggedIn;
        saveUsers();
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
                if(file.length()>0) {
                    allUsers = mapper.readValue(file, new TypeReference<ArrayList<User>>() {});
                    for(User user : allUsers) {
                        if(user.stayLoggedIn) App.setCurrentUser(user);
                    }
                }
                else {
                    allUsers = new ArrayList<>();
                }
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

package com.tilldawn;

import com.tilldawn.Models.User.User;

public class App {
    private static User currentUser;
    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }
    public static User getCurrentUser() {
        return currentUser;
    }
}

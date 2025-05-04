package com.tilldawn;

import com.tilldawn.Models.User.User;

public class App {
    private User currentUser;
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    public User getCurrentUser() {
        return currentUser;
    }
}

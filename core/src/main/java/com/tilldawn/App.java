package com.tilldawn;

import com.tilldawn.Models.User.User;

public class App {
    private static User currentUser;
    private static float musicVolume=1f;
    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }
    public static User getCurrentUser() {
        return currentUser;
    }
    public static void setMusicVolume(float musicVolume) {
        App.musicVolume = musicVolume;
    }
    public static float getMusicVolume() {
        return musicVolume;
    }
}

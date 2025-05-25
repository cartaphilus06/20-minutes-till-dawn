package com.tilldawn;

import com.tilldawn.Models.Map.Map;
import com.tilldawn.Models.User.User;

public class App {
    private static User currentUser;
    private static Map currentMap;
    private static float musicVolume=1f;
    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }
    public static User getCurrentUser() {
        return currentUser;
    }
    public static void setCurrentMap(Map currentMap) {
        App.currentMap = currentMap;
    }
    public static Map getCurrentMap() {
        return currentMap;
    }
    public static void setMusicVolume(float musicVolume) {
        App.musicVolume = musicVolume;
    }
    public static float getMusicVolume() {
        return musicVolume;
    }
    public static void setCurrentUserAsGuest(){
        for(User user:User.getAllUsers()){
            if(user.getUsername().equals("guest")) {
                user.setStayLoggedIn(true);
                App.setCurrentUser(user);
                break;
            }
        }
    }
    public static void initializeGuest(){
        for(User user:User.getAllUsers()){
            if(user.getUsername().equals("guest")) return;
        }
        new User("guest","Guest@12");
    }
}

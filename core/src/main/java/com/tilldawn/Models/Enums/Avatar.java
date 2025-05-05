package com.tilldawn.Models.Enums;

public enum Avatar {
    ZOMBIE("images/avatars/zombie_tilesheet.png"),
    PLAYER("images/avatars/player_tilesheet.png"),
    FEMALE("images/avatars/female_tilesheet.png"),
    ADVENTURER("images/avatars/adventurer_tilesheet.png"),
    SOLDIER("images/avatars/soldier_tilesheet.png");
    private final String internalPath;
    Avatar(String path) {
        internalPath = path;
    }
    public String getInternalPath() {
        return internalPath;
    }
    public static int getWidth() {
        return 80;
    }
    public static int getHeight() {
        return 110;
    }
}

package com.tilldawn.Models.Enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum Avatar {
    ZOMBIE("images/avatars/zombie_tilesheet.png",0),
    PLAYER("images/avatars/player_tilesheet.png",1),
    FEMALE("images/avatars/female_tilesheet.png",2),
    ADVENTURER("images/avatars/adventurer_tilesheet.png",3),
    SOLDIER("images/avatars/soldier_tilesheet.png",4);
    private final String internalPath;
    private final int id;
    Avatar(String path,int id) {
        internalPath = path;
        this.id = id;
    }
    public String getInternalPath() {
        return internalPath;
    }
    public TextureRegion[][] getTiles() {
        Texture texture = new Texture(Gdx.files.internal(internalPath));
        return TextureRegion.split(texture,getWidth(),getHeight());
    }
    public static int getWidth() {
        return 80;
    }
    public static int getHeight() {
        return 110;
    }
    public int getId() {
        return id;
    }
}

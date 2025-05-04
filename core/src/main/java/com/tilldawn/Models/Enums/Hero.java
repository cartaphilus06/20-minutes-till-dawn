package com.tilldawn.Models.Enums;

public enum Hero {
    SHANA(4,4,"",""),
    DIAMOND(7,1,"",""),
    SCARLET(3,5,"",""),
    LILITH(5,3,"",""),
    DASHER(2,10,"","");
    private final int hp;
    private final int speed;
    private final String texturePortraitPath;
    private final String texturePath;
    Hero(int hp, int speed, String texturePath, String texturePortraitPath) {
        this.hp = hp;
        this.speed = speed;
        this.texturePath = texturePath;
        this.texturePortraitPath = texturePortraitPath;
    }
    public int getHp() {
        return hp;
    }
    public int getSpeed() {
        return speed;
    }
    public String getTexturePortraitPath() {
        return texturePortraitPath;
    }
    public String getTexturePath() {
        return texturePath;
    }
}

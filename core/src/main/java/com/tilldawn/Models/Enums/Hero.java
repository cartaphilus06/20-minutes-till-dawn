package com.tilldawn.Models.Enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum Hero {
    SHANA(4,4,"images/Texture2D/T_Shana.png","images/Texture2D/T_Shana_Portrait.png"),
    DIAMOND(7,1,"images/Texture2D/T_Diamond #7829.png","images/Texture2D/T_Diamond_Portrait.png"),
    SCARLET(3,5,"images/Texture2D/T_Scarlett.png","images/Texture2D/T_Scarlett_Portrait.png"),
    LILITH(5,3,"images/Texture2D/T_Lilith.png","images/Texture2D/T_Lilith_Portrait.png"),
    DASHER(2,10,"images/Texture2D/T_Dasher.png","images/Texture2D/T_Dasher_Portrait.png");
    private final int hp;
    private final int speed;
    private final String texturePortraitPath;
    private final String texturePath;
    private final TextureRegion[][] iconsTiles;
    Hero(int hp, int speed, String texturePath, String texturePortraitPath) {
        this.hp = hp;
        this.speed = speed;
        this.texturePath = texturePath;
        this.texturePortraitPath = texturePortraitPath;
        Texture texture = new Texture(texturePath);
        this.iconsTiles = TextureRegion.split(texture,getIconWidth(),getIconHeight());
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
    public TextureRegion[] getRunIcons(){
        TextureRegion[] runIcons = new TextureRegion[4];
        System.arraycopy(iconsTiles[1], 0, runIcons, 0, 4);
        return runIcons;
    }
    public TextureRegion[] getWalkIcons(){
        TextureRegion[] walkIcons = new TextureRegion[8];
        System.arraycopy(iconsTiles[2], 0, walkIcons, 0, 8);
        return walkIcons;
    }
    public TextureRegion[] getStandIcon(){
        TextureRegion[] defaultIcon = new TextureRegion[6];
        System.arraycopy(iconsTiles[0], 0, defaultIcon, 0, 6);
        return defaultIcon;
    }
    public Animation<TextureRegion> getRunAnimation(){
        return new Animation<>(0.1f, getRunIcons());
    }
    public Animation<TextureRegion> getWalkAnimation(){
        return new Animation<>(0.1f, getWalkIcons());
    }
    public Animation<TextureRegion> getStandAnimation(){
        return new Animation<>(0.1f, getStandIcon());
    }
    public int getIconWidth() {
        return 32;
    }
    public int getIconHeight() {
        return 32;
    }
    public Texture getHeroPortraitTexture() {
        return new Texture(Gdx.files.internal(texturePortraitPath));
    }
    public Texture getTexture(){
        return iconsTiles[0][0].getTexture();
    }
}

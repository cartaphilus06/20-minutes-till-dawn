package com.tilldawn.Models.Map.Monster;

public abstract class Monster {
    protected int hp;
    protected int worldX;
    protected int worldY;
    protected String internalPath;
    public Monster(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getHp(){
        return hp;
    }
    public int getWorldX() {
        return worldX;
    }
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }
    public int getWorldY() {
        return worldY;
    }
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
    protected void setInternalPath(String internalPath) {
        this.internalPath = internalPath;
    }
    protected String getInternalPath() {
        return internalPath;
    }
}

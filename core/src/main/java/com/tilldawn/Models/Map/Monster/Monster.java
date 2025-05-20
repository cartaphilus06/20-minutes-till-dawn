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
}

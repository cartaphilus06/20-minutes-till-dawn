package com.tilldawn.Models.Enums;

public enum Weapon{
    SHOTGUN(10,4,1,2,""),
    REVOLVER(20,1,1,6,""),
    SMGSDUAL(8,1,1,4,"");
    private final int damage;
    private final int projectile;
    private final int reloadTime;
    private final int maxAmmo;
    private final String texturePath;
    Weapon(int damage, int projectile, int reloadTime, int maxAmmo, String texturePath) {
        this.damage = damage;
        this.projectile = projectile;
        this.reloadTime = reloadTime;
        this.maxAmmo = maxAmmo;
        this.texturePath = texturePath;
    }
    public int getDamage() {
        return damage;
    }
    public int getProjectile() {
        return projectile;
    }
    public int getReloadTime() {
        return reloadTime;
    }
    public int getMaxAmmo() {
        return maxAmmo;
    }
    public String getPath(){
        return texturePath;
    }
}

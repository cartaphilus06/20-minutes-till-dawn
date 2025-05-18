package com.tilldawn.Models.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tilldawn.Models.Ability.Ability;
import com.tilldawn.Models.Ability.DefaultAbility;
import com.tilldawn.Models.CollisionRect;
import com.tilldawn.Models.Enums.Hero;
import com.tilldawn.Models.Enums.Weapon;

import java.util.Random;

public class Character {
    private Hero hero;
    private Weapon weapon;
    private Ability ability=new DefaultAbility();
    private int score;
    private float stateTime=0;
    private boolean autoReload=true;
    private CollisionRect collisionRect;
    private Sprite sprite;
    private boolean isIdle=true;
    private boolean isRunning=false;
    private int x=0;
    private int y=0;
    public Character(){
        Random rand = new Random();
        hero=Hero.values()[rand.nextInt(Hero.values().length)];
        weapon=Weapon.values()[rand.nextInt(Weapon.values().length)];
        score=0;
        float coEfficient=2.5f;
        collisionRect=new CollisionRect((Gdx.graphics.getWidth()-hero.getIconWidth()*coEfficient)/2,
            (Gdx.graphics.getHeight()-hero.getIconHeight()*coEfficient)/2,
            hero.getIconWidth()*coEfficient,
            hero.getIconHeight()*coEfficient);
        setSprite();
    }
    public Hero getHero() {
        return hero;
    }
    public void setHero(Hero hero) {
        this.hero = hero;
    }
    public Weapon getWeapon(){
        return weapon;
    }
    public void setWeapon(Weapon weapon){
        this.weapon=weapon;
    }
    public Ability getAbility() {
        return ability;
    }
    public void setAbility(Ability ability) {
        this.ability = ability;
    }
    public boolean getAutoReload() {
        return autoReload;
    }
    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public CollisionRect getCollisionRect() {
        return collisionRect;
    }
    public void setCollisionRect(CollisionRect collisionRect) {
        this.collisionRect = collisionRect;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    @JsonIgnore
    public boolean isIdle() {
        return isIdle;
    }
    @JsonIgnore
    public void setIdle(boolean idle) {
        isIdle = idle;
    }
    @JsonIgnore
    public boolean isRunning() {
        return isRunning;
    }
    @JsonIgnore
    public void setRunning(boolean running) {
        isRunning = running;
    }
    @JsonIgnore
    public Sprite getSprite() {
        return sprite;
    }
    @JsonIgnore
    public void setSprite() {
        sprite=new Sprite(hero.getTexture());
        sprite.setPosition(collisionRect.getX(),collisionRect.getY());
        sprite.setSize(collisionRect.getWidth(),collisionRect.getHeight());
    }
    @JsonIgnore
    public float getStateTime(){
        return stateTime;
    }
    @JsonIgnore
    public void setStateTime(float stateTime){
        this.stateTime=stateTime;
    }
    @JsonIgnore
    public int getHP(){
        return hero.getHp()+ability.getHP();
    }
    @JsonIgnore
    public float getDamage(){
        return weapon.getDamage()+ability.getDamage(weapon.getDamage());
    }
    @JsonIgnore
    public int getProjectile(){
        return weapon.getProjectile()+ability.getProjectile();
    }
    @JsonIgnore
    public int getReloadTime(){
        return weapon.getReloadTime()+ability.getReloadTime();
    }
    @JsonIgnore
    public int getMaxAmmo(){
        return weapon.getMaxAmmo()+ability.getMaxAmmo();
    }
    @JsonIgnore
    public int getSpeed(){
        return hero.getSpeed()+ ability.getSpeed(hero.getSpeed());
    }
    @JsonIgnore
    public int getDisappearingTime(){
        return ability.getDisappearingTime();
    }
}

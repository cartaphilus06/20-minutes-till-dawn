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
    private Ability ability;
    private Ability lastAbility;
    private int score;
    private float stateTime=0;
    private boolean autoReload=true;
    private CollisionRect collisionRect;
    private Sprite sprite;
    private boolean isIdle=true;
    private boolean isRunning=true;
    private float x=0;
    private float y=0;
    private float heroWidth;
    private float heroHeight;
    private int currentHp;
    private int level;
    private float currentExp;
    private int currentAmmo;
    private boolean isInvincible=false;
    private boolean isAutoAim=false;
    public Character(){
        Random rand = new Random();
        ability = new DefaultAbility();
        lastAbility = null;
        hero=Hero.values()[rand.nextInt(Hero.values().length)];
        weapon=Weapon.values()[rand.nextInt(Weapon.values().length)];
        score=0;
        setWidthAndHeight();
        collisionRect=new CollisionRect((Gdx.graphics.getWidth()-heroWidth)/2,
            (Gdx.graphics.getHeight()-heroHeight)/2,
            heroWidth,
            heroHeight);
        setSprite();
        setCurrentHp(getHP());
        setLevel(1);
        setCurrentExp(0);
    }
    public Hero getHero() {
        return hero;
    }
    public void setHero(Hero hero) {
        this.hero = hero;
        setCurrentHp(getHP());
    }
    public Weapon getWeapon(){
        return weapon;
    }
    public void setWeapon(Weapon weapon){
        this.weapon=weapon;
        setCurrentAmmo(weapon.getMaxAmmo());
    }
    public Ability getAbility() {
        return ability;
    }
    public void setAbility(Ability ability) {
        this.ability = ability;
    }
    public Ability getLastAbility() {
        return lastAbility;
    }
    public void setLastAbility(Ability lastAbility) {
        this.lastAbility = lastAbility;
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
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
    public int getCurrentHp() {
        return currentHp;
    }
    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public float getCurrentExp() {
        return currentExp;
    }
    public void setCurrentExp(float currentExp) {
        this.currentExp = currentExp;
    }
    public int getCurrentAmmo() {
        return currentAmmo;
    }
    public void setCurrentAmmo(int currentAmmo) {
        this.currentAmmo = currentAmmo;
    }
    public boolean isInvincible(){
        return isInvincible;
    }
    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }
    public boolean isAutoAim() {
        return isAutoAim;
    }
    public void setAutoAim(boolean autoAim) {
        isAutoAim = autoAim;
    }
    @JsonIgnore
    public float getExpPerLevel(){
        return getLevel()*20;
    }
    @JsonIgnore
    public void setWidthAndHeight() {
        float coEfficient=2.5f;
        heroHeight=coEfficient*hero.getIconHeight();
        heroWidth=coEfficient*hero.getIconWidth();
    }
    @JsonIgnore
    public float getHeroWidth(){
        return heroWidth;
    }
    @JsonIgnore
    public float getHeroHeight(){
        return heroHeight;
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
        return ability.getHP(hero.getHp());
    }
    @JsonIgnore
    public float getDamage(){
        return ability.getDamage(weapon.getDamage());
    }
    @JsonIgnore
    public int getProjectile(){
        return ability.getProjectile(weapon.getProjectile());
    }
    @JsonIgnore
    public int getReloadTime(){
        return ability.getReloadTime(weapon.getReloadTime());
    }
    @JsonIgnore
    public int getMaxAmmo(){
        return ability.getMaxAmmo(weapon.getMaxAmmo());
    }
    @JsonIgnore
    public float getSpeed(){
        float speed=ability.getSpeed(hero.getSpeed());
        return isRunning()?speed:speed/2f;
    }
}

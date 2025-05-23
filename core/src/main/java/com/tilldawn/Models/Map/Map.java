package com.tilldawn.Models.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.tilldawn.App;
import com.tilldawn.Models.Map.Monster.Monster;

import java.util.ArrayList;

public class Map {
    private static Map map;
    private final int time;
    private float remainingTime;
    private final Texture background;
    private final ArrayList<Monster> allMonsters=new ArrayList<>();
    public Map(int time) {
        this.time = time;
        this.remainingTime = time;
        this.background = new Texture(Gdx.files.internal("images/backgrounds/background.png"));
        map=this;
    }
    public Texture getBackground() {
        return background;
    }
    public void spawnMonsters() {

    }
    public void dispose(){
        background.dispose();
    }
    public static int getWorldMinX(){
        return 0;
    }
    public static int getWorldMinY(){
        return 0;
    }
    public static int getWorldMaxX(){
        return map.background.getWidth();
    }
    public static int getWorldMaxY(){
        return map.background.getHeight();
    }
    public ArrayList<Monster> getMonsters() {
        return allMonsters;
    }
    public boolean isWalkable(float x, float y) {
        Rectangle pointRect = new Rectangle(x, y, 1, 1);
        for (Monster monster : allMonsters) {
            Rectangle monsterRect = monster.getSprite().getBoundingRectangle();
            if (monsterRect.overlaps(pointRect)) return false;
        }
        return true;
    }

    public static Map getMap() {
        return map;
    }
    public Monster isMonster(Rectangle rectangle) {
        for(Monster monster : allMonsters) {
            Rectangle rec=monster.getSprite().getBoundingRectangle();
            if(rec.contains(rectangle)) return monster;
        }
        return null;
    }
    public float getRemainingTime(){
        return remainingTime;
    }
    public void setRemainingTime(float remainingTime) {
        this.remainingTime = remainingTime;
    }
    public float getBrainMonsterSpawnRate(){
        float elapsed = time - remainingTime;
        return elapsed/30f;
    }
    public float getEyebatSpawnRate(){
        float elapsed = time - remainingTime;
        return (4*elapsed-time+30)/30f;
    }
    public int getTime(){
        return time;
    }
}

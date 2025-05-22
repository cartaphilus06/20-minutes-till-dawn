package com.tilldawn.Models.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.tilldawn.Models.Map.Monster.Monster;

import java.util.ArrayList;

public class Map {
    private static Map map;
    private int time;
    private final Texture background;
    private final ArrayList<Monster> allMonsters=new ArrayList<>();
    public Map(int time) {
        this.time = time;
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
    public boolean isWalkable(float x,float y) {
        return true;
    }
}

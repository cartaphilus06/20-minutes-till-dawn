package com.tilldawn.Models.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tilldawn.App;
import com.tilldawn.Models.Map.Monster.Monster;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Map {
    private static ArrayList<Map> allMaps = new ArrayList<>();
    private int time;
    private float remainingTime;
    private Texture background;
    private ArrayList<Monster> allMonsters=new ArrayList<>();
    private ArrayList<Exp> allExps=new ArrayList<>();
    private String characterUsername;
    public Map(){}
    public Map(int time) {
        this.time = time;
        this.remainingTime = time;
        this.background = new Texture(Gdx.files.internal("images/backgrounds/background.png"));
        characterUsername= App.getCurrentUser().getUsername();
        allMaps.add(this);
        App.setCurrentMap(this);
    }
    @JsonIgnore
    public Texture getBackground() {
        return background;
    }
    public void dispose(){
        background.dispose();
    }
    public ArrayList<Monster> getMonsters() {
        return allMonsters;
    }
    public ArrayList<Exp> getExps() {
        return allExps;
    }
    public void setExps(ArrayList<Exp> exps) {
        allExps = exps;
    }
    public String getCharacterUsername() {
        return characterUsername;
    }
    public void setCharacterUsername(String characterUsername) {
        this.characterUsername = characterUsername;
    }
    public ArrayList<Monster> getAllMonsters() {
        return allMonsters;
    }
    public void setAllMonsters(ArrayList<Monster> allMonsters) {
        this.allMonsters = allMonsters;
    }
    public int getWorldMaxX(){
        return background.getWidth();
    }
    public int getWorldMaxY(){
        return background.getHeight();
    }
    public int getWorldMinX(){
        return 0;
    }
    public int getWorldMinY(){
        return 0;
    }
    public boolean isWalkable(float x, float y) {
        Rectangle pointRect = new Rectangle(x, y, 1, 1);
        for (Monster monster : allMonsters) {
            Rectangle monsterRect = monster.getSprite().getBoundingRectangle();
            if (monsterRect.overlaps(pointRect)) return false;
        }
        return true;
    }
    @JsonIgnore
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
    @JsonIgnore
    public float getBrainMonsterSpawnRate(){
        float elapsed = time - remainingTime;
        return elapsed/30f;
    }
    @JsonIgnore
    public float getEyebatSpawnRate(){
        float elapsed = time - remainingTime;
        return (4*elapsed-time+30)/30f;
    }
    public int getTime(){
        return time;
    }
    public void addExp(Exp exp) {
        allExps.add(exp);
    }
    public static void saveMaps(){
        if(allMaps.isEmpty()) return;
        ObjectMapper mapper = new ObjectMapper();
        File file=new File("assets/data/maps.json");
        try{
            file.getParentFile().mkdirs();
            mapper.writeValue(file, allMaps);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void loadMaps(){
        ObjectMapper mapper = new ObjectMapper();
        File file=new File("assets/data/maps.json");
        try{
            if(file.exists()){
                if(file.length()>0){
                    allMaps=mapper.readValue(file,new TypeReference<ArrayList<Map>>(){});
                    for(Map map : allMaps){
                        if(map.getCharacterUsername().equals(App.getCurrentUser().getUsername())){
                            App.setCurrentMap(map);
                            break;
                        }
                    }
                } else allMaps=new ArrayList<>();
            } else allMaps=new ArrayList<>();
        }catch (IOException e){
            e.printStackTrace();
            allMaps=new ArrayList<>();
        }
        for(Map map : allMaps){
            map.reinitializeAssets();
            for(Monster monster : map.getAllMonsters()){
                monster.reinitializeAssets();
            }
        }
    }
    public void reinitializeAssets(){
        this.background = new Texture(Gdx.files.internal("images/backgrounds/background.png"));
    }
}

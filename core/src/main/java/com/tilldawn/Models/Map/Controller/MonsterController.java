package com.tilldawn.Models.Map.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.Map.Map;
import com.tilldawn.Models.Map.Monster.BrainMonster;
import com.tilldawn.Models.Map.Monster.Monster;
import com.tilldawn.Models.Map.Monster.Tree;
import com.tilldawn.Models.RandomNum;
import com.tilldawn.View.GameMenu;

import java.util.*;

public class MonsterController {
    private final GameMenu view;
    private final Map map;
    private final Character character;
    private final ArrayList<Monster> allMonsters;
    public MonsterController(GameMenu view, Map map, Character character) {
        this.view = view;
        this.map = map;
        this.character = character;
        allMonsters=map.getMonsters();
        scheduleMonsterSpawning(5);
        spawnTrees();
    }
    public void update(){
        drawMonsters(view.getStage().getBatch());
        updateMonsters();
    }
    public void scheduleMonsterSpawning(float intervalSeconds){
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                spawnMonster();
            }
        }, 0, intervalSeconds);
    }
    public void spawnMonster(){
        float x = (float)RandomNum.getRandomNumber(Map.getWorldMinX(),Map.getWorldMaxX()-100);
        float y = (float)RandomNum.getRandomNumber(Map.getWorldMinY(),Map.getWorldMaxY()-100);
        float playerX = character.getX();
        float playerY = character.getY();
        float minDistance = 300f;
        if(Math.hypot(playerX - x, playerY - y) < minDistance) return;
        if (!map.isWalkable(x,y)) {
            spawnMonster();
            return;
        }
        Monster newMonster=new BrainMonster(x,y);
        newMonster.getSprite().setPosition(x,y);
        allMonsters.add(newMonster);
    }
    public void updateMonsters(){
        for(int i=allMonsters.size()-1;i>=0;i--){
            Monster monster=allMonsters.get(i);
            if(monster.getHp()<0) allMonsters.remove(i);
            monster.update(Gdx.graphics.getDeltaTime());
        }
    }
    public void drawMonsters(Batch batch){
        for(Monster monster:allMonsters){
            monster.draw(batch);
        }
    }
    public void spawnTrees(){
        int numberOfTrees=0;
        while(numberOfTrees<=10) {
            float x = (float) RandomNum.getRandomNumber(Map.getWorldMinX(), Map.getWorldMaxX() - 100);
            float y = (float) RandomNum.getRandomNumber(Map.getWorldMinY(), Map.getWorldMaxY() - 100);
            float playerX = character.getX();
            float playerY = character.getY();
            float minDistance = 300f;
            if (Math.hypot(playerX - x, playerY - y) < minDistance) continue;
            if (!map.isWalkable(x,y)) continue;
            Monster newMonster = new Tree(x, y);
            newMonster.getSprite().setPosition(x, y);
            allMonsters.add(newMonster);
            numberOfTrees++;
        }
    }

}

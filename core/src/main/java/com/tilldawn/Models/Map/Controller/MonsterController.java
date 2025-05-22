package com.tilldawn.Models.Map.Controller;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.Map.Map;
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
        //scheduleMonsterSpawning(5);
        spawnTrees();
    }
    public void update(){
        drawMonsters(view.getStage().getBatch());
//        updateMonsters();
//        float delta = Gdx.graphics.getDeltaTime();
//        float targetX = character.getX();
//        float targetY = character.getY();
//        AStarPathFinder pathFinder = new AStarPathFinder(map);
//        for(Monster monster: allMonsters){
//            float startX=monster.getX();
//            float startY=monster.getY();
//            List<Vector2> path=pathFinder.findPath(startX,startY,targetX,targetY);
//            monster.setPath(path);
//            monster.followPath(delta);
//        }
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
        Monster newMonster=new Tree(x,y);
        newMonster.getSprite().setPosition(x,y);
        allMonsters.add(newMonster);
    }
    public void updateMonsters(){
        for(Monster monster:allMonsters){
            monster.update();
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
            Monster newMonster = new Tree(x, y);
            newMonster.getSprite().setPosition(x, y);
            allMonsters.add(newMonster);
            numberOfTrees++;
        }
    }
}

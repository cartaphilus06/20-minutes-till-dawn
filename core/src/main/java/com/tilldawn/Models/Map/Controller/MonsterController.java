package com.tilldawn.Models.Map.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Models.Map.AStar.AStarPathFinder;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.Map.Map;
import com.tilldawn.Models.Map.Monster.Monster;
import com.tilldawn.Models.Map.Monster.Tree;
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
    }
    public void update(){
        float delta = Gdx.graphics.getDeltaTime();
        float targetX = character.getX();
        float targetY = character.getY();
        AStarPathFinder pathFinder = new AStarPathFinder(map);
        for(Monster monster: allMonsters){
            float startX=monster.getX();
            float startY=monster.getY();
            List<Vector2> path=pathFinder.findPath(startX,startY,targetX,targetY);
            monster.setPath(path);
            monster.followPath(delta);
        }
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
        float x = (float)getRandomNumber(Map.getWorldMinX(),Map.getWorldMaxX());
        float y = (float)getRandomNumber(Map.getWorldMinY(),Map.getWorldMaxY());
        float playerX = character.getX();
        float playerY = character.getY();
        float minDistance = 300f;
        if(Math.hypot(playerX - x, playerY - y) < minDistance) return;
        Monster newMonster=new Tree(x,y);
        allMonsters.add(newMonster);
    }
    public int getRandomNumber(int min, int max){
        return (int)(Math.random() * (max - min) + min);
    }
    public void updateMonsters(){
        for(Monster monster:allMonsters){
            monster.update();
        }
    }
    public void draw(Batch batch){
        for(Monster monster:allMonsters){
            monster.draw(batch);
        }
    }
}

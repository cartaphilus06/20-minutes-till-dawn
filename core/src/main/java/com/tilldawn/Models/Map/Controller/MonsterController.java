package com.tilldawn.Models.Map.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.tilldawn.App;
import com.tilldawn.Models.Enums.MonsterType;
import com.tilldawn.Models.Map.Bullet;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.Map.Exp;
import com.tilldawn.Models.Map.Map;
import com.tilldawn.Models.Map.Monster.Eyebat;
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
    private final ArrayList<Monster> deadMonsters;
    private float brainMonsterSpawnTimer;
    private float elderSpawnTimer;
    private float eyebatSpawnTimer;
    private float stateTime;
    public MonsterController(GameMenu view, Map map, Character character) {
        this.view = view;
        this.map = map;
        this.character = character;
        allMonsters=map.getMonsters();
        deadMonsters=new ArrayList<>();
        spawnTrees();
    }
    public void update(){
        drawMonsters(view.getStage().getBatch());
        drawDeadMonsters(view.getStage().getBatch());
        updateMonsters();
        spawnBrainMonster();
        spawnElder();
        spawnEyebat();
        updateBullets();
        brainMonsterSpawnTimer +=Gdx.graphics.getDeltaTime();
        elderSpawnTimer +=Gdx.graphics.getDeltaTime();
        eyebatSpawnTimer +=Gdx.graphics.getDeltaTime();
        stateTime+=Gdx.graphics.getDeltaTime();
    }
    public void spawnMonster(MonsterType type){
        Map map=App.getCurrentMap();
        float x = (float)RandomNum.getRandomNumber(map.getWorldMinX(),map.getWorldMaxX()-100);
        float y = (float)RandomNum.getRandomNumber(map.getWorldMinY(),map.getWorldMaxY()-100);
        float playerX = character.getX();
        float playerY = character.getY();
        float minDistance = 300f;
        if(Math.hypot(playerX - x, playerY - y) < minDistance) return;
        if (!map.isWalkable(x,y)) {
            spawnMonster(type);
            return;
        }
        Monster newMonster=Monster.getInstance(type, x, y);
        if(newMonster==null) return;
        newMonster.getSprite().setPosition(x,y);
        allMonsters.add(newMonster);
    }
    public void spawnBrainMonster(){
        if(brainMonsterSpawnTimer<3) return;
        int spawnRate=(int)map.getBrainMonsterSpawnRate();
        while(spawnRate!=0){
            spawnMonster(MonsterType.BrainMonster);
            spawnRate--;
        }
        brainMonsterSpawnTimer=0;
    }
    public void spawnElder(){

    }
    public void spawnEyebat(){
        float time= map.getTime();
        if(stateTime<time/4 || eyebatSpawnTimer<10) return;
        int spawnRate=(int)map.getEyebatSpawnRate();
        while(spawnRate!=0){
            spawnMonster(MonsterType.Eyebat);
            spawnRate--;
        }
        eyebatSpawnTimer=0;
    }
    public void updateMonsters(){
        for(int i=allMonsters.size()-1;i>=0;i--){
            Monster monster=allMonsters.get(i);
            if(monster.getHp()<0) {
                monster.setStateTime(0);
                deadMonsters.add(monster);
                allMonsters.remove(i);
                map.addExp(new Exp(monster.getX(),monster.getY()));
                character.setKilledMonsters(character.getKilledMonsters()+1);
            }
            monster.update(Gdx.graphics.getDeltaTime());
        }
        for(int i=deadMonsters.size()-1;i>=0;i--){
            Monster monster=deadMonsters.get(i);
            monster.updateWithoutMove(Gdx.graphics.getDeltaTime());
            if(monster.getStateTime()>=0.6f) deadMonsters.remove(i);
        }
    }
    public void drawMonsters(Batch batch){
        for(Monster monster:allMonsters){
            monster.draw(batch);
        }
    }
    public void drawDeadMonsters(Batch batch){
        for(Monster monster:deadMonsters){
            monster.drawDeathAnimation(batch);
        }
    }
    public void spawnTrees(){
        int numberOfTrees=0;
        while(numberOfTrees<=10) {
            float x = (float) RandomNum.getRandomNumber(map.getWorldMinX(), map.getWorldMaxX() - 100);
            float y = (float) RandomNum.getRandomNumber(map.getWorldMinY(), map.getWorldMaxY() - 100);
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
    public void updateBullets(){
        Rectangle characterRectangle=character.getSprite().getBoundingRectangle();
        for(Monster monster:allMonsters){
            if(monster instanceof Eyebat){
                Eyebat eyebat=(Eyebat)monster;
                ArrayList<Bullet> bullets=eyebat.getBullets();
                for(int i=bullets.size()-1;i>=0;i--){
                    Bullet bullet=bullets.get(i);
                    bullet.update(Gdx.graphics.getDeltaTime());
                    bullet.draw(view.getStage().getBatch());
                    if(bullet.getBoundsBox().overlaps(characterRectangle)){
                        if(character.isInfiniteHp() || character.isInvincible()) continue;
                        character.setCurrentHp(character.getCurrentHp()-1);
                        bullets.remove(i);
                        continue;
                    }
                    if(bullet.isOutOfBounds()) bullets.remove(i);
                }
            }
        }
    }
}

package com.tilldawn.Models.Map.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tilldawn.App;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.Map.Exp;
import com.tilldawn.Models.Map.Map;
import com.tilldawn.Models.Map.Monster.BrainMonster;
import com.tilldawn.Models.Map.Monster.Monster;
import com.tilldawn.Models.Map.Monster.Tree;
import com.tilldawn.Models.User.User;
import com.tilldawn.View.GameMenu;
import com.tilldawn.View.WinMenu;

import java.util.ArrayList;

public class MapController {
    private final GameMenu view;
    private final CharacterController characterController;
    private final Character character;
    private final Map map;
    private final Texture background;
    private float stateTime;
    private final OrthographicCamera camera;
    private Texture pixel;
    private BitmapFont level;
    private final Texture ammoIcon;
    private BitmapFont ammo;
    private BitmapFont timeLabel;
    private BitmapFont kills;

    public MapController(GameMenu view, Character character, CharacterController characterController, Map map) {
        this.view = view;
        this.character = character;
        this.characterController = characterController;
        this.map = map;
        this.background = map.getBackground();
        this.camera = view.getCamera();
        this.ammoIcon = AssetManager.getAmmoIcon();

        character.setX((background.getWidth()-character.getHeroWidth()) / 2f);
        character.setY((background.getHeight()-character.getHeroHeight()) / 2f);
        setLevel();
        setAmmo();
        setPixel();
        setTimeLabel();
        setKills();
    }

    public void update() {
        Batch batch = view.getStage().getBatch();
        batch.draw(background, 0, 0);
        float screenCenterX = camera.position.x - character.getHeroWidth() / 2f;
        float screenCenterY = camera.position.y - character.getHeroHeight() / 2f;
        character.getSprite().setPosition(screenCenterX, screenCenterY);
        character.getSprite().draw(batch);
        drawHeart(batch);
        drawExpBar(batch);
        addLevel(batch);
        drawAmmo(batch);
        drawTimeLabel(batch);
        drawExps(batch);
        drawKills(batch);
        handleExps();
        handleWin();
        handleAutoAim();
        map.setRemainingTime(map.getRemainingTime()-Gdx.graphics.getDeltaTime());
        stateTime += Gdx.graphics.getDeltaTime();
    }

    public void drawHeart(Batch batch) {
        Animation<TextureRegion> heartAnimation = AssetManager.getHeart().getAnimation();
        TextureRegion deadHeart = AssetManager.getHeart().getTiles()[3];
        int currentHp = character.getCurrentHp();
        for (int i = 1; i <= character.getHP(); i++) {
            float x = camera.position.x - camera.viewportWidth / 2 + i * 64 - 32;
            float y = camera.position.y + camera.viewportHeight / 2 - 64 - getBarHeight();
            if (i <= currentHp) {
                TextureRegion currentFrame = heartAnimation.getKeyFrame(stateTime, true);
                batch.draw(currentFrame, x, y, currentFrame.getRegionWidth() * 2, currentFrame.getRegionHeight() * 2);
            } else {
                batch.draw(deadHeart, x, y, deadHeart.getRegionWidth() * 2, deadHeart.getRegionHeight() * 2);
            }
        }
    }

    public void drawExpBar(Batch batch) {
        float progress=character.getCurrentExp()/character.getExpPerLevel();
        float x=camera.position.x- getBarWidth() /2f;
        float y=camera.position.y+camera.viewportHeight / 2f - getBarHeight();
        batch.setColor(Color.DARK_GRAY);
        batch.draw(pixel, x, y, getBarWidth(), getBarHeight());
        batch.setColor(Color.GREEN);
        batch.draw(pixel, x, y, getBarWidth() * progress, getBarHeight());
        batch.setColor(Color.WHITE);
    }

    public void drawAmmo(Batch batch) {
        float width=ammoIcon.getWidth()*2f;
        float height=ammoIcon.getHeight()*2f;
        float x=camera.position.x - camera.viewportWidth / 2 + 32;
        float y=camera.position.y + camera.viewportHeight / 2f - 64 - getBarHeight() - height - 10;
        batch.draw(ammoIcon, x, y, width, height);
        float numberX=x+width;
        ammo.draw(batch,character.getCurrentAmmo()+"/"+character.getMaxAmmo(),numberX,y+40);
    }

    public void drawKills(Batch batch) {
        float x=camera.position.x - camera.viewportWidth / 2 + 32;
        float y=camera.position.y + camera.viewportHeight / 2f - 64 - getBarHeight() - ammoIcon.getHeight()*2f - 10;
        String kill="KILLS: "+map.getKills();
        kills.draw(batch, kill, x, y);
    }

    public void drawTimeLabel(Batch batch) {
        float x=camera.position.x + camera.viewportWidth / 2 - 200;
        float y=camera.position.y + camera.viewportHeight / 2f - getBarHeight() - ammoIcon.getHeight() - 10;
        float remainingTime= map.getRemainingTime();
        String sec=remainingTime%60f<10?"0"+(int)(remainingTime%60f):(int)(remainingTime%60f)+"";
        String timeStr=(int)(remainingTime/60f)+":"+sec;
        timeLabel.draw(batch,timeStr,x,y);
    }

    public void addLevel(Batch batch) {
        float x=camera.position.x-getLevelSize()-60;
        float y=camera.position.y+camera.viewportHeight / 2f - 15;
        level.draw(batch,"LEVEL: "+character.getLevel(),x,y);
    }

    public float getBarWidth(){
        return Gdx.graphics.getWidth();
    }

    public float getBarHeight(){
        return 50f;
    }

    public void setPixel() {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        pixel = new Texture(pixmap);
        pixmap.dispose();
    }
    public void setLevel(){
        FreeTypeFontGenerator generator=new FreeTypeFontGenerator(Gdx.files.internal("fonts/PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=getLevelSize();
        level=generator.generateFont(parameter);
        generator.dispose();
    }
    public void drawExps(Batch batch) {
        for(Exp exp: map.getExps()){
            exp.draw(batch);
        }
    }
    public void setAmmo(){
        FreeTypeFontGenerator generator=new FreeTypeFontGenerator(Gdx.files.internal("fonts/PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=getLevelSize();
        ammo=generator.generateFont(parameter);
        generator.dispose();
    }
    public void setTimeLabel(){
        FreeTypeFontGenerator generator=new FreeTypeFontGenerator(Gdx.files.internal("fonts/PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=getLevelSize();
        timeLabel=generator.generateFont(parameter);
        generator.dispose();
    }
    public void setKills(){
        FreeTypeFontGenerator generator=new FreeTypeFontGenerator(Gdx.files.internal("fonts/PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=getLevelSize();
        kills=generator.generateFont(parameter);
        generator.dispose();
    }
    public int getLevelSize(){
        return 32;
    }
    public void dispose() {
        background.dispose();
        map.dispose();
    }
    public void handleExps(){
        ArrayList<Exp> exps=map.getExps();
        Rectangle characterRectangle=character.getSprite().getBoundingRectangle();
        for(int i=exps.size()-1;i>=0;i--){
            Exp exp=exps.get(i);
            if(exp.getSprite().getBoundingRectangle().overlaps(characterRectangle)){
                character.setCurrentExp(character.getCurrentExp()+1);
                exps.remove(i);
            }
        }
    }
    public void handleWin(){
        if(map.getRemainingTime()<0){
            map.setScore(map.getKills()*map.getTime());
            character.setScore(character.getScore()+map.getScore());
            character.setKilledMonsters(character.getKilledMonsters()+map.getKills());
            if(map.getTime()>character.getMostSurvival()) character.setMostSurvival(map.getTime());
            User.saveUsers();
            view.getGame().setScreen(new WinMenu(view.getGame()));
        }
    }
    public void handleAutoAim(){
        if(Gdx.input.isKeyJustPressed(App.getCurrentUser().getMovingKeys().getAutoAim())){
            character.setAutoAim(!character.isAutoAim());
        }
        if(character.isAutoAim()){
            ArrayList<Monster> allMonster=map.getAllMonsters();
            if(allMonster.isEmpty()) return;
            Monster closest = getClosest(allMonster);
            if(closest!=null){
                float monsterWorldX=closest.getX();
                float monsterWorldY=closest.getY();
                if(closest instanceof BrainMonster) {
                    monsterWorldX+=closest.getWidth();
                    monsterWorldY+=closest.getHeight();
                } else{
                    monsterWorldX+=closest.getWidth()/2f;
                    monsterWorldY+=closest.getHeight()/2f;
                }
                Vector3 screenCoords=camera.project(new Vector3(monsterWorldX,monsterWorldY,0));
                int screenX=(int) screenCoords.x;
                int screenY=Gdx.graphics.getHeight()-(int)screenCoords.y;
                Gdx.input.setCursorPosition(screenX,screenY);
            }
        }
    }

    private Monster getClosest(ArrayList<Monster> allMonster) {
        Monster closest=null;
        float distance=Float.MAX_VALUE;
        float cx=character.getX()+character.getHeroWidth()/2f;
        float cy=character.getY()+character.getHeroHeight()/2f;
        for(Monster monster: allMonster){
            if(monster instanceof Tree) continue;
            float mx=monster.getX();
            float my=monster.getY();
            if(monster instanceof BrainMonster) {
                mx+=monster.getWidth();
                my+=monster.getHeight();
            } else{
                mx+=monster.getWidth()/2f;
                my+=monster.getHeight()/2f;
            }
            float dist2=(cx-mx)*(cx-mx)+(cy-my)*(cy-my);
            if(dist2<distance){
                distance=dist2;
                closest=monster;
            }
        }
        return closest;
    }
}

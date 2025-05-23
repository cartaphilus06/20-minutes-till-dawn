package com.tilldawn.Models.Map.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.Map.Map;
import com.tilldawn.Models.Map.Monster.Monster;
import com.tilldawn.Models.User.MovingKeys;
import com.tilldawn.View.GameMenu;

public class CharacterController {
    private static CharacterController characterController;
    private final GameMenu view;
    private final Character character;
    private final MovingKeys movingKeys;
    private final Map map;
    private float dx=0;
    private float dy=0;
    private boolean facingLeft=false;
    private Timer.Task resetRunTask;
    public CharacterController(Character character, MovingKeys movingKeys, GameMenu view,Map map) {
        this.character = character;
        this.movingKeys = movingKeys;
        this.view = view;
        this.map = map;
        characterController = this;
    }
    public void update() {
        handleInput();
        if(map.isWalkable(character.getX()+dx,character.getY()+dy)) {
            character.setX(character.getX() + dx);
            character.setY(character.getY() + dy);
            playAnimation();
        }
        handlePlayerCollision();
        handleHp();
    }
    public void playAnimation(){
        Animation<TextureRegion> animation;
        if(character.isIdle()) animation=view.getStandAnimation();
        else if(character.isRunning()) animation=view.getRunAnimation();
        else animation=view.getWalkAnimation();
        TextureRegion currentFrame=animation.getKeyFrame(character.getStateTime());
        if (isFacingLeft() ^ currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        }
        character.getSprite().setRegion(currentFrame);
        if(!animation.isAnimationFinished(character.getStateTime())){
            character.setStateTime(Gdx.graphics.getDeltaTime()+character.getStateTime());
        }
        else {
            character.setStateTime(0);
        }
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }
    public void handleInput() {
        dx = 0;
        dy = 0;
        float speed= (float)(character.getSpeed()*0.7);
        boolean isMoving = false;
        if (Gdx.input.isKeyPressed(movingKeys.getMoveUp())) {
            dy += speed;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(movingKeys.getMoveDown())) {
            dy -= speed;
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(movingKeys.getMoveRight())) {
            dx += speed;
            setFacingLeft(false);
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(movingKeys.getMoveLeft())) {
            dx -= speed;
            setFacingLeft(true);
            isMoving = true;
        }
        character.setIdle(!isMoving);
        if (dx != 0 && dy != 0) {
            float norm = (float) Math.sqrt(dx * dx + dy * dy);
            dx = (dx / norm) * speed;
            dy = (dy / norm) * speed;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)) {
            character.setRunning(!character.isRunning());
        }
    }
    public boolean isFacingLeft() {
        return facingLeft;
    }
    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }
    public void handleTouchDown(int button){
        if (button == Input.Buttons.LEFT) {
            handleRunning();
        }
    }
    public void handleRunning(){
        character.setRunning(false);
        if (resetRunTask != null) {
            resetRunTask.cancel();
        }
        resetRunTask = new Timer.Task() {
            @Override
            public void run() {
                character.setRunning(true);
                resetRunTask = null;
            }
        };
        Timer.schedule(resetRunTask, 0.5f);
    }
    public void handleHp(){
        if(character.getCurrentHp()<=0){
            //gameOver
        }
    }
    public void handlePlayerCollision(){
        float targetX=character.getX()+dx;
        float targetY=character.getY()+dy;
        float currentX=character.getX();
        float currentY=character.getY();
        if(character.isInvincible()) return;
        for(Monster monster: map.getMonsters()){
            Rectangle monsterRectangle=monster.getSprite().getBoundingRectangle();
            if(monsterRectangle.contains(targetX,targetY) || monsterRectangle.contains(currentX,currentY)){
                character.setCurrentHp(character.getCurrentHp()-1);
                handleInvincibility();
                break;
            }
        }
    }
    public void handleInvincibility(){
        character.setInvincible(true);
        Timer.schedule(new Timer.Task() {
            public void run(){
                character.setInvincible(false);
            }
        },1);
    }
    public static CharacterController getCharacterController() {
        return characterController;
    }
}

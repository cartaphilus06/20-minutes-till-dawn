package com.tilldawn.Models.Map.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.User.MovingKeys;
import com.tilldawn.View.GameMenu;

public class CharacterController {
    private final GameMenu view;
    private final Character character;
    private final MovingKeys movingKeys;
    private float dx=0;
    private float dy=0;
    private float minX;
    private float minY;
    private float maxX;
    private float maxY;
    public CharacterController(Character character, MovingKeys movingKeys, GameMenu view) {
        this.character = character;
        this.movingKeys = movingKeys;
        this.view = view;
    }
    public void update(){
        character.getSprite().draw(view.getStage().getBatch());
        playAnimation();
        handleInput();
    }
    public void playAnimation(){
        Animation<TextureRegion> animation;
        if(character.isIdle()) animation=view.getStandAnimation();
        else if(character.isRunning()) animation=view.getRunAnimation();
        else animation=view.getWalkAnimation();
        character.getSprite().setRegion(animation.getKeyFrame(character.getStateTime()));
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
        boolean isMoving = false;
        if (Gdx.input.isKeyPressed(movingKeys.getMoveUp())) {
            if(character.getY()+character.getSpeed()<getMaxY()) {
                dy += character.getSpeed();
            }
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(movingKeys.getMoveDown())) {
            if(character.getY()-character.getSpeed()>getMinY()) {
                dy -= character.getSpeed();
            }
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(movingKeys.getMoveRight())) {
            if(character.getX()+character.getSpeed()<getMaxX()) {
                dx += character.getSpeed();
            }
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(movingKeys.getMoveLeft())) {
            if(character.getX()-character.getSpeed()>getMinX()) {
                dx -= character.getSpeed();
            }
            character.getSprite().flip(true, false); // Might need better flip logic
            isMoving = true;
        }
        character.setIdle(!isMoving);
        if (dx != 0 && dy != 0) {
            float norm = (float) Math.sqrt(dx * dx + dy * dy);
            dx = (dx / norm) * character.getSpeed();
            dy = (dy / norm) * character.getSpeed();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)) {
            character.setRunning(!character.isRunning());
        }
    }

    public float getDx() {
        return dx;
    }
    public void setDx(float dx) {
        this.dx = dx;
    }
    public float getDy() {
        return dy;
    }
    public void setDy(float dy) {
        this.dy = dy;
    }
    public float getMinX() {
        return minX;
    }
    public void setMinX(float minX) {
        this.minX = minX;
    }
    public float getMinY() {
        return minY;
    }
    public void setMinY(float minY) {
        this.minY = minY;
    }
    public float getMaxX() {
        return maxX;
    }
    public void setMaxX(float maxX) {
        this.maxX = maxX;
    }
    public float getMaxY() {
        return maxY;
    }
    public void setMaxY(float maxY) {
        this.maxY = maxY;
    }
}

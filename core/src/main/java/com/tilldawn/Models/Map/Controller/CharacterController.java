package com.tilldawn.Models.Map.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.Map.Map;
import com.tilldawn.Models.User.MovingKeys;
import com.tilldawn.View.GameMenu;

public class CharacterController {
    private final GameMenu view;
    private final Character character;
    private final MovingKeys movingKeys;
    private final Map map;
    private float dx=0;
    private float dy=0;
    private boolean facingLeft=false;
    public CharacterController(Character character, MovingKeys movingKeys, GameMenu view,Map map) {
        this.character = character;
        this.movingKeys = movingKeys;
        this.view = view;
        this.map = map;
    }
    public void update() {
        handleInput();
        character.setX(character.getX() + dx);
        character.setY(character.getY() + dy);
        playAnimation();
    }
    public void playAnimation(){
        Animation<TextureRegion> animation;
        if(character.isIdle()) animation=view.getStandAnimation();
        else if(character.isRunning()) animation=view.getRunAnimation();
        else animation=view.getWalkAnimation();
        TextureRegion currentFrame=animation.getKeyFrame(character.getStateTime());
        if (isFacingLeft() && !currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        } else if (!isFacingLeft() && currentFrame.isFlipX()) {
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
        boolean isMoving = false;
        if (Gdx.input.isKeyPressed(movingKeys.getMoveUp())) {
            dy += character.getSpeed();
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(movingKeys.getMoveDown())) {
            dy -= character.getSpeed();
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(movingKeys.getMoveRight())) {
            dx += character.getSpeed();
            setFacingLeft(false);
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(movingKeys.getMoveLeft())) {
            dx -= character.getSpeed();

            setFacingLeft(true);
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
    public boolean isFacingLeft() {
        return facingLeft;
    }
    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }
}

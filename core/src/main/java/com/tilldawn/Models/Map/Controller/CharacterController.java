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
            dy += character.getSpeed();
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(movingKeys.getMoveDown())) {
            dy -= character.getSpeed();
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(movingKeys.getMoveRight())) {
            dx += character.getSpeed();
            isMoving = true;
        }
        if (Gdx.input.isKeyPressed(movingKeys.getMoveLeft())) {
            dx -= character.getSpeed();
            character.getSprite().flip(true, false); // Might need better flip logic
            isMoving = true;
        }

        character.setIdle(!isMoving);

        // Normalize diagonal speed to prevent faster movement when moving diagonally
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
}

package com.tilldawn.Models.Map.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.User.MovingKeys;
import com.tilldawn.View.GameMenu;

public class CharacterController {
    private final GameMenu view;
    private final Character character;
    private final MovingKeys movingKeys;
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
        if(character.getIsIdle()) animation=view.getStandAnimation();
        else if(character.getIsRunning()) animation=view.getRunAnimation();
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
    public void handleInput(){

    }
}

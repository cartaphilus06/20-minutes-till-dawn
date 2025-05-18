package com.tilldawn.Models.Map.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.View.GameMenu;

public class MapController {
    private final GameMenu view;
    private final CharacterController characterController;
    private final Character character;
    private final Texture background;
    private float backgroundX;
    private float backgroundY;
    public MapController(GameMenu view,Character character,CharacterController characterController) {
        this.view = view;
        this.character = character;
        this.characterController = characterController;
        this.background = new Texture(Gdx.files.internal("images/backgrounds/background.png"));
        backgroundX= (float) (Gdx.graphics.getWidth() - background.getWidth()) /2;
        backgroundY= (float) (Gdx.graphics.getHeight() - background.getHeight()) /2;
        character.setX((Gdx.graphics.getWidth()-character.getHeroWidth())/2);
        character.setY((Gdx.graphics.getHeight() - character.getHeroHeight())/2);
        setMinAndMax();
    }
    public void update(){
        setBackgroundX(backgroundX-characterController.getDx());
        setBackgroundY(backgroundY-characterController.getDy());
        setMinAndMax();
        view.getStage().getBatch().draw(background,backgroundX,backgroundY);
    }
    public void setBackgroundX(float x){
        backgroundX = x;
    }
    public void setBackgroundY(float y){
        backgroundY = y;
    }
    public void setMinAndMax(){
        characterController.setMinX(backgroundX-character.getHero().getIconWidth());
        characterController.setMinY(backgroundY-character.getHero().getIconHeight());
        characterController.setMaxX(backgroundX+background.getWidth()-character.getHeroWidth());
        characterController.setMaxY(backgroundY+background.getHeight()-character.getHeroHeight());
    }
}

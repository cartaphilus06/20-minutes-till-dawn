package com.tilldawn.Models.Map.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.View.GameMenu;

public class MapController {
    private final GameMenu view;
    private final Character character;
    private final Texture background;
    private float backgroundX;
    private float backgroundY;
    public MapController(GameMenu view,Character character) {
        this.view = view;
        this.character = character;
        this.background = new Texture(Gdx.files.internal("images/backgrounds/background.png"));
    }
    public void update(){
        backgroundX = character.getX();
        backgroundY = character.getY();
        view.getStage().getBatch().draw(background,backgroundX,backgroundY);
    }
}

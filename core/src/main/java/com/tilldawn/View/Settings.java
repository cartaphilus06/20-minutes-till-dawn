package com.tilldawn.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tilldawn.Controller.SettingsController;

import java.util.Stack;

public class Settings implements Screen {
    private final SettingsController controller=new SettingsController(this);
    private Stage stage;
    private final Game game;
    public Settings(Game game) {
        this.game=game;
    }
    @Override
    public void show() {
        stage=new Stage(new FitViewport(800, 600));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

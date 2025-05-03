package com.tilldawn;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tilldawn.Models.User.User;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.RegisterMenu;

public class Main extends Game {
    private static Main main;
    private SpriteBatch batch;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        User.loadUsers();
        setScreen(new MainMenu(this));
    }

    @Override
    public void render() {
        handleFullScreenToggle();
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
    public static Main getMain() {
        return main;
    }
    public SpriteBatch getBatch() {
        return batch;
    }
    public void handleFullScreenToggle(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.F11)){
            if(Gdx.graphics.isFullscreen()){
                Gdx.graphics.setWindowedMode(1280, 720);
            }
        }
    }
}

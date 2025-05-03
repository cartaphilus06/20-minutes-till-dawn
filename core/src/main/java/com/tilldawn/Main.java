package com.tilldawn;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Models.User.User;
import com.tilldawn.View.RegisterMenu;

public class Main extends Game {
    private static Main main;
    private SpriteBatch batch;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        User.loadUsers();
        setScreen(new RegisterMenu(this));
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.end();
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
}

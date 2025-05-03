package com.tilldawn;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Models.User.User;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private static Main main;
    private static SpriteBatch batch;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        User.loadUsers();
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
    public static SpriteBatch getBatch() {
        return batch;
    }
}

package com.tilldawn.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tilldawn.Controller.RegisterMenuController;
import com.tilldawn.Main;
import com.tilldawn.Models.AssetManager;

import java.awt.*;

public class RegisterMenu implements Screen {
    private final RegisterMenuController controller=new RegisterMenuController(this);
    private Stage stage;
    private final Game game;
    private Texture background;
    public RegisterMenu(Game game) {
        this.game=game;
    }
    @Override
    public void show() {
        stage=new Stage(new FitViewport(800,600));
        Gdx.input.setInputProcessor(stage);
        setUpUI();
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        Main.getMain().handleFullScreenToggle();
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
        stage.dispose();
        background.dispose();
    }
    public void setUpUI(){
        background=new Texture(Gdx.files.internal("assets/images/bg.png"));
        Skin skin=AssetManager.getSkin();
        Label.LabelStyle titleStyle=skin.get("title", Label.LabelStyle.class);
        Label title=new Label("20 MINUTES\nTILL DAWN",titleStyle);
        Table table=new Table();
        table.setFillParent(true);
        table.add(title);
        stage.addActor(table);
    }
}

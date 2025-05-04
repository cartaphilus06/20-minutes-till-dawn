package com.tilldawn.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tilldawn.Controller.MainMenuController;
import com.tilldawn.Main;
import com.tilldawn.Models.AssetManager;

public class MainMenu implements Screen {
    private final MainMenuController controller=new MainMenuController(this);
    private Stage stage;
    private final Game game;
    private Texture background;
    private TextButton register;
    private TextButton login;
    private TextButton settings;
    private TextButton exit;
    public MainMenu(Game game) {
        this.game=game;
    }
    @Override
    public void show() {
        stage=new Stage(new FitViewport(1920,1080));
        Gdx.input.setInputProcessor(stage);
        setUpUI();
        controller.handleClickedButtons();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0,
            stage.getViewport().getWorldWidth(),
            stage.getViewport().getWorldHeight());
        stage.getBatch().end();
        stage.act(v);
        stage.draw();
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
    public void setUpUI(){
        Table table=new Table();
        table.setFillParent(true);
        table.center();
        background=new Texture(Gdx.files.internal("images/bg.png"));
        Skin skin= AssetManager.getSkin();
        Label title=new Label("20 MINUTES\nTILL DAWN", skin);
        title.setAlignment(Align.center);
        register=new TextButton("REGISTER", skin);
        login=new TextButton("LOGIN", skin);
        settings=new TextButton("SETTINGS", skin);
        exit=new TextButton("EXIT", skin);
        table.add(title).colspan(2).padBottom(30).row();
        table.add(register);
        table.add(login);
        table.add(settings);
        table.add(exit);
        stage.addActor(table);
    }
    public TextButton getRegister() {
        return register;
    }
    public TextButton getLogin() {
        return login;
    }
    public TextButton getSettings() {
        return settings;
    }
    public TextButton getExit() {
        return exit;
    }
    public Game getGame() {
        return game;
    }
}

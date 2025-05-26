package com.tilldawn.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tilldawn.App;
import com.tilldawn.Models.AssetManager;

public class GameOverMenu implements Screen {
    private final Game game;
    private Stage stage;
    private final Skin skin;
    private Label youLost;
    private Label gotScore;
    private TextButton newGame;
    private TextButton returnToMainMenu;
    private TextButton exit;
    public GameOverMenu(Game game) {
        this.game = game;
        skin= AssetManager.getSkin();
    }
    @Override
    public void show() {
        stage=new Stage(new FitViewport(1920,1080));
        Gdx.input.setInputProcessor(stage);
        youLost=new Label("YOU LOST",skin);
        gotScore=new Label("GOT SCORE: "+ App.getCurrentMap().getScore(),skin);
        newGame=new TextButton("NEW GAME",skin);
        returnToMainMenu=new TextButton("RETURN TO MAIN MENU",skin);
        exit=new TextButton("EXIT",skin);
        handleButtons();
        setUpUI();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
    }
    public void setUpUI() {
        Table table=new Table();
        table.setFillParent(true);
        table.center();
        table.defaults().pad(10);
        float buttonWidth=620f;
        table.add(youLost).width(200).height(60).row();
        table.add(gotScore).width(200).height(60).row();
        table.add(newGame).width(buttonWidth).height(60).row();
        table.add(returnToMainMenu).width(buttonWidth).height(60).row();
        table.add(exit).width(buttonWidth).height(60);
        stage.addActor(table);
    }
    public void handleButtons(){
        newGame.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                game.setScreen(new PregameMenu(game));
            }
        });
        returnToMainMenu.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                game.setScreen(new MainMenu(game));
            }
        });
        exit.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                Gdx.app.exit();
            }
        });
    }
    public Game getGame() {
        return game;
    }
    public Stage getStage() {
        return stage;
    }
}

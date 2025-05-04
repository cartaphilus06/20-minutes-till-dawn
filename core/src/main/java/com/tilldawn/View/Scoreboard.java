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
import com.tilldawn.Controller.ScoreboardController;
import com.tilldawn.Models.AssetManager;

import java.util.ArrayList;

public class Scoreboard implements Screen {
    private final ScoreboardController controller=new ScoreboardController(this);
    private final Game game;
    private Texture background;
    private Stage stage;
    private TextButton sortByScore;
    private TextButton sortByUsername;
    private TextButton sortByKills;
    private TextButton sortBySurvival;
    private TextButton exit;
    private ArrayList<Label> users;
    public Scoreboard(Game game) {
        this.game=game;
        users=controller.addUsersToArrayList();
    }
    @Override
    public void show() {
        stage=new Stage();
        Gdx.input.setInputProcessor(stage);
        setUpStaticUI();
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
        stage.dispose();
        background.dispose();
    }
    public void setUpStaticUI(){
        Skin skin=AssetManager.getSkin();
        background=new Texture(Gdx.files.internal("images/backgrounds/bg.png"));
        Table buttonTables=new Table();
        buttonTables.setFillParent(true);
        buttonTables.top().pad(20);
        buttonTables.row();

        Label sort=new Label("Sorting Type", skin);
        sortBySurvival=new TextButton("SURVIVAL", skin);
        sortByUsername=new TextButton("USERNAME", skin);
        sortByKills=new TextButton("KILLS", skin);
        sortByScore=new TextButton("SCORE", skin);
        exit=new TextButton("EXIT", skin);
        buttonTables.row();
        buttonTables.add(sort);
        int buttonWidth=300;
        int buttonHeight=60;
        buttonTables.add(sortBySurvival).width(buttonWidth).height(buttonHeight);
        buttonTables.add(sortByUsername).width(buttonWidth).height(buttonHeight);
        buttonTables.add(sortByKills).width(buttonWidth).height(buttonHeight);
        buttonTables.add(sortByScore).width(buttonWidth).height(buttonHeight);
        buttonTables.add(exit).width(buttonWidth).height(buttonHeight);
        stage.addActor(buttonTables);
    }
    public TextButton getSortByUsername() {
        return sortByUsername;
    }
    public TextButton getSortByScore() {
        return sortByScore;
    }
    public TextButton getSortByKills() {
        return sortByKills;
    }
    public TextButton getSortBySurvival() {
        return sortBySurvival;
    }
    public TextButton getExit() {
        return exit;
    }
    public Game getGame() {
        return game;
    }
    public Stage getStage() {
        return stage;
    }
    public ArrayList<Label> getUsers() {
        return users;
    }
}

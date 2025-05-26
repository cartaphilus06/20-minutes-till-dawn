package com.tilldawn.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.tilldawn.App;
import com.tilldawn.Controller.ScoreboardController;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.User.User;

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
    private final ArrayList<User> allUsers;
    public Scoreboard(Game game) {
        this.game=game;
        allUsers=User.getAllUsers();
    }
    @Override
    public void show() {
        stage=new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin=AssetManager.getSkin();
        background=new Texture(Gdx.files.internal("images/backgrounds/bg.png"));
        sortBySurvival=new TextButton("SURVIVAL", skin);
        sortByUsername=new TextButton("USERNAME", skin);
        sortByKills=new TextButton("KILLS", skin);
        sortByScore=new TextButton("SCORE", skin);
        exit=new TextButton("BACK", skin);
        setUpStaticUI();
        controller.handleClickedButtons(allUsers);
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
        stage.clear();
        Skin skin=AssetManager.getSkin();
        Table root=new Table();
        root.setFillParent(true);
        root.top().padTop(20);
        Table buttonTables=new Table();
        Label sort=new Label("Sorting Type", skin);
        buttonTables.row();
        buttonTables.add(sort);
        int buttonWidth=300;
        int buttonHeight=60;
        buttonTables.add(sortBySurvival).width(buttonWidth).height(buttonHeight);
        buttonTables.add(sortByUsername).width(buttonWidth).height(buttonHeight);
        buttonTables.add(sortByKills).width(buttonWidth).height(buttonHeight);
        buttonTables.add(sortByScore).width(buttonWidth).height(buttonHeight);
        buttonTables.add(exit).width(buttonWidth).height(buttonHeight);
        root.row();
        root.add(buttonTables);
        int index=0;
        for(User u:allUsers){
            StringBuilder builder=new StringBuilder();
            Character character=u.getCharacter();
            builder.append("username: ").append(u.getUsername()).append(" | ")
                .append("score: ").append(character.getScore()).append(" | ")
                .append("kills: ").append(character.getKilledMonsters()).append(" | ")
                .append("most survival: ").append((int)character.getMostSurvival());
            if(App.getCurrentUser()!=null)
                if(u.getUsername().equals(App.getCurrentUser().getUsername()))
                    builder.append(" (logged in)");
            builder.append("\n");
            Label user=new Label(builder.toString(),skin);
            if(index==0) user.setColor(Color.RED);
            if(index==1) user.setColor(Color.GREEN);
            if(index==2) user.setColor(Color.BLUE);
            index++;
            root.row();
            root.add(user).width(600).height(60).colspan(6);
        }
        stage.addActor(root);
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
}

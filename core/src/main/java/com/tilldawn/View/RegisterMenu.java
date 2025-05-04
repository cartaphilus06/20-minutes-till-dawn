package com.tilldawn.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
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
    TextField usernameField;
    TextField passwordField;
    TextButton register;
    TextButton back;
    public RegisterMenu(Game game) {
        this.game=game;
    }
    @Override
    public void show() {
        stage=new Stage();
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
        stage.dispose();
        background.dispose();
    }
    public void setUpUI(){
        Table table=new Table();
        table.setFillParent(true);
        table.center();
        background=new Texture(Gdx.files.internal("images/bg.png"));
        Skin skin= AssetManager.getSkin();
        Label username=new Label("USERNAME",skin);
        Label password=new Label("PASSWORD",skin);
        usernameField=new TextField("",skin);
        passwordField=new TextField("",skin);
        passwordField.setPasswordMode(true);
        register=new TextButton("REGISTER",skin);
        back=new TextButton("BACK",skin);
        table.row();
        table.add(username).width(300).padBottom(20);
        table.row();
        table.add(usernameField).width(300).padBottom(20);
        table.row();
        table.add(password).width(300).padBottom(20);
        table.row();
        table.add(passwordField).width(300).padBottom(20);
        table.row();
        table.add(register).padRight(10);
        table.add(back);
        stage.addActor(table);
    }
    public TextField getUsername() {
        return usernameField;
    }
    public TextField getPassword() {
        return passwordField;
    }
    public TextButton getRegisterButton() {
        return register;
    }
    public TextButton getBackButton() {
        return back;
    }
    public Stage getStage() {
        return stage;
    }
    public Game getGame(){
        return game;
    }
}

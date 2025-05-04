package com.tilldawn.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.tilldawn.Controller.LoginMenuController;
import com.tilldawn.Models.AssetManager;

public class LoginMenu implements Screen {
    private final LoginMenuController controller=new LoginMenuController(this);
    private final Game game;
    private Stage stage;
    private Texture background;
    private TextField usernameField;
    private TextField passwordField;
    private TextButton loginButton;
    private TextButton back;
    public LoginMenu(Game game) {
        this.game = game;
    }
    @Override
    public void show() {
        stage = new Stage();
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
        loginButton=new TextButton("LOGIN",skin);
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
        table.add(loginButton).padRight(10);
        table.add(back);
        stage.addActor(table);
    }
    public TextButton getLoginButton() {
        return loginButton;
    }
    public TextButton getBackButton() {
        return back;
    }
    public TextField getUsernameField() {
        return usernameField;
    }
    public TextField getPasswordField() {
        return passwordField;
    }
    public Stage getStage() {
        return stage;
    }
    public Game getGame() {
        return game;
    }
}

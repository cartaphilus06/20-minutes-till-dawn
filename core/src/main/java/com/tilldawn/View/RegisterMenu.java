package com.tilldawn.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.tilldawn.Controller.RegisterMenuController;
import com.tilldawn.Models.AssetManager;

public class RegisterMenu implements Screen {

    private final Game game;
    private final RegisterMenuController controller;
    private Stage stage;
    private Texture background;

    private TextField usernameField;
    private TextField passwordField;
    private TextButton registerButton;
    private TextButton backButton;

    public RegisterMenu(Game game) {
        this.game = game;
        this.controller = new RegisterMenuController(this);
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        background = new Texture(Gdx.files.internal("images/backgrounds/menusBackground.png"));
        setUpUI();
        controller.handleClickedButtons();
    }

    private void setUpUI() {
        Skin skin = AssetManager.getSkin();
        Table table = new Table();
        table.setFillParent(true);
        table.center().padTop(300);

        // Create UI elements
        Label usernameLabel = new Label("USERNAME", skin);
        Label passwordLabel = new Label("PASSWORD", skin);

        usernameField = new TextField("", AssetManager.getTextFieldStyle());
        passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        registerButton = new TextButton("REGISTER", skin);
        backButton = new TextButton("BACK", skin);

        table.defaults().pad(10);

        table.add(usernameLabel).colspan(2).row();
        table.add(usernameField).width(300).height(80).colspan(2).row();

        table.add(passwordLabel).colspan(2).row();
        table.add(passwordField).width(300).height(80).colspan(2).padBottom(30).row();

        Table buttonTable = new Table();
        buttonTable.add(registerButton).width(300).height(70).padRight(20);
        buttonTable.add(backButton).width(300).height(70);

        table.add(buttonTable).colspan(2).row();
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
        stage.getBatch().end();

        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextButton getRegisterButton() {
        return registerButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public Stage getStage() {
        return stage;
    }

    public Game getGame() {
        return game;
    }
}

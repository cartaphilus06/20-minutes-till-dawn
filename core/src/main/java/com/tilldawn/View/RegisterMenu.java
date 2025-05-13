package com.tilldawn.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private TextField securityQuestionField;
    private TextField securityAnswerField;
    private TextButton registerButton;
    private TextButton backButton;
    private float stateTime=0f;
    private final Texture title=AssetManager.get20minutesTillDawnLogo();
    private final Animation<TextureRegion> rightEyesAnimation =new Animation<>(1,AssetManager.getMenusRightEyes());
    private final Animation<TextureRegion> leftEyeAnimation =new Animation<>(1,AssetManager.getMenusLeftEyes());

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
        table.center().padTop(500);

        // Create UI elements
        Label security=new Label("CHOOSE A SECURITY QUESTION", skin);

        usernameField = new TextField("", AssetManager.getTextFieldStyle());
        usernameField.setMessageText("USERNAME");
        passwordField = new TextField("", AssetManager.getTextFieldStyle());
        passwordField.setMessageText("PASSWORD");
        securityQuestionField = new TextField("", AssetManager.getTextFieldStyle());
        securityQuestionField.setMessageText("QUESTION");
        securityAnswerField = new TextField("", AssetManager.getTextFieldStyle());
        securityAnswerField.setMessageText("ANSWER");
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        registerButton = new TextButton("REGISTER", skin);
        backButton = new TextButton("BACK", skin);

        table.defaults().pad(10);

        table.add(usernameField).width(300).height(80).colspan(2).row();

        table.add(passwordField).width(300).height(80).colspan(2).padBottom(30).row();

        table.add(security).colspan(2).row();
        table.add(securityQuestionField).width(300).height(80).colspan(2).colspan(2).padBottom(30).row();
        table.add(securityAnswerField).width(300).height(80).colspan(2).padBottom(30).row();

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
        stateTime+=delta;
        float titleYPosition=stage.getViewport().getWorldHeight()-title.getHeight()*1.7f+(float)Math.sin(stateTime*2.5f)*10f;
        float titleXPosition=(stage.getViewport().getWorldWidth()-title.getWidth()*1.5f)/2+(float)Math.cos(stateTime*2.5f)*30f;
        float upRightEyeYPosition=550f+(float)Math.sin(Math.PI*stateTime*0.5f)*30f;
        float upRightEyeXPosition=1250f;
        float downRightEyeYPosition=upRightEyeYPosition-200;
        float downRightEyeXPosition=upRightEyeXPosition-30;
        float upLeftEyeXPosition=stage.getViewport().getWorldWidth()-upRightEyeXPosition-AssetManager.getMenusLeftEyes()[0].getRegionWidth();
        float downLeftEyeXPosition=stage.getViewport().getWorldWidth()-downRightEyeXPosition-AssetManager.getMenusLeftEyes()[0].getRegionWidth();
        stage.getBatch().draw(title, titleXPosition, titleYPosition,title.getWidth()*1.5f, title.getHeight()*1.5f);
        stage.getBatch().draw(rightEyesAnimation.getKeyFrame(stateTime,true),upRightEyeXPosition,upRightEyeYPosition);
        stage.getBatch().draw(rightEyesAnimation.getKeyFrame(stateTime,true),downRightEyeXPosition,downRightEyeYPosition);
        stage.getBatch().draw(leftEyeAnimation.getKeyFrame(stateTime,true),upLeftEyeXPosition, upRightEyeYPosition);
        stage.getBatch().draw(leftEyeAnimation.getKeyFrame(stateTime,true),downLeftEyeXPosition, downRightEyeYPosition);
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

    public TextField getSecurityQuestionField() {
        return securityQuestionField;
    }

    public TextField getSecurityAnswerField() {
        return securityAnswerField;
    }

    public Stage getStage() {
        return stage;
    }

    public Game getGame() {
        return game;
    }
}

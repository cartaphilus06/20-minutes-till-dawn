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
    private TextButton forgetPassword;
    private TextField questionField;
    private TextField answerField;
    private TextButton submit;
    private TextButton backToLogin;
    private float stateTime=0f;
    private final Texture title=AssetManager.get20minutesTillDawnLogo();
    private final Animation<TextureRegion> rightEyesAnimation =new Animation<>(1,AssetManager.getMenusRightEyes());
    private final Animation<TextureRegion> leftEyeAnimation =new Animation<>(1,AssetManager.getMenusLeftEyes());
    public LoginMenu(Game game) {
        this.game = game;
    }
    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        background=new Texture(Gdx.files.internal("images/backgrounds/menusBackGround.png"));
        Skin skin= AssetManager.getSkin();
        usernameField=new TextField("",AssetManager.getTextFieldStyle());
        passwordField=new TextField("",AssetManager.getTextFieldStyle());
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');
        loginButton=new TextButton("LOGIN",skin);
        back=new TextButton("BACK",skin);
        forgetPassword=new TextButton("FORGET PASSWORD",skin);
        questionField=new TextField("",AssetManager.getTextFieldStyle());
        questionField.setMessageText("QUESTION");
        answerField=new TextField("",AssetManager.getTextFieldStyle());
        answerField.setMessageText("ANSWER");
        submit=new TextButton("SUBMIT",skin);
        backToLogin=new TextButton("BACK",skin);
        setUpUI();
        controller.handleClickedButtons();
    }

    @Override
    public void render(float delta) {
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
        stage.clear();
        Table table=new Table();
        table.setFillParent(true);
        table.center().padTop(300);
        Skin skin=AssetManager.getSkin();
        Label username=new Label("USERNAME",skin);
        Label password=new Label("PASSWORD",skin);
        table.defaults().pad(10);

        table.add(forgetPassword).width(500).height(60).colspan(2).row();

        table.add(username).colspan(2).row();
        table.add(usernameField).width(300).height(80).colspan(2).row();

        table.add(password).colspan(2).row();
        table.add(passwordField).width(300).height(80).colspan(2).row();

        Table buttonTable=new Table();
        buttonTable.add(loginButton).width(300).height(70).padRight(20);
        buttonTable.add(back).width(300).height(70);

        table.add(buttonTable).colspan(2).row();

        stage.addActor(table);
    }
    public void setUpForgetUI(){
        stage.clear();
        Table table=new Table();
        table.setFillParent(true);
        table.center().padTop(300);
        table.add(questionField).width(300).height(80).colspan(2).row();
        table.add(answerField).width(300).height(80).colspan(2).row();
        Table buttonTable=new Table();
        buttonTable.padTop(20);
        buttonTable.add(submit).width(200).height(60).padRight(20);
        buttonTable.add(backToLogin).width(200).height(60);
        table.add(buttonTable).colspan(2).row();
        stage.addActor(table);
    }
    public TextButton getBackToLogin() {
        return backToLogin;
    }
    public TextButton getSubmit() {
        return submit;
    }
    public TextField getQuestionField(){
        return questionField;
    }
    public TextField getAnswerField(){
        return answerField;
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
    public TextButton getForgetPassword() {
        return forgetPassword;
    }
}

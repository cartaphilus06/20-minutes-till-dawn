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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tilldawn.Controller.MainMenuController;
import com.tilldawn.Main;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Enums.Avatar;

public class MainMenu implements Screen {
    private final MainMenuController controller=new MainMenuController(this);
    private Stage stage;
    private final Game game;
    private Texture background;
    private TextButton register;
    private TextButton login;
    private TextButton settings;
    private TextButton profile;
    private TextButton scoreBoard;
    private TextButton hints;
    private TextButton preGame;
    private TextButton exit;
    private Label username;
    private Animation<TextureRegion> walkAnimation;
    private float stateTime=0f;
    private final Texture shanaPortrait=AssetManager.getShanaPortrait();
    public MainMenu(Game game) {
        this.game=game;
    }
    @Override
    public void show() {
        stage=new Stage(new FitViewport(1920,1080));
        Gdx.input.setInputProcessor(stage);
        setUpUI();
        controller.handleClickedButtons();
        controller.handleUsernameLabel();
        controller.handleAvatar();
        controller.setCursor();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();

        // Draw background
        stage.getBatch().draw(background, 0, 0,
            stage.getViewport().getWorldWidth(),
            stage.getViewport().getWorldHeight());

        // Update avatar frame
        stateTime += delta;
        TextureRegion avatarFrame = walkAnimation.getKeyFrame(stateTime, true);
        stage.getBatch().draw(avatarFrame, 30, stage.getViewport().getWorldHeight() - Avatar.getHeight());

        // Draw Shana floating
        float yOffset = 300f + (float) Math.sin(stateTime * 2.5f) * 10f;
        Texture shana = shanaPortrait;
        float width = shana.getWidth() * 2.2f;
        float height = shana.getHeight() * 2.2f;
        stage.getBatch().draw(shana, 1920 - width, yOffset, width, height);

        //Draw title
        float titleYPosition=600f+(float)Math.sin(stateTime * 2.5f) * 10f;
        float titleXPosition=100f+(float)Math.cos(stateTime * 2.5f) * 10f;
        Texture title=AssetManager.get20minutesTillDawnLogo();
        stage.getBatch().draw(title, titleXPosition, titleYPosition,title.getWidth()*1.5f, title.getHeight()*1.5f);

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

    }
    public void setUpUI(){
        Table table = new Table();
        table.setFillParent(true);
        table.center().padTop(500).padRight(1250);
        background = AssetManager.getMainMenuBackground();
        Skin skin = AssetManager.getSkin();
        register = new TextButton("REGISTER", skin);
        login = new TextButton("LOGIN", skin);
        settings = new TextButton("SETTINGS", skin);
        profile = new TextButton("PROFILE", skin);
        scoreBoard=new TextButton("SCORE BOARD", skin);
        hints = new TextButton("HINTS", skin);
        preGame=new TextButton("PRE-GAME", skin);
        exit = new TextButton("EXIT", skin);
        username=new Label("USERNAME: ", skin);
        username.setAlignment(Align.topLeft);
        float avatarImageYPosition=stage.getViewport().getWorldHeight()- Avatar.getHeight();
        float avatarImageXPosition=30;
        username.setPosition(avatarImageXPosition,avatarImageYPosition-40);
        float buttonSpacing = 15f;
        table.add(register).padBottom(buttonSpacing).width(350).height(60);
        table.row();
        table.add(login).padBottom(buttonSpacing).width(350).height(60);
        table.row();
        table.add(preGame).padBottom(buttonSpacing).width(350).height(60);
        table.row();
        table.add(settings).padBottom(buttonSpacing).width(350).height(60);
        table.row();
        table.add(profile).padBottom(buttonSpacing).width(350).height(60);
        table.row();
        table.add(scoreBoard).padBottom(buttonSpacing).width(350).height(60);
        table.row();
        table.add(hints).padBottom(buttonSpacing).width(350).height(60);
        table.row();
        table.add(exit).width(350).height(60);
        stage.addActor(table);
        stage.addActor(username);
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
    public TextButton getProfile() {
        return profile;
    }
    public TextButton getScoreBoard() {
        return scoreBoard;
    }
    public Label getUsername() {
        return username;
    }
    public Animation<TextureRegion> getWalkAnimation() {
        return walkAnimation;
    }
    public void setAnimation(Animation<TextureRegion> walkAnimation) {
        this.walkAnimation = walkAnimation;
    }
    public TextButton getPreGame() {
        return preGame;
    }
    public TextButton getHints() {
        return hints;
    }
    public Game getGame() {
        return game;
    }
    public Stage getStage(){
        return stage;
    }
}

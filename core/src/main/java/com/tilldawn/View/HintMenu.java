package com.tilldawn.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tilldawn.Controller.HintMenuController;
import com.tilldawn.Models.AssetManager;

public class HintMenu implements Screen {
    private final HintMenuController controller;
    private Stage stage;
    private final Game game;
    private Texture background;
    private TextButton heroDetails;
    private TextButton keysDetails;
    private TextButton cheatCodes;
    private TextButton abilityDetails;
    private TextButton backToHint;
    private TextButton backToMainMenu;
    private final Skin skin;
    public HintMenu(Game game) {
        this.game = game;
        skin= AssetManager.getSkin();
        controller = new HintMenuController(this);
    }
    @Override
    public void show() {
        stage=new Stage(new FitViewport(1920,1080));
        Gdx.input.setInputProcessor(stage);
        background=new Texture(Gdx.files.internal("images/backgrounds/bg.png"));
        heroDetails=new TextButton("HERO DETAILS",skin);
        keysDetails=new TextButton("KEYS DETAILS",skin);
        cheatCodes=new TextButton("CHEAT CODES",skin);
        abilityDetails=new TextButton("ABILITY DETAILS",skin);
        backToHint=new TextButton("BACK",skin);
        backToMainMenu=new TextButton("BACK",skin);
        setUpUI();
        controller.handleClickedButtons();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0,
            stage.getViewport().getWorldWidth(),
            stage.getViewport().getWorldHeight());
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
        background.dispose();
    }
    public void setUpUI(){
        stage.clear();
        Table table=new Table();
        table.setFillParent(true);
        table.center();
        table.defaults().pad(10);
        float width=420f;
        table.add(heroDetails).width(width).height(60).row();
        table.add(keysDetails).width(width).height(60).row();
        table.add(cheatCodes).width(width).height(60).row();
        table.add(abilityDetails).width(width).height(60).row();
        table.add(backToMainMenu).width(width).height(60).row();
        stage.addActor(table);
    }
    public void setUpHeroUI(){
        stage.clear();
    }
    public void setUpAbilityUI(){
        stage.clear();
    }
    public void setUpKeysUI(){
        stage.clear();
    }
    public void setUpCheatCodeUI(){
        stage.clear();
    }
    public Game getGame() {
        return game;
    }
    public Stage getStage() {
        return stage;
    }
    public TextButton getHeroDetails(){
        return heroDetails;
    }
    public TextButton getKeysDetails(){
        return keysDetails;
    }
    public TextButton getCheatCodes(){
        return cheatCodes;
    }
    public TextButton getAbilityDetails(){
        return abilityDetails;
    }
    public TextButton getBackToHint(){
        return backToHint;
    }
    public TextButton getBackToMainMenu(){
        return backToMainMenu;
    }
}

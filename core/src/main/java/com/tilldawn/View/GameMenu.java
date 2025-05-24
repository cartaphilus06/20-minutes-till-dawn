package com.tilldawn.View;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tilldawn.App;
import com.tilldawn.Controller.GameMenuController;
import com.tilldawn.Models.Ability.Ability;
import com.tilldawn.Models.Ability.DefaultAbility;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.Map.Map;

public class GameMenu implements Screen, InputProcessor {
    private final GameMenuController controller;
    private final Game game;
    private Stage stage;
    private final Animation<TextureRegion> walkAnimation;
    private final Animation<TextureRegion> runAnimation;
    private final Animation<TextureRegion> standAnimation;
    private final Map map;
    private final OrthographicCamera camera;
    private boolean paused =false;
    private Table pauseMenuTable;
    public GameMenu(Game game,int minutes) {
        this.game = game;
        map=new Map(minutes*60);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        controller = new GameMenuController(this,map);
        Character character= App.getCurrentUser().getCharacter();
        walkAnimation=character.getHero().getWalkAnimation();
        runAnimation=character.getHero().getRunAnimation();
        standAnimation=character.getHero().getStandAnimation();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode==Input.Keys.ESCAPE){
            setPaused(!isPaused());
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        App.getCurrentUser().getCharacter().setIdle(true);
        controller.handleKeyUp();
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        controller.handleTouchDown(screenX,screenY,button);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        controller.getGunController().handleWeaponRotation(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public void show() {
        stage=new Stage(new FitViewport(1920,1080));
        Gdx.input.setInputProcessor(this);
        Skin skin= AssetManager.getSkin();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(!isPaused()) {
            Character character = App.getCurrentUser().getCharacter();
            camera.position.set(character.getX(), character.getY(), 0);
            camera.update();
            stage.getBatch().setProjectionMatrix(camera.combined);
            stage.getBatch().begin();
            controller.update();
            stage.getBatch().end();
        }
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
    public Game getGame() {
        return game;
    }
    public Stage getStage() {
        return stage;
    }
    public Animation<TextureRegion> getWalkAnimation() {
        return walkAnimation;
    }
    public Animation<TextureRegion> getRunAnimation() {
        return runAnimation;
    }
    public Animation<TextureRegion> getStandAnimation() {
        return standAnimation;
    }
    public OrthographicCamera getCamera(){
        return camera;
    }
    public boolean isPaused() {
        return paused;
    }
    public void setPaused(boolean paused) {
        this.paused = paused;
        if(paused){
            createPauseTable();
            Gdx.input.setInputProcessor(stage);
        }else if(pauseMenuTable!=null){
            pauseMenuTable.remove();
            Gdx.input.setInputProcessor(this);
        }
    }
    public void createPauseTable(){
        Character character = App.getCurrentUser().getCharacter();
        Skin skin=AssetManager.getSkin();
        pauseMenuTable=new Table();
        pauseMenuTable.setFillParent(true);
        pauseMenuTable.top().padTop(50);
        TextButton resume=new TextButton("RESUME",skin);
        TextButton save=new TextButton("SAVE",skin);
        TextButton exitGame=new TextButton("EXIT",skin);
        resume.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x,float y) {
                AssetManager.getUiClickSound().play();
                setPaused(!isPaused());
                pauseMenuTable.remove();
            }
        });
        exitGame.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x,float y) {
                AssetManager.getUiClickSound().play();
                Gdx.app.exit();
            }
        });
        save.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x,float y) {
                //save the map
            }
        });
        Ability abilities=character.getAbility();
        Label ab=new Label("acquired abilities:",skin);
        pauseMenuTable.add(ab).width(300).height(60).colspan(2);
        int count=0;
        while(!(abilities instanceof DefaultAbility)){
            Label ability=new Label(abilities.getName(),skin);
            if(count%2==0) pauseMenuTable.row();
            pauseMenuTable.add(ability).width(150).height(60).colspan(1);
            abilities=abilities.getAbility();
            count++;
        }
        if(count==1) pauseMenuTable.row();
        pauseMenuTable.add(resume).width(300).height(60).colspan(2);
        pauseMenuTable.add(save).width(300).height(60).colspan(2);
        pauseMenuTable.add(exitGame).width(300).height(60).colspan(2);
        stage.addActor(pauseMenuTable);
    }
}

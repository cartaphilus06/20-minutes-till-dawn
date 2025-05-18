package com.tilldawn.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tilldawn.App;
import com.tilldawn.Controller.GameMenuController;
import com.tilldawn.Models.Map.Character;

public class GameMenu implements Screen, InputProcessor {
    private final GameMenuController controller;
    private final Game game;
    private Stage stage;
    private final Animation<TextureRegion> walkAnimation;
    private final Animation<TextureRegion> runAnimation;
    private final Animation<TextureRegion> standAnimation;
    public GameMenu(Game game) {
        this.game = game;
        controller = new GameMenuController(this);
        Character character= App.getCurrentUser().getCharacter();
        walkAnimation=character.getHero().getWalkAnimation();
        runAnimation=character.getHero().getRunAnimation();
        standAnimation=character.getHero().getStandAnimation();
    }

    @Override
    public boolean keyDown(int keycode) {
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
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.153f, 0.125f, 0.188f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        controller.update();
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
}

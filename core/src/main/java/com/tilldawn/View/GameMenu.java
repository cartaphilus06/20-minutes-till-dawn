package com.tilldawn.View;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tilldawn.App;
import com.tilldawn.Controller.GameMenuController;
import com.tilldawn.Models.Ability.Ability;
import com.tilldawn.Models.Ability.DefaultAbility;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.Map.Map;
import com.tilldawn.Models.User.User;

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
    private Table selectAbilityTable;
    private boolean leveledUp = false;
    private float stateTime;
    private float abilityTimer;
    private TextField cheatField;
    private TextButton submitCheat;
    public GameMenu(Game game,int minutes) {
        this.game = game;
        Map.removeMapIfExists();
        map=new Map(minutes*60);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        controller = new GameMenuController(this,map);
        Character character= App.getCurrentUser().getCharacter();
        walkAnimation=character.getHero().getWalkAnimation();
        runAnimation=character.getHero().getRunAnimation();
        standAnimation=character.getHero().getStandAnimation();
    }
    public GameMenu(Map map,Game game) {
        this.game = game;
        this.map=map;
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
        cheatField=new TextField("",AssetManager.getSkin());
        cheatField.setMessageText("write your cheat here");
        submitCheat=new TextButton("SUBMIT",AssetManager.getSkin());
        controller.handleClickedButtons();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(!isPaused() && !isLeveledUp()) {
            Character character = App.getCurrentUser().getCharacter();
            camera.position.set(character.getX(), character.getY(), 0);
            camera.update();
            stage.getBatch().setProjectionMatrix(camera.combined);
            stage.getBatch().begin();
            controller.update();
            stage.getBatch().end();
            stateTime+=delta;
            abilityTimer += delta;
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
    public boolean isLeveledUp() {
        return leveledUp;
    }
    public void setLeveledUp(boolean leveledUp) {
        this.leveledUp = leveledUp;
        if(leveledUp){
            createSelectAbilityTable();
            Gdx.input.setInputProcessor(stage);
        } else if(selectAbilityTable!=null){
            selectAbilityTable.remove();
            Gdx.input.setInputProcessor(this);
        }
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
        TextButton exitGame=new TextButton("SAVE AND EXIT",skin);
        TextButton giveUp=new TextButton("GIVE UP",skin);
        resume.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x,float y) {
                AssetManager.getUiClickSound().play();
                setPaused(!isPaused());
                pauseMenuTable.remove();
                if(controller.isAddLevel()){
                    setLeveledUp(true);
                    controller.setAddLevel(false);
                }
            }
        });
        exitGame.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x,float y) {
                AssetManager.getUiClickSound().play();
                float elapsedTime=map.getTime()-map.getRemainingTime();
                if(character.getMostSurvival()<elapsedTime) character.setMostSurvival(elapsedTime);
                Map.saveMaps();
                User.saveUsers();
                Gdx.app.exit();
            }
        });
        giveUp.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x,float y) {
                AssetManager.getUiClickSound().play();
                float elapsedTime=map.getTime()-map.getRemainingTime();
                if(character.getMostSurvival()<elapsedTime) character.setMostSurvival(elapsedTime);
                map.deleteMap();
                User.saveUsers();
                Gdx.app.exit();
            }
        });
        Ability abilities=character.getAbility();
        pauseMenuTable.row();
        Label ab=new Label("acquired abilities:",skin);
        pauseMenuTable.add(ab).width(400).height(60).colspan(2);
        int count=0;
        while(!(abilities instanceof DefaultAbility)){
            Label ability=new Label(abilities.getName(),skin);
            if(count%2==0) pauseMenuTable.row();
            pauseMenuTable.add(ability).width(200).height(60).colspan(1);
            abilities=abilities.getAbility();
            count++;
        }
        if(count==1 || count==0) pauseMenuTable.row();
        pauseMenuTable.add(cheatField).width(400).height(80).colspan(2);
        pauseMenuTable.row();
        pauseMenuTable.add(submitCheat).width(400).height(60).colspan(2);
        pauseMenuTable.row();
        pauseMenuTable.add(resume).width(400).height(60).colspan(2);
        pauseMenuTable.row();
        pauseMenuTable.add(exitGame).width(400).height(60).colspan(2);
        pauseMenuTable.row();
        pauseMenuTable.add(giveUp).width(400).height(60).colspan(2);
        stage.addActor(pauseMenuTable);
    }
    public void createSelectAbilityTable(){
        Character character = App.getCurrentUser().getCharacter();
        Skin skin=AssetManager.getSkin();
        selectAbilityTable=new Table();
        selectAbilityTable.setFillParent(true);
        selectAbilityTable.center();
        TextButton damager=new TextButton("DAMAGER",skin);
        TextButton speedy=new TextButton("SPEEDY",skin);
        TextButton vitality=new TextButton("VITALITY",skin);
        TextButton amocrease=new TextButton("AMOCREASE",skin);
        TextButton procrease=new TextButton("PROCREASE",skin);
        TextButton[] abilities={damager,speedy,vitality,amocrease,procrease};
        for(TextButton button:abilities){
            button.addListener(new ClickListener(){
                public void clicked(InputEvent event,float x,float y) {
                    abilityTimer=0;
                    AssetManager.getUiClickSound().play();
                    character.setLastAbility(character.getAbility());
                    character.setAbility(Ability.getInstance(button.getText().toString(),character.getAbility()));
                    setLeveledUp(false);
                    if(button.getText().toString().equals("DAMAGER") || button.getText().toString().equals("SPEEDY")){
                        Timer.schedule(new Timer.Task() {
                            @Override
                            public void run() {
                                character.setAbility(character.getLastAbility());
                            }
                        },10,0);
                    }
                }
            });
            selectAbilityTable.add(button).width(300).height(60).row();
        }
        stage.addActor(selectAbilityTable);
    }
    public TextField getCheatField(){
        return cheatField;
    }
    public TextButton getSubmitCheat(){
        return submitCheat;
    }
}

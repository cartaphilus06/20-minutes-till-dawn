package com.tilldawn.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tilldawn.App;
import com.tilldawn.Controller.SettingsController;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Enums.BackgroundMusic;
import com.tilldawn.Models.User.User;


public class Settings implements Screen {
    private final SettingsController controller=new SettingsController(this);
    private Stage stage;
    private final Game game;
    private Slider musicSlider;
    private Slider sfxSlider;
    private final Label musicVolumeLabel=new Label("",AssetManager.getSkin());
    private final Label sfxVolumeLabel=new Label("",AssetManager.getSkin());
    private TextButton back;
    private SelectBox<String> musicPicker;
    private Texture background;
    private TextButton moveUp,moveDown,moveLeft,moveRight;
    private TextButton autoReload;
    private TextButton shoot;
    public Settings(Game game) {
        this.game=game;
    }
    @Override
    public void show() {
        stage=new Stage(new FitViewport(1920, 1080));
        Gdx.input.setInputProcessor(stage);
        setUpUI();
        controller.handleMusicSlider();
        controller.handleClickedButtons();
        controller.handleSelectBox();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        musicVolumeLabel.setText("MUSIC: "+ (int) (App.getMusicVolume() * 100));
        sfxVolumeLabel.setText("SFX: "+ (int) (AssetManager.getUiClickSound().getVolume() * 100));
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, stage.getViewport().getWorldWidth(), stage.getViewport().getWorldHeight());
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
        back=new TextButton("BACK",AssetManager.getSkin());
        Skin skin= AssetManager.getSkin();
        User user=App.getCurrentUser();
        moveUp=new TextButton("Move up key: "+ Input.Keys.toString(user.getMovingKeys().getMoveUp()),skin);
        moveDown=new TextButton("Move down key: "+Input.Keys.toString(user.getMovingKeys().getMoveDown()),skin);
        moveLeft=new TextButton("Move left key: "+Input.Keys.toString(user.getMovingKeys().getMoveLeft()),skin);
        moveRight=new TextButton("Move right key: "+Input.Keys.toString(user.getMovingKeys().getMoveRight()),skin);
        background=new Texture(Gdx.files.internal("images/backgrounds/menusBackGround.png"));
        autoReload=new TextButton("Auto reload: "+(App.getCurrentUser().getCharacter().getAutoReload()?"On":"Off"),skin);
        String a;
        if(user.getMovingKeys().getShoot()==0) a="Left";
        else a=Input.Keys.toString(user.getMovingKeys().getShoot());
        shoot=new TextButton("Shoot: "+a,skin);
        musicPicker=new SelectBox<>(skin);
        String[] items= BackgroundMusic.getItems();
        musicPicker.setItems(items);
        musicPicker.setSelected(App.getCurrentUser().getBackgroundMusic().getDisplayName());
        Table table=new Table();
        table.setFillParent(true);
        table.center().pad(10);
        sfxSlider=new Slider(0f,1f,0.01f,false,skin);
        sfxSlider.setValue(AssetManager.getUiClickSound().getVolume());
        musicSlider=new Slider(0f,1f,0.01f,false,skin);
        musicSlider.setValue(App.getMusicVolume());
        table.row();
        table.add(musicVolumeLabel).width(150);
        table.add(musicSlider).width(300);
        table.row();
        table.add(sfxVolumeLabel).width(150);
        table.add(sfxSlider).width(300);
        table.row();
        table.add(musicPicker).width(470).height(60).colspan(2).padTop(10);
        table.row();
        table.add(moveUp).width(470).height(60).colspan(2).padTop(10);
        table.row();
        table.add(moveDown).width(470).height(60).colspan(2).padTop(10);
        table.row();
        table.add(moveLeft).width(470).height(60).colspan(2).padTop(10);
        table.row();
        table.add(moveRight).width(470).height(60).colspan(2).padTop(10);
        table.row();
        table.add(shoot).width(470).height(60).colspan(2).padTop(10);
        table.row();
        table.add(autoReload).width(470).height(60).colspan(2).padTop(10);
        table.row();
        table.add(back).width(300).height(60).colspan(2).padTop(10);
        stage.addActor(table);
    }
    public Slider getMusicSlider() {
        return musicSlider;
    }
    public Slider getSfxSlider() {
        return sfxSlider;
    }
    public TextButton getBack() {
        return back;
    }
    public Stage getStage() {
        return stage;
    }
    public Game getGame() {
        return game;
    }
    public SelectBox<String> getMusicPicker() {
        return musicPicker;
    }
    public TextButton getMoveUp(){
        return moveUp;
    }
    public TextButton getMoveDown(){
        return moveDown;
    }
    public TextButton getMoveLeft(){
        return moveLeft;
    }
    public TextButton getMoveRight(){
        return moveRight;
    }
    public TextButton getAutoReload(){
        return autoReload;
    }
    public TextButton getShoot(){
        return shoot;
    }
}

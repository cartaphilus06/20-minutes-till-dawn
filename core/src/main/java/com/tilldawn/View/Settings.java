package com.tilldawn.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tilldawn.App;
import com.tilldawn.Controller.SettingsController;
import com.tilldawn.Models.AssetManager;


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
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        musicVolumeLabel.setText("MUSIC: "+ (int) (App.getCurrentUser().getBackgroundMusic().getVolume() * 100));
        sfxVolumeLabel.setText("SFX: "+ (int) (AssetManager.getUiClickSound().getVolume() * 100));
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
        Table table=new Table();
        table.setFillParent(true);
        table.top().pad(10);
        sfxSlider=new Slider(0f,1f,0.01f,false,skin);
        sfxSlider.setValue(AssetManager.getUiClickSound().getVolume());
        musicSlider=new Slider(0f,1f,0.01f,false,skin);
        musicSlider.setValue(App.getCurrentUser().getBackgroundMusic().getVolume());
        table.row();
        table.add(musicVolumeLabel).width(150);
        table.add(musicSlider).width(300);
        table.row();
        table.add(sfxVolumeLabel).width(150);
        table.add(sfxSlider).width(300);
        table.row();
        table.add(back).width(300).height(60).colspan(2);
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
}

package com.tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.App;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Enums.BackgroundMusic;
import com.tilldawn.Models.User.Character;
import com.tilldawn.Models.User.User;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.Settings;

public class SettingsController {
    private final Settings view;
    public SettingsController(Settings view) {
        this.view = view;
    }
    public void handleMusicSlider(){
        view.getMusicSlider().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                App.getCurrentUser().getBackgroundMusic().setVolume(view.getMusicSlider().getValue());
            }
        });
        view.getSfxSlider().addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                AssetManager.getUiClickSound().setVolume(view.getSfxSlider().getValue());
            }
        });
    }
    public void handleClickedButtons(){
        view.getBack().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.getGame().setScreen(new MainMenu(view.getGame()));
            }
        });
        view.getAutoReload().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                Character character=App.getCurrentUser().getCharacter();
                character.setAutoReload(!character.getAutoReload());
                view.getAutoReload().setText("Auto reload: "+(character.getAutoReload()?"On":"Off"));
                User.saveUsers();
            }
        });
        view.getMoveUp().addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                User user=App.getCurrentUser();
                Gdx.input.setInputProcessor(new InputAdapter(){
                    @Override
                    public boolean keyDown(int keycode) {
                        AssetManager.getUiClickSound().play();
                        user.getMovingKeys().setMoveUp(keycode);
                        view.getMoveUp().setText("Move up key: "+ Input.Keys.toString(keycode));
                        Gdx.input.setInputProcessor(view.getStage());
                        view.getStage().unfocusAll();
                        User.saveUsers();
                        return true;
                    }
                });
                return true;
            }
        });
        view.getMoveDown().addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                User user=App.getCurrentUser();
                Gdx.input.setInputProcessor(new InputAdapter(){
                    @Override
                    public boolean keyDown(int keycode) {
                        AssetManager.getUiClickSound().play();
                        user.getMovingKeys().setMoveDown(keycode);
                        view.getMoveDown().setText("Move down key: "+ Input.Keys.toString(keycode));
                        Gdx.input.setInputProcessor(view.getStage());
                        view.getStage().unfocusAll();
                        User.saveUsers();
                        return true;
                    }
                });
                return true;
            }
        });
        view.getMoveLeft().addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                User user=App.getCurrentUser();
                Gdx.input.setInputProcessor(new InputAdapter(){
                    @Override
                    public boolean keyDown(int keycode) {
                        AssetManager.getUiClickSound().play();
                        user.getMovingKeys().setMoveLeft(keycode);
                        view.getMoveLeft().setText("Move left key: "+ Input.Keys.toString(keycode));
                        Gdx.input.setInputProcessor(view.getStage());
                        view.getStage().unfocusAll();
                        User.saveUsers();
                        return true;
                    }
                });
                return true;
            }
        });
        view.getMoveRight().addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                User user=App.getCurrentUser();
                Gdx.input.setInputProcessor(new InputAdapter(){
                    @Override
                    public boolean keyDown(int keycode) {
                        AssetManager.getUiClickSound().play();
                        user.getMovingKeys().setMoveRight(keycode);
                        view.getMoveRight().setText("Move right key: "+ Input.Keys.toString(keycode));
                        Gdx.input.setInputProcessor(view.getStage());
                        view.getStage().unfocusAll();
                        User.saveUsers();
                        return true;
                    }
                });
                return true;
            }
        });
    }
    public void handleSelectBox(){
        view.getMusicPicker().addListener(new ChangeListener() {
            public void changed(ChangeEvent changeEvent, Actor actor) {
                String selectedTrack=view.getMusicPicker().getSelected();
                BackgroundMusic selectedMusic=BackgroundMusic.displayNameToEnum(selectedTrack);
                User currentUser=App.getCurrentUser();
                currentUser.getBackgroundMusic().getMusic().stop();
                currentUser.setBackgroundMusic(selectedMusic);
                currentUser.getBackgroundMusic().getMusic().play();
                User.saveUsers();
            }
        });
    }
}

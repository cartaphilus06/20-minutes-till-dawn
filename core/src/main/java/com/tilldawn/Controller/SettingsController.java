package com.tilldawn.Controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.App;
import com.tilldawn.Models.AssetManager;
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
    }
}

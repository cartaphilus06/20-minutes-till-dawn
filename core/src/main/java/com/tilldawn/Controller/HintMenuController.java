package com.tilldawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.View.HintMenu;
import com.tilldawn.View.MainMenu;

public class HintMenuController {
    private final HintMenu view;
    public HintMenuController(HintMenu view) {
        this.view = view;
    }
    public void handleClickedButtons(){
        view.getBackToMainMenu().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.getGame().setScreen(new MainMenu(view.getGame()));
            }
        });
        view.getBackToHint().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.setUpUI();
            }
        });
        view.getAbilityDetails().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.setUpAbilityUI();
            }
        });
        view.getHeroDetails().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.setUpHeroUI();
            }
        });
        view.getCheatCodes().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.setUpCheatCodeUI();
            }
        });
        view.getKeysDetails().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.setUpKeysUI();
            }
        });
    }
}

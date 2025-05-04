package com.tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.App;
import com.tilldawn.Models.AlertGenerator;
import com.tilldawn.View.*;

public class MainMenuController {
    private final MainMenu view;
    public MainMenuController(MainMenu view) {
        this.view = view;
    }
    public void handleClickedButtons(){
        view.getRegister().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                view.getGame().setScreen(new RegisterMenu(view.getGame()));
            }
        });
        view.getLogin().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                view.getGame().setScreen(new LoginMenu(view.getGame()));
            }
        });
        view.getExit().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        view.getSettings().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {}
        });
        view.getProfile().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.getCurrentUser()==null){
                    AlertGenerator.showAlert("","you haven't logged in yet!",view.getStage());
                    return;
                }
                view.getGame().setScreen(new ProfileMenu(view.getGame()));
            }
        });
        view.getScoreBoard().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                view.getGame().setScreen(new Scoreboard(view.getGame()));
            }
        });
    }
}

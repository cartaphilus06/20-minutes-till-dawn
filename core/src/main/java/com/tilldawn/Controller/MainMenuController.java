package com.tilldawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.View.LoginMenu;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.RegisterMenu;

public class MainMenuController {
    private final MainMenu view;
    public MainMenuController(MainMenu view) {
        this.view = view;
    }
    public void handleClickedButtons(){
        view.getRegister().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                view.getGame().setScreen(new RegisterMenu(view.getGame()));
            }
        });
        view.getLogin().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                view.getGame().setScreen(new LoginMenu(view.getGame()));
            }
        });
    }
}

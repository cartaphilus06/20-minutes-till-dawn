package com.tilldawn.Controller;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.View.LoginMenu;

public class LoginMenuController {
    private final LoginMenu view;
    public LoginMenuController(LoginMenu view) {
        this.view = view;
    }
    public void handleClickedButtons(){
        view.getLoginButton().addListener(new ClickListener(){
            
        });
    }
}

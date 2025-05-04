package com.tilldawn.Controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.AlertGenerator;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Enums.Register;
import com.tilldawn.Models.User.User;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.RegisterMenu;

import java.awt.*;

public class RegisterMenuController {
    private final RegisterMenu view;
    public RegisterMenuController(RegisterMenu registerMenu) {
        this.view = registerMenu;
    }
    public void handleClickedButtons(){
        view.getRegisterButton().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                register();
            }
        });
        view.getBackButton().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                view.getGame().setScreen(new MainMenu(view.getGame()));
            }
        });
    }
    public void register(){
        String username=view.getUsername().getText();
        String password=view.getPassword().getText();
        if(!Register.USERNAME.matches(username)){
            AlertGenerator.showAlert("Username error!","Username format is invalid!",view.getStage());
            return;
        }
        if(!Register.PASSWORD.matches(password)){
            AlertGenerator.showAlert("Password error!","Password format is invalid!",view.getStage());
            return;
        }
        User user=User.getUser(username);
        if(user!=null){
            AlertGenerator.showAlert("Error!","Username already exists!",view.getStage());
            return;
        }
        AlertGenerator.showAlert("Success","Successfully registered!",view.getStage());
        new User(username,password);
    }
}

package com.tilldawn.Controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
            showAlert("Username error!","Username format is invalid!");
            return;
        }
        if(!Register.PASSWORD.matches(password)){
            showAlert("Password error!","Password format is invalid!");
            return;
        }
        User user=User.getUser(username);
        if(user!=null){
            showAlert("Error!","Username already exists!");
            return;
        }
        showAlert("Success","Successfully registered!");
        new User(username,password);
    }
    private void showAlert(String title, String message) {
        showAlert(title, message, null);
    }

    private void showAlert(String title, String message, Runnable callback) {
        Dialog dialog = new Dialog(title, AssetManager.getSkin()) {
            @Override
            protected void result(Object object) {
                if (callback != null) callback.run();
            }
        };
        dialog.text(message).padTop(20);
        dialog.button("OK", true);
        dialog.key(Input.Keys.ENTER, true); // Enter to confirm
        dialog.show(view.getStage());
    }
}

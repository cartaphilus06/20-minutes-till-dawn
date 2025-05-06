package com.tilldawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.Models.AlertGenerator;
import com.tilldawn.App;
import com.tilldawn.Models.User.User;
import com.tilldawn.View.LoginMenu;
import com.tilldawn.View.MainMenu;

public class LoginMenuController {
    private final LoginMenu view;
    public LoginMenuController(LoginMenu view) {
        this.view = view;
    }
    public void handleClickedButtons(){
        view.getLoginButton().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                login();
            }
        });
        view.getBackButton().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                view.getGame().setScreen(new MainMenu(view.getGame()));
            }
        });
    }
    public void login() {
        String username = view.getUsernameField().getText();
        String password = view.getPasswordField().getText();
        User user = User.getUser(username);

        if (user == null) {
            AlertGenerator.showAlert("Error!", "User not found!", view.getStage());
            return;
        }
        if (!user.getPassword().equals(password)) {
            AlertGenerator.showAlert("Error!", "Provided password is wrong!", view.getStage());
            return;
        }
        User lastUser =App.getCurrentUser();
        if(lastUser!=null && lastUser.getUsername().equals(username)){
            AlertGenerator.showAlert("","You are already logged in!",view.getStage());
            return;
        }
        if(lastUser != null) {
            lastUser.setStayLoggedIn(false);
        }
        user.setStayLoggedIn(true);
        App.setCurrentUser(user);

        AlertGenerator.showAlert("Success!", user.getUsername() + " successfully logged in!", view.getStage(), () -> {
            view.getGame().setScreen(new MainMenu(view.getGame()));
        });
    }

}

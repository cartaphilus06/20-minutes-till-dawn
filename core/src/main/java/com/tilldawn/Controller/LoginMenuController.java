package com.tilldawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.Models.AlertGenerator;
import com.tilldawn.App;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Map.Map;
import com.tilldawn.Models.User.Question;
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
                AssetManager.getUiClickSound().play();
                login();
            }
        });
        view.getBackButton().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.getGame().setScreen(new MainMenu(view.getGame()));
            }
        });
        view.getForgetPassword().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.setUpForgetUI();
            }
        });
        view.getSubmit().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                forgetPassword();
            }
        });
        view.getBackToLogin().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.setUpUI();
            }
        });
    }
    public void forgetPassword(){
        String username=view.getUsernameField().getText();
        String question=view.getQuestionField().getText();
        String answer=view.getAnswerField().getText();
        User user=User.getUser(username);
        if(user==null){
            AlertGenerator.showAlert("wrong username!","there is not such a user with this username!",view.getStage());
            return;
        }
        if(!user.getSecurityQuestion().getQuestion().equals(question) ||
            !user.getSecurityQuestion().getAnswer().equals(answer)){
            AlertGenerator.showAlert("wrong security question","either your security question or answer is wrong!",view.getStage());
            return;
        }
        AlertGenerator.showAlert("","your password: "+user.getPassword(),view.getStage());
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
        App.setCurrentMap(null);
        user.setStayLoggedIn(true);
        App.setCurrentUser(user);
        for(Map map:Map.getAllMaps()){
            if(map.getCharacterUsername().equals(username)) {
                App.setCurrentMap(map);
                break;
            }
        }

        AlertGenerator.showAlert("Success!", user.getUsername() + " successfully logged in!", view.getStage(), () -> {
            view.getGame().setScreen(new MainMenu(view.getGame()));
        });
    }

}

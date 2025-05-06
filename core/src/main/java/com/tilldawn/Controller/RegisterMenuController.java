package com.tilldawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.Models.AlertGenerator;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Enums.Register;
import com.tilldawn.Models.User.Question;
import com.tilldawn.Models.User.User;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.RegisterMenu;

public class RegisterMenuController {
    private final RegisterMenu view;
    public RegisterMenuController(RegisterMenu registerMenu) {
        this.view = registerMenu;
    }
    public void handleClickedButtons(){
        view.getRegisterButton().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                register();
            }
        });
        view.getBackButton().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.getGame().setScreen(new MainMenu(view.getGame()));
            }
        });
    }
    public void register(){
        String username=view.getUsernameField().getText();
        String password=view.getPasswordField().getText();
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
        if(view.getSecurityQuestionField().getText().isEmpty() || view.getSecurityAnswerField().getText().isEmpty()){
            AlertGenerator.showAlert("Error!","Please fill all the fields",view.getStage());
            return;
        }
        AlertGenerator.showAlert("Success","Successfully registered!",view.getStage());
        User newUser=new User(username,password);
        newUser.setSecurityQuestion(new Question(
            view.getSecurityQuestionField().getText(),view.getSecurityAnswerField().getText()));
    }
}

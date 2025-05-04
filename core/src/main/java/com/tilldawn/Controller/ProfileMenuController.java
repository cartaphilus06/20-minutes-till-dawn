package com.tilldawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.App;
import com.tilldawn.Models.AlertGenerator;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.ConfirmDialog;
import com.tilldawn.Models.Enums.Register;
import com.tilldawn.Models.User.User;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.ProfileMenu;

public class ProfileMenuController {
    private ProfileMenu view;
    public ProfileMenuController(ProfileMenu view) {
        this.view = view;
    }
    public void handleClickedButtons(){
        view.getBack().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                view.getGame().setScreen(new MainMenu(view.getGame()));
            }
        });
        view.getChangeAvatar().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        view.getChangeUsername().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                view.setUpChangeUsernamePageUI();
            }
        });
        view.getChangePassword().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                view.setUpChangePasswordPageUI();
            }
        });
        view.getDeleteAccount().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfirmDialog confirm=new ConfirmDialog(
                    "Are you sure you want to delete your account?",
                    AssetManager.getSkin(),
                    () -> deleteAccount(),
                    System.out::println
                );
                view.getStage().addActor(confirm);
            }
        });
    }
    public void handleChangeUsername(TextButton button, TextField username,TextButton cancel){
        button.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                String newUsername=username.getText();
                if(!Register.USERNAME.matches(newUsername)){
                    AlertGenerator.showAlert("", "new username format is wrong!",view.getStage());
                    return;
                }
                User currentUser=App.getCurrentUser();
                if(currentUser==null){
                    AlertGenerator.showAlert("","you haven't logged in yet!",view.getStage());
                    return;
                }
                if(currentUser.getUsername().equals(newUsername)){
                    AlertGenerator.showAlert("",
                        "new username is the same as the old username!",view.getStage());
                    return;
                }
                currentUser.setUsername(newUsername);
                AlertGenerator.showAlert("", "username changed successfully!",view.getStage());
            }
        });
        cancel.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                view.setUpUI();
            }
        });
    }
    public void handleChangePassword(TextButton confirm, TextField newPassword,TextField confirmPassword,TextButton cancel){
        confirm.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                String newPass=newPassword.getText();
                String confirmPass=confirmPassword.getText();
                User currentUser=App.getCurrentUser();
                if(currentUser==null){
                    AlertGenerator.showAlert("","you haven't logged in yet!",view.getStage());
                    return;
                }
                if(currentUser.getPassword().equals(newPass)){
                    AlertGenerator.showAlert("",
                        "new password cannot be the same as the old password!",view.getStage());
                    return;
                }
                if(!Register.PASSWORD.matches(newPass)){
                    AlertGenerator.showAlert("", "password format is wrong!",view.getStage());
                    return;
                }
                if(!newPass.equals(confirmPass)){
                    AlertGenerator.showAlert("", "passwords do not match!",view.getStage());
                    return;
                }
                currentUser.setPassword(newPass);
                AlertGenerator.showAlert("", "password changed successfully!",view.getStage());
            }
        });
        cancel.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                view.setUpUI();
            }
        });
    }
    public void deleteAccount(){
        User user=App.getCurrentUser();
        if(user==null){
            AlertGenerator.showAlert("","you haven't logged in yet!",view.getStage());
            return;
        }
        user.deleteUser();
        App.setCurrentUser(null);
        view.getGame().setScreen(new MainMenu(view.getGame()));
    }
}

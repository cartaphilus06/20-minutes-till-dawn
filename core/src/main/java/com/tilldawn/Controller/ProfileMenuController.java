package com.tilldawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.tilldawn.App;
import com.tilldawn.Models.AlertGenerator;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.ConfirmDialog;
import com.tilldawn.Models.Enums.Avatar;
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
                AssetManager.getUiClickSound().play();
                view.getGame().setScreen(new MainMenu(view.getGame()));
            }
        });
        view.getChangeUsername().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.setUpChangeUsernamePageUI();
            }
        });
        view.getChangePassword().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.setUpChangePasswordPageUI();
            }
        });
        view.getDeleteAccount().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                ConfirmDialog confirm=new ConfirmDialog(
                    "Are you sure you want to delete your account?",
                    AssetManager.getSkin(),
                    () -> deleteAccount(),
                    System.out::println
                );
                view.getStage().addActor(confirm);
            }
        });
        view.getChangeAvatar().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.setUpChangeAvatarPageUI();
            }
        });
    }
    public void handleChangeUsername(TextButton button, TextField username,TextButton cancel){
        button.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
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
                AssetManager.getUiClickSound().play();
                view.setUpUI();
            }
        });
    }
    public void handleChangePassword(TextButton confirm, TextField newPassword,TextField confirmPassword,TextButton cancel){
        confirm.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
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
                AssetManager.getUiClickSound().play();
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
    public void handleImageButtonListener(ImageButton[] buttons, Drawable[][] drawables){
        for(int i=0; i<buttons.length; i++){
            int index=i;
            buttons[i].addListener(new ClickListener(){
                public void clicked(InputEvent event, float x, float y) {
                    AssetManager.getUiClickSound().play();
                    App.getCurrentUser().setAvatar(Avatar.values()[index]);
                    Drawable imageDown=drawables[index][2];
                    buttons[index].getStyle().imageUp=imageDown;
                    buttons[index].getStyle().imageOver=imageDown;
                    for(int j=0;j<buttons.length;j++){
                        if(j!=index) {
                            buttons[j].getStyle().imageUp = drawables[j][0];
                            buttons[j].getStyle().imageOver = drawables[j][1];
                        }
                    }
                }
            });
        }
    }
    public void handleChangeAvatarButtons(TextButton back){
        back.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.setUpUI();
                System.gc();
            }
        });
    }
}

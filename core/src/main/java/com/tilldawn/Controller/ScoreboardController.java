package com.tilldawn.Controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.User.User;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.Scoreboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class ScoreboardController {
    private final Scoreboard view;
    public ScoreboardController(Scoreboard view) {
        this.view = view;
    }
    public void handleClickedButtons(){
        view.getExit().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                view.getGame().setScreen(new MainMenu(view.getGame()));
            }
        });
        view.getSortByKills().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {}
        });
        view.getSortByScore().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {}
        });
        view.getSortBySurvival().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {}
        });
        view.getSortByUsername().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                ArrayList<Label> users=view.getUsers();
                users.sort(Comparator.comparing(o -> o.getText().toString()));
            }
        });
    }
    public ArrayList<Label> addUsersToArrayList(){
        ArrayList<User> allUsers= User.getAllUsers();
        ArrayList<Label> usersLabel=new ArrayList<>();
        for(User u:allUsers){
            Label label=new Label(u.getUsername(), AssetManager.getSkin());
            usersLabel.add(label);
        }
        return usersLabel;
    }
    private int compareStrings(String s1,String s2){
        return s1.compareTo(s2);
    }
}

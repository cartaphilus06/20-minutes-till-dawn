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
import java.util.Collections;
import java.util.Comparator;

public class ScoreboardController {
    private final Scoreboard view;
    public ScoreboardController(Scoreboard view) {
        this.view = view;
    }
    public void handleClickedButtons(ArrayList<User> users){
        view.getExit().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.getGame().setScreen(new MainMenu(view.getGame()));
            }
        });
        view.getSortByKills().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                users.sort((u1,u2) -> Integer.compare(u2.getCharacter().getKilledMonsters(), u1.getCharacter().getKilledMonsters()));
                view.setUpStaticUI();
            }
        });
        view.getSortByScore().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                users.sort((u1,u2) -> Integer.compare(u2.getCharacter().getScore(), u1.getCharacter().getScore()));
                view.setUpStaticUI();
            }
        });
        view.getSortBySurvival().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                users.sort((u1, u2) -> {
                    float s1 = u1.getCharacter().getMostSurvival();
                    float s2 = u2.getCharacter().getMostSurvival();
                    return Float.compare(s2, s1);
                });
                view.setUpStaticUI();
            }
        });
        view.getSortByUsername().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                users.sort(Comparator.comparing(User::getUsername));
                view.setUpStaticUI();
            }
        });
    }

}

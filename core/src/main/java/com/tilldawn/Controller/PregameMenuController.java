package com.tilldawn.Controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.App;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Enums.Hero;
import com.tilldawn.Models.User.User;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.PregameMenu;

public class PregameMenuController {
    private final PregameMenu view;
    public PregameMenuController(PregameMenu view) {
        this.view = view;
    }
    public void handleClickedButtons(){
        ImageButton[] heroButtons = view.getHeroButtons();
        for(int i=0; i<heroButtons.length; i++){
            ImageButton heroButton = heroButtons[i];
            int finalI = i;
            heroButton.addListener(new ClickListener(){
                public void clicked(InputEvent event, float x, float y) {
                    App.getCurrentUser().getCharacter().setHero(Hero.values()[finalI]);
                    User.saveUsers();
                }
            });
        }
        view.getBack().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.getGame().setScreen(new MainMenu(view.getGame()));
            }
        });
    }
    public void handleHoveredButtons(){
        ImageButton[] heroButtons = view.getHeroButtons();
        Animation<TextureRegion>[] runAnimations=view.getRunFrames();
        Animation<TextureRegion>[] standAnimations=view.getStandFrames();
        Animation<TextureRegion>[] currentAnimations=view.getCurrentAnimations();
        for(int i=0;i<heroButtons.length;i++){
            ImageButton heroButton = heroButtons[i];
            int finalI = i;
            heroButton.addListener(new InputListener(){
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    currentAnimations[finalI]=runAnimations[finalI];
                    Texture[] portraits=view.getPortraits();
                    view.setCurrentPortrait(portraits[finalI]);
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    currentAnimations[finalI]=standAnimations[finalI];
                }
            });
        }
    }
}

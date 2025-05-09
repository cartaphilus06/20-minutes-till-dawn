package com.tilldawn.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.App;
import com.tilldawn.Models.AlertGenerator;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.User.User;
import com.tilldawn.View.*;

public class MainMenuController {
    private final MainMenu view;
    public MainMenuController(MainMenu view) {
        this.view = view;
    }
    public void handleClickedButtons(){
        view.getRegister().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.getGame().setScreen(new RegisterMenu(view.getGame()));
            }
        });
        view.getLogin().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.getGame().setScreen(new LoginMenu(view.getGame()));
            }
        });
        view.getExit().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                Gdx.app.exit();
            }
        });
        view.getSettings().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(App.getCurrentUser()==null){
                    AlertGenerator.showAlert("","Please login first!",view.getStage());
                }
                AssetManager.getUiClickSound().play();
                view.getGame().setScreen(new Settings(view.getGame()));
            }
        });
        view.getProfile().addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                if(App.getCurrentUser()==null){
                    AlertGenerator.showAlert("","you haven't logged in yet!",view.getStage());
                    return;
                }
                view.getGame().setScreen(new ProfileMenu(view.getGame()));
            }
        });
        view.getScoreBoard().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.getGame().setScreen(new Scoreboard(view.getGame()));
            }
        });
        view.getPreGame().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
            }
        });
    }
    public void handleUsernameLabel(){
        Label username=view.getUsername();
        User currentUser = App.getCurrentUser();
        if(currentUser!=null){
            username.setText(username.getText()+currentUser.getUsername());
        }
        else {
            username.setText("not logged in yet!");
        }
    }
    public void handleAvatar(){
        TextureRegion[][] tiles= AssetManager.getAvatarTiles();
        TextureRegion[] walkFrames=new TextureRegion[6];
        System.arraycopy(tiles[0], 0, walkFrames, 0, 6);
        view.setAnimation(new Animation<>(0.2f, walkFrames));
    }
    public void setCursor(){
        Pixmap pixmap=AssetManager.getCursorIcon();
        Cursor customCursor = Gdx.graphics.newCursor(pixmap, pixmap.getWidth()/2, pixmap.getHeight()/2);
        Gdx.graphics.setCursor(customCursor);
        pixmap.dispose();
    }
}

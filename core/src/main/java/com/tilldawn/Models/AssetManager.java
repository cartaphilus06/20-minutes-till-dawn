package com.tilldawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.tilldawn.App;
import com.tilldawn.Models.Enums.Avatar;
import com.tilldawn.Models.User.User;

public class AssetManager {
    private final static AssetManager assetManager=new AssetManager();
    private Skin skin;
    private AssetManager(){
        skin=new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    }
    public static Skin getSkin(){
        return assetManager.skin;
    }
    public static TextFieldStyle getTextFieldStyle() {
        Skin skin = getSkin();
        TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20; // Adjust size as needed
        BitmapFont customFont = generator.generateFont(parameter);
        generator.dispose();

        textFieldStyle.font = customFont;
        textFieldStyle.fontColor = Color.BLACK;
        textFieldStyle.background = skin.getDrawable("textfield");

        return textFieldStyle;
    }
    public static TextureRegion[][] getAvatarTiles(){
        User currentUser= App.getCurrentUser();
        if(currentUser==null || currentUser.getAvatar()==null) {
            return Avatar.PLAYER.getTiles();
        }
        return currentUser.getAvatar().getTiles();
    }
    public static Pixmap getCursorIcon(){
        return new Pixmap(Gdx.files.internal("images/Texture2D/T_Cursor.png"));
    }
    public static Texture getMainMenuBackground(){
        return new Texture(Gdx.files.internal("images/backgrounds/mainMenuBackground.png"));
    }
    public static Texture getMenusBackground(){
        return new Texture(Gdx.files.internal("images/backgrounds/menusBackground.png"));
    }
    public static Texture getShanaPortrait(){
        return new Texture(Gdx.files.internal("images/Texture2D/T_Shana_Portrait.png"));
    }
    public static Texture get20minutesTillDawnLogo(){
        return new Texture(Gdx.files.internal("images/Texture2D/T_20Logo.png"));
    }
}

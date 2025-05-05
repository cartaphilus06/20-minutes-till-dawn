package com.tilldawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
        Texture texture;
        if(currentUser==null || currentUser.getAvatar()==null) {
            texture=new Texture(Gdx.files.internal(Avatar.PLAYER.getInternalPath())); //default avatar
        }
        else{
            texture=new Texture(Gdx.files.internal(currentUser.getAvatar().getInternalPath()));
        }
        return TextureRegion.split(texture, Avatar.getWidth(), Avatar.getHeight());
    }
}

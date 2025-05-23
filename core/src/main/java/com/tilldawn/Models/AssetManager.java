package com.tilldawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
import com.tilldawn.Models.Enums.BackgroundMusic;
import com.tilldawn.Models.Enums.Hero;
import com.tilldawn.Models.User.User;

public class AssetManager {
    private final static AssetManager assetManager=new AssetManager();
    private final Skin skin;
    private final Music uiClicks;
    private final Music shotSound;
    private final Music reloadSound;
    private final AssetHelper heart=new AssetHelper(new Texture(Gdx.files.internal("images/Texture2D/T_HeartAnimation.png")),32,32,0.3f);
    private AssetManager(){
        skin=new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        uiClicks=Gdx.audio.newMusic(Gdx.files.internal("SFX/AudioClip/UI Click 36.wav"));
        uiClicks.setVolume(1f);
        shotSound=Gdx.audio.newMusic(Gdx.files.internal("SFX/AudioClip/single_shot.wav"));
        reloadSound=Gdx.audio.newMusic(Gdx.files.internal("SFX/AudioClip/Weapon_Shotgun_Reload.wav"));
        shotSound.setVolume(1f);
        reloadSound.setVolume(1f);
        heart.setAnimation();
    }
    public static Skin getSkin(){
        return assetManager.skin;
    }
    public static TextFieldStyle getTextFieldStyle() {
        Skin skin = getSkin();
        TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
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
    public static Texture[] getHeroPortraits(){
        Texture[] textures=new Texture[Hero.values().length];
        for(int i=0;i<Hero.values().length;i++) {
            textures[i]=Hero.values()[i].getHeroPortraitTexture();
        }
        return textures;
    }
    public static Texture get20minutesTillDawnLogo(){
        return new Texture(Gdx.files.internal("images/Texture2D/T_20Logo.png"));
    }
    public static TextureRegion[] getMenusRightEyes(){
        TextureRegion t1=new TextureRegion(new Texture(Gdx.files.internal("images/backgrounds/eyes/openRight.png")));
        TextureRegion t2=new TextureRegion(new Texture(Gdx.files.internal("images/backgrounds/eyes/closeRight.png")));
        return new TextureRegion[]{t1,t1,t1,t2};
    }
    public static TextureRegion[] getMenusLeftEyes(){
        TextureRegion t1=new TextureRegion(new Texture(Gdx.files.internal("images/backgrounds/eyes/openRight.png")));
        t1.flip(true, false);
        TextureRegion t2=new TextureRegion(new Texture(Gdx.files.internal("images/backgrounds/eyes/closeRight.png")));
        t2.flip(true, false);
        return new TextureRegion[]{t1,t1,t1,t2};
    }
    public static Music getUiClickSound(){
        return assetManager.uiClicks;
    }
    public static Music getDefaultMusic(){
        return BackgroundMusic.PRETTY_DUNGEON.getMusic();
    }
    public static Texture getSelectorBubbleDefault(){
        return new Texture(Gdx.files.internal("images/Sprite/T_SelectorBubble_0.png"));
    }
    public static Texture getSelectorBubbleHover(){
        return new Texture(Gdx.files.internal("images/Sprite/T_SelectorBubble_1.png"));
    }
    public static AssetHelper getHeart(){
        return assetManager.heart;
    }
    public static Texture getAmmoIcon(){
        return new Texture(Gdx.files.internal("images/Texture2D/T_AmmoIcon.png"));
    }
    public static Texture getBulletTexture(){
        return new Texture(Gdx.files.internal("images/Texture2D/EyeMonsterProjecitle.png"));
    }
    public static Music getShotSound(){
        return assetManager.shotSound;
    }
    public static Music getReloadSound(){
        return assetManager.reloadSound;
    }
    public static Texture getEggIcon(){
        return new Texture(Gdx.files.internal("images/Texture2D/T_DragonEgg.png"));
    }
}

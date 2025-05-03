package com.tilldawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager {
    private final static AssetManager assetManager=new AssetManager();
    private Skin skin;
    private AssetManager(){
        skin=new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    }
    public static Skin getSkin(){
        return assetManager.skin;
    }
}

package com.tilldawn.Models.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.tilldawn.Models.Map.Monster.Monster;

import java.util.ArrayList;

public class Map {
    private int time;
    private final Texture background;
    private final ArrayList<Monster> allMonsters=new ArrayList<>();
    public Map(int time) {
        this.time = time;
        this.background = new Texture(Gdx.files.internal("images/backgrounds/background.png"));
    }
    public Texture getBackground() {
        return background;
    }
}

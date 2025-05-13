package com.tilldawn.Models.Enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public enum BackgroundMusic {
    PRETTY_DUNGEON("SFX/AudioClip/Pretty Dungeon LOOP.wav","Pretty Dungeon"),
    RIVER("SFX/backgroundMusics/River.mp3","River"),
    WITHOUT_LOVE("SFX/backgroundMusics/Without Love.mp3","Without Love"),
    PARADOX("SFX/backgroundMusics/Paradox.mp3","Paradox"),
    EMBER("SFX/backgroundMusics/Ember.mp3","Ember");
    private final String internalPath;
    private final Music music;
    private static float volume=1f;
    private final String displayName;
    BackgroundMusic(String internalPath,String displayName) {
        this.internalPath = internalPath;
        this.displayName = displayName;
        music = Gdx.audio.newMusic(Gdx.files.internal(internalPath));
        music.setLooping(true);
        music.setVolume(1f);
    }
    public String getInternalPath() {
        return internalPath;
    }
    public Music getMusic() {
        return music;
    }
    public float getVolume() {
        return volume;
    }
    public void setVolume(float volume) {
        BackgroundMusic.volume = volume;
        music.setVolume(volume);
    }
    public String getDisplayName() {
        return displayName;
    }
    public static String[] getItems(){
        String[] items = new String[BackgroundMusic.values().length];
        for(int i=0;i<BackgroundMusic.values().length;i++){
            items[i] = BackgroundMusic.values()[i].getDisplayName();
        }
        return items;
    }
    public static BackgroundMusic displayNameToEnum(String displayName){
        BackgroundMusic[] values = BackgroundMusic.values();
        for (BackgroundMusic value : values) {
            if (value.getDisplayName().equals(displayName)) return value;
        }
        return null;
    }
}

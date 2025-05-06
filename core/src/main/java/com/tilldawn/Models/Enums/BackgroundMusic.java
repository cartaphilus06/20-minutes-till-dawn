package com.tilldawn.Models.Enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public enum BackgroundMusic {
    PRETTY_DUNGEON("SFX/AudioClip/Pretty Dungeon LOOP.wav");
    private final String internalPath;
    private final Music music;
    private static float volume=1f;
    BackgroundMusic(String internalPath) {
        this.internalPath = internalPath;
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
}

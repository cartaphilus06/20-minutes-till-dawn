package com.tilldawn.Models.Map.Monster;

public class BrainMonster extends Monster {
    public BrainMonster(int worldX, int worldY) {
        super(worldX, worldY);
        setHp(25);
        setInternalPath("images/Texture2D/BrainMonster.png");
    }
}

package com.tilldawn.Models.Map.Monster;

public class Tree extends Monster{
    public Tree(int worldX, int worldY) {
        super(worldX, worldY);
        setHp(Integer.MAX_VALUE);
        setInternalPath("images/Texture2D/T_TreeMonster.png");
    }
}

package com.tilldawn.Models.Map.Controller;

import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.Map.Map;
import com.tilldawn.View.GameMenu;

public class MonsterController {
    private final GameMenu view;
    private final Map map;
    private final Character character;
    public MonsterController(GameMenu view, Map map, Character character) {
        this.view = view;
        this.map = map;
        this.character = character;
    }
    public void update(){

    }
}

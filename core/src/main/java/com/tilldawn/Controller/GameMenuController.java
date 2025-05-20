package com.tilldawn.Controller;

import com.tilldawn.App;
import com.tilldawn.Models.Map.Controller.CharacterController;
import com.tilldawn.Models.Map.Controller.GunController;
import com.tilldawn.Models.Map.Controller.MapController;
import com.tilldawn.Models.Map.Controller.MonsterController;
import com.tilldawn.Models.Map.Map;
import com.tilldawn.View.GameMenu;

public class GameMenuController {
    private final GameMenu view;
    private final CharacterController characterController;
    private final MapController mapController;
    private final GunController gunController;
    private final MonsterController monsterController;
    private final Map map;
    public GameMenuController(GameMenu view,Map map) {
        this.view = view;
        this.map = map;
        characterController = new CharacterController(App.getCurrentUser().getCharacter(),App.getCurrentUser().getMovingKeys(),view,map);
        mapController = new MapController(view,App.getCurrentUser().getCharacter(),characterController,map);
        gunController = new GunController(view,App.getCurrentUser().getCharacter(),characterController,map);
        monsterController = new MonsterController(view,map,App.getCurrentUser().getCharacter());
    }
    public void update(){
        mapController.update();
        characterController.update();
        gunController.update();
        monsterController.update();
    }
    public void handleKeyUp(){

    }
    public GunController getGunController(){
        return gunController;
    }
    public void dispose(){

    }
}

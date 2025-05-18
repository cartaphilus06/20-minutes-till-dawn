package com.tilldawn.Controller;

import com.tilldawn.App;
import com.tilldawn.Models.Map.Controller.CharacterController;
import com.tilldawn.Models.Map.Controller.GunController;
import com.tilldawn.Models.Map.Controller.MapController;
import com.tilldawn.View.GameMenu;

public class GameMenuController {
    private final GameMenu view;
    private final CharacterController characterController;
    private final MapController mapController;
    private final GunController gunController;
    public GameMenuController(GameMenu view) {
        this.view = view;
        characterController = new CharacterController(App.getCurrentUser().getCharacter(),App.getCurrentUser().getMovingKeys(),view);
        mapController = new MapController(view,App.getCurrentUser().getCharacter(),characterController);
        gunController = new GunController(view,App.getCurrentUser().getCharacter(),characterController);
    }
    public void update(){
        mapController.update();
        characterController.update();
        gunController.update();
    }
    public void handleKeyUp(){

    }
    public GunController getGunController(){
        return gunController;
    }
}

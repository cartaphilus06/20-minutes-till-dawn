package com.tilldawn.Controller;

import com.tilldawn.App;
import com.tilldawn.Models.Map.Controller.CharacterController;
import com.tilldawn.Models.Map.Controller.MapController;
import com.tilldawn.View.GameMenu;

public class GameMenuController {
    private final GameMenu view;
    private final CharacterController characterController;
    private final MapController mapController;
    public GameMenuController(GameMenu view) {
        this.view = view;
        characterController = new CharacterController(App.getCurrentUser().getCharacter(),App.getCurrentUser().getMovingKeys(),view);
        mapController = new MapController(view,App.getCurrentUser().getCharacter(),characterController);
    }
    public void update(){
        mapController.update();
        characterController.update();
    }
    public void handleKeyUp(){
        characterController.setDy(0);
        characterController.setDx(0);
    }
}

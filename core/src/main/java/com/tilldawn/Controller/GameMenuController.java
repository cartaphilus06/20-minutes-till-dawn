package com.tilldawn.Controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tilldawn.App;
import com.tilldawn.Models.Map.Controller.*;
import com.tilldawn.Models.Map.Map;
import com.tilldawn.View.GameMenu;

public class GameMenuController {
    private final GameMenu view;
    private final CharacterController characterController;
    private final MapController mapController;
    private final GunController gunController;
    private final MonsterController monsterController;
    private final BulletController bulletController;
    private final Map map;
    private final OrthographicCamera camera;
    public GameMenuController(GameMenu view,Map map) {
        this.view = view;
        this.map = map;
        this.camera = view.getCamera();
        characterController = new CharacterController(App.getCurrentUser().getCharacter(),App.getCurrentUser().getMovingKeys(),view,map);
        mapController = new MapController(view,App.getCurrentUser().getCharacter(),characterController,map);
        gunController = new GunController(view,App.getCurrentUser().getCharacter(),characterController,map);
        monsterController = new MonsterController(view,map,App.getCurrentUser().getCharacter());
        bulletController = new BulletController(view,gunController);
    }
    public void update(){
        mapController.update();
        characterController.update();
        gunController.update();
        monsterController.update();
        gunController.update();
        bulletController.update();
    }
    public void handleKeyUp(){

    }
    public GunController getGunController(){
        return gunController;
    }
    public void dispose(){
        mapController.dispose();
    }
}

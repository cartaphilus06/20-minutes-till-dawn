package com.tilldawn.Controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.App;
import com.tilldawn.Models.AlertGenerator;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Enums.CheatCodes;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.Map.Controller.*;
import com.tilldawn.Models.Map.Map;
import com.tilldawn.View.GameMenu;

import java.util.regex.Matcher;

public class GameMenuController {
    private final GameMenu view;
    private final CharacterController characterController;
    private final MapController mapController;
    private final GunController gunController;
    private final MonsterController monsterController;
    private final BulletController bulletController;
    private final Map map;
    public GameMenuController(GameMenu view,Map map) {
        this.view = view;
        this.map = map;
        characterController = new CharacterController(App.getCurrentUser().getCharacter(),App.getCurrentUser().getMovingKeys(),view,map);
        mapController = new MapController(view,App.getCurrentUser().getCharacter(),characterController,map);
        gunController = new GunController(view,App.getCurrentUser().getCharacter(),characterController,map);
        monsterController = new MonsterController(view,map,App.getCurrentUser().getCharacter());
        bulletController = new BulletController(view,gunController,App.getCurrentUser().getCharacter(),map);
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
    public void handleTouchDown(int screenX,int screenY,int button){
        characterController.handleTouchDown(button);
        bulletController.touchDown(screenX,screenY);
    }
    public GunController getGunController(){
        return gunController;
    }
    public void dispose(){
        mapController.dispose();
    }
    public void handleClickedButtons(){
        view.getSubmitCheat().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                cheat();
            }
        });
    }
    public void cheat(){
        String input=view.getCheatField().getText();
        Matcher lessenTime= CheatCodes.LESSEN_TIME.getMatcher(input);
        Matcher lessenArbitraryTime= CheatCodes.LESSEN_ARBITRARY_TIME.getMatcher(input);
        Matcher addLevel=CheatCodes.ADD_LEVEL.getMatcher(input);
        Matcher addHp=CheatCodes.ADD_HP.getMatcher(input);
        Matcher infiniteHp=CheatCodes.INFINITE_HP.getMatcher(input);
        Character character=App.getCurrentUser().getCharacter();
        if(lessenTime.matches()){
            map.setRemainingTime((map.getRemainingTime()-60)<0?0:map.getRemainingTime()-60);
            return;
        }
        if(lessenArbitraryTime.matches()){
            int amount;
            try{
                amount=Integer.parseInt(lessenArbitraryTime.group("amount"));
            } catch (NumberFormatException e){
                AlertGenerator.showAlert("","please enter a valid number!",view.getStage());
                return;
            }
            map.setRemainingTime((map.getRemainingTime()-amount)<0?0:map.getRemainingTime()-amount);
            return;
        }
        if(addLevel.matches()){
            character.setLevel(App.getCurrentUser().getCharacter().getLevel()+1);
            view.setLeveledUp(true);
            return;
        }
        if(addHp.matches()){
            if(character.getCurrentHp()<character.getHP()){
                character.setCurrentHp(character.getCurrentHp()+1);
            }
            return;
        }
        if(infiniteHp.matches()){
            character.setInfiniteHp(true);
        }
    }
}

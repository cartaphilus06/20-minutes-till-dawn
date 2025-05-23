package com.tilldawn.Controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tilldawn.App;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Enums.Hero;
import com.tilldawn.Models.Enums.Weapon;
import com.tilldawn.Models.User.User;
import com.tilldawn.View.GameMenu;
import com.tilldawn.View.MainMenu;
import com.tilldawn.View.PregameMenu;

public class PregameMenuController {
    private final PregameMenu view;
    public PregameMenuController(PregameMenu view) {
        this.view = view;
    }
    public void handleClickedButtons(){
        ImageButton[] heroButtons = view.getHeroButtons();
        for(int i=0; i<heroButtons.length; i++){
            ImageButton heroButton = heroButtons[i];
            int finalI = i;
            heroButton.addListener(new ClickListener(){
                public void clicked(InputEvent event, float x, float y) {
                    AssetManager.getUiClickSound().play();
                    App.getCurrentUser().getCharacter().setHero(Hero.values()[finalI]);
                    User.saveUsers();
                    view.setSelectHero(false);
                    view.setSelectWeaponUI();
                }
            });
        }
        view.getBack().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.getGame().setScreen(new MainMenu(view.getGame()));
            }
        });
        ImageButton[] weaponButtons=view.getWeaponButtons();
        for(int i=0;i<weaponButtons.length;i++){
            ImageButton weaponButton=weaponButtons[i];
            int finalI = i;
            weaponButton.addListener(new ClickListener(){
                public void clicked(InputEvent event, float x, float y) {
                    AssetManager.getUiClickSound().play();
                    App.getCurrentUser().getCharacter().setWeapon(Weapon.values()[finalI]);
                    User.saveUsers();
                    view.getGame().setScreen(new GameMenu(view.getGame(),view.getSelectTime().getSelected()));
                }
            });
        }
    }
    public void handleSelectWeaponButtons(){
        view.getBackToSelectHero().addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                AssetManager.getUiClickSound().play();
                view.setUpUI();
                view.setSelectHero(true);
            }
        });
    }
    public void handleHoveredButtons(){
        ImageButton[] heroButtons = view.getHeroButtons();
        Animation<TextureRegion>[] runAnimations=view.getRunFrames();
        Animation<TextureRegion>[] standAnimations=view.getStandFrames();
        Animation<TextureRegion>[] currentAnimations=view.getCurrentAnimations();
        for(int i=0;i<heroButtons.length;i++){
            ImageButton heroButton = heroButtons[i];
            int finalI = i;
            heroButton.addListener(new InputListener(){
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    currentAnimations[finalI]=runAnimations[finalI];
                    Texture[] portraits=view.getPortraits();
                    view.setCurrentPortrait(portraits[finalI]);
                    view.getHeroName().setText("NAME: "+Hero.values()[finalI].name().toUpperCase());
                    view.getHeroHP().setText("HP: "+Hero.values()[finalI].getHp());
                    view.getHeroSpeed().setText("SPEED: "+Hero.values()[finalI].getSpeed());
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    currentAnimations[finalI]=standAnimations[finalI];
                    view.getHeroName().setText("");
                    view.getHeroHP().setText("");
                    view.getHeroSpeed().setText("");
                }
            });
        }
        ImageButton[] weaponButtons=view.getWeaponButtons();
        Animation<TextureRegion>[] weaponAnimations=view.getWeaponAnimations();
        Animation<TextureRegion>[] weaponDefaultAnimations=view.getWeaponDefaultAnimation();
        Animation<TextureRegion>[] weaponCurrentAnimations=view.getWeaponCurrentAnimation();
        for(int i=0;i<weaponButtons.length;i++){
            ImageButton weaponButton=weaponButtons[i];
            int finalI = i;
            weaponButton.addListener(new InputListener(){
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    weaponCurrentAnimations[finalI]=weaponAnimations[finalI];
                    view.getWeaponName().setText("NAME: "+Weapon.values()[finalI].name().toUpperCase());
                    view.getWeaponDamage().setText("DAMAGE: "+Weapon.values()[finalI].getDamage());
                    view.getWeaponAmmo().setText("AMMO: "+Weapon.values()[finalI].getMaxAmmo());
                    view.getWeaponProjectile().setText("PROJECTILE: "+Weapon.values()[finalI].getProjectile());
                    view.getWeaponReloadTime().setText("RELOAD TIME: "+Weapon.values()[finalI].getReloadTime());
                }
                public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    weaponCurrentAnimations[finalI]=weaponDefaultAnimations[finalI];
                    view.getWeaponName().setText("");
                    view.getWeaponDamage().setText("");
                    view.getWeaponAmmo().setText("");
                    view.getWeaponProjectile().setText("");
                    view.getWeaponReloadTime().setText("");
                }
            });
        }
    }
}

package com.tilldawn.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tilldawn.App;
import com.tilldawn.Controller.PregameMenuController;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Enums.Hero;
import com.tilldawn.Models.Enums.Weapon;

public class PregameMenu implements Screen {
    private final PregameMenuController controller=new PregameMenuController(this);
    private final Game game;
    private Stage stage;
    private final Animation<TextureRegion>[] runFrames=new Animation[Hero.values().length];
    private final Animation<TextureRegion>[] standFrames=new Animation[Hero.values().length];
    private final Animation<TextureRegion>[] currentAnimations=new Animation[Hero.values().length];
    private final Animation<TextureRegion>[] weaponAnimations=new Animation[Weapon.values().length];
    private final Animation<TextureRegion>[] weaponDefaultAnimation=new Animation[Weapon.values().length];
    private final Animation<TextureRegion>[] currentWeaponsAnimations=new Animation[Weapon.values().length];
    private float stateTime=0;
    private ImageButton[] heroButtons;
    private ImageButton[] weaponButtons;
    private TextButton back;
    private TextButton backToSelectHero;
    private final Texture[] portraits=AssetManager.getHeroPortraits();
    private Texture currentPortrait;
    private boolean selectHero=true;

    public PregameMenu(Game game) {
        this.game=game;
    }
    @Override
    public void show() {
        stage=new Stage(new FitViewport(1920,1080));
        Gdx.input.setInputProcessor(stage);
        Skin skin= AssetManager.getSkin();
        heroButtons=new ImageButton[Hero.values().length];
        weaponButtons=new ImageButton[Weapon.values().length];
        back=new TextButton("BACK", skin);
        if(App.getCurrentUser().getCharacter().getHero()==null) currentPortrait=portraits[0];
        for(int i=0;i<heroButtons.length;i++){
            ImageButton.ImageButtonStyle style=new ImageButton.ImageButtonStyle();
            Drawable imageUp=new TextureRegionDrawable(AssetManager.getSelectorBubbleDefault());
            Drawable imageOver=new TextureRegionDrawable(AssetManager.getSelectorBubbleHover());
            style.up=imageUp;
            style.over=imageOver;
            style.down=imageOver;
            heroButtons[i]=new ImageButton(style);
            if(App.getCurrentUser().getCharacter().getHero()!=null) {
                if(Hero.values()[i].equals(App.getCurrentUser().getCharacter().getHero())){
                    currentPortrait=portraits[i];
                }
            }
        }
        for(int i=0;i<weaponButtons.length;i++){
            ImageButton.ImageButtonStyle style=new ImageButton.ImageButtonStyle();
            Drawable imageUp=new TextureRegionDrawable(AssetManager.getSelectorBubbleDefault());
            Drawable imageOver=new TextureRegionDrawable(AssetManager.getSelectorBubbleHover());
            style.up=imageUp;
            style.over=imageOver;
            style.down=imageOver;
            weaponButtons[i]=new ImageButton(style);
        }
        setUpUI();
        controller.handleClickedButtons();
        controller.handleHoveredButtons();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.153f, 0.125f, 0.188f, 1); // Set background color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stateTime+=delta;
        if(selectHero) {
            int index=0;
            for (ImageButton heroButton : heroButtons) {
                TextureRegion currentFrame = currentAnimations[index++].getKeyFrame(stateTime, true);
                float width = 64;
                float height = 64;
                stage.getBatch().draw(currentFrame, heroButton.getX() + (heroButton.getWidth() - width) / 2, heroButton.getY() + (heroButton.getHeight() - height) / 2, width, height);
            }
            float yOffSet = 300f + (float) Math.sin(2.5f * stateTime) * 10f;
            stage.getBatch().draw(currentPortrait, stage.getViewport().getWorldWidth() - currentPortrait.getWidth() * 2.2f - 30, yOffSet, currentPortrait.getWidth() * 2.2f, currentPortrait.getHeight() * 2.2f);
        }else{
            int index=0;
            for(ImageButton weaponButton:weaponButtons){
                TextureRegion currentFrame = currentWeaponsAnimations[index++].getKeyFrame(stateTime, true);
                float width = 64;
                float height = 64;
                stage.getBatch().draw(currentFrame,weaponButton.getX()+(weaponButton.getWidth()-width)/2,weaponButton.getY()+(weaponButton.getHeight()-height)/2, width, height);
            }
            float yOffSet=300f + (float) Math.sin(2.5f * stateTime) * 10f;
            Texture hero=App.getCurrentUser().getCharacter().getHero().getHeroPortraitTexture();
            stage.getBatch().draw(hero,stage.getViewport().getWorldWidth()-hero.getWidth()*2.2f-30,yOffSet, hero.getWidth()*2.2f, hero.getHeight()*2.2f);
        }
        stage.getBatch().end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {
        stage.dispose();
    }
    public void setUpUI(){
        stage.clear();
        Table table=new Table();
        table.setFillParent(true);
        table.top().left().padTop(300).padLeft(100);
        table.row();
        for(ImageButton heroButton:heroButtons){
            table.add(heroButton).width(120).height(120).pad(10);
        }
        table.row();
        table.add(back).width(400).height(60).colspan(5).padTop(100);
        for(int i=0;i<Hero.values().length;i++){
            runFrames[i]=Hero.values()[i].getRunAnimation();
            standFrames[i]=Hero.values()[i].getStandAnimation();
            currentAnimations[i]=standFrames[i];
        }
        stage.addActor(table);
    }
    public void setSelectWeaponUI(){
        stage.clear();
        backToSelectHero=new TextButton("BACK",AssetManager.getSkin());
        Table table=new Table();
        table.setFillParent(true);
        table.top().left().padTop(300).padLeft(100);
        table.row();
        for(ImageButton weaponButton:weaponButtons){
            table.add(weaponButton).width(120).height(120).pad(10);
        }
        table.row();
        table.add(backToSelectHero).width(400).height(60).colspan(5).padTop(100);
        for(int i=0;i<Weapon.values().length;i++){
            weaponAnimations[i]=Weapon.values()[i].getAnimation();
            weaponDefaultAnimation[i]=Weapon.values()[i].getDefaultAnimation();
            currentWeaponsAnimations[i]=weaponDefaultAnimation[i];
        }
        stage.addActor(table);
        controller.handleSelectWeaponButtons();
    }
    public Game getGame() {
        return game;
    }
    public Stage getStage() {
        return stage;
    }
    public Animation<TextureRegion>[] getRunFrames() {
        return runFrames;
    }
    public Animation<TextureRegion>[] getStandFrames() {
        return standFrames;
    }
    public ImageButton[] getHeroButtons() {
        return heroButtons;
    }
    public Animation<TextureRegion>[] getCurrentAnimations() {
        return currentAnimations;
    }
    public TextButton getBack() {
        return back;
    }
    public Texture[] getPortraits() {
        return portraits;
    }
    public void setCurrentPortrait(Texture portrait) {
        this.currentPortrait=portrait;
    }
    public TextButton getBackToSelectHero() {
        return backToSelectHero;
    }
    public void setSelectHero(boolean selectHero) {
        this.selectHero=selectHero;
        stateTime=0;
    }
    public Animation<TextureRegion>[] getWeaponAnimations(){
        return weaponAnimations;
    }
    public Animation<TextureRegion>[] getWeaponDefaultAnimation(){
        return weaponDefaultAnimation;
    }
    public Animation<TextureRegion>[] getWeaponCurrentAnimation(){
        return currentWeaponsAnimations;
    }
    public ImageButton[] getWeaponButtons(){
        return weaponButtons;
    }
}

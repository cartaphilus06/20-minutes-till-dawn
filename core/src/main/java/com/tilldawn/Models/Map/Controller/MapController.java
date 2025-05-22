package com.tilldawn.Models.Map.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.Map.Map;
import com.tilldawn.View.GameMenu;

public class MapController {
    private final GameMenu view;
    private final CharacterController characterController;
    private final Character character;
    private final Map map;
    private final Texture background;
    private float stateTime = 0f;
    private final OrthographicCamera camera;
    private Texture pixel;
    private BitmapFont level;
    private final Texture ammoIcon;
    private BitmapFont ammo;

    public MapController(GameMenu view, Character character, CharacterController characterController, Map map) {
        this.view = view;
        this.character = character;
        this.characterController = characterController;
        this.map = map;
        this.background = map.getBackground();
        this.camera = view.getCamera();
        this.ammoIcon = AssetManager.getAmmoIcon();

        character.setX((background.getWidth()-character.getHeroWidth()) / 2f);
        character.setY((background.getHeight()-character.getHeroHeight()) / 2f);
        setLevel();
        setAmmo();
        setPixel();
    }

    public void update() {
        camera.position.set(character.getX(), character.getY(), 0);
        camera.update();
        Batch batch = view.getStage().getBatch();
        batch.draw(background, 0, 0);
        float screenCenterX = camera.position.x - character.getHeroWidth() / 2f;
        float screenCenterY = camera.position.y - character.getHeroHeight() / 2f;
        character.getSprite().setPosition(screenCenterX, screenCenterY);
        character.getSprite().draw(batch);
        drawHeart(batch);
        drawExpBar(batch);
        addLevel(batch);
        drawAmmo(batch);
        stateTime += Gdx.graphics.getDeltaTime();
    }

    public void drawHeart(Batch batch) {
        Animation<TextureRegion> heartAnimation = AssetManager.getHeart().getAnimation();
        TextureRegion deadHeart = AssetManager.getHeart().getTiles()[3];
        int currentHp = character.getCurrentHp();
        for (int i = 1; i <= character.getHP(); i++) {
            float x = camera.position.x - camera.viewportWidth / 2 + i * 64 - 32;
            float y = camera.position.y + camera.viewportHeight / 2 - 64 - getBarHeight();
            if (i <= currentHp) {
                TextureRegion currentFrame = heartAnimation.getKeyFrame(stateTime, true);
                batch.draw(currentFrame, x, y, currentFrame.getRegionWidth() * 2, currentFrame.getRegionHeight() * 2);
            } else {
                batch.draw(deadHeart, x, y, deadHeart.getRegionWidth() * 2, deadHeart.getRegionHeight() * 2);
            }
        }
    }

    public void drawExpBar(Batch batch) {
        float progress=character.getCurrentExp()/character.getExpPerLevel();
        float x=camera.position.x- getBarWidth() /2f;
        float y=camera.position.y+camera.viewportHeight / 2f - getBarHeight();
        batch.setColor(Color.DARK_GRAY);
        batch.draw(pixel, x, y, getBarWidth(), getBarHeight());
        batch.setColor(Color.GREEN);
        batch.draw(pixel, x, y, getBarWidth() * progress, getBarHeight());
        batch.setColor(Color.WHITE);
    }

    public void drawAmmo(Batch batch) {
        float width=ammoIcon.getWidth()*2f;
        float height=ammoIcon.getHeight()*2f;
        float x=camera.position.x - camera.viewportWidth / 2 + 32;
        float y=camera.position.y + camera.viewportHeight / 2f - 64 - getBarHeight() - height - 10;
        batch.draw(ammoIcon, x, y, width, height);
        float numberX=x+width;
        ammo.draw(batch,character.getCurrentAmmo()+"/"+character.getWeapon().getMaxAmmo(),numberX,y+40);
    }

    public void addLevel(Batch batch) {
        float x=camera.position.x-getLevelSize()-60;
        float y=camera.position.y+camera.viewportHeight / 2f - 15;
        level.draw(batch,"LEVEL: "+character.getLevel(),x,y);
    }

    public float getBarWidth(){
        return Gdx.graphics.getWidth();
    }

    public float getBarHeight(){
        return 50f;
    }

    public void setPixel() {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        pixel = new Texture(pixmap);
        pixmap.dispose();
    }
    public void setLevel(){
        FreeTypeFontGenerator generator=new FreeTypeFontGenerator(Gdx.files.internal("fonts/PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=getLevelSize();
        level=generator.generateFont(parameter);
        generator.dispose();
    }
    public void setAmmo(){
        FreeTypeFontGenerator generator=new FreeTypeFontGenerator(Gdx.files.internal("fonts/PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=getLevelSize();
        ammo=generator.generateFont(parameter);
        generator.dispose();
    }
    public int getLevelSize(){
        return 32;
    }
    public void dispose() {
        background.dispose();
        map.dispose();
    }
}

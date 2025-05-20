package com.tilldawn.Models.Map.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    public MapController(GameMenu view, Character character, CharacterController characterController, Map map) {
        this.view = view;
        this.character = character;
        this.characterController = characterController;
        this.map = map;
        this.background = map.getBackground();
        this.camera = view.getCamera();

        // Character world position (center of world initially)
        character.setX(background.getWidth() / 2f);
        character.setY(background.getHeight() / 2f);
    }

    public void update() {
        // Move camera to follow character's world position
        camera.position.set(character.getX(), character.getY(), 0);
        camera.update();

        Batch batch = view.getStage().getBatch();
        batch.setProjectionMatrix(camera.combined);

        // Draw background relative to camera
        batch.draw(background,
            0,
            0
        );

        // Draw character always centered
        float screenCenterX = camera.position.x - character.getHeroWidth() / 2;
        float screenCenterY = camera.position.y - character.getHeroHeight() / 2;
        character.getSprite().setPosition(screenCenterX, screenCenterY);
        character.getSprite().draw(batch);

        drawHeart(batch);

        stateTime += Gdx.graphics.getDeltaTime();
    }

    public void drawHeart(Batch batch) {
        Animation<TextureRegion> heartAnimation = AssetManager.getHeart().getAnimation();
        TextureRegion deadHeart = AssetManager.getHeart().getTiles()[3];
        int currentHp = character.getCurrentHp();
        for (int i = 1; i <= character.getHP(); i++) {
            float x = camera.position.x - camera.viewportWidth / 2 + i * 64;
            float y = camera.position.y + camera.viewportHeight / 2 - 64;
            if (i <= currentHp) {
                TextureRegion currentFrame = heartAnimation.getKeyFrame(stateTime, true);
                batch.draw(currentFrame, x, y, currentFrame.getRegionWidth() * 2, currentFrame.getRegionHeight() * 2);
            } else {
                batch.draw(deadHeart, x, y, deadHeart.getRegionWidth() * 2, deadHeart.getRegionHeight() * 2);
            }
        }
    }

    public void dispose() {
        background.dispose();
        map.dispose();
    }
}

package com.tilldawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ConfirmDialog extends Window {
    public ConfirmDialog(String message, Skin skin, Runnable onConfirm, Runnable onCancel) {
        super("Confirm", skin);

        // Add the message
        Label label = new Label(message, skin);
        label.setWrap(true);
        add(label).colspan(2).pad(10).row();

        // Confirm Button
        TextButton yesButton = new TextButton("YES", skin);
        yesButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onConfirm.run();
                remove(); // Close dialog
            }
        });

        // Cancel Button
        TextButton noButton = new TextButton("NO", skin);
        noButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (onCancel != null) onCancel.run();
                remove(); // Close dialog
            }
        });

        add(yesButton).pad(10);
        add(noButton).pad(10);

        pack();
        setPosition(
            Gdx.graphics.getWidth() / 2f - getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f - getHeight() / 2f
        );
    }
}

package com.tilldawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class ConfirmDialog extends Window {
    public ConfirmDialog(String message, Skin skin, Runnable onConfirm, Runnable onCancel) {
        super("Confirm", skin);

        // Set size for the dialog
        setWidth(400);
        setKeepWithinStage(true);
        setModal(true);

        // Create label with wrap and alignment
        Label label = new Label(message, skin);
        label.setWrap(true);
        label.setAlignment(Align.center);
        label.setWidth(360); // Ensure enough width to avoid vertical wrapping

        // Add label to window
        add(label).colspan(2).width(360).pad(10).row();

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

        // Add buttons
        add(yesButton).pad(10);
        add(noButton).pad(10).row();

        pack();
        setPosition(
            Gdx.graphics.getWidth() / 2f - getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f - getHeight() / 2f
        );
    }
}

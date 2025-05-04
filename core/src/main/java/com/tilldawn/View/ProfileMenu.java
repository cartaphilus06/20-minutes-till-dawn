package com.tilldawn.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.tilldawn.Controller.ProfileMenuController;
import com.tilldawn.Models.AssetManager;

public class ProfileMenu implements Screen {
    private ProfileMenuController controller=new ProfileMenuController(this);
    private final Game game;
    private Stage stage;
    private Texture background;
    private TextButton changeUsername;
    private TextButton changePassword;
    private TextButton deleteAccount;
    private TextButton changeAvatar;
    private TextButton back;
    public ProfileMenu(Game game) {
        this.game=game;
    }
    @Override
    public void show() {
        stage=new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin= AssetManager.getSkin();
        background=new Texture(Gdx.files.internal("images/backgrounds/menusBackground.png"));
        changeUsername=new TextButton("CHANGE USERNAME",skin);
        changePassword=new TextButton("CHANGE PASSWORD",skin);
        deleteAccount=new TextButton("DELETE ACCOUNT",skin);
        changeAvatar=new TextButton("CHANGE AVATAR",skin);
        back=new TextButton("BACK",skin);
        setUpUI();
        controller.handleClickedButtons();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0,
            stage.getViewport().getWorldWidth(),
            stage.getViewport().getWorldHeight());
        stage.getBatch().end();
        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
    }
    public void setUpUI(){
        stage.clear();
        Table table=new Table();
        table.setFillParent(true);
        table.center().padTop(350);
        table.defaults().pad(5);
        table.add(changeUsername).width(500).height(70).row();
        table.add(changePassword).width(500).height(70).row();
        table.add(deleteAccount).width(500).height(70).row();
        table.add(changeAvatar).width(500).height(70).row();
        table.add(back).width(500).height(70).row();
        stage.addActor(table);
    }
    public TextButton getChangeUsername() {
        return changeUsername;
    }
    public TextButton getChangePassword() {
        return changePassword;
    }
    public TextButton getDeleteAccount() {
        return deleteAccount;
    }
    public TextButton getChangeAvatar() {
        return changeAvatar;
    }
    public TextButton getBack() {
        return back;
    }
    public Game getGame() {
        return game;
    }
    public Stage getStage() {
        return stage;
    }
    public void setUpChangeUsernamePageUI(){
        stage.clear();
        Skin skin= AssetManager.getSkin();
        Table table=new Table();
        table.setFillParent(true);
        table.center().padTop(300);
        Label instruction=new Label("NEW USERNAME",skin);
        TextField changeUsername=new TextField("",AssetManager.getTextFieldStyle());
        TextButton confirm=new TextButton("confirm",skin);
        TextButton cancel=new TextButton("cancel",skin);
        table.defaults().pad(10);
        table.add(instruction).colspan(2).row();
        table.add(changeUsername).width(300).height(80).colspan(2).row();

        Table buttonTable=new Table();
        buttonTable.add(confirm).width(300).height(60);
        buttonTable.add(cancel).width(300).height(60);
        table.add(buttonTable).colspan(2).row();
        stage.addActor(table);
        controller.handleChangeUsername(confirm,changeUsername,cancel);
    }
    public void setUpChangePasswordPageUI(){
        stage.clear();
        Skin skin= AssetManager.getSkin();
        Table table=new Table();
        table.setFillParent(true);
        table.center().padTop(300);
        Label instruction=new Label("NEW PASSWORD",skin);
        Label confirmPassword=new Label("CONFIRM PASSWORD",skin);
        TextField changePasswordField=new TextField("",AssetManager.getTextFieldStyle());
        TextField confirmPasswordField=new TextField("",AssetManager.getTextFieldStyle());
        TextButton confirm=new TextButton("confirm",skin);
        TextButton cancel=new TextButton("cancel",skin);
        table.defaults().pad(10);

        table.add(instruction).colspan(2).row();
        table.add(changePasswordField).width(300).height(80).colspan(2).row();

        table.add(confirmPassword).colspan(2).row();
        table.add(confirmPasswordField).width(300).height(80).colspan(2).row();

        Table buttonTable=new Table();
        buttonTable.add(confirm).width(300).height(60);
        buttonTable.add(cancel).width(300).height(60);
        table.add(buttonTable).colspan(2).row();
        stage.addActor(table);
        controller.handleChangePassword(confirm,changePasswordField,confirmPasswordField,cancel);
    }
}

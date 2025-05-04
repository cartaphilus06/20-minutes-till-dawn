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
        background=new Texture(Gdx.files.internal("images/bg.png"));
        changeUsername=new TextButton("change username",skin);
        changePassword=new TextButton("change password",skin);
        deleteAccount=new TextButton("delete account",skin);
        changeAvatar=new TextButton("change avatar",skin);
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
        table.center();
        table.row();
        table.add(changeUsername);
        table.row();
        table.add(changePassword);
        table.row();
        table.add(deleteAccount);
        table.row();
        table.add(changeAvatar);
        table.row();
        table.add(back);
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
        table.center();
        Label instruction=new Label("enter you new username:",skin);
        TextField changeUsername=new TextField("",skin);
        TextButton confirm=new TextButton("confirm",skin);
        TextButton cancel=new TextButton("cancel",skin);
        table.row();
        table.add(instruction);
        table.row();
        table.add(changeUsername).width(300);
        table.row();
        table.add(confirm);
        table.add(cancel);
        stage.addActor(table);
        controller.handleChangeUsername(confirm,changeUsername,cancel);
    }
    public void setUpChangePasswordPageUI(){
        stage.clear();
        Skin skin= AssetManager.getSkin();
        Table table=new Table();
        table.setFillParent(true);
        table.center();
        Label instruction=new Label("enter you new password:",skin);
        TextField changePassword=new TextField("",skin);
        TextField confirmPassword=new TextField("",skin);
        TextButton confirm=new TextButton("confirm",skin);
        TextButton cancel=new TextButton("back",skin);
        changePassword.setPasswordMode(true);
        confirmPassword.setPasswordMode(true);
        table.row();
        table.add(instruction);
        table.row();
        table.add(changePassword).width(300);
        table.row();
        table.add(confirmPassword).width(300);
        table.row();
        table.add(confirm);
        table.add(cancel);
        stage.addActor(table);
        controller.handleChangePassword(confirm,changePassword,confirmPassword,cancel);
    }
}

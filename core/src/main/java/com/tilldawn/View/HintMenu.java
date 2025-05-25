package com.tilldawn.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tilldawn.App;
import com.tilldawn.Controller.HintMenuController;
import com.tilldawn.Models.AssetManager;
import com.tilldawn.Models.Enums.CheatCodes;
import com.tilldawn.Models.Enums.Hero;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.User.MovingKeys;

public class HintMenu implements Screen {
    private final HintMenuController controller;
    private Stage stage;
    private final Game game;
    private Texture background;
    private TextButton heroDetails;
    private TextButton keysDetails;
    private TextButton cheatCodes;
    private TextButton abilityDetails;
    private TextButton backToHint;
    private TextButton backToMainMenu;
    private final Skin skin;
    public HintMenu(Game game) {
        this.game = game;
        skin= AssetManager.getSkin();
        controller = new HintMenuController(this);
    }
    @Override
    public void show() {
        stage=new Stage(new FitViewport(1920,1080));
        Gdx.input.setInputProcessor(stage);
        background=new Texture(Gdx.files.internal("images/backgrounds/bg.png"));
        heroDetails=new TextButton("HERO DETAILS",skin);
        keysDetails=new TextButton("KEYS DETAILS",skin);
        cheatCodes=new TextButton("CHEAT CODES",skin);
        abilityDetails=new TextButton("ABILITY DETAILS",skin);
        backToHint=new TextButton("BACK",skin);
        backToMainMenu=new TextButton("BACK",skin);
        setUpUI();
        controller.handleClickedButtons();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0,
            stage.getViewport().getWorldWidth(),
            stage.getViewport().getWorldHeight());
        stage.getBatch().end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
        background.dispose();
    }
    public void setUpUI(){
        stage.clear();
        Table table=new Table();
        table.setFillParent(true);
        table.center();
        table.defaults().pad(10);
        float width=420f;
        table.add(heroDetails).width(width).height(60).row();
        table.add(keysDetails).width(width).height(60).row();
        table.add(cheatCodes).width(width).height(60).row();
        table.add(abilityDetails).width(width).height(60).row();
        table.add(backToMainMenu).width(width).height(60).row();
        stage.addActor(table);
    }
    public void setUpHeroUI(){
        stage.clear();
        Table table=new Table();
        table.setFillParent(true);
        table.center();
        table.defaults().pad(10);
        Table[] heroInfo=new Table[Hero.values().length];
        for(int i=0;i<Hero.values().length;i++){
            Hero hero=Hero.values()[i];
            heroInfo[i]=new Table();
            StringBuilder builder=new StringBuilder();
            builder.append("name: ").append(hero.name()).append("\n")
                .append("speed: ").append(hero.getSpeed()).append("\n")
                .append("hp: ").append(hero.getHp()).append("\n");
            Label label=new Label(builder.toString(),skin);
            heroInfo[i].add(label).width(300).height(100);
            table.add(heroInfo[i]).row();
        }
        table.add(backToHint).width(300).height(60).padTop(50).row();
        stage.addActor(table);
    }
    public void setUpAbilityUI(){
        stage.clear();
        Table table=new Table();
        table.setFillParent(true);
        table.center();
        table.defaults().pad(10);
        Label speedy=new Label("speedy: doubles character's speed for 10 seconds",skin);
        Label damager=new Label("damager: 25 percent damage increase for 10 seconds",skin);
        Label procrease=new Label("procrease: 1 unit increase for projectile",skin);
        Label amocrease=new Label("amocrease: 1 unit increase for max ammo",skin);
        Label vitality=new Label("vitality: 1 unit increase for max hp",skin);
        table.add(damager).width(600).height(60).row();
        table.add(speedy).width(600).height(60).row();
        table.add(procrease).width(600).height(60).row();
        table.add(amocrease).width(600).height(60).row();
        table.add(vitality).width(600).height(60).row();
        table.add(backToHint).width(300).height(60).row();
        stage.addActor(table);
    }
    public void setUpKeysUI(){
        stage.clear();
        Table table=new Table();
        table.setFillParent(true);
        table.center();
        table.defaults().pad(10);
        MovingKeys movingKeys=App.getCurrentUser().getMovingKeys();
        Label moveUp=new Label("move up: "+ Input.Keys.toString(movingKeys.getMoveUp()),skin);
        Label moveDown=new Label("move down: "+ Input.Keys.toString(movingKeys.getMoveDown()),skin);
        Label moveRight=new Label("move right: "+ Input.Keys.toString(movingKeys.getMoveRight()),skin);
        Label moveLeft=new Label("move left: "+ Input.Keys.toString(movingKeys.getMoveLeft()),skin);
        Label reload=new Label("reload: "+ Input.Keys.toString(movingKeys.getReload()),skin);
        float labelWidth=300f;
        table.add(moveUp).width(labelWidth).height(60).row();
        table.add(moveDown).width(labelWidth).height(60).row();
        table.add(moveRight).width(labelWidth).height(60).row();
        table.add(moveLeft).width(labelWidth).height(60).row();
        table.add(reload).width(labelWidth).height(60).row();
        table.add(backToHint).width(300).height(60).row();
        stage.addActor(table);
    }
    public void setUpCheatCodeUI(){
        stage.clear();
        Table table=new Table();
        table.setFillParent(true);
        table.center();
        table.defaults().pad(10);
        for(CheatCodes cheatCodes:CheatCodes.values()){
            Label label=new Label(cheatCodes.getDescription()+cheatCodes.getPattern(),skin);
            table.add(label).width(300).height(60).row();
        }
        table.add(backToHint).width(300).height(60).row();
        stage.addActor(table);
    }
    public Game getGame() {
        return game;
    }
    public Stage getStage() {
        return stage;
    }
    public TextButton getHeroDetails(){
        return heroDetails;
    }
    public TextButton getKeysDetails(){
        return keysDetails;
    }
    public TextButton getCheatCodes(){
        return cheatCodes;
    }
    public TextButton getAbilityDetails(){
        return abilityDetails;
    }
    public TextButton getBackToHint(){
        return backToHint;
    }
    public TextButton getBackToMainMenu(){
        return backToMainMenu;
    }
}

package com.tilldawn.Models.Map.Monster;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tilldawn.App;
import com.tilldawn.Models.Enums.MonsterType;
import com.tilldawn.Models.Map.Character;
import com.tilldawn.Models.Map.Controller.CharacterController;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = BrainMonster.class, name = "BrainMonster"),
    @JsonSubTypes.Type(value = Eyebat.class, name = "Eyebat"),
    @JsonSubTypes.Type(value = Tree.class, name = "Tree"),
    @JsonSubTypes.Type(value = Elder.class, name = "Elder")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Monster {
    @JsonIgnore
    protected Sprite sprite;
    protected int hp;
    protected float x;
    protected float y;
    protected String internalPath;
    @JsonIgnore
    protected Texture monsterTexture;
    protected float speed=100f;
    protected float stateTime;
    protected boolean isFacingLeft=false;
    public Monster() {}
    public Monster(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getHp(){
        return hp;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    protected void setInternalPath(String internalPath) {
        this.internalPath = internalPath;
    }
    public void update(float delta){
        Character character= App.getCurrentUser().getCharacter();
        move(character.getX(),character.getY(),delta);
        shoot();
        stateTime+=delta;
    }
    public void move(float targetX, float targetY,float delta) {
        if(this instanceof Tree) return;
        Character character= App.getCurrentUser().getCharacter();
        float dx = targetX - x;
        float dy = targetY - y;
        float distance=(float)Math.sqrt(dx*dx+dy*dy);
        if(sprite.getBoundingRectangle().overlaps(character.getSprite().getBoundingRectangle())){
            character.setCurrentHp(character.getCurrentHp()-1);
            App.getCurrentMap().getMonsters().remove(this);
            CharacterController.getCharacterController().handleInvincibility();
            return;
        }
        if(distance>1 && App.getCurrentMap().isWalkable(x + dx, y + dy)) {
            float step=speed*delta;
            x+=(dx/distance)*step;
            y+=(dy/distance)*step;
            sprite.setPosition(x, y);
            if(dx<0 ^ sprite.isFlipX()){
                setFacingLeft(dx<0);
            }
        }
    }
    public void shoot(){}
    public void draw(Batch batch){
        sprite.draw(batch);
    }
    @JsonIgnore
    public void setTexture(Texture texture){
        monsterTexture = texture;
    }
    public abstract int getWidth();
    public abstract int getHeight();
    protected String getInternalPath() {
        return internalPath;
    }
    @JsonIgnore
    public Sprite getSprite() {
        return sprite;
    }
    public boolean isFacingLeft() {
        return isFacingLeft;
    }
    public void setFacingLeft(boolean facingLeft) {
        isFacingLeft = facingLeft;
    }
    public static Monster getInstance(MonsterType type,float x,float y){
        if(type == MonsterType.Tree) return new Tree(x,y);
        if(type == MonsterType.BrainMonster) return new BrainMonster(x,y);
        if(type == MonsterType.Eyebat) return new Eyebat(x,y);
        if(type == MonsterType.Elder) return new Elder(x,y);
        return null;
    }
    public abstract void reinitializeAssets();
}

package com.tilldawn.Models.User;

import com.badlogic.gdx.Input;

public class MovingKeys {
    private int moveUp= Input.Keys.W;
    private int moveDown= Input.Keys.S;
    private int moveLeft= Input.Keys.A;
    private int moveRight= Input.Keys.D;
    public int getMoveUp() {
        return moveUp;
    }
    public int getMoveDown() {
        return moveDown;
    }
    public int getMoveLeft() {
        return moveLeft;
    }
    public int getMoveRight() {
        return moveRight;
    }
    public void setMoveUp(int moveUp) {
        this.moveUp = moveUp;
    }
    public void setMoveDown(int moveDown) {
        this.moveDown = moveDown;
    }
    public void setMoveLeft(int moveLeft) {
        this.moveLeft = moveLeft;
    }
    public void setMoveRight(int moveRight) {
        this.moveRight = moveRight;
    }
}

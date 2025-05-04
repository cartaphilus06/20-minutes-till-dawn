package com.tilldawn.Models.Ability;

public class Vitality implements Ability{
    @Override
    public int getHP() {
        return 1;
    }

    @Override
    public float getDamage(int damage) {
        return 0;
    }

    @Override
    public int getProjectile() {
        return 0;
    }

    @Override
    public int getReloadTime() {
        return 0;
    }

    @Override
    public int getMaxAmmo() {
        return 0;
    }

    @Override
    public int getDisappearingTime() {
        return 0;
    }

    @Override
    public int getSpeed(int speed) {
        return 0;
    }
}

package com.tilldawn.Models.Ability;

public class Speedy implements Ability {
    @Override
    public int getHP() {
        return 0;
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
        return 10;
    }

    @Override
    public int getSpeed(int speed) {
        return speed;
    }
}

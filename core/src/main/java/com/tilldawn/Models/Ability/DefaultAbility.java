package com.tilldawn.Models.Ability;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultAbility implements Ability {
    @Override
    public int getHP(int hp) {
        return hp;
    }

    @Override
    public float getDamage(int damage) {
        return damage;
    }

    @Override
    public int getProjectile(int projectile) {
        return projectile;
    }

    @Override
    public int getReloadTime(int reloadTime) {
        return reloadTime;
    }

    @Override
    public int getMaxAmmo(int maxAmmo) {
        return maxAmmo;
    }

    @Override
    public int getSpeed(int speed) {
        return speed;
    }

    @Override
    public Ability getAbility() {
        return null;
    }

    @JsonIgnore
    public String getName() {
        return "Default";
    }
}

package com.tilldawn.Models.Ability;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Procrease implements Ability{
    private Ability ability;
    public Procrease(Ability ability) {
        this.ability = ability;
    }
    @Override
    public int getHP(int hp) {
        return ability.getHP(hp);
    }

    @Override
    public float getDamage(int damage) {
        return ability.getDamage(damage);
    }

    @Override
    public int getProjectile(int projectile) {
        return ability.getProjectile(projectile)+1;
    }

    @Override
    public int getReloadTime(int reloadTime) {
        return ability.getReloadTime(reloadTime);
    }

    @Override
    public int getMaxAmmo(int maxAmmo) {
        return ability.getMaxAmmo(maxAmmo);
    }

    @Override
    public int getSpeed(int speed) {
        return ability.getSpeed(speed);
    }
    public Ability getAbility() {
        return ability;
    }
    public void setAbility(Ability ability) {
        this.ability = ability;
    }
}

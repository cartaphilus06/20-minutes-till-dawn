package com.tilldawn.Models.Ability;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Amocrease implements Ability {
    private Ability ability;
    public Amocrease() {}
    public Amocrease(Ability ability) {
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
        return ability.getProjectile(projectile);
    }

    @Override
    public int getReloadTime(int reloadTime) {
        return ability.getReloadTime(reloadTime);
    }

    @Override
    public int getMaxAmmo(int maxAmmo) {
        return ability.getMaxAmmo(maxAmmo)+5;
    }

    @Override
    public int getSpeed(int speed) {
        return ability.getSpeed(speed)*2;
    }
    public Ability getAbility() {
        return ability;
    }
    public void setAbility(Ability ability) {
        this.ability = ability;
    }
}

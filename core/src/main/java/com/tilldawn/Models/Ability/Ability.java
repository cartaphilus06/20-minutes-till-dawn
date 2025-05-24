package com.tilldawn.Models.Ability;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@JsonSubTypes({
    @JsonSubTypes.Type(value = DefaultAbility.class),
    @JsonSubTypes.Type(value = Damager.class),
    @JsonSubTypes.Type(value = Speedy.class),
    @JsonSubTypes.Type(value = Amocrease.class),
    @JsonSubTypes.Type(value = Procrease.class),
    @JsonSubTypes.Type(value = Vitality.class)
})
public interface Ability {
    int getHP(int hp);
    float getDamage(int damage);
    int getProjectile(int projectile);
    int getReloadTime(int reloadTime);
    int getMaxAmmo(int maxAmmo);
    int getSpeed(int speed);
    Ability getAbility();
    @JsonIgnore
    String getName();
}

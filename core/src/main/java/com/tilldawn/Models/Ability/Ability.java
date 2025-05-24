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
    static Ability getInstance(String name,Ability currentAbility){
        if(name.equalsIgnoreCase("amocrease")) return new Amocrease(currentAbility);
        if(name.equalsIgnoreCase("speedy")) return new Speedy(currentAbility);
        if(name.equalsIgnoreCase("vitality")) return new Vitality(currentAbility);
        if(name.equalsIgnoreCase("damager")) return new Damager(currentAbility);
        if(name.equalsIgnoreCase("procrease")) return new Procrease(currentAbility);
        return null;
    }
}

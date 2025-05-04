package com.tilldawn.Models.Ability;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Vitality.class, name = "Vitality"),
    @JsonSubTypes.Type(value = Damager.class, name = "Damager"),
    @JsonSubTypes.Type(value = Speedy.class, name = "Speedy"),
    @JsonSubTypes.Type(value = Procrease.class, name = "Procrease"),
    @JsonSubTypes.Type(value = Amocrease.class, name = "Amocrease"),
    @JsonSubTypes.Type(value = DefaultAbility.class, name = "DefaultAbility"),
})
public interface Ability {
    int getHP();
    float getDamage(int damage);
    int getProjectile();
    int getReloadTime();
    int getMaxAmmo();
    int getDisappearingTime();
    int getSpeed(int speed);
}

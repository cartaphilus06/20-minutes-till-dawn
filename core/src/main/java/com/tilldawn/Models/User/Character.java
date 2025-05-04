package com.tilldawn.Models.User;

import com.tilldawn.Models.Ability.Ability;
import com.tilldawn.Models.Ability.DefaultAbility;
import com.tilldawn.Models.Enums.Hero;
import com.tilldawn.Models.Enums.Weapon;

public class Character {
    private Hero hero;
    private Weapon weapon;
    private Ability ability=new DefaultAbility();
    public Hero getHero() {
        return hero;
    }
    public void setHero(Hero hero) {
        this.hero = hero;
    }
    public Weapon getWeapon(){
        return weapon;
    }
    public void setWeapon(Weapon weapon){
        this.weapon=weapon;
    }
    public Ability getAbility() {
        return ability;
    }
    public void setAbility(Ability ability) {
        this.ability = ability;
    }
    public int getHP(){
        return hero.getHp()+ability.getHP();
    }
    public float getDamage(){
        return weapon.getDamage()+ability.getDamage(weapon.getDamage());
    }
    public int getProjectile(){
        return weapon.getProjectile()+ability.getProjectile();
    }
    public int getReloadTime(){
        return weapon.getReloadTime()+ability.getReloadTime();
    }
    public int getMaxAmmo(){
        return weapon.getMaxAmmo()+ability.getMaxAmmo();
    }
    public int getSpeed(){
        return hero.getSpeed()+ ability.getSpeed(hero.getSpeed());
    }
    public int getDisappearingTime(){
        return ability.getDisappearingTime();
    }
}

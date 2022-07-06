package fr.mainox.swingy.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Creature {
    
    @NotNull
    @Size(min = 1, max = 20)
    protected String name;
    @NotNull
    @Min(100)
    @Max(1000)
    protected int hp;
    @NotNull
    @Min(10)
    @Max(15)
    protected int attack;
    @NotNull
    @Min(10)
    @Max(15)
    protected int defense;
    private int x;
    private int y;

    public Creature(String name, int hp, int attack, int defense, int x, int y) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void attack(Creature creature) {
        int damage = this.getAttack() - creature.getDefense();
        if (damage < 0)
            damage = 1;
        creature.setHp(creature.getHp() - damage);
    }

}

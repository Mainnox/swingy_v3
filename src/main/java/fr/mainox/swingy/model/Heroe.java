package fr.mainox.swingy.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Heroe extends Creature {

    int id;
    @NotNull
    @Size(min = 1)
    private int level;
    @Size(min = 0)
    private int experience;
    private int maxHP;
    private String spe;
    private String weapon;
    private String armor;
    private String helm;

    public Heroe(String name, int hp, int attack, int defense, int level, int experience, String spe) {
        super(name, hp, attack, defense, 0, 0);
        this.spe = spe;
        this.level = level;
        this.experience = experience;
        this.weapon = "none";
        this.armor = "none";
        this.helm = "none";
        this.maxHP = hp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getSpe() {
        return spe;
    }

    public void setSpe(String spe) {
        this.spe = spe;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public String getHelm() {
        return helm;
    }

    public void setHelm(String helm) {
        this.helm = helm;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }
    
    public void levelUp() {
        this.setLevel(this.getLevel() + 1);
        this.setExperience(0);
        this.setMaxHP(this.getMaxHP() + 10);
        this.setAttack(this.getAttack() + 1);
        this.setDefense(this.getDefense() + 1);
    }

    public void addExperience(int experience) {
        this.setExperience(this.getExperience() + experience);
        if (this.getExperience() >= (this.level * 1000) + ((this.level - 1) * (this.level - 1)) * 450)
            this.levelUp();
    }

    public void addWeapon(String weapon, int attack) {
        this.setWeapon(weapon);
        this.setAttack(this.getAttack() + attack);
    }

    public void addArmor(String armor, int defense) {
        this.setArmor(armor);
        this.setDefense(this.getDefense() + defense);
    }

    public void addHelm(String helm, int hp) {
        this.setHelm(helm);
        this.setHp(this.getHp() + hp);
    }

    public void kill() {
        this.setHp(0);
    }

    @Override
    public String toString() {
        return "Heroe{" + "name=" + getName() + ", hp=" + getHp() + ", attack="
            + getAttack() + ", defense=" + getDefense() + ", level=" + level
            + ", experience=" + experience + ", spe=" + spe + ", weapon=" + weapon
            + ", armor=" + armor + ", helm=" + helm + '}';
    }

    public String getInfo() {
        return "Name: " + getName() + "\n" + "Level: " + getLevel() + "\n"
            + "Experience: " + getExperience() + "\n" + "Spe: " + getSpe() + "\n"
            + "Weapon: " + getWeapon() + "\n" + "Armor: " + getArmor() + "\n"
            + "Helm: " + getHelm() + "\n" + "Hp: " + getHp() + "\n" + "Attack: "
            + getAttack() + "\n" + "Defense: " + getDefense();
    }

}

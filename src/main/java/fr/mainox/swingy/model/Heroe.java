package fr.mainox.swingy.model;

public class Heroe extends Creature {

    int id;
    private int level;
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
    
    public String getArmor() {
        return armor;
    }

    public String getHelm() {
        return helm;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public void setHelm(String helm) {
        this.helm = helm;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
    
    public void addWeapon(String weapon) {
        String[] weaponSplit = weapon.split(" ");
        if (weaponSplit[0].equals("none")) {
            this.weapon = "none";
        } else {
            this.weapon = weapon;
            this.attack += Integer.parseInt(weaponSplit[1]);
        }
    }

    public void addArmor(String armor) {
        String[] armorSplit = armor.split(" ");
        if (armorSplit[0].equals("none")) {
            this.armor = "none";
        } else {
            this.armor = armor;
            this.defense += Integer.parseInt(armorSplit[1]);
        }
    }
    
    public void addHelm(String helm) {
        String[] split = helm.split(" ");
        if (split[0].equals("none")) {
            this.helm = "none";
        } else {
            this.helm = helm;
            this.maxHP += Integer.parseInt(split[1]) * 10;
        }
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
        this.setHp(this.getMaxHP());
        this.setAttack(this.getAttack() + 1);
        this.setDefense(this.getDefense() + 1);
    }

    public void addExperience(int experience) {
        this.setExperience(this.getExperience() + experience);
        if (this.getExperience() >= (this.level * 1000) + ((this.level - 1) * (this.level - 1)) * 450)
            this.levelUp();
    }

    public void addArtefact(Artefact artefact) {
        if (artefact.getType().equals("Weapon")) {
            if (this.weapon.equals("none"))
                this.addWeapon(artefact.getName());
            else
                this.changeWeapon(artefact.getName());
        } else if (artefact.getType().equals("Armor")) {
            if (this.armor.equals("none"))
                this.addArmor(artefact.getName());
            else
                this.changeArmor(artefact.getName());
        } else if (artefact.getType().equals("Helmet")) {
            if (this.helm.equals("none"))
                this.addHelm(artefact.getName());
            else
                this.changeHelm(artefact.getName());
        }
    }

    public void changeWeapon(String weapon) {
        this.attack -= Integer.parseInt(this.weapon.split(" ")[1]);
        this.addWeapon(weapon);
    }

    public void changeArmor(String armor) {
        this.defense -= Integer.parseInt(this.armor.split(" ")[1]);
        this.addArmor(armor);
    }

    public void changeHelm(String helm) {
        this.maxHP -= Integer.parseInt(this.helm.split(" ")[1]) * 10;
        this.addHelm(helm);
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

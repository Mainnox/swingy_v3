package fr.mainox.swingy.view;

import fr.mainox.swingy.model.Creature;
import fr.mainox.swingy.model.Heroe;

public class ViewGame {

    public ViewGame(Heroe heroe) {
        printHeroInfo(heroe);
    }

    public void printMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public void printHeroInfo(Heroe heroe) {
        System.out.println("Your hero is " + heroe.getName() + " the " + heroe.getSpe() + ".");
        System.out.println("Your hero is lost in the dungeon.\nBeware you're not alone here.\nYou can rest but beware of the monsters.\nYou can go to the left, right, up or down to find the exit of this madness.");
    }

    public void startFight() {
        System.out.println("You have encountered a monster.\nYou can fight or run.");
    }

    public void getHit(int damage) {
        System.out.println("You have been hit by the monster.\nYou lost " + damage + " HP.");
    }

    public void printAttack(Creature creature, int damage) {
        System.out.println(creature.getName() + " attacks and deals " + damage + " damage.");
    }

    public void printWin(int experience) {
        System.out.println("You have won the fight.\nYou gained " + experience + " experience.");
    }

    public void printLose() {
        System.out.println("You have lost the fight.");
    }

    public void levelUp(int level) {
        System.out.println("You have reached level " + level + ".");
    }

    public void printSelectAction() {
        System.out.println("Select an action:\nw - Move up\na - Move left\ns - Move down\nd - Move right\nq - Quit");
    }

    public void printCreatureInfo(Creature creature) {
        System.out.println("You have encountered a " + creature.getName() + ".");
    }

    public void printCreatureDead(Creature creature) {
        System.out.println("You have killed the " + creature.getName() + ".");
    }

    public void printHeroDead() {
        System.out.println("You got killed your hero.\nYou lost the game.");
    }

    public void printFight(Heroe heroe, Creature creature) {
        System.out.print("You have " + heroe.getHp() + " HP.\nThe " + creature.getName() + " has " + creature.getHp() + " HP.\n");
        System.out.print("Select an action:\na - Attack\nq - Quit\n");
    }

    public void printEmbush(Creature creature) {
        System.out.println("You have been embushed by the " + creature.getName() + ".");
    }

    public void printRest(Heroe heroe) {
        System.out.println("You have rested.\nYou have " + heroe.getMaxHP() + " HP.");
    }

    public void printWinGame() {
        System.out.println("Congratulation you survived the dungeon.\nThe road was long but you have found the exit.");
    }

    public void printHeroePos(Heroe heroe) {
        System.out.println("Your hero is at position " + heroe.getX() + "," + heroe.getY() + ".");
    }

}

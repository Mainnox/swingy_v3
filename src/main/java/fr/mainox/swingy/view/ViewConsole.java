package fr.mainox.swingy.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import fr.mainox.swingy.model.Heroe;

public class ViewConsole implements ViewSelection {
    
    public ViewConsole() {
        System.out.println("Welcome to Swingy v1.0.0");
    }

    public void printListHeroes(List<Heroe> heroes) {
        System.out.println("List of heroes:");
        for (Heroe heroe : heroes) {
            System.out.println("- " + heroe.getId() + ": " + heroe.getName());
        }
    }

    public void printSelectionHeroes(List<Heroe> heroes) {
        System.out.println("Select a heroe:");
        printListHeroes(heroes);
        System.out.println("- exit: Back");
        System.out.println("- new: Create a new heroe");
    }

    public void printSelectedHeroe(Heroe heroe) {
        System.out.println("Selected heroe:");
        System.out.println("- name: " + heroe.getName() + "\n- hp: " + heroe.getHp() + "\n- attack: " + heroe.getAttack() + "\n- defense: " + heroe.getDefense() + "\n- level: " + heroe.getLevel() + "\n- xp: " + heroe.getExperience() + "\n- specialisation: " + heroe.getSpe());
        System.out.println("- start: Start the game");
        System.out.println("- delete: Delete the heroe");
        System.out.println("- exit: Back");
    }

    public Heroe createHeroe() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter your heroe name:");
            String name = br.readLine();
            System.out.println("Enter your heroe hp:");
            int hp = Integer.parseInt(br.readLine());
            System.out.println("Enter your heroe attack:");
            int attack = Integer.parseInt(br.readLine());
            System.out.println("Enter your heroe defense:");
            int defense = Integer.parseInt(br.readLine());
            System.out.println("Enter your heroe's specialisation");
            String specialisation = br.readLine();
            return new Heroe(name, hp, attack, defense, 1, 0,  specialisation);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void printError(String error) {
        System.out.println(error);
    }

}

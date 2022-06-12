package fr.mainox.swingy.view;

import java.util.List;

import fr.mainox.swingy.model.Heroe;

public class ViewConsole {
    
    private List<Heroe> heroeList;

    public ViewConsole(List<Heroe> heroeList) {
        this.heroeList = heroeList;
    }

    public void printHeroList() {
        for (int i = 0; i < heroeList.size(); i++) {
            System.out.println(i + 1 + ") " + heroeList.get(i).getName());
        }
    }

    public void printSelectHeroe() {
        System.out.println("Select your heroe for more information: ");
        printHeroList();
        System.out.println("or enter \"0\" to create a new one.\nEnter \"-1\" to exit.");
    }

    public void printHeroeInfo(int index) {
        System.out.println(heroeList.get(index).getInfo());
    }

    public void setHeroeList(List<Heroe> heroeList) {
        this.heroeList = heroeList;
    }

}

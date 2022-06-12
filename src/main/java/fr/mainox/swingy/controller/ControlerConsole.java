package fr.mainox.swingy.controller;

import fr.mainox.swingy.model.ModelConsole;
import fr.mainox.swingy.model.ModelGame;
import fr.mainox.swingy.view.ViewConsole;
import fr.mainox.swingy.view.ViewGame;

public class ControlerConsole {
    
    private ModelConsole modelConsole;
    private ViewConsole viewConsole;

    public ControlerConsole() {
        this.modelConsole = new ModelConsole();
        this.viewConsole = new ViewConsole(modelConsole.getHeroList());
        while (true) {
            this.viewConsole.printSelectHeroe();
            try {
                int heroeId = Integer.parseInt(System.console().readLine());
                if (heroeId == 0) {
                    this.modelConsole.createHeroe();
                    this.viewConsole.setHeroeList(modelConsole.getHeroList());
                } else if (heroeId > 0 && heroeId <= modelConsole.getHeroList().size()) {
                    this.viewConsole.printHeroeInfo(heroeId - 1);
                    System.out.println("You want to play with this heroe? (y/n)");
                    String answer = System.console().readLine();
                    if (answer.equals("y")) {
                        ControlerGame controlerGame = new ControlerGame(new ModelGame(modelConsole.getHeroList().get(heroeId - 1)), new ViewGame(modelConsole.getHeroList().get(heroeId - 1)));
                        controlerGame.startGame();
                    }
                }
                if (heroeId == -1)
                    break;
            } catch (Exception e) {
                System.out.println("Please enter a valid number");
            }
        }
    }
}

package fr.mainox.swingy.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import fr.mainox.swingy.model.ModelConsole;
import fr.mainox.swingy.model.ModelGame;
import fr.mainox.swingy.view.ViewConsole;
import fr.mainox.swingy.view.ViewGame;
import fr.mainox.swingy.view.ViewSelection;
import fr.mainox.swingy.model.Heroe;

public class ControlerConsole {
    
    private ModelConsole model;
    private ViewSelection view;

    public ControlerConsole() {
        try {
            this.model = new ModelConsole();
            this.view = new ViewConsole();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectHeroe(int id) {
        Heroe heroe = model.getHeroe(id);
        if (heroe == null) {
            view.printError("No heroe with id " + id);
            return ;
        }
        view.printSelectedHeroe(heroe);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String command = br.readLine();
                if (command.equals("start")) {
                    ControlerGame controlerGame = new ControlerGame(new ModelGame(heroe), new ViewGame(heroe));
                    Heroe heroeGame = controlerGame.startGame();
                    if (heroeGame != null)
                        model.updateHeroe(heroeGame);
                    break;
                } else if (command.equals("delete")) {
                    model.deleteHeroe(heroe.getId());
                    break;
                } else if (command.equals("exit")) {
                    break;
                } else {
                    view.printError("Unknown command");
                }
            }
        } catch (Exception e) {
            view.printError(e.getMessage());
        }
    }

    public void runConsole() {
        while (true) {
            this.view.printSelectionHeroes(this.model.getHeroeList());
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input = br.readLine();
                if (input.equals("exit"))
                    break;
                else if (input.equals("new")) {
                    Heroe heroe = view.createHeroe();
                    if (heroe != null)
                        model.addHeroe(heroe);
                }
                else if (input.matches("\\d+"))
                    this.selectHeroe(Integer.parseInt(input));
                else
                    view.printError("Invalid Input");
            } catch (Exception e) {
                view.printError(e.getMessage());
            }
        }
    }

}

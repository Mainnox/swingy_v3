package fr.mainox.swingy.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import fr.mainox.swingy.model.ModelConsole;
import fr.mainox.swingy.model.ModelGame;
import fr.mainox.swingy.view.ViewConsole;
import fr.mainox.swingy.view.ViewGame;
import fr.mainox.swingy.view.ViewSelection;
import fr.mainox.swingy.App;
import fr.mainox.swingy.model.Creature;
import fr.mainox.swingy.model.Heroe;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ControlerConsole {
    
    private ModelConsole model;
    private ViewSelection view;
    private Validator validator;
    
    public ControlerConsole() {
        try {
            this.model = new ModelConsole();
            this.view = new ViewConsole();
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();
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
            e.printStackTrace();
        }
    }
    
    public List<String> validate(Heroe heroe) {
        Set<ConstraintViolation<Creature>> cvs = validator.validate(heroe);
        List<String> messages = new ArrayList<String>();
        
        for (ConstraintViolation<Creature> cv : cvs) {
            messages.add(cv.getPropertyPath() + ": " + cv.getMessage() + "\n");
        }
        return messages;
    }

    public void runConsole() {
        int ret = 0;

        while (true) {
            this.view.printSelectionHeroes(this.model.getHeroeList());
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String input = br.readLine();
                if (input.equals("exit"))
                    break;
                else if (input.equals("new")) {
                    Heroe heroe = view.createHeroe();
                    if (heroe != null) {
                        List<String> messages = validate(heroe);
                        if (messages.isEmpty()) {
                            model.addHeroe(heroe);
                        } else {
                            view.printError(messages.toString());
                        }
                    }
                }
                else if (input.matches("switch")) {
                    ret = 1;
                    break;
                }
                else if (input.matches("\\d+"))
                    this.selectHeroe(Integer.parseInt(input));
                else
                    view.printError("Invalid Input");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ret == 1) {
            String[] args = new String[1];
            args[0] = "gui";
            App.selectMode(args);
        }
    }

}

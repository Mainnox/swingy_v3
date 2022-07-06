package fr.mainox.swingy.controller;

import fr.mainox.swingy.model.Artefact;
import fr.mainox.swingy.model.Creature;
import fr.mainox.swingy.model.Heroe;
import fr.mainox.swingy.model.ModelGame;
import fr.mainox.swingy.view.ViewGame;

public class ControlerGame {
    
    private ModelGame modelGame;
    private ViewGame viewGame;

    public ControlerGame(ModelGame modelGame, ViewGame viewGame) {
        this.modelGame = modelGame;
        this.viewGame = viewGame;
    }

    public Heroe startGame() {
        Creature creature;
        viewGame.printMap(modelGame.getMap());
        viewGame.printHeroInfo(modelGame.getHeroe());
        while (true) {
            creature = null;
            System.out.println("Where do you want to go?");
            viewGame.printSelectAction();
            viewGame.printHeroePos(modelGame.getHeroe());
            System.out.println(modelGame.getHeroe().getInfo());
            String action = System.console().readLine();
            if (action.equals("w")) {
                if (modelGame.getHeroe().getX() == 0) {
                    creature = modelGame.getHeroe();
                } else
                    creature = modelGame.moveHero(0);
            } else if (action.equals("a")) {
                if (modelGame.getHeroe().getY() == 0){
                    creature = modelGame.getHeroe();
                } else
                    creature = modelGame.moveHero(1);
            } else if (action.equals("s")) {
                if (modelGame.getHeroe().getX() == modelGame.getMapSize()){
                    creature = modelGame.getHeroe();
                } else
                    creature = modelGame.moveHero(2);
            } else if (action.equals("d")) {
                if (modelGame.getHeroe().getY() == modelGame.getMapSize()){
                    creature = modelGame.getHeroe();
                } else
                    creature = modelGame.moveHero(3);
            } else if (action.equals("r")) {
                viewGame.printRest(modelGame.getHeroe());
                modelGame.rest();
            } else if (action.equals("q"))
                break;
            if (creature == modelGame.getHeroe()) {
                viewGame.printWinGame();
                modelGame.winGame();
                return modelGame.getHeroe();
            }
            if (creature != null)
                startFight(modelGame.getHeroe(), creature);
            creature = modelGame.moveCreature();
            if (creature != null)
            {
                viewGame.printEmbush(creature);
                viewGame.printAttack(creature,
                    creature.getAttack() - modelGame.getHeroe().getDefense());
                creature.attack(modelGame.getHeroe());
                startFight(modelGame.getHeroe(), creature);
            }
            viewGame.printMap(modelGame.getMap());
        }
        return null;
    }

    public void startFight(Heroe heroe, Creature creature) {
        while (true) {
            viewGame.printFight(heroe, creature);
            if (heroe.getHp() <= 0) {
                viewGame.printHeroDead();
                break;
            }
            String action = System.console().readLine();
            if (action.equals("a"))
            {
                viewGame.printAttack(heroe, heroe.getAttack() - creature.getDefense());
                heroe.attack(creature);
            }
            else if (action.equals("q"))
                break;
            if (creature.getHp() <= 0) {
                viewGame.printCreatureDead(creature);
                modelGame.getListCreatures().remove(creature);
                Artefact artefact = modelGame.fightWin(creature);
                if (artefact != null)
                    artefactLooted(artefact);
                viewGame.printWin(100);
                break;
            }
            viewGame.printAttack(creature, creature.getAttack() - heroe.getDefense());
            creature.attack(heroe);
        }
    }

    public void artefactLooted(Artefact artefact) {
        viewGame.printArtefactLooted(artefact);
        while (true) {
            String action = System.console().readLine();
            if (action.equals("a")) {
                switch (artefact.getType()) {
                    case "Weapon":
                        if (modelGame.getHeroe().getWeapon().equals("none"))
                            modelGame.getHeroe().addWeapon(artefact.getName());
                        else
                            modelGame.getHeroe().changeWeapon(artefact.getName());
                        break;
                    case "Armor":
                        if (modelGame.getHeroe().getArmor().equals("none"))
                            modelGame.getHeroe().addArmor(artefact.getName());
                        else
                            modelGame.getHeroe().changeArmor(artefact.getName());
                        break;
                    case "Helmet":
                        if (modelGame.getHeroe().getHelm().equals("none"))
                            modelGame.getHeroe().addHelm(artefact.getName());
                        else
                            modelGame.getHeroe().changeHelm(artefact.getName());
                        break;
                    default:
                        break;
                }
                break;
            }
            else if (action.equals("q"))
                break;
        }
    }

    public void winGame() {
        modelGame.rest();
        viewGame.printWinGame();
        modelGame.winGame();
    }
}
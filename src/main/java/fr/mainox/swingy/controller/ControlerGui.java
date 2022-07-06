package fr.mainox.swingy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.mainox.swingy.App;
import fr.mainox.swingy.model.Artefact;
import fr.mainox.swingy.model.Creature;
import fr.mainox.swingy.model.Heroe;
import fr.mainox.swingy.model.ModelConsole;
import fr.mainox.swingy.model.ModelGame;
import fr.mainox.swingy.view.ViewGuiSelection;
import fr.mainox.swingy.view.MainFrame;
import fr.mainox.swingy.view.ViewGameGui;
import fr.mainox.swingy.view.ViewGuiCreateHero;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import javax.sound.sampled.*;

import java.io.File;


public class ControlerGui {
    
    private ModelConsole model;
    private ModelGame modelGame;
    private ViewGuiSelection viewSelection;
    private ViewGuiCreateHero viewCreateHero;
    private ViewGameGui viewGame;
    private MainFrame mainFrame;
    private Creature fightingCreature = null;
    private int id = -1;
    private Random random = new Random();
    private Validator validator;
    String screamFile = "src/main/resources/Scream.wav";

    public ControlerGui() {
        try {
            this.model = new ModelConsole();
            this.mainFrame = new MainFrame("Swingy");
            createSelectionGui();
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();
            mainFrame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void createSelectionGui() {
        this.viewSelection = new ViewGuiSelection(this.mainFrame);
        this.viewSelection.setEnabledSelection(false);
        this.viewSelection.setListHeroes(this.model.getHeroeList());
        this.viewSelection.addActionListener(new SelectionListener());
        this.viewSelection.addListSelectionListener(new SelectionEventListener());
        this.mainFrame.showView(this.viewSelection);
    }

    public void createHeroeGui() {
        this.viewCreateHero = new ViewGuiCreateHero(this.mainFrame);
        this.viewCreateHero.addActionListener(new CreateHeroeListener());
        this.mainFrame.showView(this.viewCreateHero);
    }

    public void setListHeroes(List<Heroe> heroeList) {
        viewSelection.setListHeroes(heroeList);
    }

    public void loadHero(int id) {
        this.viewGame = new ViewGameGui(this.mainFrame);
        this.modelGame = new ModelGame(this.model.getHeroe(id));
        this.viewGame.getHeroeInformation().setText(this.model.getHeroe(id).getInfo());
        this.viewGame.addActionListener(new GameListener());
        this.viewGame.startGame(modelGame.getHeroe());
        this.mainFrame.showView(this.viewGame);
    }

    public void deleteHero(int id) {
        try {
            this.model.deleteHeroe(id);
            this.viewSelection.setListHeroes(this.model.getHeroeList());
            this.id = -1;
            this.viewSelection.getTextPaneHeroInformation().setText("");
            this.viewSelection.setEnabledSelection(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.mainFrame, "A problem occured while deleting the heroe\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void startFight(Creature creature) {
        this.fightingCreature = creature;
        this.viewGame.setEnabledFight(true);
        this.viewGame.setEnabledMovement(false);
        playSoundEffect(screamFile, 0);
        this.viewGame.getMap().setText("");
        this.viewGame.setMap(5);
        this.viewGame.startFight(creature);
        this.viewGame.fillMessage("You have " + modelGame.getHeroe().getHp() + " hp");
        this.viewGame.fillMessage("The creature has " + creature.getHp() + " hp");
    }

    public void loseFight() {
        JOptionPane.showMessageDialog(this.mainFrame, "You lose", "Game Over", JOptionPane.ERROR_MESSAGE);
        createSelectionGui();
    }

    public void attack() {
        this.viewGame.attack(modelGame.getHeroe().getAttack() - fightingCreature.getDefense());
        modelGame.getHeroe().attack(fightingCreature);
        if (fightingCreature.getHp() <= 0) {
            this.viewGame.winFight(100);
            Artefact artefact =  this.modelGame.fightWin(fightingCreature);
            if (artefact != null) {
                int ret = this.viewGame.artefactLooted(artefact);
                if (ret == JOptionPane.YES_OPTION) {
                    this.modelGame.getHeroe().addArtefact(artefact);
                }
            }
            this.viewGame.getHeroeInformation().setText(this.model.getHeroe(id).getInfo());
            this.viewGame.setEnabledFight(false);
            this.viewGame.setEnabledMovement(true);
            this.fightingCreature = null;
        }
        else {
            fightingCreature.attack(modelGame.getHeroe());
            this.viewGame.defend(fightingCreature.getAttack() - modelGame.getHeroe().getDefense());
            if (this.modelGame.getHeroe().getHp() <= 0)
                loseFight();
            this.viewGame.fillMessage("You have " + modelGame.getHeroe().getHp() + " hp");
            this.viewGame.fillMessage("The creature has " + fightingCreature.getHp() + " hp");
        }
    }

    public void escape() {
        int escape = random.nextInt(2);

        if (escape == 1) {
            this.viewGame.escape();
            this.viewGame.setEnabledFight(false);
            this.viewGame.setEnabledMovement(true);
        }
        else {
            this.viewGame.defend(fightingCreature.getAttack() - modelGame.getHeroe().getDefense());
            if (this.modelGame.getHeroe().getHp() <= 0)
                loseFight();
            this.viewGame.fillMessage("You have " + modelGame.getHeroe().getHp() + " hp");
            this.viewGame.fillMessage("The creature has " + fightingCreature.getHp() + " hp");
        }
    }

    public void winGame() {
        JOptionPane.showMessageDialog(this.mainFrame, "You win", "Game Over", JOptionPane.ERROR_MESSAGE);
        this.modelGame.winGame();
        try {
            this.model.updateHeroe(modelGame.getHeroe());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.mainFrame, "A problem occured while updating the heroe\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(2);
        }
        createSelectionGui();
    }

    public void moveHero(int direction) {
        if ((direction == 0 && modelGame.getHeroe().getX() == 0)
            || (direction == 1 && modelGame.getHeroe().getY() == 0)
            || (direction == 2 && modelGame.getHeroe().getX() == modelGame.getMapSize())
            || (direction == 3 && modelGame.getHeroe().getY() == modelGame.getMapSize())) {
            winGame();
            return ;
        }
        Creature creature = this.modelGame.moveHero(direction);
        if (creature != null)
            startFight(creature);
        this.viewGame.getMap().setText("");
        this.viewGame.setMap(this.modelGame.getRandom().nextInt(this.viewGame.getImages().length - 1));
    }

    public void rest() {
        this.modelGame.rest();
        this.viewGame.fillMessage("You rested.\nYou have " + modelGame.getHeroe().getHp() + " hp.");
    }

    public void quit() {
        createSelectionGui();
    }

    public static void playSoundEffect(String soundFile, int loop) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(soundFile)));
            clip.loop(loop);
            clip.start();
            
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

    class GameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewGame.getMoveUp())
                moveHero(0);
            else if (e.getSource() == viewGame.getMoveLeft())
                moveHero(1);
            else if (e.getSource() == viewGame.getMoveDown())
                moveHero(2);
            else if (e.getSource() == viewGame.getMoveRight())
                moveHero(3);
            else if (e.getSource() == viewGame.getAttack())
                attack();
            else if (e.getSource() == viewGame.getEscape())
                escape();
            else if (e.getSource() == viewGame.getRest())
                rest();
            else if (e.getSource() == viewGame.getQuit())
                quit();
        }
    }

    public void switchMode() {
        mainFrame.dispose();
        String[] args = new String[1];
        args[0] = "console";
        App.selectMode(args);
    }

    class CreateHeroeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewCreateHero.getButtonCreateHero()) {
                try {
                    Heroe heroe = new Heroe(viewCreateHero.getTextFieldName(),
                        viewCreateHero.getSpinnerdHp(), viewCreateHero.getSpinnerdAttack(),
                        viewCreateHero.getSpinnerdDefense(), 1, 0, viewCreateHero.getTextFieldSpe());
                    List<String> error = validate(heroe);
                    if (!error.isEmpty()) {
                        JOptionPane.showMessageDialog(mainFrame, error.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                        return ;
                    }
                    model.addHeroe(heroe);
                    createSelectionGui();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(mainFrame, "A problem occured while creating the heroe\n" + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == viewCreateHero.getButtonReturnSelection()) {
                createSelectionGui();
            }
        }
    }

    class SelectionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewSelection.getButtonCreateHero()) {
                createHeroeGui();
            } else if (e.getSource() == viewSelection.getButtonLoadHero()) {
                loadHero(id);
            } else if (e.getSource() == viewSelection.getButtonDeleteHero()) {
                deleteHero(id);
            } else if (e.getSource() == viewSelection.getButtonQuit()) {
                System.exit(0);
            } else if (e.getSource() == viewSelection.getButtonSwitchMode()) {
                switchMode();
            }
        }
    }

    class SelectionEventListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getSource() == viewSelection.getListHero()) {
                    int index = viewSelection.getListHero().getSelectedIndex();
                    if (index != -1) {
                        viewSelection.getTextPaneHeroInformation().setText(model.getHeroeList().get(index).getInfo());
                        id = model.getHeroeList().get(index).getId();
                        viewSelection.setEnabledSelection(true);
                }
            }
        }
    }
}

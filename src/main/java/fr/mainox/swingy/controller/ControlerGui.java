package fr.mainox.swingy.controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.mainox.swingy.model.Creature;
import fr.mainox.swingy.model.Heroe;
import fr.mainox.swingy.model.ModelConsole;
import fr.mainox.swingy.model.ModelGame;
import fr.mainox.swingy.view.ViewGuiSelection;
import fr.mainox.swingy.view.MainFrame;
import fr.mainox.swingy.view.ViewGameGui;
import fr.mainox.swingy.view.ViewGuiCreateHero;

public class ControlerGui {
    
    private ModelConsole model;
    private ModelGame modelGame;
    private ViewGuiSelection viewSelection;
    private ViewGuiCreateHero viewCreateHero;
    private ViewGameGui viewGame;
    private MainFrame mainFrame;
    private int id = -1;

    public ControlerGui() {
        try {
            this.model = new ModelConsole();
            this.mainFrame = new MainFrame("Swingy");
            createSelectionGui();
            mainFrame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void createSelectionGui() {
        this.viewSelection = new ViewGuiSelection(this.mainFrame);
        if (this.model.getHeroeList().isEmpty()) {
            this.id = -1;
            this.viewSelection.getTextPaneHeroInformation().setText("");
        }
        else {
            this.id = model.getHeroeList().get(0).getId();
            this.viewSelection.getTextPaneHeroInformation().setText(model.getHeroeList().get(0).getInfo());
        }
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
        this.viewGame.setMap(this.modelGame.getMap());
        this.viewGame.addActionListener(new GameListener());
        this.viewGame.startGame(modelGame.getHeroe());
        this.mainFrame.showView(this.viewGame);
    }

    public void deleteHero(int id) {
        try {
            this.model.deleteHeroe(id);
            this.viewSelection.setListHeroes(this.model.getHeroeList());
            if (this.model.getHeroeList().isEmpty()) {
                this.id = -1;
                this.viewSelection.getTextPaneHeroInformation().setText("");
            }
            else {
                this.id = model.getHeroeList().get(0).getId();
                this.viewSelection.getTextPaneHeroInformation().setText(model.getHeroeList().get(0).getInfo());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.mainFrame, "A proble occured while creating the heroe\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    public void startFight(Heroe heroe, Creature creature) {
        this.viewGame.startFight(creature);
        this.viewGame.fillMessage("You have " + modelGame.getHeroe().getHp() + " hp");
        this.viewGame.fillMessage("The creature has " + creature.getHp() + " hp");
    }

    public void moveHero(int direction) {
        Creature creature = this.modelGame.moveHero(direction);
        if (creature != null)
            startFight(modelGame.getHeroe(), creature);
        this.viewGame.getMap().setText("");
        this.viewGame.setMap(this.modelGame.getMap());
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
        }
    }

    class CreateHeroeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewCreateHero.getButtonCreateHero()) {
                try {
                    Heroe heroe = new Heroe(viewCreateHero.getTextFieldName(),
                        viewCreateHero.getSpinnerdHp(), viewCreateHero.getSpinnerdAttack(),
                        viewCreateHero.getSpinnerdDefense(), 1, 0, viewCreateHero.getTextFieldSpe());
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
                }
            }
        }
    }
}

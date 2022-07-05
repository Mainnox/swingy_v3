package fr.mainox.swingy.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.mainox.swingy.model.Creature;
import fr.mainox.swingy.model.Heroe;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

public class ViewGameGui extends JPanel {
    
    private MainFrame mainFrame;
    private JTextArea map = new JTextArea();
    private JTextArea heroeInformation = new JTextArea();
    private JTextArea enemyInformation = new JTextArea();
    private JTextArea message = new JTextArea();
    private JButton moveUp = new JButton("Up");
    private JButton moveDown = new JButton("Down");
    private JButton moveLeft = new JButton("Left");
    private JButton moveRight = new JButton("Right");
    private JButton rest = new JButton("Rest");
    private JButton run = new JButton("Run");
    private JButton attack = new JButton("Attack");
    private JButton quit = new JButton("Quit");
    private JButton escape = new JButton("Escape");
    private JScrollPane scrollBar = new JScrollPane(message, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


    public ViewGameGui(MainFrame mainFrame) {
        super(new BorderLayout());
        this.mainFrame = mainFrame;
        createGui();
    }

    private GridBagConstraints addComponents(int x, int y, int width, int height, int place, int stretch) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.weightx = 100;
        constraints.weighty = 100;
        constraints.anchor = place;
        constraints.fill = stretch;
        return constraints;
    }

    public void createGui() {
        JPanel centerJPanel = new JPanel(new BorderLayout());
        JPanel southJPanel = new JPanel(new BorderLayout());
        JPanel westJPanel = new JPanel(new GridBagLayout());
        JPanel subcenterPanel = new JPanel(new BorderLayout());
        map.setEditable(false);
        heroeInformation.setEditable(false);
        enemyInformation.setEditable(false);
        message.setText("");
        message.setEditable(false);
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setMaximumSize(message.getSize());

        centerJPanel.add(map, BorderLayout.CENTER);

        westJPanel.add(rest, addComponents(0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        westJPanel.add(moveUp, addComponents(1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        westJPanel.add(quit, addComponents(2, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        westJPanel.add(moveLeft, addComponents(0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        westJPanel.add(moveDown, addComponents(1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        westJPanel.add(moveRight, addComponents(2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));

        subcenterPanel.add(scrollBar, BorderLayout.CENTER);
        subcenterPanel.add(attack, BorderLayout.NORTH);
        subcenterPanel.add(escape, BorderLayout.SOUTH);

        southJPanel.add(heroeInformation, BorderLayout.EAST);
        southJPanel.add(westJPanel, BorderLayout.WEST);
        southJPanel.add(subcenterPanel, BorderLayout.CENTER);

        this.add(centerJPanel, BorderLayout.CENTER);
        this.add(southJPanel, BorderLayout.SOUTH);
    }

    public void addActionListener(ActionListener listener) {
        moveUp.addActionListener(listener);
        moveDown.addActionListener(listener);
        moveLeft.addActionListener(listener);
        moveRight.addActionListener(listener);
        rest.addActionListener(listener);
        run.addActionListener(listener);
        attack.addActionListener(listener);
        quit.addActionListener(listener);
        escape.addActionListener(listener);
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                this.map.append(map[i][j] + " ");
            }
            this.map.append("\n");
        }
    }

    public void fillMessage(String message) {
        this.message.append(message + '\n');
    }

    public void startGame(Heroe heroe) {
        this.message.append("Welcome " + heroe.getName() + " !\nYou're lost in the dungeon of " + heroe.getLevel() + " level.\n");
    }

    public void startFight(Creature creature) {
        this.message.append("You have encountered a " + creature.getName() + " !\n");
    }

    public void winFight(int experience) {
        this.message.append("You won the fight and gained " + experience + " experience.\n");
    }

    public void attack(int damage) {
        this.message.append("You attacked the enemy and did " + damage + " damage.\n");
    }

    public void defend(int damage) {
        this.message.append("You defended and took " + damage + " damage.\n");
    }

    public void creatureInformation(Creature creature) {
        this.message.append("Name: " + creature.getName() + "Hp: " + creature.getHp());
    }

    public void setHeroeInformation(String heroeInformation) {
        this.heroeInformation.setText(heroeInformation);
    }

    public void setEnemyInformation(String enemyInformation) {
        this.enemyInformation.setText(enemyInformation);
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }

    public JButton getMoveUp() {
        return moveUp;
    }

    public JButton getMoveDown() {
        return moveDown;
    }

    public JButton getMoveLeft() {
        return moveLeft;
    }

    public JButton getMoveRight() {
        return moveRight;
    }

    public JButton getRest() {
        return rest;
    }

    public JButton getRun() {
        return run;
    }

    public JButton getAttack() {
        return attack;
    }

    public JButton getQuit() {
        return quit;
    }

    public JButton getEscape() {
        return escape;
    }

    public JTextArea getMap() {
        return map;
    }

    public JTextArea getHeroeInformation() {
        return heroeInformation;
    }

    public JTextArea getEnemyInformation() {
        return enemyInformation;
    }

    public JTextArea getMessage() {
        return message;
    }

    
}

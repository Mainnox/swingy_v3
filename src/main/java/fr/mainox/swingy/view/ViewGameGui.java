package fr.mainox.swingy.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import fr.mainox.swingy.model.Artefact;
import fr.mainox.swingy.model.Creature;
import fr.mainox.swingy.model.Heroe;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

public class ViewGameGui extends JPanel {
    
    private MainFrame mainFrame;
    private JLabel map = new  JLabel("", SwingConstants.CENTER);
    private JTextArea heroeInformation = new JTextArea();
    private JTextArea enemyInformation = new JTextArea();
    private JTextArea message = new JTextArea(6, 10);
    private JButton moveUp = new JButton("Up");
    private JButton moveDown = new JButton("Down");
    private JButton moveLeft = new JButton("Left");
    private JButton moveRight = new JButton("Right");
    private JButton rest = new JButton("Rest");
    private JButton run = new JButton("Run");
    private JButton attack = new JButton("Attack");
    private JButton quit = new JButton("Quit");
    private JButton escape = new JButton("Escape");
    private JScrollPane scrollPaneMessage = new JScrollPane(message, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
    private String[] images = {"/darkalley.jpg", "/darkness.jpg", "/darkvoute.jpg", "/redalley.jpg", "/Sanctuaire.jpg", "/Monster.jpg"};


    public ViewGameGui(MainFrame mainFrame) {
        super(new BorderLayout());
        this.mainFrame = mainFrame;
        this.setBackground(Color.BLACK);
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

        this.map.setIcon(new ImageIcon(getClass().getResource("/luminoushub.jpg")));

        heroeInformation.setEditable(false);
        heroeInformation.setBackground(new Color(0, 0, 0));
        heroeInformation.setForeground(new Color(139, 0, 0));
        enemyInformation.setEditable(false);
        message.setText("");
        message.setEditable(false);
        message.setWrapStyleWord(true);
        message.setLineWrap(true);
        message.setBackground(new Color(0, 0, 0));
        message.setForeground(new Color(139, 0, 0));

        setEnabledFight(false);

        centerJPanel.add(map, BorderLayout.CENTER);

        westJPanel.add(rest, addComponents(0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        westJPanel.add(moveUp, addComponents(1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        westJPanel.add(quit, addComponents(2, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        westJPanel.add(moveLeft, addComponents(0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        westJPanel.add(moveDown, addComponents(1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        westJPanel.add(moveRight, addComponents(2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));

        subcenterPanel.add(scrollPaneMessage, BorderLayout.CENTER);
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

    public void setMap(int image) {
        this.map.setIcon(new ImageIcon(getClass().getResource(this.images[image])));
    }

    public int artefactLooted(Artefact artefact) {
        return JOptionPane.showConfirmDialog(mainFrame, "You have looted " + artefact.getName()
            + ".\nDo you wanna equip it ?", "LOOT!", JOptionPane.YES_NO_OPTION);
    }

    public void fillMessage(String message) {
        this.message.append(message + '\n');
    }

    public void startGame(Heroe heroe) {
        this.message.append("Welcome " + heroe.getName() + " !\nYou're lost in the dungeon of " + heroe.getLevel() + " level.\n");
    }

    public void startFight(Creature creature) {
        this.map.setIcon(new ImageIcon(getClass().getResource(this.images[5])));
        this.message.append("You have encountered a " + creature.getName() + " !\n");
    }

    public void winFight(int experience) {
        this.message.append("You won the fight and gained " + experience + " experience.\n");
    }

    public void attack(int damage) {
        if (damage <= 0)
            damage = 1;
        this.message.append("You attacked the enemy and did " + damage + " damage.\n");
    }

    public void defend(int damage) {
        this.message.append("You defended and took " + damage + " damage.\n");
    }

    public void escape() {
        this.message.append("You succesfully escape the fight.\nCoward !\n");
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

    public void setEnabledFight(boolean enabled) {
        attack.setEnabled(enabled);
        escape.setEnabled(enabled);
    }

    public void setEnabledMovement(boolean enabled) {
        moveUp.setEnabled(enabled);
        moveDown.setEnabled(enabled);
        moveLeft.setEnabled(enabled);
        moveRight.setEnabled(enabled);
        rest.setEnabled(enabled);
        run.setEnabled(enabled);
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

    public JLabel getMap() {
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

    public String[] getImages() {
        return images;
    }

    
}

package fr.mainox.swingy.view;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import fr.mainox.swingy.model.Heroe;

public class ViewGuiSelection extends JPanel {
    
    private final MainFrame mainFrame;
    private JLabel labelTitle = new JLabel("Select your hero");
    private JLabel labelHeroInformation = new JLabel("Information");
    private JButton buttonCreateHero = new JButton("Create Hero");
    private JButton buttonLoadHero = new JButton("Load Hero");
    private JButton buttonDeleteHero = new JButton("Delete Hero");
    private JButton buttonQuit = new JButton("Quit the game");
    private JButton buttonSwitchMode = new JButton("Switch mode");
    private JList<String> listHero = new JList<>();
    private JTextArea textPaneHeroInformation = new JTextArea();

    public ViewGuiSelection(MainFrame mainFrame) {
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

    public void setListHeroes(List<Heroe> heroeList) {
        String[] heroeNames = new String[heroeList.size()];
        listHero.removeAll();
        for (int i = 0; i < heroeList.size(); i++) {
            heroeNames[i] = heroeList.get(i).getName();
        }
        listHero.setListData(heroeNames);
        listHero.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
    }

    private void createGui() {
        JPanel northPanel = new JPanel();
        JPanel centerPanel = new JPanel(new GridBagLayout());
        JPanel southPanel = new JPanel();

        listHero.setFixedCellWidth(100);
        listHero.setFixedCellHeight(20);
        listHero.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listHero.setBackground(Color.BLACK);
        listHero.setForeground(new Color(139, 0, 0));

        textPaneHeroInformation.setEditable(false);
        textPaneHeroInformation.setLineWrap(true);
        textPaneHeroInformation.setWrapStyleWord(true);
        textPaneHeroInformation.setText("");
        textPaneHeroInformation.setBackground(Color.BLACK);
        textPaneHeroInformation.setForeground(new Color(139, 0, 0));
        textPaneHeroInformation.setFont(new Font("Arial", Font.BOLD, 12));

        northPanel.add(labelTitle);
        northPanel.add(labelHeroInformation);

        centerPanel.add(listHero, addComponents(0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        centerPanel.add(textPaneHeroInformation, addComponents(1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));

        southPanel.add(buttonCreateHero);
        southPanel.add(buttonQuit);
        southPanel.add(buttonLoadHero);
        southPanel.add(buttonDeleteHero);
        southPanel.add(buttonSwitchMode);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    public void addListSelectionListener(ListSelectionListener listener) {
        listHero.addListSelectionListener(listener);
    }

    public void addActionListener(ActionListener listener) {
        buttonCreateHero.addActionListener(listener);
        buttonLoadHero.addActionListener(listener);
        buttonDeleteHero.addActionListener(listener);
        buttonQuit.addActionListener(listener);
        buttonSwitchMode.addActionListener(listener);
    }

    public void setEnabledSelection(boolean enabled) {
        buttonDeleteHero.setEnabled(enabled);
        buttonLoadHero.setEnabled(enabled);
    }

    public JButton getButtonSwitchMode() {
        return buttonSwitchMode;
    }

    public JList<String> getListHero() {
        return listHero;
    }

    public JButton getButtonCreateHero() {
        return buttonCreateHero;
    }

    public JButton getButtonLoadHero() {
        return buttonLoadHero;
    }

    public JButton getButtonDeleteHero() {
        return buttonDeleteHero;
    }

    public JButton getButtonQuit() {
        return buttonQuit;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public JTextArea getTextPaneHeroInformation() {
        return textPaneHeroInformation;
    }

}

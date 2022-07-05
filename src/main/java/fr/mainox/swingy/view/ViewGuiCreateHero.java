package fr.mainox.swingy.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class ViewGuiCreateHero extends JPanel{
    
    private final MainFrame mainFrame;
    private JButton buttonReturnSelection = new JButton("Return");
    private JButton buttonCreateHero = new JButton("Create Hero");
    private JLabel labelTitle = new JLabel("Create your hero");
    private JLabel labelName = new JLabel("Name");
    private JLabel labelSpe = new JLabel("Spe");
    private JLabel labelHp = new JLabel("Hp");
    private JLabel labelAttack = new JLabel("Attack");
    private JLabel labelDefense = new JLabel("Defense");
    private JTextField textFieldName = new JTextField();
    private JTextField textFieldSpe = new JTextField();
    private JSpinner spinnerdHp = new JSpinner();
    private JSpinner spinnerdAttack = new JSpinner();
    private JSpinner spinnerdDefense = new JSpinner();


    public ViewGuiCreateHero(MainFrame mainFrame) {
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

    private void createGui() {
        JPanel northPanel = new JPanel();
        JPanel centerPanel = new JPanel(new GridBagLayout());
        JPanel southPanel = new JPanel();

        northPanel.add(labelTitle);

        centerPanel.add(labelName, addComponents(0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        centerPanel.add(textFieldName, addComponents(1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        centerPanel.add(labelSpe, addComponents(0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        centerPanel.add(textFieldSpe, addComponents(1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        centerPanel.add(labelHp, addComponents(0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        centerPanel.add(spinnerdHp, addComponents(1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        centerPanel.add(labelAttack, addComponents(0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        centerPanel.add(spinnerdAttack, addComponents(1, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        centerPanel.add(labelDefense, addComponents(0, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));
        centerPanel.add(spinnerdDefense, addComponents(1, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));

        southPanel.add(buttonReturnSelection);
        southPanel.add(buttonCreateHero);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    public void addActionListener(ActionListener listener) {
        buttonReturnSelection.addActionListener(listener);
        buttonCreateHero.addActionListener(listener);
    }

    public JButton getButtonReturnSelection() {
        return buttonReturnSelection;
    }

    public JButton getButtonCreateHero() {
        return buttonCreateHero;
    }

    public String getTextFieldName() {
        return textFieldName.getText();
    }

    public String getTextFieldSpe() {
        return textFieldSpe.getText();
    }
    
    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public int getSpinnerdHp() {
        return (int) spinnerdHp.getValue();
    }

    public int getSpinnerdAttack() {
        return (int) spinnerdAttack.getValue();
    }

    public int getSpinnerdDefense() {
        return (int) spinnerdDefense.getValue();
    }

}

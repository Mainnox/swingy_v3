package fr.mainox.swingy.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    
    private JPanel contentPanel;

    public MainFrame(String title) {
        super(title);
        createGui();
    }

    private void createGui() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
        pack();
    }

    public void showView(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void showView(JPanel northPanel, JPanel southPanel, JPanel centerPanel) {
        contentPanel.removeAll();
        contentPanel.add(northPanel, BorderLayout.NORTH);
        contentPanel.add(southPanel, BorderLayout.SOUTH);
        contentPanel.add(centerPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

}

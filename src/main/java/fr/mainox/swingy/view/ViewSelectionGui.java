package fr.mainox.swingy.view;

import java.util.List;
import java.awt.event.ActionListener;

import fr.mainox.swingy.model.Heroe;

public interface ViewSelectionGui {
    
    public void createGui();
    public void addActionListener(ActionListener listener);
    public void setListHeroes(List<Heroe> heroeList);
    public void getJPanel();
}

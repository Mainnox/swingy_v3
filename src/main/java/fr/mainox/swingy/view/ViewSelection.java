package fr.mainox.swingy.view;

import java.util.List;

import fr.mainox.swingy.model.Heroe;


public interface ViewSelection {
    void printListHeroes(List<Heroe> heroes);
    void printSelectionHeroes(List<Heroe> heroes);
    void printSelectedHeroe(Heroe heroe);
    public Heroe createHeroe();
    public void printError(String error);
}

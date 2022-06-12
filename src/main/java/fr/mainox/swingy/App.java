package fr.mainox.swingy;

import fr.mainox.swingy.controller.ControlerConsole;
import fr.mainox.swingy.view.SwingyGui;

public class App 
{
    public static void selectMode(String[] args) {

        if (args.length != 1)
            System.out.println("Usage: java -jar swingy.jar <mode>");
        else {
            if (args[0].equals("console"))
                new ControlerConsole();
            else if (args[0].equals("gui"))
                new SwingyGui();
            else
                System.out.println("Usage: java -jar swingy.jar <mode>");
        }
    }
    public static void main( String[] args )
    {
        selectMode(args);
    }
}

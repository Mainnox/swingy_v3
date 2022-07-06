package fr.mainox.swingy;

import java.io.File;

import javax.sound.sampled.*;

import fr.mainox.swingy.controller.ControlerConsole;
import fr.mainox.swingy.controller.ControlerGui;

public class App 
{

    public static void selectMode(String[] args) {
        if (args.length != 1)
            System.out.println("Usage: java -jar swingy.jar <mode>");
        else {
            if (args[0].equals("console")) {
                ControlerConsole controlerConsole = new ControlerConsole();
                controlerConsole.runConsole();
            }
            else if (args[0].equals("gui")) {
                new ControlerGui();
            }
            else
                System.out.println("Usage: java -jar swingy.jar <mode>");
        }
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

    public static void main( String[] args )
    {
        playSoundEffect("src/main/resources/ambient.wav", Clip.LOOP_CONTINUOUSLY);
        selectMode(args);
    }
}

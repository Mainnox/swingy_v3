package fr.mainox.swingy.model;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModelConsole {

    private List<Heroe> heroeList;

    public List<Heroe> readHeroList() {
        try {
            heroeList = new ArrayList<Heroe>();
            File file = new File("heroes.txt");
            Scanner scanner = new Scanner(file);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine() + "\n");
            }
            scanner.close();
            System.out.println(sb.toString());
            String[] heroeListString = sb.toString().split("\n");
            for (String heroe : heroeListString) {
                String[] splitHero = heroe.split(",");
                heroeList.add(new Heroe(splitHero[0], Integer.parseInt(splitHero[1]),
                    Integer.parseInt(splitHero[2]), Integer.parseInt(splitHero[3]),
                    Integer.parseInt(splitHero[4]), Integer.parseInt(splitHero[5]),
                    splitHero[6]));
            }
            return heroeList;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(2);
            return new ArrayList<Heroe>();
        }
    }

    public void writeHeroeList() {

        try {

            FileWriter fileWriter = new FileWriter("heroes.txt");
            fileWriter.write("Mainox The True God,9999,9999,9999,99,0,god\n");
            fileWriter.write("Astaroth Big Arms,100,15,5,1,0,berserker\n");
            fileWriter.write("Unbreakable Beber,150,5,15,1,0,warrior\n");
            fileWriter.write("Annoying Gaytan,10,0,1,1,0,victime\n");
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(2);
        }

    }

    public void createHeroe() {
        try (FileWriter fileWriter = new FileWriter("heroes.txt", true)) {
            System.out.println("Enter your heroe name: ");
            String heroeName = System.console().readLine();
            System.out.println("Enter your heroe attack: ");
            int heroeAttack = Integer.parseInt(System.console().readLine());
            System.out.println("Enter your heroe defense: ");
            int heroeDefense = Integer.parseInt(System.console().readLine());
            System.out.println("Enter your heroe hp: ");
            int heroeHp = Integer.parseInt(System.console().readLine());
            System.out.println("Enter your heroe spe: ");
            String heroeSpe = System.console().readLine();
            Heroe heroe = new Heroe(heroeName, heroeHp, heroeAttack, heroeDefense,1, 0, heroeSpe);
            fileWriter.write(heroeName + "," + heroeHp + "," + heroeAttack + "," + heroeDefense + "," + 1 + "," + 0 + "," + heroeSpe + "\n");
            heroeList.add(heroe);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<Heroe> getHeroList() {
        if (this.heroeList != null) {
            return this.heroeList;
        }
        try {

            File file = new File("heroes.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName() + "\nSetting default heroe list");
                writeHeroeList();
                return (readHeroList());
            } else {
                System.out.println("File already exists.\nRecuperation of saves...");
                return (readHeroList());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(2);
            return new ArrayList<Heroe>();
        }
    }

}

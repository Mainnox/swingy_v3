package fr.mainox.swingy.model;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class ModelGame {
    
    private int[][] map;
    private List<Creature> listCreatures = new ArrayList<Creature>();
    private Heroe heroe;
    private SecureRandom random = new SecureRandom();
    private int mapSize;

    public ModelGame(Heroe heroe) {
        this.heroe = heroe;
        this.mapSize = ((heroe.getLevel() - 1) * 5) + 10;
        this.map = new int[mapSize + 1][mapSize + 1];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = 0;
            }
        }
        setupListCreature();
    }

    public int[][] getMap() {
        return map;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public Heroe getHeroe() {
        return heroe;
    }

    public void setHeroe(Heroe heroe) {
        this.heroe = heroe;
    }

    public List<Creature> getListCreatures() {
        return listCreatures;
    }

    public Creature getCreature(int id) {
        for (int i = 0; i < listCreatures.size(); i++) {
            if (i == id) {
                return listCreatures.get(i);
            }
        }
        return null;
    }

    public void setupListCreature() {
        heroe.setX(mapSize / 2);  
        heroe.setY(mapSize / 2);
        map[mapSize / 2][mapSize / 2] = 1;
        for (int i = 0; i < 5; i++) {
            while (true) {
                int x = random.nextInt(mapSize);
                int y = random.nextInt(mapSize);
                if (map[x][y] == 0) {
                    map[x][y] = 2;
                    listCreatures.add(new Creature("Mob", 10, 10, 10, x, y));
                    break ;
                }
            }
        }
    }

    public Creature getCreature(int x, int y) {
        for (int i = 0; i < listCreatures.size(); i++) {
            if (listCreatures.get(i).getX() == x && listCreatures.get(i).getY() == y) {
                return listCreatures.get(i);
            }
        }
        return null;
    }

    public Creature moveCreature() {
        for (Creature creature : listCreatures) {
            int direction = this.random.nextInt(4);
            switch (direction) {
                case 0:
                    if (creature.getX() - 1 >= 0 && map[creature.getX() - 1][creature.getY()] != 1) {
                        map[creature.getX()][creature.getY()] = 0;
                        creature.setX(creature.getX() - 1);
                        map[creature.getX()][creature.getY()] = 2;
                    } else if (creature.getX() - 1 >= 0 && map[creature.getX() - 1][creature.getY()] == 1)
                        return creature;
                    break ;
                case 1:
                    if (creature.getX() + 1 < mapSize && map[creature.getX() + 1][creature.getY()] != 1) {
                        map[creature.getX()][creature.getY()] = 0;
                        creature.setX(creature.getX() + 1);
                        map[creature.getX()][creature.getY()] = 2;
                    } else if (creature.getX() + 1 < mapSize && map[creature.getX() + 1][creature.getY()] == 1)
                        return creature;
                    break ;
                case 2:
                    if (creature.getY() - 1 >= 0 && map[creature.getX()][creature.getY() - 1] != 1) {
                        map[creature.getX()][creature.getY()] = 0;
                        creature.setY(creature.getY() - 1);
                        map[creature.getX()][creature.getY()] = 2;
                    } else if (creature.getY() - 1 >= 0 && map[creature.getX()][creature.getY() - 1] == 1)
                        return creature;
                    break ;
                case 3:
                    if (creature.getY() + 1 < mapSize && map[creature.getX()][creature.getY() + 1] != 1) {
                        map[creature.getX()][creature.getY()] = 0;
                        creature.setY(creature.getY() + 1);
                        map[creature.getX()][creature.getY()] = 2;
                    } else if (creature.getY() + 1 < mapSize && map[creature.getX()][creature.getY() + 1] == 1)
                        return creature;
                    break ;
                default:
                    break ;
            }
        }
        return null;
    }

    public Creature moveHero(int direction) {
        int x = heroe.getX();
        int y = heroe.getY();
        if (direction == 0) {
            if (map[x - 1][y] == 0) {
                map[x][y] = 0;
                map[x - 1][y] = 1;
                heroe.setX(x - 1);
                return null;
            }
            else if (map[x - 1][y] == 2) {
                return getCreature(x - 1, y);
            }
        }
        else if (direction == 1) {
            if (map[x][y - 1] == 0) {
                map[x][y] = 0;
                map[x][y - 1] = 1;
                heroe.setY(y - 1);
                return null;
            }
            else if (map[x][y - 1] == 2) {
                return getCreature(x, y - 1);
            }
        }
        else if (direction == 2) {
            if (map[x + 1][y] == 0) {
                map[x][y] = 0;
                map[x + 1][y] = 1;
                heroe.setX(x + 1);
                return null;
            }
            else if (map[x + 1][y] == 2) {
                return getCreature(x + 1, y);
            }
        }
        else if (direction == 3) {
            if (map[x][y + 1] == 0) {
                map[x][y] = 0;
                map[x][y + 1] = 1;
                heroe.setY(y + 1);
                return null;
            }
            else if (map[x][y + 1] == 2) {
                return getCreature(x, y + 1);
            }
        }
        return null;
    }

    public void fightWin(Creature creature) {
        map[creature.getX()][creature.getY()] = 0;
        listCreatures.remove(creature);
        heroe.addExperience(100);
    }

    public void rest() {
        heroe.setHp(heroe.getMaxHP());
    }

    public void winGame() {
        rest();
        heroe.addExperience(1000);
    }
}

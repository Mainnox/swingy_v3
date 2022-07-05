package fr.mainox.swingy.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModelConsole {
    
    private Connection connectionHeroe = null;
    private List<Heroe> heroeList = new ArrayList<Heroe>();

    public ModelConsole() throws SQLException {
        connectionHeroe = DriverManager.getConnection("jdbc:sqlite:./Heroe.db");
        try (Statement statement = connectionHeroe.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Heroe (\n" +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    name TEXT NOT NULL,\n" +
                    "    hp INTEGER NOT NULL,\n" +
                    "    attack INTEGER NOT NULL,\n" +
                    "    defense INTEGER NOT NULL,\n" +
                    "    level INTEGER NOT NULL,\n" +
                    "    experience INTEGER NOT NULL,\n" +
                    "    spe TEXT NOT NULL,\n" +
                    "    weapon TEXT NOT NULL,\n" +
                    "    armor TEXT NOT NULL,\n" +
                    "    helm TEXT NOT NULL\n" +
                    ");");
            readHeroe();
        }
    }

    public void readHeroe() throws SQLException {
        try (Statement statement = connectionHeroe.createStatement()) {
            heroeList.clear();
            ResultSet rs = statement.executeQuery("SELECT * FROM Heroe");
            while (rs.next()) {
                Heroe heroe = new Heroe(rs.getString("name"), rs.getInt("hp"), rs.getInt("attack"), rs.getInt("defense"),
                        rs.getInt("level"), rs.getInt("experience"), rs.getString("spe"));
                heroe.setId(rs.getInt("id"));
                heroe.setArmor(rs.getString("armor"));
                heroe.setWeapon(rs.getString("weapon"));
                heroe.setHelm(rs.getString("helm"));
                heroeList.add(heroe);
            }
        }
    }

    public void addHeroe(Heroe heroe) throws SQLException {
        try (PreparedStatement statement = connectionHeroe.prepareStatement("INSERT INTO Heroe (name, hp, attack, defense, level, experience, spe, weapon, armor, helm) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, heroe.getName());
            statement.setInt(2, heroe.getHp());
            statement.setInt(3, heroe.getAttack());
            statement.setInt(4, heroe.getDefense());
            statement.setInt(5, heroe.getLevel());
            statement.setInt(6, heroe.getExperience());
            statement.setString(7, heroe.getSpe());
            statement.setString(8, heroe.getWeapon());
            statement.setString(9, heroe.getArmor());
            statement.setString(10, heroe.getHelm());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                heroe.setId(rs.getInt(1));
            }
            readHeroe();
        }
    }

    public Heroe getHeroe(int id) {
        for (Heroe heroe : heroeList) {
            if (heroe.getId() == id) {
                return heroe;
            }
        }
        return null;
    }

    public void deleteHeroe(int id) throws SQLException {
        try (PreparedStatement statement = connectionHeroe.prepareStatement("DELETE FROM Heroe WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            readHeroe();
        }
    }

    public void updateHeroe(Heroe heroe) throws SQLException {
        try (PreparedStatement statement = connectionHeroe.prepareStatement("UPDATE Heroe SET name = ?, hp = ?, attack = ?, defense = ?, level = ?, experience = ?, spe = ?, weapon = ?, armor = ?, helm = ? WHERE id = ?")) {
            statement.setString(1, heroe.getName());
            statement.setInt(2, heroe.getHp());
            statement.setInt(3, heroe.getAttack());
            statement.setInt(4, heroe.getDefense());
            statement.setInt(5, heroe.getLevel());
            statement.setInt(6, heroe.getExperience());
            statement.setString(7, heroe.getSpe());
            statement.setString(8, heroe.getWeapon());
            statement.setString(9, heroe.getArmor());
            statement.setString(10, heroe.getHelm());
            statement.setInt(11, heroe.getId());
            statement.executeUpdate();
            readHeroe();
        }
    }

    public List<Heroe> getHeroeList() {
        return heroeList;
    }
}

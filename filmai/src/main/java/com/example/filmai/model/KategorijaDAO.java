package com.example.filmai.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KategorijaDAO {

    public static void createKategorija(Kategorija kategorija){
    String jdbcUrl = "jdbc:mysql://localhost:3306/db";
    String querry = "INSERT INTO `kategorija`(kategorija) VALUES (?)";
        try {
        Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");

        PreparedStatement preparedStatement = connection.prepareStatement(querry);
        preparedStatement.setString(1, kategorija.getKategorija());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    } catch (
    SQLException e) {
        e.printStackTrace();
    }
    }
    public static void deleteByName(String name) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String delete = "DELETE FROM kategorija WHERE kategorija LIKE%"+name+"%";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");

            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            System.out.println("Pavyko ištrinti įrašą");
        } catch (SQLException e) {
            e.printStackTrace();

            System.out.println("Įrašo ištrinti nepavyko");
        }
    }

    public static void updateKategorija(Kategorija kategorija){
        String jdbcUrl = "jdbc:mysql://localhost:3306/db";
        String update = "UPDATE `kategorija` SET `kategorija`= ? WHERE `id` = ?";
        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, kategorija.getKategorija());
            preparedStatement.setInt(2, kategorija.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            System.out.println("Pavyko atnaujinti įrašą");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Nepavyko atnaujinti įrašą");
        }
    }

}


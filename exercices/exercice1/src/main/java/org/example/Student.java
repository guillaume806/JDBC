package org.example;

import java.sql.*;
import java.util.Scanner;

public class Student {

    private String nom;

    private String prenom;

    private int classe;

    private String dateDiplome;


    public static void addEtudiant(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Nom : ");
        String nom = scanner.next();
        System.out.print("Prénom : ");
        String prenom = scanner.next();
        System.out.print("Numéro de classe : ");
        int classe = Integer.parseInt(scanner.next());
        System.out.print("Date de diplôme (AAAA-MM-JJ) : ");
        String dateDiplome = scanner.next();

        String query = "INSERT INTO etudiant (first_name, last_name, number_class, diplome_date) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setInt(3, classe);
            preparedStatement.setDate(4, Date.valueOf(dateDiplome));

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Étudiant ajouté avec succès.");
            } else {
                System.out.println("Erreur lors de l'ajout de l'étudiant.");
            }
        }
    }

    public static void listAllEtudiants(Connection connection) throws SQLException {
        String query = "SELECT * FROM etudiant";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("first_name");
                String prenom = resultSet.getString("last_name");
                String classe = resultSet.getString("number_class");
                Date dateDiplome = resultSet.getDate("diplome_date");

                System.out.println("ID : " + id);
                System.out.println("Nom : " + nom);
                System.out.println("Prénom : " + prenom);
                System.out.println("Classe : " + classe);
                System.out.println("Date de diplôme : " + dateDiplome);
                System.out.println();
            }
        }
    }

    public static void listEtudiantsByClasse(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Numéro de classe : ");
        int classe = Integer.parseInt(scanner.next());

        String query = "SELECT * FROM etudiant WHERE number_class = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, classe);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("first_name");
                    String prenom = resultSet.getString("last_name");
                    Date dateDiplome = resultSet.getDate("diplome_date");

                    System.out.println("ID : " + id);
                    System.out.println("Nom : " + nom);
                    System.out.println("Prénom : " + prenom);
                    System.out.println("Date de diplôme : " + dateDiplome);
                    System.out.println();
                }
            }
        }
    }

    public static void deleteEtudiant(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("ID de l'étudiant à supprimer : ");
        int id = scanner.nextInt();

        String query = "DELETE FROM etudiant WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Étudiant supprimé avec succès.");
            } else {
                System.out.println("Aucun étudiant trouvé avec cet ID.");
            }
        }
    }
}

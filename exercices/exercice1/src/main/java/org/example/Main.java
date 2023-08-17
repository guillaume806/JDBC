package org.example;



import org.example.utlis.DatabaseManager;

import java.sql.*;

import java.util.Scanner;

import static org.example.Student.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {


        try {
            Connection connection = DatabaseManager.getPostgreSQLConnection();
            while (true) {
                System.out.println("Menu:");
                System.out.println("1. Ajouter un étudiant");
                System.out.println("2. Afficher tous les étudiants");
                System.out.println("3. Afficher les étudiants d'une classe");
                System.out.println("4. Supprimer un étudiant");
                System.out.println("5. Quitter");
                System.out.print("Choix : ");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addEtudiant(connection, scanner);
                        break;
                    case 2:
                        listAllEtudiants(connection);
                        break;
                    case 3:
                        listEtudiantsByClasse(connection, scanner);
                        break;
                    case 4:
                        deleteEtudiant(connection, scanner);
                        break;
                    case 5:
                        System.out.println("Au revoir !");
                        return;
                    default:
                        System.out.println("Choix invalide.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
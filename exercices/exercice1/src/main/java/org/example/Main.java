package org.example;

import org.example.utlis.DatabaseManager;

import java.sql.*;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        try {
            connection = DatabaseManager.getPostgreSQLConnection();
            System.out.println("Merci de saisir le prénom :");
            String firstname = scanner.nextLine();
            System.out.println("Merci de saisir le nom :");
            String lastname = scanner.nextLine();
            System.out.println("Merci de saisir le numero de classe :");
            String classnumber = scanner.nextLine();
            System.out.println("Merci de saisir la date de diplome :");
            String diplomedate = scanner.nextLine();

            String request = "INSERT INTO person (firstname, lastname, classnumber, diplomedate) VALUES ( ? , ? , ? , ?)";



            // Facon 2 => avec une requête préparée

            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1,firstname);
            preparedStatement.setString(2,lastname);
            preparedStatement.setString(2,classnumber);
            preparedStatement.setString(2,diplomedate);
            //preparedStatement.execute(); // retour boolean
            int nbRows = preparedStatement.executeUpdate();
            System.out.println("Nombre ligne "+nbRows);


            // version avec recuperation de l'id auto genere

            /*
            PreparedStatement preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,firstname);
            preparedStatement.setString(2,lastname);
            int nbRows = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            System.out.println("Nombre ligne "+nbRows);
            if(resultSet.next()){
                System.out.println(resultSet.getInt(1));
            }

             */

            // facon 3 => avec une requete de lecture
            String request = "SELECT * FROM person";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            while(resultSet.next()){
                System.out.println(resultSet.getInt("id")+ " , "+ resultSet.getString("first_name")+
                        " , "+ resultSet.getString("last_name"));
            }



        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if(connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        }
    }
}
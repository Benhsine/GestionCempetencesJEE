package school.esi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/gestioncompetencesesi";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static boolean inscrireUtilisateur(User utilisateur) {
        boolean inscriptionReussie = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Établir la connexion à la base de données
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Préparer la requête SQL pour insérer l'utilisateur dans la base de données
            String query = "INSERT INTO Utilisateurs (nom, email, mot_de_passe) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getEmail());
            preparedStatement.setString(3, utilisateur.getMotDePasse());

            // Exécuter la requête
            int rowsInserted = preparedStatement.executeUpdate();

            // Vérifier si l'insertion a réussi
            if (rowsInserted > 0) {
                inscriptionReussie = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer les ressources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return inscriptionReussie;
    }
    public static User connexionUtilisateur(String email, String motDePasse) {
        User utilisateur = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Établir la connexion à la base de données
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Préparer la requête SQL pour vérifier les informations de connexion de l'utilisateur
            String query = "SELECT * FROM Utilisateurs WHERE email = ? AND mot_de_passe = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, motDePasse);

            // Exécuter la requête
            resultSet = preparedStatement.executeQuery();

            // Vérifier si l'utilisateur a été trouvé dans la base de données
            if (resultSet.next()) {
                // Créer un objet User et le remplir avec les données de l'utilisateur
                utilisateur = new User();
                utilisateur.setId(resultSet.getInt("id_utilisateur"));
                utilisateur.setNom(resultSet.getString("nom"));
                utilisateur.setEmail(email);
                utilisateur.setMotDePasse(motDePasse);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer les ressources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return utilisateur;
    }
    public static boolean modifierInformations(User utilisateur, String newName, String newPassword) {
        boolean modificationReussie = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Établir la connexion à la base de données
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Préparer la requête SQL pour mettre à jour les informations de l'utilisateur
            String query = "UPDATE Utilisateurs SET nom = ?, mot_de_passe = ? WHERE id_utilisateur = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newPassword);
            preparedStatement.setInt(3, utilisateur.getId());

            // Exécuter la requête
            int rowsUpdated = preparedStatement.executeUpdate();

            // Vérifier si la modification a réussi
            if (rowsUpdated > 0) {
                modificationReussie = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer les ressources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return modificationReussie;
    }

}

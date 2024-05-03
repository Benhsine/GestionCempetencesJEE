package school.esi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompetenceDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/gestioncompetencesesi";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public boolean ajouterCompetence(Competence competence) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO competences (nom, description, niveau_requis, domaine) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, competence.getNom());
            statement.setString(2, competence.getDescription());
            statement.setString(3, competence.getNiveauRequis());
            statement.setString(4, competence.getDomaine());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    competence.setId(id);
                }
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modifierCompetence(Competence competence) {
        try (Connection connection = getConnection()) {
            String query = "UPDATE competences SET nom=?, description=?, niveau_requis=?, domaine=? WHERE id_competence=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, competence.getNom());
            statement.setString(2, competence.getDescription());
            statement.setString(3, competence.getNiveauRequis());
            statement.setString(4, competence.getDomaine());
            statement.setInt(5, competence.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Competence getCompetenceById(int id) {
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM competences WHERE id_competence = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Competence competence = new Competence();
                competence.setId(resultSet.getInt("id_competence"));
                competence.setNom(resultSet.getString("nom"));
                competence.setDescription(resultSet.getString("description"));
                competence.setNiveauRequis(resultSet.getString("niveau_requis"));
                competence.setDomaine(resultSet.getString("domaine"));
                return competence;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean supprimerCompetence(int id) {
        try (Connection connection = getConnection()) {
            String query = "DELETE FROM competences WHERE id_competence=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Competence> getAllCompetences() {
        List<Competence> competences = new ArrayList<>();
        String sql = "SELECT * FROM competences";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Competence competence = new Competence();
                competence.setId(rs.getInt("id_competence"));
                competence.setNom(rs.getString("nom"));
                competence.setDescription(rs.getString("description"));
                competence.setNiveauRequis(rs.getString("niveau_requis"));
                competence.setDomaine(rs.getString("domaine"));
                competences.add(competence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return competences;
    }
    public List<Competence> getCompetencesFiltrees(String domaine, String niveauRequis) {
        List<Competence> competencesFiltrees = new ArrayList<>();

        try (Connection conn = getConnection()) {
            // Construction de la requête SQL en fonction des paramètres de filtrage
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM competences WHERE 1=1");

            if (domaine != null && !domaine.isEmpty()) {
                queryBuilder.append(" AND domaine = ?");
            }

            if (niveauRequis != null && !niveauRequis.isEmpty()) {
                queryBuilder.append(" AND niveau_requis = ?");
            }

            // Préparation de la requête
            PreparedStatement statement = conn.prepareStatement(queryBuilder.toString());

            // Remplissage des paramètres de la requête en fonction des valeurs de filtrage
            int paramIndex = 1;
            if (domaine != null && !domaine.isEmpty()) {
                statement.setString(paramIndex++, domaine);
            }

            if (niveauRequis != null && !niveauRequis.isEmpty()) {
                statement.setString(paramIndex++, niveauRequis);
            }

            // Exécution de la requête
            ResultSet resultSet = statement.executeQuery();

            // Traitement des résultats
            while (resultSet.next()) {
                Competence competence = new Competence();
                competence.setId(resultSet.getInt("id_competence"));
                competence.setNom(resultSet.getString("nom"));
                competence.setDescription(resultSet.getString("description"));
                competence.setNiveauRequis(resultSet.getString("niveau_requis"));
                competence.setDomaine(resultSet.getString("domaine"));
                competencesFiltrees.add(competence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return competencesFiltrees;
    }

}

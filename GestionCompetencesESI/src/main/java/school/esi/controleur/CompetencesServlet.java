package school.esi.controleur;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import school.esi.model.Competence;
import school.esi.model.CompetenceDAO;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class CompetencesServlet
 */
@WebServlet(name="ComptenceServlet", urlPatterns="*.competence" )
public class CompetencesServlet extends HttpServlet {
    private CompetenceDAO competenceDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        competenceDAO = new CompetenceDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "modifier":
                    afficherFormulaireModifierCompetence(request, response);
                    break;
                case "filtrer":
                    filtrerCompetences(request, response); // Appel de la nouvelle méthode pour filtrer les compétences
                    break;
                default:
                    afficherListeCompetences(request, response);
            }
        } else {
            afficherListeCompetences(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "ajouter":
                    ajouterCompetence(request, response);
                    break;
                case "modifier":
                    modifierCompetence(request, response);
                    break;
                case "supprimer":
                    supprimerCompetence(request, response);
                    break;
                default:
                	RequestDispatcher dispatcher = request.getRequestDispatcher("/viewCompetences.jsp");
            	    dispatcher.forward(request, response);
            }
        } else {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/viewCompetences.jsp");
    	    dispatcher.forward(request, response);
        }
    }

    private void ajouterCompetence(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String niveauRequis = request.getParameter("niveauRequis");
        String domaine = request.getParameter("domaine");

        Competence competence = new Competence(nom, description, niveauRequis, domaine);
        boolean success = competenceDAO.ajouterCompetence(competence);

	    if (success) {
	        // Récupérer la liste des compétences mise à jour depuis la base de données
	        List<Competence> competences = competenceDAO.getAllCompetences();
	        
	        // Mettre la liste des compétences dans l'attribut de requête
	        request.setAttribute("competences", competences);

	        request.setAttribute("message", "La compétence a été ajoutée avec succès.");
	    } else {
	        request.setAttribute("erreur", "Erreur lors de l'ajout de la compétence.");
	    }

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/viewCompetences.jsp");
	    dispatcher.forward(request, response);
    }
    private void afficherFormulaireModifierCompetence(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Competence competence = competenceDAO.getCompetenceById(id);
        request.setAttribute("competence", competence);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/modifierCompetence.jsp");
        dispatcher.forward(request, response);
    }

    private void modifierCompetence(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String niveauRequis = request.getParameter("niveauRequis");
        String domaine = request.getParameter("domaine");

        // Retrieve the existing competence by its ID
        Competence existingCompetence = competenceDAO.getCompetenceById(id);
        
        // Check if the competence exists
        if (existingCompetence != null) {
            // Update the attributes of the existing competence
            existingCompetence.setNom(nom);
            existingCompetence.setDescription(description);
            existingCompetence.setNiveauRequis(niveauRequis);
            existingCompetence.setDomaine(domaine);
            
            // Attempt to modify the competence in the database
            boolean success = competenceDAO.modifierCompetence(existingCompetence);

            if (success) {
                // If modification is successful, retrieve the updated list of competences
                List<Competence> competences = competenceDAO.getAllCompetences();
                request.setAttribute("competences", competences);
                request.setAttribute("message", "La compétence a été modifiée avec succès.");
            } else {
                request.setAttribute("erreur", "Erreur lors de la modification de la compétence.");
            }
        } else {
            // If the competence does not exist, set an error message
            request.setAttribute("erreur", "La compétence à modifier n'existe pas.");
        }
        
        // Forward the request to the viewCompetences.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewCompetences.jsp");
        dispatcher.forward(request, response);
    }


    private void supprimerCompetence(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        boolean success = competenceDAO.supprimerCompetence(id);

        if (success) {
            // Récupérer la liste des compétences mise à jour depuis la base de données
            List<Competence> competences = competenceDAO.getAllCompetences();
            
            // Mettre la liste des compétences dans l'attribut de requête
            request.setAttribute("competences", competences);

            request.setAttribute("message", "La compétence a été supprimée avec succès.");
        } else {
            request.setAttribute("erreur", "Erreur lors de la suppression de la compétence.");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewCompetences.jsp");
        dispatcher.forward(request, response);
    }

    private void afficherListeCompetences(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Competence> competences = competenceDAO.getAllCompetences();
        request.setAttribute("competences", competences);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewCompetences.jsp");
        dispatcher.forward(request, response);
    }
    private void filtrerCompetences(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres de filtrage depuis le formulaire
        String domaine = request.getParameter("domaine");
        String niveauRequis = request.getParameter("niveauRequis");
        
        // Appeler la méthode de la DAO pour récupérer les compétences filtrées
        List<Competence> competencesFiltrees = competenceDAO.getCompetencesFiltrees(domaine, niveauRequis);
        
        // Mettre les compétences filtrées dans l'attribut de la requête
        request.setAttribute("competences", competencesFiltrees);
        
        // Rediriger vers la page d'affichage des compétences avec les résultats filtrés
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewCompetences.jsp");
        dispatcher.forward(request, response);
    }

    
}

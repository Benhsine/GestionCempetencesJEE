package school.esi.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import school.esi.model.User;
import school.esi.model.UserDAO;

import java.io.IOException;

@WebServlet(name="InscriptionServlet", urlPatterns="*.inscription") // Modification du nom de la servlet pour éviter les conflits
public class UserInscriptionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("mot_de_passe");
        
        // Créer un objet User avec les données du formulaire
        User utilisateur = new User();
        utilisateur.setNom(nom);
        utilisateur.setEmail(email);
        utilisateur.setMotDePasse(motDePasse);
        
        // Appeler la méthode inscrireUtilisateur de la classe UserInscription
        boolean inscriptionReussie = UserDAO.inscrireUtilisateur(utilisateur);
        
        // Rediriger vers la page de connexion avec un message de succès
        if (inscriptionReussie) {
            response.sendRedirect("index.jsp?inscriptionSuccess=true");
        } else {
            response.sendRedirect("Inscription.jsp?inscriptionSuccess=false");
        }
    }
}
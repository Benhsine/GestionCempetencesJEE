package school.esi.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import school.esi.model.User;
import school.esi.model.UserDAO;

@WebServlet(name="ConnexionServlet", urlPatterns="*.connexion" )
public class UserConnexionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres de la requête
        String email = request.getParameter("username");
        String motDePasse = request.getParameter("password");

        // Vérifier si l'utilisateur existe dans la base de données
        User utilisateur1 = UserDAO.connexionUtilisateur(email, motDePasse);

        // Redirection vers la page appropriée en fonction du résultat de la connexion
        if (utilisateur1 != null) {
            // L'utilisateur est connecté avec succès
        	// Stocker l'utilisateur dans la session
        	request.getSession().setAttribute("message", "connexion avec succès.");
            request.getSession().setAttribute("user", utilisateur1);
        	request.getRequestDispatcher("login.jsp").forward(request, response);
        	

        } else {
            response.sendRedirect("index.jsp?connexion=false");
        }
    }
}

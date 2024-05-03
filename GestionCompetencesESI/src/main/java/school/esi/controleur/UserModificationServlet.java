package school.esi.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import school.esi.model.User;
import school.esi.model.UserDAO;

import java.io.IOException;

@WebServlet(name="ModificationServlet", urlPatterns="*.modification" )
public class UserModificationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les nouveaux détails de l'utilisateur
        String newName = request.getParameter("newName");
        String newPassword = request.getParameter("newPassword");

        // Récupérer l'utilisateur connecté depuis la session
        User utilisateur = (User) request.getSession().getAttribute("user");

        // Mettre à jour les détails de l'utilisateur dans la base de données
        boolean modificationReussie = UserDAO.modifierInformations(utilisateur, newName, newPassword);

        if (modificationReussie) {
            // Rediriger vers une page de succès
            response.sendRedirect("login.jsp?modification=true");
        } else {
            // Rediriger vers une page d'échec
            response.sendRedirect("editProfile.jsp?modification=false");
        }    }
}

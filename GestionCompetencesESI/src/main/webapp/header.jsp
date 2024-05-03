<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="school.esi.model.User" %>
<!DOCTYPE html>
<html>
<head>
<title>header</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-dark fixed-top">
        <div class="container-fluid ">
            <a class="navbar-brand" href="login.jsp">
                <img src="images/logo1.png" height="50px">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="login.jsp" style="color: white; font-size: 17px;">Ajouter une compétence</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="viewCompetences.competence" style="color: white; font-size: 17px;">Voir les compétences</a>
        </li>
    </ul>
    <div class="ml-auto">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="editProfile.jsp" style="color: white; font-size: 17px;">Modifier le profil</a>
            </li>
            <li class="nav-item">
                <% User user = (User) request.getSession().getAttribute("user");
                   String username = (user != null) ? user.getNom() : "Utilisateur"; %>
                <a class="nav-link active" aria-current="page" href="index.jsp" style="color: white; font-size: 17px;">Déconnexion <%= username %> !</a>
            </li>
        </ul>
    </div>
</div>

        </div>
    </nav>
</body>
</html>
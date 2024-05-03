<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="school.esi.model.Competence" %>
<%@ page import="school.esi.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulaire de modification des compétences</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/style.css"> 
<style>
    	.header_space{
        	margin-top: 100px;
        }
</style>
</head>
<body>
<%@include file="header.jsp" %>
<form method="post" action="modifierCompetence.competence" class="header_space">
   <input type="hidden" name="action" value="modifier">
    <div class="container mt-5">
        <div class="card">
            <div class="card-header bg-primary text-white">
                Formulaire de modification des détails de la compétence
            </div>
            <div class="card-body">
                <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
                <div class="form-group">
                    <label for="skillName">Nom de la compétence :</label>
                    <input type="text" id="skillName" name="nom" class="form-control" value="<%= request.getParameter("nom") %>" required>
                </div>
                <div class="form-group">
                    <label for="skillDescription">Description :</label>
                    <textarea id="skillDescription" class="form-control" name="description" rows="3" required><%= request.getParameter("description") %></textarea>
                </div>
                <div class="form-group">
                    <label for="skillLevel">Niveau requis :</label>
                    <select id="skillLevel" name="niveauRequis" class="form-select" required>
						<%
						String niveauRequisParam = request.getParameter("niveauRequis");
						%>
						<option value="Débutant" <%= "Débutant".equals(niveauRequisParam) ? "selected" : "" %>>Débutant</option>
						<option value="Intermédiaire" <%= "Intermédiaire".equals(niveauRequisParam) ? "selected" : "" %>>Intermédiaire</option>
						<option value="Avancé" <%= "Avancé".equals(niveauRequisParam) ? "selected" : "" %>>Avancé</option>

                    </select>
                </div>
                <div class="form-group">
                    <label for="skillDomain">Domaine :</label>
                    <select id="skillDomain" name="domaine" class="form-select" required>
						<%
						String domaineParam = request.getParameter("domaine");
						%>
						<option value="Sciences de données" <%= "Sciences de données".equals(domaineParam) ? "selected" : "" %>>Sciences de données</option>
						<option value="Sécurité" <%= "Sécurité".equals(domaineParam) ? "selected" : "" %>>Sécurité</option>

                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Modifier la compétence</button>
            </div>
        </div>
    </div>
</form>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

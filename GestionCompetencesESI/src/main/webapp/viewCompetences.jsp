<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="school.esi.model.User" %>
<%@ page import="school.esi.model.Competence" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Liste des compétences</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" integrity="sha512-5A8nwdMOWrSz20fDsjczgUidUBR8liPYU+WymTZP1lmY9G6Oc7HlZv156XqnsgNUzTyMefFTcsFH/tnJE/+xBg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
    	.header_space{
        	margin-top: 100px;
        }
</style>
</head>
<body>

    <%@include file="header.jsp" %>

    <h4 class="header_space">Liste des compétences</h4>
    <div class="container">
     <form action="CompetencesServlet.competence" method="get">
    <input type="hidden" name="action" value="filtrer">
    <div class="mb-3">
        <label for="domaineSelect" class="form-label">Filtrer par domaine :</label>
        <select class="form-select" id="domaineSelect" name="domaine">
            <option value="">Tous les Domaines</option>
            <option value="Sciences de données">Sciences de données</option>
            <option value="Sécurité">Sécurité</option>
            <!-- Ajoutez ici les options pour les domaines -->
        </select>
    </div>
    <div class="mb-3">
        <label for="niveauSelect" class="form-label">Filtrer par niveau requis :</label>
        <select class="form-select" id="niveauSelect" name="niveauRequis">
            <option value="">Tous les Niveaux</option>
            <option value="Débutant">Débutant</option>
            <option value="Intermédiaire">Intermédiaire</option>
            <option value="Avancé">Avancé</option>
            <!-- Ajoutez ici les options pour les niveaux -->
        </select>
    </div>
    <div class="mb-3">
        <button type="submit" class="btn btn-primary">Filtrer</button>
    </div>
</form>

        <table class="table">
            <thead style="color: white;">
                <tr bgcolor="#120671">
                    <th scope="col">Nom</th>
                    <th scope="col">Description</th>
                    <th scope="col">Niveau requis</th>
                    <th scope="col">Domaine</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <% List<Competence> competences = (List<Competence>) request.getAttribute("competences");
                    if (competences != null) {
                        for (Competence competence : competences) {
                %>
                <tr bgcolor="#bffef4">
                    <td><%= competence.getNom() %></td>
                    <td><%= competence.getDescription() %></td>
                    <td><%= competence.getNiveauRequis() %></td>
                    <td><%= competence.getDomaine() %></td>
                    <td>
                        <div class="d-flex">
                            <form action="modifierCompetence.competence" method="get" style="display: inline;">
                                <input type="hidden" name="action" value="modifier">
                                <input type="hidden" name="id" value="<%= competence.getId() %>">
                                <input type="hidden" name="nom" value="<%= competence.getNom() %>">
                                <input type="hidden" name="description" value="<%= competence.getDescription() %>">
                                <input type="hidden" name="niveauRequis" value="<%= competence.getNiveauRequis() %>">
                                <input type="hidden" name="domaine" value="<%= competence.getDomaine() %>">
                                <button type="submit" class="btn btn-primary btn-sm me-2">
                                    <i class="fa fa-edit"></i> Éditer
                                </button>
                            </form>
                            <form action="supprimerCompetence.competence" method="post" style="display: inline;">
                                <input type="hidden" name="action" value="supprimer">
                                <input type="hidden" name="id" value="<%= competence.getId() %>">
                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette compétence?');">
                                    <i class="fa fa-trash"></i> Supprimer
                                </button>
                            </form>
                        </div>
                    </td>
                </tr>
                <% }
                    }
                %>
            </tbody>
        </table>
    </div>

    

</body>
</html>

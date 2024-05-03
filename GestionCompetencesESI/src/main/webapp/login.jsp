<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gestion competences ESI</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <style>
    	.header_space{
        	margin-top: 100px;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />
   

    <h4 class="header_space">Competences ESI</h4>
    <form method="post" action="ajouterCompetence.competence">
    <!-- Ajoutez un champ caché pour l'action -->
    <input type="hidden" name="action" value="ajouter">

    <div class="container mt-5">
        <div class="card">
            <div class="card-header bg-primary text-white">
                Formulaire de saisie des détails de la compétence
            </div>
            <div class="card-body">
                <div class="form-group">
                    <label for="skillName">Nom de la compétence :</label>
                    <input type="text" id="skillName" name="nom" class="form-control" placeholder="Entrez le nom de la compétence" required>
                </div>
                <div class="form-group">
                    <label for="skillDescription">Description :</label>
                    <textarea id="skillDescription" class="form-control" name="description" rows="3" placeholder="Entrez la description de la compétence" required></textarea>
                </div>
                <div class="form-group">
                    <label for="skillLevel">Niveau requis :</label>
                    <select id="skillLevel" name="niveauRequis" class="form-select" required>
                        <option value="">Sélectionnez le niveau requis</option>
                        <option value="Débutant">Débutant</option>
                        <option value="Intermédiaire">Intermédiaire</option>
                        <option value="Avancé">Avancé</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="skillDomain">Domaine :</label>
                    <select id="skillDomain" name="domaine" class="form-select" required>
                        <option value="">Sélectionnez le domaine</option>
                        <option value="Sciences de données">Sciences de données</option>
                        <option value="Sécurité">Sécurité</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Ajouter la compétence</button>
            </div>
        </div>
    </div>
</form>



    <% if ("true".equals(request.getParameter("modification"))) { %>
        <script>
            alert("Modification réussie !");
        </script>
    <% } %>
    

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>

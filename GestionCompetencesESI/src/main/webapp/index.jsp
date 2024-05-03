<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page de connexion</title>
    <link rel="stylesheet" href="css/style.css"> 
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f0f0;
        }
        .container {
            text-align: center;
        }
        form {
            margin-bottom: 20px;
        }
        input[type="text"],
        input[type="password"],
        input[type="submit"] {
            padding: 10px;
            margin: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        button {
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #28a745;
            color: white;
            cursor: pointer;
        }
        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Page de connexion</h1>
        <% if ("true".equals(request.getParameter("inscriptionSuccess"))) { %>
        <script>
            alert("Inscription réussie ! Vous pouvez maintenant vous connecter.");
        </script>
    <% } %>
    <% if ("false".equals(request.getParameter("connexion"))) { %>
        <script>
            alert("mot de passe ou password incorrect");
        </script>
    <% } %>
        <form action="login.connexion" method="post">
            <label for="username">Email d'utilisateur:</label><br>
            <input type="text" id="username" name="username" required><br>
            <label for="password">Mot de passe:</label><br>
            <input type="password" id="password" name="password" required><br>
            <input type="submit" value="Se connecter">
        </form>
        <button onclick="window.location.href='Inscription.jsp'">S'inscrire</button>
    </div>
</body>
</html>

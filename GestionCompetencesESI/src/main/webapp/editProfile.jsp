<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modification des informations</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css"> 
    <style>
    	.header_space{
        	margin-top: 100px;
        }
	</style>
</head>
<body>
         <jsp:include page="header.jsp" />
          
    <div class="container mt-5 header_space" >
        <div class="row justify-content-center">
            <div class="col-md-6">
                <% if ("false".equals(request.getParameter("modification"))) { %>
                    <div class="alert alert-danger" role="alert">
                        La modification a échoué !
                    </div>
                <% } %>
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h2 class="text-center">Modification des informations</h2>
                    </div>
                    <div class="card-body">
                        <form action="user.modification" method="post">
                            <div class="form-group">
                                <label for="newName">Nouveau Nom:</label>
                                <input type="text" id="newName" name="newName" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="newPassword">Nouveau Mot de passe:</label>
                                <input type="password" id="newPassword" name="newPassword" class="form-control" required>
                            </div>
                            <div class="text-center">
                                <input type="submit" value="Modifier" class="btn btn-primary">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

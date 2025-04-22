<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier un Module - Gestion Scolaire</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            height: 100vh;
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
        }
        .sidebar {
            width: 220px;
            background-color: #343a40;
            color: white;
            padding: 1.5rem;
            height: 100%;
        }
        .sidebar h4 {
            text-align: center;
            margin-bottom: 2rem;
        }
        .sidebar a {
            display: block;
            color: white;
            text-decoration: none;
            margin-bottom: 1rem;
            padding: 0.5rem;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .sidebar a:hover {
            background-color: #495057;
        }
        .main-content {
            flex: 1;
            padding: 2rem;
            overflow-y: auto;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <h4>Gestion Scolaire</h4>
        <a href="students">Students</a>
        <a href="modules">Modules</a>
        <a href="notes">Notes</a>
    </div>

    <div class="main-content">
        <h2 class="mb-4">Modifier un Module</h2>

        <form action="modules" method="post" class="needs-validation" novalidate>
            <input type="hidden" name="id" value="${module.id}">

            <div class="mb-3">
                <label for="name" class="form-label">Nom du module :</label>
                <input type="text" class="form-control" id="name" name="name" value="${module.name}" required>
            </div>

            <div class="mb-3">
                <label for="coefficient" class="form-label">Coefficient :</label>
                <input type="number" class="form-control" id="coefficient" name="coefficient" value="${module.coefficient}" step="0.01" required>
            </div>

            <button type="submit" class="btn btn-primary">Modifier</button>
            <a href="modules" class="btn btn-secondary ms-2">Retour à la liste des modules</a>
        </form>
    </div>
</body>
</html>

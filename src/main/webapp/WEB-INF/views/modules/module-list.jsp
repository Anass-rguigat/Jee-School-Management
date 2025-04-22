<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Modules - Gestion Scolaire</title>
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
        .table th, .table td {
            vertical-align: middle;
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
        <h2 class="mb-4">Liste des Modules</h2>

        <!-- Barre de recherche -->
        <form method="get" action="modules" class="row g-3 mb-4">
            <input type="hidden" name="action" value="search">
            <div class="col-auto">
                <input type="number" name="id" class="form-control" placeholder="Rechercher par ID" required>
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary">Rechercher</button>
            </div>
        </form>

        <!-- Résultat de la recherche -->
        <c:if test="${not empty searchedModule}">
            <div class="alert alert-success">
                Module trouvé : <strong>${searchedModule.name}</strong> (ID: ${searchedModule.id}, Coefficient: ${searchedModule.coefficient})
            </div>
        </c:if>
        <c:if test="${not empty notFound}">
            <div class="alert alert-danger">${notFound}</div>
        </c:if>

        <!-- Tableau des modules -->
        <div class="table-responsive">
            <table class="table table-bordered table-hover bg-white">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nom du module</th>
                        <th>Coefficient</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="module" items="${modules}">
                        <tr>
                            <td>${module.id}</td>
                            <td>${module.name}</td>
                            <td>${module.coefficient}</td>
                            <td>
                                <a href="modules?action=edit&id=${module.id}" class="btn btn-sm btn-warning me-1">Éditer</a>
                                <a href="modules?action=delete&id=${module.id}" class="btn btn-sm btn-danger" onclick="return confirm('Voulez-vous vraiment supprimer ce module ?');">Supprimer</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="mt-4">
            <a href="modules?action=new" class="btn btn-success me-2">Ajouter un module</a>
            <a href="index.jsp" class="btn btn-secondary">Retour à l'accueil</a>
        </div>
    </div>
</body>
</html>

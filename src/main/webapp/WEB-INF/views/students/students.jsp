<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>School Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            height: 100vh;
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
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
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .main-content h2 {
            font-size: 2rem;
            margin-bottom: 1.5rem;
        }
        .form-inline {
            margin-bottom: 2rem;
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .action-links a {
            margin-right: 10px;
        }
        .btn-custom {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 0.5rem 1.5rem;
            text-align: center;
            transition: background-color 0.3s ease;
        }
        .btn-custom:hover {
            background-color: #0056b3;
        }
        .alert {
            margin-top: 20px;
        }
        /* Ajouter ces styles dans la balise <style> */
.student-card {
    max-width: 500px;
    margin: 20px 0;
    box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

.average-badge {
    font-size: 1.1em;
    padding: 8px 15px;
    border-radius: 20px;
}

.search-results {
    background-color: #f8f9fa;
    padding: 20px;
    border-radius: 8px;
    margin-bottom: 30px;
}
    </style>
</head>
<body>
    <div class="sidebar">
        <h4>School Management</h4>
        <a href="students">Students</a>
        <a href="modules">Modules</a>
        <a href="notes">Notes</a>
    </div>

    <div class="main-content">
        <h2>Student List</h2>

        <!-- Search form -->
        <form action="students" method="get" class="form-inline">
            <input type="hidden" name="action" value="search">
            <div class="input-group mb-3">
                <label for="id" class="form-label">Search by ID:</label>
                <input type="number" name="id" id="id" class="form-control" required>
                <button type="submit" class="btn btn-primary ms-2">Search</button>
            </div>
        </form>

        <!-- Message if student is not found -->
        <c:if test="${not empty notFound}">
            <div class="alert alert-danger">
                ${notFound}
            </div>
        </c:if>

        <!-- Search result -->
        <!-- Remplacer la section search result existante par : -->
<c:if test="${not empty searchedStudent}">
    <div class="search-results">
        <h3><i class="bi bi-search me-2"></i>Résultats de la recherche</h3>
        <div class="student-card">
            <div class="card-header bg-primary text-white">
                Étudiant trouvé
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-4">
                        <p class="mb-1"><strong>ID:</strong> ${searchedStudent.id}</p>
                        <p class="mb-1"><strong>Nom:</strong> ${searchedStudent.lastName}</p>
                        <p class="mb-0"><strong>Prénom:</strong> ${searchedStudent.firstName}</p>
                    </div>
                    <div class="col-md-8 text-end">
                        <div class="display-4 text-primary">
                            ${searchedAverage}
                            <small class="text-muted fs-6">/20</small>
                        </div>
                        <span class="text-muted">Moyenne générale calculée</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>

        <!-- Complete student list -->
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.firstName}</td>
                        <td>${student.lastName}</td>
                        <td class="action-links">
                            <a href="students?action=edit&id=${student.id}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="students?action=delete&id=${student.id}" class="btn btn-danger btn-sm" 
                               onclick="return confirm('Are you sure you want to delete this student?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <br>
        <a href="students?action=new" class="btn btn-success">Add New Student</a>
        <br>
        <a href="index.jsp" class="btn btn-secondary mt-3">Back to Home</a>
    </div>
</body>
</html>

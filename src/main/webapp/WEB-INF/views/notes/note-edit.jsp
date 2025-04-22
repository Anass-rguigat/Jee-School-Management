<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier une Note - Gestion Scolaire</title>
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
        .main-content h1 {
            font-size: 2rem;
            margin-bottom: 2rem;
        }
        .form-label {
            font-weight: bold;
        }
        .btn-custom {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 0.5rem 1.5rem;
            transition: background-color 0.3s ease;
        }
        .btn-custom:hover {
            background-color: #0056b3;
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
        <h1>Modifier une Note</h1>

        <form action="notes" method="post" class="w-50">
            <input type="hidden" name="id" value="${note.id}"/>

            <div class="mb-3">
                <label for="studentId" class="form-label">Sélectionner un étudiant</label>
                <select class="form-select" name="studentId" required>
                    <c:forEach var="student" items="${students}">
                        <option value="${student.id}" ${student.id == note.student.id ? 'selected' : ''}>
                            ${student.firstName} ${student.lastName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label for="moduleId" class="form-label">Sélectionner un module</label>
                <select class="form-select" name="moduleId" required>
                    <c:forEach var="module" items="${modules}">
                        <option value="${module.id}" ${module.id == note.module.id ? 'selected' : ''}>
                            ${module.name}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label for="grade" class="form-label">Note</label>
                <input type="number" step="0.01" name="grade" class="form-control" value="${note.grade}" required />
            </div>

            <button type="submit" class="btn btn-custom">Modifier</button>
            <a href="notes" class="btn btn-secondary ms-2">Retour</a>
        </form>
    </div>
</body>
</html>

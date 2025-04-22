<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Grades Management</title>
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
            height: auto;
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
        .table th, .table td {
            vertical-align: middle;
        }
        .text-danger { color: #dc3545; }
        .average-badge { background-color: #17a2b8; }
        .action-links { white-space: nowrap; }
    </style>
</head>
<body>
    <div class="sidebar">
        <h4>School Management</h4>
        <a href="students">Students</a>
        <a href="modules">Modules</a>
        <a href="notes">Grades</a>
    </div>

    <div class="main-content">
        <h2>Grades Management</h2>

        <!-- Search Form -->
        <form action="notes" method="get" class="mb-4 border p-3 rounded">
            <input type="hidden" name="action" value="search">
            <div class="input-group">
                <span class="input-group-text">Search Student by ID</span>
                <input type="number" name="id" class="form-control" required>
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </form>

        <!-- Search Results -->
        <c:if test="${not empty notFound}">
            <div class="alert alert-danger">${notFound}</div>
        </c:if>

        <c:if test="${not empty searchedStudent}">
            <div class="alert alert-success mb-4">
                <h4>Student Details</h4>
                <div class="row">
                    <div class="col-md-6">
                        <p><strong>ID:</strong> ${searchedStudent.id}</p>
                        <p><strong>Name:</strong> ${searchedStudent.firstName} ${searchedStudent.lastName}</p>
                    </div>
                    <div class="col-md-6 text-end">
                        <h3 class="text-primary">
                            Average: <span class="average-badge badge">${searchedAverage}/20</span>
                        </h3>
                    </div>
                </div>
            </div>

            <!-- Searched Student's Grades -->
            <h4 class="mt-4 mb-3">Student's Grades</h4>
            <table class="table table-hover table-bordered">
                <thead class="table-primary">
                    <tr>
                        <th>Module</th>
                        <th>Initial Grade</th>
                        <th>Final Grade</th>
                        <th>Absences</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="note" items="${searchedNotes}">
                        <tr>
                            <td>${note.module.name} (Coeff. ${note.module.coefficient})</td>
                            <td>${note.grade}/20</td>
                            <td class="${note.gradeWithPenalty < note.grade ? 'text-danger' : ''}">
                                ${note.gradeWithPenalty}/20
                            </td>
                            <td>${note.absencesModule}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <!-- All Students' Grades -->
        <h4 class="mt-5 mb-3">All Grades</h4>
        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Student</th>
                    <th>Module</th>
                    <th>Initial Grade</th>
                    <th>Final Grade</th>
                    <th>Absences</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="note" items="${allNotes}">
                    <tr>
                        <td>${note.student.firstName} ${note.student.lastName}</td>
                        <td>${note.module.name} (Coeff. ${note.module.coefficient})</td>
                        <td>${note.grade}/20</td>
                        <td class="${note.gradeWithPenalty < note.grade ? 'text-danger' : ''}">
                            ${note.gradeWithPenalty}/20
                        </td>
                        <td>
                            ${note.absencesModule}
                            <form action="notes" method="post" class="d-inline">
                                <input type="hidden" name="action" value="incrementAbsence">
                                <input type="hidden" name="id" value="${note.id}">
                                <button type="submit" class="btn btn-sm btn-link">+</button>
                            </form>
                        </td>
                        <td class="action-links">
                            <a href="notes?action=edit&id=${note.id}" class="btn btn-sm btn-warning">Edit</a>
                            <a href="notes?action=delete&id=${note.id}" 
                               class="btn btn-sm btn-danger"
                               onclick="return confirm('Confirm deletion?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="mt-4">
            <a href="notes?action=new" class="btn btn-success">Add New Grade</a>
            <a href="index.jsp" class="btn btn-secondary">Back to Home</a>
        </div>
    </div>
</body>
</html>
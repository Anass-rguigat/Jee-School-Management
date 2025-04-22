<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Student - School Management</title>
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
            padding: 2rem;
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
            padding: 3rem;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            margin-left: 2rem;
        }
        .main-content h2 {
            font-size: 2rem;
            margin-bottom: 1.5rem;
        }
        .form-group {
            margin-bottom: 1.5rem;
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
            text-align: center;
            transition: background-color 0.3s ease;
        }
        .btn-custom:hover {
            background-color: #0056b3;
        }
        .btn-secondary {
            margin-top: 1rem;
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
        <h2>Add New Student</h2>
        <form action="students" method="post">
            <!-- Hidden ID for differentiation (id = 0) -->
            <input type="hidden" name="id" value="0">

            <div class="form-group">
                <label for="firstName" class="form-label">First Name:</label>
                <input type="text" id="firstName" name="firstName" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="lastName" class="form-label">Last Name:</label>
                <input type="text" id="lastName" name="lastName" class="form-control" required>
            </div>

            <button type="submit" class="btn btn-custom">Add Student</button>
        </form>
        <br>
        <a href="students" class="btn btn-secondary">Back to Student List</a>
    </div>
</body>
</html>

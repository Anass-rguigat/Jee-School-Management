<!DOCTYPE html>
<html>
<head>
    <title>School Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            height: 100vh;
        }
        .sidebar {
            width: 220px;
            background-color: #343a40;
            color: white;
            padding: 1rem;
        }
        .sidebar a {
            display: block;
            color: white;
            text-decoration: none;
            margin-bottom: 1rem;
        }
        .sidebar a:hover {
            background-color: #495057;
            padding-left: 10px;
        }
        .main-content {
            flex: 1;
            padding: 2rem;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <h4>Gestion Scolaire</h4>
        <a href="students">Student</a>
        <a href="modules">Modules</a>
        <a href="notes">Notes</a>
    </div>

    <div class="main-content">
        <h2>Bienvenue dans le systeme de gestion scolaire</h2>
        <p>Utilisez la barre laterale pour naviguer.</p>
    </div>
</body>
</html>

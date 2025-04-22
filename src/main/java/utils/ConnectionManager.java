package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/DB_ISI_G1"; // Remplace par ton nom de base
    private static final String USER = "root"; // Ton utilisateur MySQL
    private static final String PASSWORD = ""; // Ton mot de passe MySQL

    private static Connection connection;

    static {
        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Établir la connexion
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connexion établie avec succès !");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver JDBC non trouvé !");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la connexion à la base de données !");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}

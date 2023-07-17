package sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class connexionsql {
        static String jdbcUrl = "jdbc:postgresql://localhost:5432/pharmacie";
        static String username = "postgres";
        static String password = "root";
        
    public static Connection connecter(){
        Connection connection = null;
        try {
            // Chargement du driver JDBC
            Class.forName("org.postgresql.Driver");
            
            // Établissement de la connexion à la base de données
            connection = DriverManager.getConnection(jdbcUrl, username, password);
         
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC introuvable");
            JOptionPane.showMessageDialog(null, "erreur JDBC"+e,"erreur",JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données");
            JOptionPane.showMessageDialog(null, "erreur de connexion"+e,"erreur",JOptionPane.ERROR_MESSAGE);
        }
            
        
        return connection;
    }
        
}


//Pacote
package Factory;

//Importações
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Felipe
 */
public class ConnectionFactory {
    private String url = "jdbc:mysql://localhost:3306/mdim";
    private String user = "root";
    private String password = "root";
    
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url,user,password);
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Erro!", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
}

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
    private String url = "jdbc:mysql://mysql.febataglini.duckdns.org/mdim";
    private String user = "root";
    private String password = "#D;!4}1U3Wze";
    
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url,user,password);
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Erro!", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
}

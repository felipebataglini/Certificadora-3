package Voluntario;

import Factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Leonardo
 */
public class DAOVoluntario {
    ResultSet rs;
    private Connection link;
    ArrayList<Voluntario> lista = new ArrayList();
    
    public DAOVoluntario(){
        this.link = new ConnectionFactory().getConnection();
    }
    
    public void Create(Voluntario v){
        String sql = "INSERT INTO voluntario(vol_id, vol_cpf, vol_nome, vol_telefone, vol_email, vol_senha) VALUES(?,?,?,?,?)";
        try{
            PreparedStatement stmt = link.prepareStatement(sql);
            stmt.setInt(1,v.getIdvoluntario());
            stmt.setLong(2, v.getCpf());
            stmt.setString(3,v.getNome());
            stmt.setLong(4,v.getTelefone());
            stmt.setString(5,v.getEmail());
            stmt.setString(6,v.getSenha());
            stmt.execute();
            stmt.close();            
            JOptionPane.showMessageDialog(null, "Voluntário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void Remove(int idVolun) {
        String sql = "DELETE FROM voluntario WHERE vol_id = ?";
        try {
            PreparedStatement stmt = link.prepareStatement(sql);
            stmt.setInt(1, idVolun);
            stmt.execute();
            stmt.close();            
            JOptionPane.showMessageDialog(null, "Voluntário excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void Update(Voluntario v) {
        String sql = "UPDATE voluntario set vol_nome = ?, vol_cpf =  ?, vol_email = ?, vol_senha = ? where vol_id = ?";
        try {
            PreparedStatement stmt = link.prepareStatement(sql);
            stmt.setString(1, v.getNome());
            stmt.setLong(2, v.getCpf());
            stmt.setString(3, v.getEmail());
            stmt.setString(4, v.getSenha());
            stmt.setInt(5, v.getIdvoluntario());
            stmt.execute();
            stmt.close();            
            JOptionPane.showMessageDialog(null, "Voluntário atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ArrayList<Voluntario> Display() {
        String sql = "select * from voluntario";
        try {
            PreparedStatement stmt = link.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Voluntario objvoluntario = new Voluntario();
                objvoluntario.setIdvoluntario(rs.getInt("vol_id"));
                objvoluntario.setCpf(rs.getLong("vol_cpf"));
                objvoluntario.setNome(rs.getString("vol_nome"));
                objvoluntario.setTelefone(rs.getLong("vol_telefone"));
                objvoluntario.setEmail(rs.getString("vol_email"));
                objvoluntario.setSenha(rs.getString("vol_senha"));
                
                lista.add(objvoluntario);
            }
            JOptionPane.showMessageDialog(null, "Voluntário recuperados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }
    
     
     
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Externo;

import Factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Ideia.Ideia;
/*
 *
 * @author Leonardo
 * 
 */
public class DAOExterno {
    ResultSet rs;
    private Connection link;
    ArrayList<Externo> lista = new ArrayList();
    ArrayList<Ideia> listaideia = new ArrayList();
    
    public DAOExterno(){
        this.link = new ConnectionFactory().getConnection();
    }
    
    public void Create(Externo ex){
        String sql = "INSERT INTO externo(ext_id, ext_nome, ext_email, ext_senha) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = link.prepareStatement(sql);
            stmt.setInt(1, ex.getIdexterno());
            stmt.setString(2, ex.getNome());
            stmt.setString(3, ex.getEmail());
            stmt.setString(4,ex.getSenha());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Usuário externo cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void Remove(int idExt) {
        String sql = "DELETE FROM externo WHERE ext_id= ?";
        try {
            PreparedStatement stmt = link.prepareStatement(sql);
            stmt.setInt(1, idExt);
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Usuário externo excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }        
    public void Update(Externo ex) {
        String sql = "UPDATE externo set ext_nome = ?, ext_email = ?, ext_senha = ? where ext_id = ?";
        try {
            PreparedStatement stmt = link.prepareStatement(sql);
            stmt.setString(1, ex.getNome());
            stmt.setString(2, ex.getEmail());
            stmt.setString(3,ex.getSenha());
            stmt.setInt(4, ex.getIdexterno());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Usuário externo atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
     
    public ArrayList<Externo> Display() {
        String sql = "select * from externo";
        try {
            PreparedStatement stmt = link.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Externo objexterno = new Externo();
                objexterno.setIdexterno(rs.getInt("ext_id"));
                objexterno.setNome(rs.getString("ext_nome"));
                objexterno.setEmail(rs.getString("ext_email"));
                objexterno.setSenha(rs.getString("ext_senha"));
                lista.add(objexterno);
            }
            JOptionPane.showMessageDialog(null, "Usuários externos recuperados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);    
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    } 
}

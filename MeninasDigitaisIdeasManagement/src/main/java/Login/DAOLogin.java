/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import Factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Felipe
 */
public class DAOLogin {
    ResultSet rs;
    private Connection link;
       
    public DAOLogin(){
        this.link = new ConnectionFactory().getConnection();
    }
    
    public int autenticaUsuario(Login l){
        int permissao = 0;
        Login objadm = new Login();
        Login objvol = new Login();
        Login objext = new Login();
        PreparedStatement stmt;
        
        //Verifica se o usuário é um administrador registrado
        String sql = "SELECT * FROM administrador WHERE adm_id = " + l.getUsuario();        
        try{
            stmt = link.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                objadm.setUsuario(rs.getInt("adm_id"));
                objadm.setSenha(rs.getString("adm_senha"));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        
        //Verifica se o usuário é um voluntário registrado
        sql = "SELECT * FROM voluntario WHERE vol_id = " + l.getUsuario();
        try{
            stmt = link.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                objvol.setUsuario(rs.getInt("vol_id"));
                objvol.setSenha(rs.getString("vol_senha"));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        
        //Verifica se o usuário é um externo registrado
        sql = "SELECT * FROM externo WHERE ext_id = " + l.getUsuario();
        try{
            stmt = link.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                objext.setUsuario(rs.getInt("ext_id"));
                objext.setSenha(rs.getString("ext_senha"));
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        
        if(objadm.getUsuario()==l.getUsuario() && objadm.getSenha().equals(l.getSenha())){
            permissao = 1;
            return permissao;
        } else if(objvol.getUsuario()==l.getUsuario() && objvol.getSenha().equals(l.getSenha())){
            permissao = 2;
            return permissao;
        } else if(objext.getUsuario()==l.getUsuario() && objext.getSenha().equals(l.getSenha())){
            permissao = 3;
            return permissao;
        } else {
            return permissao;
        }
    }
}
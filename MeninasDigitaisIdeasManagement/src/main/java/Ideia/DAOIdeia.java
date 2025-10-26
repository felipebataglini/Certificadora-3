/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ideia;

import Factory.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Stefano
 */
public class DAOIdeia {
    private Connection link;
    private ResultSet rs;
    private ArrayList<Ideia> lista = new ArrayList<>();

    public DAOIdeia() {
        this.link = new ConnectionFactory().getConnection();
    }

    // CREATE (inserir nova ideia)
    public void create(Ideia ideia) {
        String sql = "INSERT INTO ideia (titulo, descricao, autor, status, sg_aprovado, sg_reprovado) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = link.prepareStatement(sql);
            stmt.setString(1, ideia.getTitulo());
            stmt.setString(2, ideia.getDescricao());
            stmt.setString(3, ideia.getAutor());
            stmt.setString(4, ideia.getStatus());
            stmt.setInt(5, ideia.getSGAprovado());
            stmt.setInt(6, ideia.getSGReprovado());
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Ideia cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar ideia:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // DELETE (remover ideia)
    public void remove(int idIdeia) {
        String sql = "DELETE FROM ideia WHERE id_ideia = ?";
        try {
            PreparedStatement stmt = link.prepareStatement(sql);
            stmt.setInt(1, idIdeia);
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Ideia removida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover ideia:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // UPDATE (alterar status ou conteúdo)
    public void update(Ideia ideia) {
        String sql = "UPDATE ideia SET titulo = ?, descricao = ?, autor = ?, status = ?, sg_aprovado = ?, sg_reprovado = ? WHERE id_ideia = ?";
        try {
            PreparedStatement stmt = link.prepareStatement(sql);
            stmt.setString(1, ideia.getTitulo());
            stmt.setString(2, ideia.getDescricao());
            stmt.setString(3, ideia.getAutor());
            stmt.setString(4, ideia.getStatus());
            stmt.setInt(5, ideia.getSGAprovado());
            stmt.setInt(6, ideia.getSGReprovado());
            stmt.setInt(7, ideia.getIdIdeia());
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Ideia atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar ideia:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    // DISPLAY (listar todas as ideias)
    public ArrayList<Ideia> display() {
        String sql = "SELECT * FROM ideia";
        lista.clear();

        try {
            PreparedStatement stmt = link.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Ideia obj = new Ideia(
                    rs.getString("titulo"),
                    rs.getString("descricao"),
                    rs.getString("autor")
                );
                obj.setIdIdeia(rs.getInt("id_ideia"));
                obj.setStatus(rs.getString("status"));
                obj.setSGAprovado(rs.getInt("sg_aprovado"));
                obj.setSGReprovado(rs.getInt("sg_reprovado"));

                lista.add(obj);
            }

            JOptionPane.showMessageDialog(null, "Ideias carregadas com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar ideias:\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return lista;
    }
}

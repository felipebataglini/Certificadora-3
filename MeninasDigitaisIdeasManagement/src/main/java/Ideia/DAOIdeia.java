/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ideia;

import Factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Stefano
 */
public class DAOIdeia {
    ResultSet rs;
    private Connection link;
    ArrayList<Ideia> lista = new ArrayList<>();

    public DAOIdeia() {
        this.link = new ConnectionFactory().getConnection();
    }

    // Obtém o próximo ID disponível
    private int getProximoIdeId() {
        String sql = "SELECT COALESCE(MAX(ide_id), 0) + 1 AS proximo_id FROM ideia";
        try {
            PreparedStatement stmt = link.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int proximoId = rs.getInt("proximo_id");
                rs.close();
                stmt.close();
                return proximoId;
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar ID: " + e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
        return 1;
    }

    // CREATE (inserir nova ideia)
    public void Create(Ideia ideia) {
        String sql = "INSERT INTO ideia (ide_id, ide_titulo, ide_conteudo, ide_status, vol_id, adm_id, ext_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            int proximoId = getProximoIdeId();
            ideia.setIdeId(proximoId);

            // garante que toda ideia nova começa como "Pendente"
            if (ideia.getIdeStatus() == null || ideia.getIdeStatus().trim().isEmpty()) {
                ideia.setIdeStatus("Pendente");
            }

            PreparedStatement stmt = link.prepareStatement(sql);
            stmt.setInt(1, ideia.getIdeId());
            stmt.setString(2, ideia.getIdeTitulo());
            stmt.setString(3, ideia.getIdeConteudo());
            stmt.setString(4, ideia.getIdeStatus());
            if (ideia.getVolId() == null) stmt.setNull(5, Types.INTEGER); else stmt.setInt(5, ideia.getVolId());
            if (ideia.getAdmId() == null) stmt.setNull(6, Types.INTEGER); else stmt.setInt(6, ideia.getAdmId());
            if (ideia.getExtId() == null) stmt.setNull(7, Types.INTEGER); else stmt.setInt(7, ideia.getExtId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Ideia cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    // DELETE (remover ideia)
    public void Remove(int ideId) {
        String sql = "DELETE FROM ideia WHERE ide_id = ?";
        try {
            PreparedStatement stmt = link.prepareStatement(sql);
            stmt.setInt(1, ideId);
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Ideia removida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    // UPDATE (alterar status ou conteúdo)
    public void Update(Ideia ideia) {
        String sql = "UPDATE ideia SET ide_titulo = ?, ide_conteudo = ?, ide_status = ?, vol_id = ?, adm_id = ?, ext_id = ? WHERE ide_id = ?";
        try {
            PreparedStatement stmt = link.prepareStatement(sql);
            stmt.setString(1, ideia.getIdeTitulo());
            stmt.setString(2, ideia.getIdeConteudo());
            stmt.setString(3, ideia.getIdeStatus());
            if (ideia.getVolId() == null) stmt.setNull(4, Types.INTEGER); else stmt.setInt(4, ideia.getVolId());
            if (ideia.getAdmId() == null) stmt.setNull(5, Types.INTEGER); else stmt.setInt(5, ideia.getAdmId());
            if (ideia.getExtId() == null) stmt.setNull(6, Types.INTEGER); else stmt.setInt(6, ideia.getExtId());
            stmt.setInt(7, ideia.getIdeId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Ideia atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    // DISPLAY (listar todas as ideias)
    public ArrayList<Ideia> Display() {
        String sql = "SELECT * FROM ideia";
        lista.clear();
        try {
            PreparedStatement stmt = link.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Ideia objIdeia = new Ideia();
                objIdeia.setIdeId(rs.getInt("ide_id"));
                objIdeia.setIdeTitulo(rs.getString("ide_titulo"));
                objIdeia.setIdeConteudo(rs.getString("ide_conteudo"));
                objIdeia.setIdeStatus(rs.getString("ide_status"));

                int vol = rs.getInt("vol_id");
                if (rs.wasNull()) objIdeia.setVolId(null); else objIdeia.setVolId(vol);

                int adm = rs.getInt("adm_id");
                if (rs.wasNull()) objIdeia.setAdmId(null); else objIdeia.setAdmId(adm);

                int ext = rs.getInt("ext_id");
                if (rs.wasNull()) objIdeia.setExtId(null); else objIdeia.setExtId(ext);

                lista.add(objIdeia);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Erro ao listar ideias!", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    // Alias para compatibilidade com GerenciadorIdeia
    public ArrayList<Ideia> listar() {
        return Display();
    }

    // Atualiza somente o status (e opcionalmente registra o administrador que alterou)
    public boolean updateStatus(int ideId, String novoStatus, Integer admId) {
        String sql = "UPDATE ideia SET ide_status = ?, adm_id = ? WHERE ide_id = ?";
        try (PreparedStatement stmt = link.prepareStatement(sql)) {
            stmt.setString(1, novoStatus);
            if (admId == null) stmt.setNull(2, Types.INTEGER); else stmt.setInt(2, admId);
            stmt.setInt(3, ideId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar status: " + e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}

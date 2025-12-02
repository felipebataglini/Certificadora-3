/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ideia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.util.List;

/**
 *
 * @author Stefano
 */
public class GerenciadorIdeia implements ActionListener {

    private GUIIdeia guiideia;
    private DAOIdeia daoideia;
    private Ideia ideia;
    private String autorLogado;

    public GerenciadorIdeia() {
        guiideia = new GUIIdeia();
        guiideia.addListener(this);
        daoideia = new DAOIdeia();
        ideia = new Ideia();
    }

    /**
     * Interpreta o código e atribui o autor (administrador, voluntário ou externo)
     * 1-9: Administrador
     * 10-99: Voluntário
     * 100-1000: Externo
     *
     * Retorna true se o código foi válido e o autor atribuído, false caso contrário.
     */
    private boolean atribuirAutorPorCodigo(Ideia ideia, String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Informe o código do autor!", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
            return false;
        }
        String trimmed = codigo.trim();
        if (!trimmed.matches("\\d+")) {
            javax.swing.JOptionPane.showMessageDialog(null, "Código deve conter apenas números!", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            int id = Integer.parseInt(trimmed);
            if (id >= 1 && id <= 9) {
                ideia.setAdmId(id);
                ideia.setVolId(null);
                ideia.setExtId(null);
                return true;
            } else if (id >= 10 && id <= 99) {
                ideia.setVolId(id);
                ideia.setAdmId(null);
                ideia.setExtId(null);
                return true;
            } else if (id >= 100 && id <= 1000) {
                ideia.setExtId(id);
                ideia.setAdmId(null);
                ideia.setVolId(null);
                return true;
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Código fora do intervalo esperado. Use: 1-9 (Admin), 10-99 (Voluntário), 100-1000 (Externo).", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Código inválido!", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botaopressionado = (JButton) e.getSource();
        String opc = botaopressionado.getName();

        switch (opc) {
            case "cadastrar":
                ideia = guiideia.getIdeia(autorLogado);
                // validação usando os novos getters
                if (ideia == null
                        || ideia.getIdeTitulo() == null || ideia.getIdeTitulo().trim().isEmpty()
                        || ideia.getIdeConteudo() == null || ideia.getIdeConteudo().trim().isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Obter o código do autor do campo extra na GUI
                String codigoAutor = guiideia.getCodigoAutor();
                // valida e atribui; se inválido, não prossegue com o cadastro
                if (!atribuirAutorPorCodigo(ideia, codigoAutor)) {
                    return;
                }
                
                daoideia.Create(ideia);
                guiideia.limpar();
                guiideia.consultar();
                break;

            case "deletar":
                int idIdeia = guiideia.getIdIdeia();
                if (idIdeia == 0) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Selecione uma ideia para deletar!", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                daoideia.Remove(idIdeia);
                guiideia.limpar();
                guiideia.consultar();
                break;

            case "alterar":
                ideia = guiideia.getIdeia(autorLogado);
                if (ideia == null || ideia.getIdeId() <= 0) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Selecione uma ideia válida para alterar.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                daoideia.Update(ideia);
                guiideia.limpar();
                guiideia.consultar();
                break;

            case "mostrar":
                guiideia.consultar();
                break;
        }
    }

    public List<Ideia> listarIdeias() {
        return daoideia.listar();
    }

    public void setAutorLogado(String autor) {
        this.autorLogado = autor;
    }
}

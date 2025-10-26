/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ideia;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Stefano
 */
public class GerenciadorIdeia implements ActionListener {

    private GUIIdeia gui;
    private List<Ideia> listaIdeias;
    private String autorLogado;
    
    public GerenciadorIdeia() {
    // construtor vazio para o MenuVoluntario
    this.listaIdeias = new ArrayList<>();
    }

    public GerenciadorIdeia(GUIIdeia gui, String autorLogado) {
        this.gui = gui;
        this.autorLogado = autorLogado;
        this.listaIdeias = new ArrayList<>();
        gui.addListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == gui.JBTenviar()) {
            cadastrarIdeia();
        } else if (src == gui.JBTlimpar()) {
            gui.limpar();
        }
    }

    private void cadastrarIdeia() {
        Ideia nova = gui.getIdeia(autorLogado);

        if (nova.getTitulo().isEmpty() || nova.getDescricao().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        listaIdeias.add(nova);
        JOptionPane.showMessageDialog(null, "Ideia cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        gui.limpar();
    }

    public List<Ideia> getListaIdeias() {
        return listaIdeias;
    }
}

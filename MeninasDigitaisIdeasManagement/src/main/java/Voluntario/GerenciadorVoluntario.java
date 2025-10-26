/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Voluntario;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Leonardo
 */
public class GerenciadorVoluntario implements ActionListener {
    private GUIVoluntario guivoluntario;
    private DAOVoluntario daovoluntario;
    private Voluntario v;

    public GerenciadorVoluntario() {
        guivoluntario = new GUIVoluntario();
        guivoluntario.addListener(this);
        daovoluntario = new DAOVoluntario();
        v = new Voluntario();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botaopressionado = (JButton) e.getSource();
        String opc = botaopressionado.getName();
        switch (opc) {
            case "cadastrar":
                v = guivoluntario.getVoluntario();
                daovoluntario.Create(v);
                guivoluntario.limpar();
                break;
            case "deletar":
                int idVol = guivoluntario.getID();
                daovoluntario.Remove(idVol);
                guivoluntario.limpar();
                break;
            case "alterar":
                v = guivoluntario.getVoluntario();
                daovoluntario.Update(v);
                guivoluntario.limpar();
                break;
            case "mostrar":
                guivoluntario.consultar();
                break;
        }
    }
}

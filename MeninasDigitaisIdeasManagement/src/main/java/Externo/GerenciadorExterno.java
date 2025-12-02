/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Externo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


/**
 *
 * @author Leonardo
 */
public class GerenciadorExterno implements ActionListener {
    private GUIExterno guiexterno;
    private DAOExterno daoexterno;
    private Externo ex;

    public GerenciadorExterno(){
        guiexterno = new GUIExterno();
        guiexterno.addListener(this);
        daoexterno = new DAOExterno();
        ex = new Externo();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botaopressionado = (JButton)e.getSource();
        String opc = botaopressionado.getName();
        
        switch(opc){
            case "cadastrar":
                ex = guiexterno.getExterno();
                daoexterno.Create(ex);
                guiexterno.limpar();
                break;
            case "deletar":
                int idExt = guiexterno.getID();
                daoexterno.Remove(idExt);
                guiexterno.limpar();
                break;
            case "alterar":
                ex = guiexterno.getExterno();
                daoexterno.Update(ex);
                break;
            case "mostrar":
                guiexterno.consultar();
                break;
        }
    }
}
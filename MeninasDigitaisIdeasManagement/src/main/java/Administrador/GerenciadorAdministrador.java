/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administrador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Matheus
 */
public class GerenciadorAdministrador implements ActionListener {
    private GUIAdministrador guiadministrador;
    private DAOAdministrador daoadministrador;
    private Administrador a;
  
    public GerenciadorAdministrador(){
        guiadministrador = new GUIAdministrador();
        guiadministrador.addListener(this);
        daoadministrador = new DAOAdministrador();
        a = new Administrador();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botaopressionado = (JButton)e.getSource();
        String opc = botaopressionado.getName();
          
        switch(opc){
            case "cadastrar":
                a = guiadministrador.getAdministrador();
                daoadministrador.Create(a);
                guiadministrador.limpar();
                break;                 
            case "deletar":
                int idAdm = guiadministrador.getID();
                daoadministrador.Remove(idAdm);
                guiadministrador.limpar();
                break;
            case "mostrar":
                guiadministrador.consultar();
                break;
        }
    }
}

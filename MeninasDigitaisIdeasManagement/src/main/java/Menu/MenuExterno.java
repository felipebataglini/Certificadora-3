/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;


import Ideia.GerenciadorIdeia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Felipe
 */
public class MenuExterno implements ActionListener{
    private GUIMenuExterno guimenuexterno;
    
    public MenuExterno(){
        guimenuexterno = new GUIMenuExterno();
        guimenuexterno.addListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botaopressionado = (JButton)e.getSource();
        String opc = botaopressionado.getName();
        
        switch(opc){
            case "ideia":
                new GerenciadorIdeia();
                break;
        }
    }
}

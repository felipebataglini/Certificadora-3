/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import Menu.MenuAdministrador;
import Menu.MenuVoluntario;
import Menu.MenuExterno;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Felipe
 */
public class GerenciadorLogin implements ActionListener{
    private GUILogin guilogin;
    private DAOLogin daologin;
    private Login l;
    
    public GerenciadorLogin(){
        guilogin = new GUILogin();
        guilogin.addListener(this);
        daologin = new DAOLogin();
        l = new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botaopressionado = (JButton)e.getSource();
        String opc = botaopressionado.getName();
        
        switch(opc){
            case "entrar":
                //Verifica qual o tipo de usuário;
                guilogin.getLogin(l);
                switch(daologin.autenticaUsuario(l)){
                    case 1:
                        System.out.println("Administrador logado!");
                        new MenuAdministrador();
                        break;
                    case 2:
                        System.out.println("Voluntário logado!");
                        new MenuVoluntario();
                        break;
                    case 3:
                        System.out.println("Usuário externo logado!");
                        new MenuExterno();
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null, "Usuário ou senha não encontrado", "Aviso", JOptionPane.ERROR_MESSAGE);
                        break;
                }
                break;
        }
    }
}

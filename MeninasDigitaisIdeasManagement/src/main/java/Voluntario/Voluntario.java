/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Voluntario;

import Usuario.Usuario;

/**
 *
 * @author Leonardo
 */
public class Voluntario extends Usuario{
    private int idvoluntario;
    private String senha;

    public int getIdvoluntario() {
        return idvoluntario;
    }
    public void setIdvoluntario(int idvoluntario) {
        this.idvoluntario = idvoluntario;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}

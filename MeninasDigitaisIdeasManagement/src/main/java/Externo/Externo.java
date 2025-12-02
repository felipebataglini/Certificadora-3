/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Externo;

import Usuario.Usuario;
/**
 *
 * @author Leonardo
 */
public class Externo extends Usuario {
    private int idexterno;
    private String senha;
    
        public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public int getIdexterno() {
        return idexterno;
    }
    public void setIdexterno(int idexterno) {
        this.idexterno = idexterno;
    }
}

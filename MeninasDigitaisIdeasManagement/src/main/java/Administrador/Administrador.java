/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administrador;

import Voluntario.Voluntario;

/**
 *
 * @author Matheus
 */
public class Administrador extends Voluntario {
    private int idadministrador;
    private String senha;
    private String cargo;

    public int getIDAdministrador() {
        return idadministrador;
    }
    public void setIDAdministrador(int idadministrador) {
        this.idadministrador = idadministrador;
    }
    
    public String getSenha(){
        return senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }

    public int getIdadministrador() {
        return idadministrador;
    }
    public void setIdadministrador(int idadministrador) {
        this.idadministrador = idadministrador;
    }

    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}

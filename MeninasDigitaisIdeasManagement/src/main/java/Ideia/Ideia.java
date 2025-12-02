/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ideia;

/**
 *
 * @author Stefano
 */
public class Ideia {
    private int ideId;
    private String ideTitulo;
    private String ideConteudo;
    private String ideStatus; // Pendente, Aprovada, Reprovada
    private Integer volId; // id do voluntário (nullable)
    private Integer admId; // id do administrador (nullable)
    private Integer extId; // id do externo (nullable)

    public Ideia() {
        this.ideStatus = "Pendente";
    }

    public Ideia(String ideTitulo, String ideConteudo) {
        this.ideTitulo = ideTitulo;
        this.ideConteudo = ideConteudo;
        this.ideStatus = "Pendente";
    }

    // Getters e Setters (correspondem às colunas do banco)
    public int getIdeId() {
        return ideId;
    }

    public void setIdeId(int ideId) {
        this.ideId = ideId;
    }

    public String getIdeTitulo() {
        return ideTitulo;
    }

    public void setIdeTitulo(String ideTitulo) {
        this.ideTitulo = ideTitulo;
    }

    public String getIdeConteudo() {
        return ideConteudo;
    }

    public void setIdeConteudo(String ideConteudo) {
        this.ideConteudo = ideConteudo;
    }

    public String getIdeStatus() {
        return ideStatus;
    }

    public void setIdeStatus(String ideStatus) {
        this.ideStatus = ideStatus;
    }

    public Integer getVolId() {
        return volId;
    }

    public void setVolId(Integer volId) {
        this.volId = volId;
    }

    public Integer getAdmId() {
        return admId;
    }

    public void setAdmId(Integer admId) {
        this.admId = admId;
    }

    public Integer getExtId() {
        return extId;
    }

    public void setExtId(Integer extId) {
        this.extId = extId;
    }

    @Override
    public String toString() {
        return "Ideia{" +
                "ideId=" + ideId +
                ", ideTitulo='" + ideTitulo + '\'' +
                ", ideConteudo='" + ideConteudo + '\'' +
                ", ideStatus='" + ideStatus + '\'' +
                ", volId=" + volId +
                ", admId=" + admId +
                ", extId=" + extId +
                '}';
    }
}


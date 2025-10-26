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
private int idIdeia;
    private String titulo;
    private String descricao;
    private String autor; 
    private String status; // Pendente, Aprovada, Reprovada
    public int SGAprovado; //Quantidade de sugestão de ideia aprovada
    public int SGReprovado; //Quantidade de sugestão de ideia reprovada

    public Ideia(String titulo, String descricao, String autor) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.autor = autor;
        this.status = "Pendente";
        this.SGAprovado = 0;
        this.SGReprovado = 0;
    }

    // Getters e Setters
    public int getIdIdeia() { return idIdeia; }
    public void setIdIdeia(int idIdeia) { this.idIdeia = idIdeia; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status;}

    int getSGAprovado() { return SGAprovado;}
    public void setSGAprovado(int SGAprovado) {this.SGAprovado = SGAprovado;}
    
    int getSGReprovado() { return SGReprovado;}
    public void setSGReprovado(int SGReprovado) {this.SGReprovado = SGReprovado;}

}


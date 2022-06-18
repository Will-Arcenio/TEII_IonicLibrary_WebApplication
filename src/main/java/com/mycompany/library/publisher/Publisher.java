
package com.mycompany.library.publisher;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "publishers", schema = "public")
public class Publisher implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column
    private String endereco;
    
    @Column
    private String telefone;
    
    @Column(columnDefinition = "TEXT")
    private String foto;
    
    @Column
    private String site;
    
    @Column
    private String email;
    
    @Column(columnDefinition = "TEXT")
    private String localizacao;
    
    @Column(columnDefinition = "TEXT")
    private String iframeMap;
    
//    Constructors
    public Publisher() {
        
    }
    
    public Publisher(String nome, String endereco, String telefone, String foto, String site, String email, String localizacao, String iframeMap) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.foto = foto;
        this.site = site;
        this.email = email;
        this.localizacao = localizacao;
        this.iframeMap = iframeMap;
    }
    
    
//    Setters
    public void setId(long id) {
        this.id = id;
    }
    
    public void setNome(String nome) {
        this.nome = nome.trim();
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco.trim();
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public void setFoto(String foto) {
        this.foto = foto.trim();
    }
    
    public void setSite(String site) {
        this.site = site.trim();
    }
    
    public void setEmail(String email) {
        this.email = email.trim().toLowerCase();
    }
    
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao.trim();
    }
    
    public void setIframeMap(String iframeMap) {
        this.iframeMap = iframeMap.trim();
    }
    
    
//    Getters
    public long getId() {
        return this.id;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public String getEndereco() {
        return this.endereco;
    }
    
    public String getTelefone() {
        return this.telefone;
    }
    
    public String getFoto() {
        return this.foto;
    }
    
    public String getSite() {
        return this.site;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getLocalizaca() {
        return this.localizacao;
    }
    
    public String getIframeMap() {
        return this.iframeMap;
    }
    
}

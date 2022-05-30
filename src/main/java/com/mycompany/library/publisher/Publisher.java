
package com.mycompany.library.resources;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "publishers", schema = "public")
public class Publisher implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column
    private String endereco;
    
    @Column
    private String telefone;
    
    @Column
    private String foto;
    
    @Column
    private String site;
    
    @Column
    private String email;
    
    @Column
    private String localizacao;
    
    @Column
    private String iframeMap;
    
//    Constructors
    public Publisher() {
    this.nome = "NOME-PADRÃO";
    this.endereco = "ENDEREÇO-PADRÃO";
    this.telefone = "TELEFONE-PADRÃO";
    this.foto = "FOTO-PADRÃO";
    this.site = "SITE-PADRÃO";
    this.email = "EMAIL-PADRÃO";
    this.localizacao = "LOCALIZAÇÃO-PADRÃO";
    this.iframeMap = "IFRAMEMAP-PADRÃO";
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
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setNome(String nome) {
        this.nome = nome.trim().isEmpty() ? "NOME PADRÃO" : nome;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco.trim().isEmpty() ? "ENDEREÇO PADRÃO" : endereco;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone.trim().isEmpty() ? "(00) 90000-0000" : telefone;
    }
    
    public void setFoto(String foto) {
        this.foto = foto.trim().isEmpty() ? "FOTO PADRÃO" : foto.toLowerCase();
    }
    
    public void setSite(String site) {
        this.site = site.trim().isEmpty() ? "site_padrão.com.br" : site.toLowerCase();
    }
    
    public void setEmail(String email) {
        this.email = email.trim().isEmpty() ? "email_padrão@email.com" : email.toLowerCase();
    }
    
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao.trim().isEmpty() ? "LOCALIZAÇÃO PADRÃO" : localizacao.toLowerCase();
    }
    
    public void setIframeMap(String iframeMap) {
        this.iframeMap = iframeMap.trim().isEmpty() ? "IFRAMEMAP PADRÃO" : iframeMap.toLowerCase();
    }
    
    
//    Getters
    public Long getId() {
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

package com.mycompany.library.book;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books", schema = "public")
public class Book implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String referencia;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;
    
    @Column
    private String sinopse;
    
    @Column
    private LocalDate publicacao;
    
    @Column(nullable = false)
    private String imagem;

    @Column
    private String autor;

    @Column(nullable = false)
    private Float preco;

    @Column
    private String editora;
    
//    Constructors
    public Author() {
        this.nome = "NOME PADRÃO";
        this.referencia = "REFERÊNCIA PADRÃO";
        this.genero = Genero.SUSPENSE;
        this.sinopse = "SINOPSE PADRÃO";
        this.publicacao = LocalDate.now();
        this.imagem = "ImagemPadrão.png";
        this.autor = "AUTOR PADRÃO";
        this.preco = 0.1;
        this.editora = "EDITORA PADRÃO";

    }

    public Author(String nome, String referencia, Genero genero, String sinopse, LocalDate publicacao, String imagem, String autor, Float preco, String editora ) {
        this.nome = nome;
        this.referencia = sobrenome;
        this.genero = genero;
        this.sinopse = sinopse;
        this.publicacao = publicacao;
        this.imagem = imagem;
        this.autor = autor;
        this.preco = preco;
        this.editora = editora;
    }
    
//    Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome.trim().isEmpty() ? "NOME PADRÃO" : nome;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia.trim().isEmpty() ? "REFERÊNCIA PADRÃO" : referencia;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse.trim().isEmpty() ? "SINOPSE PADRÃO" : sinopse.toLowerCase();
    }

    public void setPublicacao(LocalDate publicacao) {
        this.publicacao = publicacao;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem.trim().isEmpty() ? "ImagemPadrão.png" : imagem.toLowerCase();
    }

    public void setAutor(String autor) {
        this.autor = autor.trim().isEmpty() ? "AUTOR PADRÃO" : autor.toLowerCase();
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public void setEditor(String editora) {
        this.editora = editora.trim().isEmpty() ? "EDITORA PADRÃO" : editora.toLowerCase();
    }
    
//    Getters
    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getReferencia() {
        return this.referencia;
    }

    public Genero getGenero() {
        return this.genero;
    }

    public String getSinopse() {
        return this.sinopse;
    }

    public LocalDate getPublicacao() {
        return this.publicacao;
    }

    public String getImagem() {
        return this.imagem;
    }
    public String getAutor() {
        return this.autor;
    }    
    public Float getPreco() {
        return this.preco;
    }
    public String getEditora() {
        return this.editora;
    }
    
}
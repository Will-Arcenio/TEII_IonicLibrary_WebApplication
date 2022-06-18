
package com.mycompany.library.author;

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
@Table(name = "authors", schema = "public")
public class Author implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String sobrenome;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Nacionalidade nacionalidade;
    
    @Column
    private String redes;
    
    @Column
    private LocalDate nascimento;
    
    @Column(columnDefinition = "TEXT")
    private String retrato;
    
//    Constructors
    public Author() {
    }

    public Author(String nome, String sobrenome, Nacionalidade nacionalidade, String redes, LocalDate nascimento, String retrato) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nacionalidade = nacionalidade;
        this.redes = redes;
        this.nascimento = nascimento;
        this.retrato = retrato;
    }
    
//    Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome.trim();
    }

    public void setNacionalidade(Nacionalidade nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public void setRedes(String redes) {
        this.redes = redes.trim().toLowerCase();
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public void setRetrato(String retrato) {
        this.retrato = retrato.trim();
    }
    
//    Getters
    public long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSobrenome() {
        return this.sobrenome;
    }

    public Nacionalidade getNacionalidade() {
        return this.nacionalidade;
    }

    public String getRedes() {
        return this.redes;
    }

    public LocalDate getNascimento() {
        return this.nascimento;
    }

    public String getRetrato() {
        return this.retrato;
    }
    
}

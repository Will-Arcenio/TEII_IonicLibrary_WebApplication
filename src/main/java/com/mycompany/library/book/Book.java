package com.mycompany.library.book;

import com.mycompany.library.author.Author;
import com.mycompany.library.publisher.Publisher;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "books", schema = "public")
@SequenceGenerator(name = "BOOK_SEQ", sequenceName = "BOOK_SEQ")
public class Book implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQ")
    private Long id;
    
    @Column(nullable = false)
    private String referencia;
    
    @Column(nullable = false)
    private String nome;
    
    @Column
    private String sinopse;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "id_book"),
            foreignKey = @ForeignKey(name = "fk_books"),
            inverseJoinColumns = @JoinColumn(name = "id_author"),
            inverseForeignKey = @ForeignKey(name = "fk_authors")
    )
    private List<Author> autor;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_publisher", foreignKey = @ForeignKey(name = "fk_id_publisher"))
    private Publisher editora;
    
    @Column
    private LocalDate publicacao;
    
    @Column(nullable = false)
    private Float preco;
    
    @Column(nullable = false)
    private String imagem;
    
//    Constructors

    public Book() {
    
    }

    public Book(String referencia, String nome, String sinopse, Genero genero, List<Author> autor, Publisher editora, LocalDate publicacao, Float preco, String imagem) {
        this.referencia = referencia;
        this.nome = nome;
        this.sinopse = sinopse;
        this.genero = genero;
        this.autor = autor;
        this.editora = editora;
        this.publicacao = publicacao;
        this.preco = preco;
        this.imagem = imagem;
    }

//    Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setAutor(List<Author> autor) {
        this.autor = autor;
    }

    public void setEditora(Publisher editora) {
        this.editora = editora;
    }

    public void setPublicacao(LocalDate publicacao) {
        this.publicacao = publicacao;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
//    Getters
    public Long getId() {
        return this.id;
    }

    public String getReferencia() {
        return this.referencia;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSinopse() {
        return this.sinopse;
    }

    public Genero getGenero() {
        return this.genero;
    }

    public List<Author> getAutor() {
        return this.autor;
    }

    public Publisher getEditora() {
        return this.editora;
    }

    public LocalDate getPublicacao() {
        return this.publicacao;
    }

    public Float getPreco() {
        return this.preco;
    }

    public String getImagem() {
        return this.imagem;
    }
    
}
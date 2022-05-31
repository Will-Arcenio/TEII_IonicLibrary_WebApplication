
package com.mycompany.library.book;

public enum Genero {
    
    FICCAO_LITERARIA("Ficção Literária"),
    NAO_FICCAO("Não Ficção"),
    SUSPENSE("Suspense"),
    FICCAO_CIENTIFICA ("Ficção Científica"),
    FANTASIA("Fantasia"),
    HORROR("Horror");
    POESIA("Poesia");
    ROMANCE("Romance");
    RELIGIOSO("Religioso");
    
    private String descricao;

    private Genero(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
    
}

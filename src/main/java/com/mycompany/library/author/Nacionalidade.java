
package com.mycompany.library.author;

public enum Nacionalidade {
    
    BRASIL("Brasileiro(a)"),
    EUA("Norte Americano(a)"),
    ENGLISH("Britânico(a)"),
    KOREAN("Coreano(a)"),
    JAPANESE("Japonês(a)"),
    ISRAEL("Israelense");
    
    private String descricao;

    private Nacionalidade(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
    
}

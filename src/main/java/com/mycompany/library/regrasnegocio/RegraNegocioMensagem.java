package com.mycompany.library.regrasnegocio;

public class RegraNegocioMensagem {

    private String erro;

    public RegraNegocioMensagem(String erro) {
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}

package com.mycompany.library.config;

public class WebApplicationExceptionMessage {
    
    private String erro;

    public WebApplicationExceptionMessage(String erro) {
        this.erro = erro;
    }
    
    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }
    
}

package br.com.pempec.finance.models;

import java.io.Serializable;

public class ParticionadorTituloPagarModel implements Serializable {

    private TituloPagarModel tituloNovo;
    private UsuarioModel usuario;
    private TituloPagarModel tituloAnterior;

    public TituloPagarModel getTituloAnterior() {
        return tituloAnterior;
    }

    public void setTituloAnterior(TituloPagarModel tituloAnterior) {
        this.tituloAnterior = tituloAnterior;
    }

    public TituloPagarModel getTituloNovo() {
        return tituloNovo;
    }

    public void setTituloNovo(TituloPagarModel tituloNovo) {
        this.tituloNovo = tituloNovo;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
}// fim da class


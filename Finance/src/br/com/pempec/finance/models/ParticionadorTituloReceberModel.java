package br.com.pempec.finance.models;

import java.io.Serializable;

public class ParticionadorTituloReceberModel implements Serializable {

    private TituloReceberModel tituloNovo;
    private UsuarioModel usuario;
    private TituloReceberModel tituloAnterior;

    public TituloReceberModel getTituloAnterior() {
        return tituloAnterior;
    }

    public void setTituloAnterior(TituloReceberModel tituloAnterior) {
        this.tituloAnterior = tituloAnterior;
    }

    public TituloReceberModel getTituloNovo() {
        return tituloNovo;
    }

    public void setTituloNovo(TituloReceberModel tituloNovo) {
        this.tituloNovo = tituloNovo;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
}// fim da class


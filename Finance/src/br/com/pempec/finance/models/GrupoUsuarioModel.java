package br.com.pempec.finance.models;

import java.io.Serializable;

public class GrupoUsuarioModel implements Serializable {

    private GrupoUsuarioIDModel grupoUsuarioIDModel;

    public GrupoUsuarioIDModel getGrupoUsuarioIDModel() {
        return grupoUsuarioIDModel;
    }

    public void setGrupoUsuarioIDModel(GrupoUsuarioIDModel grupoUsuarioIDModel) {
        this.grupoUsuarioIDModel = grupoUsuarioIDModel;
    }
}

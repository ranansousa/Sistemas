package br.com.pempec.finance.models;

import java.io.Serializable;

public class GrupoActionModel implements Serializable {

    private GrupoActionIDModel grupoActionIDModel;

    public GrupoActionIDModel getGrupoActionIDModel() {
        return grupoActionIDModel;
    }

    public void setGrupoActionIDModel(GrupoActionIDModel grupoActionIDModel) {
        this.grupoActionIDModel = grupoActionIDModel;
    }
}

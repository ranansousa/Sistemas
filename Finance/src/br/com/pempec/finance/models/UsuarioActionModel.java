/**
 *
 */
package br.com.pempec.finance.models;

import java.io.Serializable;

public class UsuarioActionModel implements Serializable {

    private UsuarioActionIDModel usuarioActionIDModel;

    public UsuarioActionIDModel getUsuarioActionIDModel() {
        return usuarioActionIDModel;
    }

    public void setUsuarioActionIDModel(
            UsuarioActionIDModel usuarioActionIDModel) {
        this.usuarioActionIDModel = usuarioActionIDModel;
    }
}

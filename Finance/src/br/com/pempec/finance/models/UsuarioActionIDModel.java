/**
 *
 */
package br.com.pempec.finance.models;

import java.io.Serializable;

public class UsuarioActionIDModel implements Serializable {

    private Long id;
    private UsuarioModel usuario;
    private TelaModel tela;
    private ActionModel action;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public TelaModel getTela() {
        return tela;
    }

    public void setTela(TelaModel tela) {
        this.tela = tela;
    }

    public ActionModel getAction() {
        return action;
    }

    public void setAction(ActionModel action) {
        this.action = action;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((action == null) ? 0 : action.hashCode());
        result = prime * result + ((tela == null) ? 0 : tela.hashCode());
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UsuarioActionIDModel)) {
            return false;
        }
        final UsuarioActionIDModel other = (UsuarioActionIDModel) obj;
        if (action == null) {
            if (other.action != null) {
                return false;
            }
        } else if (!action.equals(other.action)) {
            return false;
        }
        if (tela == null) {
            if (other.tela != null) {
                return false;
            }
        } else if (!tela.equals(other.tela)) {
            return false;
        }
        if (usuario == null) {
            if (other.usuario != null) {
                return false;
            }
        } else if (!usuario.equals(other.usuario)) {
            return false;
        }
        return true;
    }
}

package br.com.pempec.finance.models;

import java.io.Serializable;

public class GrupoActionIDModel implements Serializable {

    private Long id;
    private GrupoModel grupo;
    private TelaModel tela;
    private ActionModel action;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GrupoModel getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoModel grupo) {
        this.grupo = grupo;
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
        result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
        result = prime * result + ((tela == null) ? 0 : tela.hashCode());
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
        if (!(obj instanceof GrupoActionIDModel)) {
            return false;
        }
        final GrupoActionIDModel other = (GrupoActionIDModel) obj;
        if (action == null) {
            if (other.action != null) {
                return false;
            }
        } else if (!action.equals(other.action)) {
            return false;
        }
        if (grupo == null) {
            if (other.grupo != null) {
                return false;
            }
        } else if (!grupo.equals(other.grupo)) {
            return false;
        }
        if (tela == null) {
            if (other.tela != null) {
                return false;
            }
        } else if (!tela.equals(other.tela)) {
            return false;
        }
        return true;
    }
}

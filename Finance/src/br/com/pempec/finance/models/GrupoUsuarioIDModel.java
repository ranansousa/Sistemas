package br.com.pempec.finance.models;

import java.io.Serializable;

public class GrupoUsuarioIDModel implements Serializable {

    private Long id;
    private UsuarioModel usuario;
    private GrupoModel grupo;

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

    public GrupoModel getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoModel grupo) {
        this.grupo = grupo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
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
        if (!(obj instanceof GrupoUsuarioIDModel)) {
            return false;
        }
        final GrupoUsuarioIDModel other = (GrupoUsuarioIDModel) obj;
        if (grupo == null) {
            if (other.grupo != null) {
                return false;
            }
        } else if (!grupo.equals(other.grupo)) {
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

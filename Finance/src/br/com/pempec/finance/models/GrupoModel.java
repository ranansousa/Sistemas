package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Collection;

public class GrupoModel implements Serializable {

    private Boolean check = false;
    private Long id;
    private String descricao;
    private MovimentoDiarioModel movimentoDiarioModel;
    private Collection<GrupoActionModel> listaPermissoes;

    public Collection<GrupoActionModel> getListaPermissoes() {
        return listaPermissoes;
    }

    public void setListaPermissoes(Collection<GrupoActionModel> listaPermissoes) {
        this.listaPermissoes = listaPermissoes;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (!(obj instanceof GrupoModel)) {
            return false;
        }
        final GrupoModel other = (GrupoModel) obj;
        if (descricao == null) {
            if (other.descricao != null) {
                return false;
            }
        } else if (!descricao.equals(other.descricao)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }
}

package br.com.pempec.finance.models;

import java.io.Serializable;

public class PKModel implements Serializable {

    private String id;
    private OrganizacaoModel organizacao;

    public PKModel() {
        super();
    }

    public PKModel(String id, OrganizacaoModel organizacao) {
        super();
        this.id = id;
        this.organizacao = organizacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrganizacaoModel getOrganizacao() {
        return organizacao;
    }

    public void setOrganizacao(OrganizacaoModel organizacao) {
        this.organizacao = organizacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((organizacao == null) ? 0 : organizacao.hashCode());
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PKModel other = (PKModel) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (organizacao == null) {
            if (other.organizacao != null) {
                return false;
            }
        } else if (!organizacao.equals(other.organizacao)) {
            return false;
        }
        return true;
    }
}

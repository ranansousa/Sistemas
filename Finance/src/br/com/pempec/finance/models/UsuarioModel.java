/**
 *
 */
package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class UsuarioModel implements Serializable {

    private Long id;
    private String login;
    private String nome;
    private String senha;
    private Integer ativo;
    private Integer ehAdministrador;
    private Date ultimoAcesso;
    private OrganizacaoModel organizacao;
    private MovimentoDiarioModel movimentoDiarioModel;
    private Collection<GrupoModel> listaGrupos;
    private Collection<UsuarioActionModel> listaPermissoes;

    public Integer getEhAdministrador() {
        return ehAdministrador;
    }

    public void setEhAdministrador(Integer ehAdministrador) {
        this.ehAdministrador = ehAdministrador;
    }

    public Collection<UsuarioActionModel> getListaPermissoes() {
        return listaPermissoes;
    }

    public void setListaPermissoes(Collection<UsuarioActionModel> listaPermissoes) {
        this.listaPermissoes = listaPermissoes;
    }

    public Collection<GrupoModel> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(Collection<GrupoModel> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public String getName() {
        return this.login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getAtivo() {
        return ativo;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Considerando no Banco: 0 - Inativo 1 - Ativo
     *
     * @return ativo
     */
    
    /*
    public Boolean isAtivo() {
        Boolean aux = false;


        if (ativo != null && ativo == 1) {
            aux = true;
        }


        return aux;
    }
    
    */

    public OrganizacaoModel getOrganizacao() {
        return organizacao;
    }
    
    

    public void setOrganizacao(OrganizacaoModel organizacao) {
        this.organizacao = organizacao;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioModel other = (UsuarioModel) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.login != other.login && (this.login == null || !this.login.equals(other.login))) {
            return false;
        }
        if (this.nome != other.nome && (this.nome == null || !this.nome.equals(other.nome))) {
            return false;
        }
        if (this.senha != other.senha && (this.senha == null || !this.senha.equals(other.senha))) {
            return false;
        }
        if (this.ativo != other.ativo && (this.ativo == null || !this.ativo.equals(other.ativo))) {
            return false;
        }
        if (this.ultimoAcesso != other.ultimoAcesso && (this.ultimoAcesso == null || !this.ultimoAcesso.equals(other.ultimoAcesso))) {
            return false;
        }
        if (this.organizacao != other.organizacao && (this.organizacao == null || !this.organizacao.equals(other.organizacao))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 29 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 29 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 29 * hash + (this.senha != null ? this.senha.hashCode() : 0);
        hash = 29 * hash + (this.ativo != null ? this.ativo.hashCode() : 0);
        hash = 29 * hash + (this.ultimoAcesso != null ? this.ultimoAcesso.hashCode() : 0);
        hash = 29 * hash + (this.organizacao != null ? this.organizacao.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}

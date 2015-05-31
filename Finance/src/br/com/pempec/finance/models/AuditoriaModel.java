/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.models;

import java.util.Date;

/**
 *
 * @author Alexandre
 */
public class AuditoriaModel {

    private String id;
    private UsuarioModel usuario;
    private ActionModel acao;
    private TelaModel tela;
    private String idObjeto;
    private String idOrganizacao;
    private Date data;

    public ActionModel getAcao() {
        return acao;
    }

    public void setAcao(ActionModel acao) {
        this.acao = acao;
    }

    public String getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(String idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getIdOrganizacao() {
        return idOrganizacao;
    }

    public void setIdOrganizacao(String idOrganizacao) {
        this.idOrganizacao = idOrganizacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TelaModel getTela() {
        return tela;
    }

    public void setTela(TelaModel tela) {
        this.tela = tela;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
}

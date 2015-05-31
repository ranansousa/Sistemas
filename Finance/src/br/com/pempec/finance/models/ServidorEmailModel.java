package br.com.pempec.finance.models;

import java.io.Serializable;

public class ServidorEmailModel implements Serializable {

    private Long id;
    private String host;
    private String remetente;
    private Integer requerAutenticacao;
    private String login;
    private String senha;
    private MovimentoDiarioModel movimentoDiario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getRequerAutenticacao() {
        return requerAutenticacao;
    }

    public Boolean getRequerAutenticacaoBoolean() {
        return (this.getRequerAutenticacao() != null && this.getRequerAutenticacao() == 1);
    }

    public void setRequerAutenticacao(Integer requerAutenticacao) {
        this.requerAutenticacao = requerAutenticacao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public MovimentoDiarioModel getMovimentoDiario() {
        return movimentoDiario;
    }

    public void setMovimentoDiario(MovimentoDiarioModel movimentoDiario) {
        this.movimentoDiario = movimentoDiario;
    }

    @Override
    public String toString() {
        return this.getHost();
    }
}

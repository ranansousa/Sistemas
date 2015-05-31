package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Date;

public class ParametrosModel implements Serializable {

    private String codigo;
    private String mensagem;
    private String tela;
    private Date data;
    private Double valor;
    private Double porcentagem;
    private Integer status;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(Double porcentagem) {
        this.porcentagem = porcentagem;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTela() {
        return tela;
    }

    public void setTela(String tela) {
        this.tela = tela;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean isAtivo() {
        return (this.status != null && this.status.intValue() != 0);
    }

    @Override
    public String toString() {
        return "Codigo: " + this.codigo
                + "\nMensagem: " + this.mensagem
                + "\nTela: " + this.tela
                + "\nStatus: " + this.status
                + "\nValor: " + this.valor
                + "\nPorcentam: " + this.porcentagem
                + "\nData: " + this.data;
    }
}

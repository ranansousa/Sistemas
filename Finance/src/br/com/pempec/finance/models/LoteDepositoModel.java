/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Date;

/**
 * * @author PEMPEC
 */
public class LoteDepositoModel extends FinancePkModel implements Serializable {

    private String lote; //numero do lote
    private Date dataRegistro; //data inicio do registro do lote
    private UsuarioModel usuario;
    private String status; //exportado - removido
    private Date dataAtualizacao; //ultima data que ocorreu manutencao
    private long qtdCheque;

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public long getQtdCheque() {
        return qtdCheque;
    }

    public void setQtdCheque(long qtdCheque) {
        this.qtdCheque = qtdCheque;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return this.getLote();
    }
}//fim


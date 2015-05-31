/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.models;

import java.util.Date;

/**
 *
 * @author PEMPEC
 */
public class ImpressaoMultiplosRecibosFilter {

    private Date dataPagamento;
    private OrganizacaoModel organizacao;
    private String numeroDocumentoInicial;
    private String numeroDocumentoFinal;

    public OrganizacaoModel getOrganizacao() {
        return organizacao;
    }

    public void setOrganizacao(OrganizacaoModel organizacao) {
        this.organizacao = organizacao;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getNumeroDocumentoFinal() {
        return numeroDocumentoFinal;
    }

    public void setNumeroDocumentoFinal(String numeroDocumentoFinal) {
        this.numeroDocumentoFinal = numeroDocumentoFinal;
    }

    public String getNumeroDocumentoInicial() {
        return numeroDocumentoInicial;
    }

    public void setNumeroDocumentoInicial(String numeroDocumentoInicial) {
        this.numeroDocumentoInicial = numeroDocumentoInicial;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.models.reports;

import java.io.Serializable;
import java.util.Date;

/**
 * Esta classe e utilizada para guardar os filtros do relatorio.
 *
 * @author PEMPEC
 */
public class FilterReportContaBancariaTransferencia implements Serializable {

    private String organizacao;
    private Date dataInicial;
    private Date dataFinal;
    private String responsavel;
    private String contaBancariaCreditoModel;
    private String contaBancariaDebitoModel;
    private String tipoOperacaoBancariaModel;
    private String loteContabil;
    private String usuario;

    public String getContaBancariaCreditoModel() {
        return contaBancariaCreditoModel;
    }

    public void setContaBancariaCreditoModel(String contaBancariaCreditoModel) {
        this.contaBancariaCreditoModel = contaBancariaCreditoModel;
    }

    public String getContaBancariaDebitoModel() {
        return contaBancariaDebitoModel;
    }

    public void setContaBancariaDebitoModel(String contaBancariaDebitoModel) {
        this.contaBancariaDebitoModel = contaBancariaDebitoModel;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getLoteContabil() {
        return loteContabil;
    }

    public void setLoteContabil(String loteContabil) {
        this.loteContabil = loteContabil;
    }

    public String getOrganizacao() {
        return organizacao;
    }

    public void setOrganizacao(String organizacao) {
        this.organizacao = organizacao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getTipoOperacaoBancariaModel() {
        return tipoOperacaoBancariaModel;
    }

    public void setTipoOperacaoBancariaModel(String tipoOperacaoBancariaModel) {
        this.tipoOperacaoBancariaModel = tipoOperacaoBancariaModel;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
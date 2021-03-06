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
public class FilterReportContaBancariaDebito implements Serializable {

    private String organizacao;
    private Date dataInicial;
    private Date dataFinal;
    private String responsavel;
    private String contaBancaria;
    private String tipoOperacaoBancaria;
    private String tituloPagar;
    private String contaBancariaCheque;
    private String loteContabil;
    private String usuario;

    public String getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(String contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public String getContaBancariaCheque() {
        return contaBancariaCheque;
    }

    public void setContaBancariaCheque(String contaBancariaCheque) {
        this.contaBancariaCheque = contaBancariaCheque;
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

    public String getTipoOperacaoBancaria() {
        return tipoOperacaoBancaria;
    }

    public void setTipoOperacaoBancaria(String tipoOperacaoBancaria) {
        this.tipoOperacaoBancaria = tipoOperacaoBancaria;
    }

    public String getTituloPagar() {
        return tituloPagar;
    }

    public void setTituloPagar(String tituloPagar) {
        this.tituloPagar = tituloPagar;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
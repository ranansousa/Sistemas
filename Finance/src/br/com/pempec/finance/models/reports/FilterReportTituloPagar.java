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
//Filter utilizado tambem na FichaCadastralCedente com o parametro CEDENTE
//Filter utilizado tambem no Relatorio Cartao  com o parametro CEDENTE e Data Vcto
public class FilterReportTituloPagar implements Serializable {

    private String organizacao;
    private Date dataInicial;
    private Date dataFinal;
    private String cedente;
    private String tipoCobranca;
    private String localPagamento;
    private String historico;
    private String centroCusto;
    private String responsavel;
    private String status;

    public String getCedente() {
        return cedente;
    }

    public void setCedente(String cedente) {
        this.cedente = cedente;
    }

    public String getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(String centroCusto) {
        this.centroCusto = centroCusto;
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

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getLocalPagamento() {
        return localPagamento;
    }

    public void setLocalPagamento(String localPagamento) {
        this.localPagamento = localPagamento;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipoCobranca() {
        return tipoCobranca;
    }

    public void setTipoCobranca(String tipoCobranca) {
        this.tipoCobranca = tipoCobranca;
    }
}
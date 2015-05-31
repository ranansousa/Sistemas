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
public class ReportFluxoCaixaReceber implements Serializable {

    private String numeroDocumentoReceber;
    private String descricaoReceber;
    private String historicoReceber;
    private Date dataVencimentoReceber;
    private String centroCustoReceber;
    private String parcelaReceber;
    private Double valorNominalReceber;
    private String localPagamentoReceber;
    private String tipoCobrancaReceber;

    public String getCentroCustoReceber() {
        return centroCustoReceber;
    }

    public void setCentroCustoReceber(String centroCustoReceber) {
        this.centroCustoReceber = centroCustoReceber;
    }

    public Date getDataVencimentoReceber() {
        return dataVencimentoReceber;
    }

    public void setDataVencimentoReceber(Date dataVencimentoReceber) {
        this.dataVencimentoReceber = dataVencimentoReceber;
    }

    public String getDescricaoReceber() {
        return descricaoReceber;
    }

    public void setDescricaoReceber(String descricaoReceber) {
        this.descricaoReceber = descricaoReceber;
    }

    public String getHistoricoReceber() {
        return historicoReceber;
    }

    public void setHistoricoReceber(String historicoReceber) {
        this.historicoReceber = historicoReceber;
    }

    public String getLocalPagamentoReceber() {
        return localPagamentoReceber;
    }

    public void setLocalPagamentoReceber(String localPagamentoReceber) {
        this.localPagamentoReceber = localPagamentoReceber;
    }

    public String getNumeroDocumentoReceber() {
        return numeroDocumentoReceber;
    }

    public void setNumeroDocumentoReceber(String numeroDocumentoReceber) {
        this.numeroDocumentoReceber = numeroDocumentoReceber;
    }

    public String getParcelaReceber() {
        return parcelaReceber;
    }

    public void setParcelaReceber(String parcelaReceber) {
        this.parcelaReceber = parcelaReceber;
    }

    public String getTipoCobrancaReceber() {
        return tipoCobrancaReceber;
    }

    public void setTipoCobrancaReceber(String tipoCobrancaReceber) {
        this.tipoCobrancaReceber = tipoCobrancaReceber;
    }

    public Double getValorNominalReceber() {
        return valorNominalReceber;
    }

    public void setValorNominalReceber(Double valorNominalReceber) {
        this.valorNominalReceber = valorNominalReceber;
    }
}
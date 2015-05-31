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
public class ReportFluxoCaixaPagar implements Serializable {

    private String numeroDocumentoPagar;
    private String descricaoPagar;
    private String historicoPagar;
    private Date dataVencimentoPagar;
    private String centroCustoPagar;
    private String parcelaPagar;
    private Double valorNominalPagar;
    private String localPagamentoPagar;
    private String tipoCobrancaPagar;

    public String getCentroCustoPagar() {
        return centroCustoPagar;
    }

    public void setCentroCustoPagar(String centroCustoPagar) {
        this.centroCustoPagar = centroCustoPagar;
    }

    public Date getDataVencimentoPagar() {
        return dataVencimentoPagar;
    }

    public void setDataVencimentoPagar(Date dataVencimentoPagar) {
        this.dataVencimentoPagar = dataVencimentoPagar;
    }

    public String getDescricaoPagar() {
        return descricaoPagar;
    }

    public void setDescricaoPagar(String descricaoPagar) {
        this.descricaoPagar = descricaoPagar;
    }

    public String getHistoricoPagar() {
        return historicoPagar;
    }

    public void setHistoricoPagar(String historicoPagar) {
        this.historicoPagar = historicoPagar;
    }

    public String getLocalPagamentoPagar() {
        return localPagamentoPagar;
    }

    public void setLocalPagamentoPagar(String localPagamentoPagar) {
        this.localPagamentoPagar = localPagamentoPagar;
    }

    public String getNumeroDocumentoPagar() {
        return numeroDocumentoPagar;
    }

    public void setNumeroDocumentoPagar(String numeroDocumentoPagar) {
        this.numeroDocumentoPagar = numeroDocumentoPagar;
    }

    public String getParcelaPagar() {
        return parcelaPagar;
    }

    public void setParcelaPagar(String parcelaPagar) {
        this.parcelaPagar = parcelaPagar;
    }

    public String getTipoCobrancaPagar() {
        return tipoCobrancaPagar;
    }

    public void setTipoCobrancaPagar(String tipoCobrancaPagar) {
        this.tipoCobrancaPagar = tipoCobrancaPagar;
    }

    public Double getValorNominalPagar() {
        return valorNominalPagar;
    }

    public void setValorNominalPagar(Double valorNominalPagar) {
        this.valorNominalPagar = valorNominalPagar;
    }
}
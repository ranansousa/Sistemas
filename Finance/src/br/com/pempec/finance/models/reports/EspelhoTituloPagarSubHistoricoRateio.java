/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.models.reports;

import java.io.Serializable;

/**
 * Esta classe e utilizada para guardar os filtros do relatorio.
 *
 * @author PEMPEC
 */
public class EspelhoTituloPagarSubHistoricoRateio implements Serializable {

    private String codigoHistorico;
    private String historico;
    private Double valor;

    public String getCodigoHistorico() {
        return codigoHistorico;
    }

    public void setCodigoHistorico(String codigoHistorico) {
        this.codigoHistorico = codigoHistorico;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
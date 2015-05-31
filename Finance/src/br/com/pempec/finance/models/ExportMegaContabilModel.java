/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author PEMPEC
 */
public class ExportMegaContabilModel {

    private static final String DATA_PATTERN = "dd/MM/yyyy";
    private static final String TIPO_LANCAMENTOS = "04";
    private Integer centroCusto;
    private Date dataLancamento;
    private String contaDebito;
    private String digitoContaDebito;
    private String contaCredito;
    private String digitoContaCredito;
    private Integer codigoHistoricoPadrao;
    private String historico;
    private Double valor;

    public String getTipoLancamento() {

        StringBuilder linha = new StringBuilder();

        linha.append(TIPO_LANCAMENTOS);

        if (validaCampo(centroCusto)) {

            linha.append(String.format("%06d", Long.valueOf(getCentroCusto())));

        } else {

            linha.append(String.format("%06d", Long.valueOf(0)));

        }

        linha.append(String.format("%06d", Long.valueOf(0)));

        linha.append(new SimpleDateFormat(DATA_PATTERN).format(getDataLancamento()));

        if (validaCampo(contaDebito) && validaCampo(digitoContaDebito)) {

            linha.append(String.format("%-14s", getContaDebito()));
            linha.append(String.format("%-1s", getDigitoContaDebito()));

        } else {

            linha.append(String.format("%-15s", ""));

        }

        if (validaCampo(contaCredito) && validaCampo(digitoContaCredito)) {

            linha.append(String.format("%-14s", getContaCredito()));
            linha.append(String.format("%-1s", getDigitoContaCredito()));

        } else {

            linha.append(String.format("%-15s", ""));

        }

        if (validaCampo(codigoHistoricoPadrao)) {

            linha.append(String.format("%06d", getCodigoHistoricoPadrao()));

        } else {

            linha.append(String.format("%06d", Long.valueOf(0)));

        }

        if (validaCampo(historico)) {

            linha.append(String.format("%-50s", getHistorico()));

        } else {

            linha.append(String.format("%-50s", ""));

        }

        linha.append(String.format("%018.2f", getValor()).replace(',', '.'));

        linha.append(String.format("%-1s", ""));

        linha.append(String.format("%-6s", ""));

        linha.append(String.format("%-6s", ""));

        return linha.toString();

    }

    private static Boolean validaCampo(String object) {
        return (object != null && !object.isEmpty());
    }

    private static Boolean validaCampo(Integer object) {
        return object != null;
    }

    public Integer getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(Integer centroCusto) {
        this.centroCusto = centroCusto;
    }

    public Integer getCodigoHistoricoPadrao() {
        return codigoHistoricoPadrao;
    }

    public void setCodigoHistoricoPadrao(Integer codigoHistoricoPadrao) {
        this.codigoHistoricoPadrao = codigoHistoricoPadrao;
    }

    public String getContaCredito() {
        if (contaCredito != null) {
            if (contaCredito.trim().length() > 14) {
                return contaCredito.trim().substring(0, 14);
            } else {
                return contaCredito.trim();
            }
        }
        return null;
    }

    public void setContaCredito(String contaCredito) {
        this.contaCredito = contaCredito;
    }

    public String getContaDebito() {
        if (contaDebito != null) {
            if (contaDebito.trim().length() > 14) {
                return contaDebito.trim().substring(0, 14);
            } else {
                return contaDebito.trim();
            }
        }
        return null;
    }

    public void setContaDebito(String contaDebito) {
        this.contaDebito = contaDebito;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDigitoContaCredito() {
        if (digitoContaCredito != null) {
            if (digitoContaCredito.trim().length() > 1) {
                return digitoContaCredito.trim().substring(0, 1);
            } else {
                return digitoContaCredito.trim();
            }
        }
        return null;
    }

    public void setDigitoContaCredito(String digitoContaCredito) {
        this.digitoContaCredito = digitoContaCredito;
    }

    public String getDigitoContaDebito() {
        if (digitoContaDebito != null) {
            if (digitoContaDebito.trim().length() > 1) {
                return digitoContaDebito.trim().substring(0, 1);
            } else {
                return digitoContaDebito.trim();
            }
        }
        return null;
    }

    public void setDigitoContaDebito(String digitoContaDebito) {
        this.digitoContaDebito = digitoContaDebito;
    }

    public String getHistorico() {
        if (historico != null) {
            if (historico.trim().length() > 50) {
                return historico.trim().substring(0, 50);
            } else {
                return historico.trim();
            }
        }
        return null;
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
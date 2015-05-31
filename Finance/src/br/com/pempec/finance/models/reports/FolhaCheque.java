package br.com.pempec.finance.models.reports;

import java.io.Serializable;

/**
 * Esta classe e utilizada para guardar os filtros do relatorio.
 *
 * @author PEMPEC
 */
public class FolhaCheque implements Serializable {

    private Double valor;
    private String extenso;
    private String extenso2 = "*****************";
    private String portador;
    private String cidade = "SALVADOR";
    private String dia;
    private String mes;
    private String ano;

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCidade() {
        return cidade.toUpperCase();
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getExtenso() {
        return extenso.toUpperCase();
    }

    public void setExtenso(String extenso) {
        this.extenso = extenso;
    }

    public String getExtenso2() {
        return extenso2.toUpperCase();
    }

    public void setExtenso2(String extenso2) {
        this.extenso2 = extenso2;
    }

    public String getMes() {
        return mes.toUpperCase();
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getPortador() {
        return portador.toUpperCase();
    }

    public void setPortador(String portador) {
        this.portador = portador;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}//fim
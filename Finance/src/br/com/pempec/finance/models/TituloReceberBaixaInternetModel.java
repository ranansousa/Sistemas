package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Equipe Pempec
 */
public class TituloReceberBaixaInternetModel extends FinancePkModel implements Serializable {

    private ContaBancariaModel contaBancaria;
    private TituloReceberBaixaModel tituloReceberBaixa;
    private TipoOperacaoBancariaModel tipoOperacaoBancaria;
    private Date dataOperacao;
    private Double valor;
    private String detalhamento;

    public ContaBancariaModel getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancariaModel contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public Date getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(Date dataOperacao) {
        this.dataOperacao = dataOperacao;
    }

    public String getDetalhamento() {
        return detalhamento;
    }

    public void setDetalhamento(String detalhamento) {
        this.detalhamento = detalhamento;
    }

    public TipoOperacaoBancariaModel getTipoOperacaoBancaria() {
        return tipoOperacaoBancaria;
    }

    public void setTipoOperacaoBancaria(TipoOperacaoBancariaModel tipoOperacaoBancaria) {
        this.tipoOperacaoBancaria = tipoOperacaoBancaria;
    }

    public TituloReceberBaixaModel getTituloReceberBaixa() {
        return tituloReceberBaixa;
    }

    public void setTituloReceberBaixa(TituloReceberBaixaModel tituloReceberBaixa) {
        this.tituloReceberBaixa = tituloReceberBaixa;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}

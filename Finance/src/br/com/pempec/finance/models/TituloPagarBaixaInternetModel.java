package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Equipe Pempec
 */
public class TituloPagarBaixaInternetModel extends FinancePkModel implements Serializable {

    private ContaBancariaModel contaBancaria;
    private TituloPagarBaixaModel tituloPagarBaixa;
    private TipoOperacaoBancariaModel tipoOperacaoBancaria;
    private BancoModel bancoDestino;
    private Date dataOperacao;
    private Double valor;
    private String detalhamento;
    private String cpfCpnjCorrentista;
    private String nomeCorrentista;
    private String agenciaDestino;
    private String contaDestino;
    private String personalidadeCorrentista;

    public String getPersonalidadeCorrentista() {
        return personalidadeCorrentista;
    }

    public void setPersonalidadeCorrentista(String personalidadeCorrentista) {
        this.personalidadeCorrentista = personalidadeCorrentista;
    }

    public String getAgenciaDestino() {
        return agenciaDestino;
    }

    public void setAgenciaDestino(String agenciaDestino) {
        this.agenciaDestino = agenciaDestino;
    }

    public BancoModel getBancoDestino() {
        return bancoDestino;
    }

    public void setBancoDestino(BancoModel bancoDestino) {
        this.bancoDestino = bancoDestino;
    }

    public ContaBancariaModel getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancariaModel contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public String getCpfCpnjCorrentista() {
        return cpfCpnjCorrentista;
    }

    public void setCpfCpnjCorrentista(String cpfCpnjCorrentista) {
        this.cpfCpnjCorrentista = cpfCpnjCorrentista;
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

    public String getNomeCorrentista() {
        return nomeCorrentista;
    }

    public void setNomeCorrentista(String nomeCorrentista) {
        this.nomeCorrentista = nomeCorrentista;
    }

    public TipoOperacaoBancariaModel getTipoOperacaoBancaria() {
        return tipoOperacaoBancaria;
    }

    public void setTipoOperacaoBancaria(TipoOperacaoBancariaModel tipoOperacaoBancaria) {
        this.tipoOperacaoBancaria = tipoOperacaoBancaria;
    }

    public TituloPagarBaixaModel getTituloPagarBaixa() {
        return tituloPagarBaixa;
    }

    public void setTituloPagarBaixa(TituloPagarBaixaModel tituloPagarBaixa) {
        this.tituloPagarBaixa = tituloPagarBaixa;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}

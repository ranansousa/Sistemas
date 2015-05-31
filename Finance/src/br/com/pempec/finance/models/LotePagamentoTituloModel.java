/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Equipe Pempec
 */
public class LotePagamentoTituloModel extends FinancePkModel implements Serializable {

    private UsuarioModel usuario;
    private ContaBancariaModel contaBancariaModel;
    private FuncionarioModel responsavel;
    private ContaBancariaChequeModel cheque;
    private TipoOperacaoBancariaModel tipoOperacaoBancariaModel;
    private FormaPagamentoModel formaPagamentoModel;
    private MovimentoDiarioModel movimentoDiarioModel;
    private String lote; //numero do lote
    private String status;
    private Date dataRegistro; //data inicio do registro do lote
    private Date dataPagamento;
    private Double valor;
    private long qtdTituloPagar;

    public ContaBancariaChequeModel getCheque() {
        return cheque;
    }

    public void setCheque(ContaBancariaChequeModel cheque) {
        this.cheque = cheque;
    }

    public ContaBancariaModel getContaBancariaModel() {
        return contaBancariaModel;
    }

    public void setContaBancariaModel(ContaBancariaModel contaBancariaModel) {
        this.contaBancariaModel = contaBancariaModel;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public FormaPagamentoModel getFormaPagamentoModel() {
        return formaPagamentoModel;
    }

    public void setFormaPagamentoModel(FormaPagamentoModel formaPagamentoModel) {
        this.formaPagamentoModel = formaPagamentoModel;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public long getQtdTituloPagar() {
        return qtdTituloPagar;
    }

    public void setQtdTituloPagar(long qtdTituloPagar) {
        this.qtdTituloPagar = qtdTituloPagar;
    }

    public FuncionarioModel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(FuncionarioModel responsavel) {
        this.responsavel = responsavel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TipoOperacaoBancariaModel getTipoOperacaoBancariaModel() {
        return tipoOperacaoBancariaModel;
    }

    public void setTipoOperacaoBancariaModel(TipoOperacaoBancariaModel tipoOperacaoBancariaModel) {
        this.tipoOperacaoBancariaModel = tipoOperacaoBancariaModel;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return this.getLote();
    }
}// fim da class


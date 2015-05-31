package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Equipe Pempec
 */
public class TituloReceberBaixaChequeModel extends FinancePkModel implements Serializable {

    private Boolean check = false;
    private BancoModel banco;
    private TituloReceberBaixaModel tituloReceberBaixa;
    private Date dataProtocolo;
    private Date dataDeposito;
    private String agencia;
    private String numeroCheque;
    private String conta;
    private String titular;
    private String personalidade;
    private String cpfCnpj;
    private Double valor;
    private LoteDepositoModel loteDeposito;
    private MovimentoDiarioModel movimentoDiarioModel;
    private String observacao;

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public BancoModel getBanco() {
        return banco;
    }

    public void setBanco(BancoModel banco) {
        this.banco = banco;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Date getDataProtocolo() {
        return dataProtocolo;
    }

    public void setDataProtocolo(Date dataProtocolo) {
        this.dataProtocolo = dataProtocolo;
    }

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public String getPersonalidade() {
        return personalidade;
    }

    public void setPersonalidade(String personalidade) {
        this.personalidade = personalidade;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
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

    public Date getDataDeposito() {
        return dataDeposito;
    }

    public void setDataDeposito(Date dataDeposito) {
        this.dataDeposito = dataDeposito;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public LoteDepositoModel getLoteDeposito() {
        return loteDeposito;
    }

    public void setLoteDeposito(LoteDepositoModel loteDeposito) {
        this.loteDeposito = loteDeposito;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return this.getNumeroCheque();
    }
}// fim da classe


package br.com.pempec.finance.models;

import java.io.Serializable;

public class ContaBancariaModel extends FinancePkModel implements Serializable {

    private BancoModel banco;
    private String conta;
    private String agencia;
    private String observacao;
    private String titular;
    private String dependente;
    private Double limiteCredito;
    private Double saldoInicial;
    private UsuarioModel usuario;
    private ContaContabilModel contaContabil;
    private MovimentoDiarioModel movimentoDiarioModel;

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public ContaContabilModel getContaContabil() {
        return contaContabil;
    }

    public void setContaContabil(ContaContabilModel contaContabil) {
        this.contaContabil = contaContabil;
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

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getDependente() {
        return dependente;
    }

    public void setDependente(String dependente) {
        this.dependente = dependente;
    }

    public Double getLimiteCredito() {
        return limiteCredito;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setLimiteCredito(Double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return this.getConta();
    }
}// fim da class


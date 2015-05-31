package br.com.pempec.finance.models;

import java.io.Serializable;
//Equipe Pempec

public class SacadoModel extends FinancePkModel implements Serializable {

    private TipoSacadoModel tipoSacado;
    private EnderecoModel endereco;
    private ContatoModel contato;
    private String nome;
    private String cpfCnpj;
    private String personalidade;
    private String conta;
    private String agencia;
    private BancoModel banco;
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

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
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

    public String getPersonalidade() {
        return personalidade;
    }

    public void setPersonalidade(String personalidade) {
        this.personalidade = personalidade;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public TipoSacadoModel getTipoCedente() {
        return tipoSacado;
    }

    public void setTipoCedente(TipoSacadoModel tipoCedente) {
        this.tipoSacado = tipoCedente;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

    public TipoSacadoModel getTipoSacado() {
        return tipoSacado;
    }

    public void setTipoSacado(TipoSacadoModel tipoSacado) {
        this.tipoSacado = tipoSacado;
    }

    public ContatoModel getContato() {
        return contato;
    }

    public void setContato(ContatoModel contato) {
        this.contato = contato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}// fim da class


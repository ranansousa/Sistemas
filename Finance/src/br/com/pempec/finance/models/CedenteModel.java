package br.com.pempec.finance.models;

import java.io.Serializable;

//Equipe Pempec
public class CedenteModel extends FinancePkModel implements Serializable {

    private TipoCedenteModel tipoCedente;
    private EnderecoModel endereco;
    private ContatoModel contato;
    private String nome;
    private String cpfCnpj;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String cga;
    private String personalidade;
    private String conta;
    private String agencia;
    private BancoModel banco;
    private ContaContabilModel contaContabil;
    private CartaoCreditoModel cartaoCreditoModel;
    private MovimentoDiarioModel movimentoDiarioModel;

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public String getCga() {
        return cga;
    }

    public void setCga(String cga) {
        this.cga = cga;
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

    public TipoCedenteModel getTipoCedente() {
        return tipoCedente;
    }

    public void setTipoCedente(TipoCedenteModel tipoCedente) {
        this.tipoCedente = tipoCedente;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
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

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public CartaoCreditoModel getCartaoCreditoModel() {
        return cartaoCreditoModel;
    }

    public void setCartaoCreditoModel(CartaoCreditoModel cartaoCreditoModel) {
        this.cartaoCreditoModel = cartaoCreditoModel;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    @Override
    public String toString() {
        return this.getNome();
    }
}// fim da class

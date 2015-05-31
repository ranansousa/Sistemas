/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.models;

/**
 * Classe que armazena os dados provindos do Sistema MegaContabil para
 * sincronizar com a Conta Contabil deste Sistema (Finance).
 *
 * @author PEMPEC
 */
public class PlanoContasMegaContabil {

    private String id;
    private String descricao;
    private String conta;
    private String digitoConta;
    private String contaReduzida;
    private String digitoContaReduzida;
    private String inscricaoCMF;
    private String tipo;
    private String grau;
    private Integer ordemDIPJ;
    private Integer relacionamento;
    private Integer natureza;

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getContaReduzida() {
        return contaReduzida;
    }

    public void setContaReduzida(String contaReduzida) {
        this.contaReduzida = contaReduzida;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDigitoConta() {
        return digitoConta;
    }

    public void setDigitoConta(String digitoConta) {
        this.digitoConta = digitoConta;
    }

    public String getDigitoContaReduzida() {
        return digitoContaReduzida;
    }

    public void setDigitoContaReduzida(String digitoContaReduzida) {
        this.digitoContaReduzida = digitoContaReduzida;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInscricaoCMF() {
        return inscricaoCMF;
    }

    public void setInscricaoCMF(String inscricaoCMF) {
        this.inscricaoCMF = inscricaoCMF;
    }

    public Integer getNatureza() {
        return natureza;
    }

    public void setNatureza(Integer natureza) {
        this.natureza = natureza;
    }

    public Integer getOrdemDIPJ() {
        return ordemDIPJ;
    }

    public void setOrdemDIPJ(Integer ordemDIPJ) {
        this.ordemDIPJ = ordemDIPJ;
    }

    public Integer getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(Integer relacionamento) {
        this.relacionamento = relacionamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

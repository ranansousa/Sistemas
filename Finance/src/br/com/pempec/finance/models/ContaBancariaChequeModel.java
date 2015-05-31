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
public class ContaBancariaChequeModel extends FinancePkModel implements Serializable {

    private FuncionarioModel responsavel;
    private UsuarioModel usuario;
    private TipoStatusModel status;
    private ContaBancariaModel contaBancaria;
    private String numeroCheque;
    private Date dataRegistro;
    private Date dataEmissao;
    private Date dataCompensado;
    private Date dataEstornado;
    private Date dataPrevisaoCompensacao;
    private Double valor;
    private String observacao;
    private String portador;
    private MovimentoDiarioModel movimentoDiarioModel;
    private Integer qtdImpressao;

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public Date getDataEstornado() {
        return dataEstornado;
    }

    public void setDataEstornado(Date dataEstornado) {
        this.dataEstornado = dataEstornado;
    }

    public Date getDataPrevisaoCompensacao() {
        return dataPrevisaoCompensacao;
    }

    public void setDataPrevisaoCompensacao(Date dataPrevisaoCompensacao) {
        this.dataPrevisaoCompensacao = dataPrevisaoCompensacao;
    }

    public String getPortador() {
        return portador;
    }

    public void setPortador(String portador) {
        this.portador = portador;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public ContaBancariaModel getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancariaModel contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public Date getDataCompensado() {
        return dataCompensado;
    }

    public void setDataCompensado(Date dataCompensado) {
        this.dataCompensado = dataCompensado;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public FuncionarioModel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(FuncionarioModel responsavel) {
        this.responsavel = responsavel;
    }

    public TipoStatusModel getStatus() {
        return status;
    }

    public void setStatus(TipoStatusModel status) {
        this.status = status;
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

    public Integer getQtdImpressao() {
        return qtdImpressao;
    }

    public void setQtdImpressao(Integer qtdImpressao) {
        this.qtdImpressao = qtdImpressao;
    }

    @Override
    public String toString() {
        return this.getNumeroCheque();
    }
}//fim da class


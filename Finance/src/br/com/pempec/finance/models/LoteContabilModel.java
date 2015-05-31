/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Date;

/**
 * * @author PEMPEC
 */
public class LoteContabilModel extends FinancePkModel implements Serializable {

    private String lote; //numero do lote
    private Date dataRegistro; //data inicio do registro do lote
    private UsuarioModel usuario;
    private String status; //exportado - removido
    private Date dataAtualizacao; //ultima data que ocorreu manutencao
    private Long qtdTituloPagar;
    private Long qtdTituloReceber;
    private Long qtdTituloPagarBaixa;
    private Long qtdTituloReceberBaixa;
    private Long qtdTesourariaCredito;
    private Long qtdTesourariaDebito;
    private Long qtdContaBancariaCredito;
    private Long qtdContaBancariaDebito;
    private Long qtdContaBancariaTransferencia;
    private MovimentoDiarioModel movimentoDiarioModel;
    private Boolean exportarDefinitivo;
    private Date periodoInicial;
    private Date periodoFinal;

    public Date getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(Date periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public Date getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(Date periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Boolean getExportarDefinitivo() {
        return exportarDefinitivo;
    }

    public void setExportarDefinitivo(Boolean exportarDefinitivo) {
        this.exportarDefinitivo = exportarDefinitivo;
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

    public Long getQtdContaBancariaCredito() {
        return qtdContaBancariaCredito;
    }

    public void setQtdContaBancariaCredito(Long qtdContaBancariaCredito) {
        this.qtdContaBancariaCredito = qtdContaBancariaCredito;
    }

    public Long getQtdContaBancariaDebito() {
        return qtdContaBancariaDebito;
    }

    public void setQtdContaBancariaDebito(Long qtdContaBancariaDebito) {
        this.qtdContaBancariaDebito = qtdContaBancariaDebito;
    }

    public Long getQtdContaBancariaTransferencia() {
        return qtdContaBancariaTransferencia;
    }

    public void setQtdContaBancariaTransferencia(Long qtdContaBancariaTransferencia) {
        this.qtdContaBancariaTransferencia = qtdContaBancariaTransferencia;
    }

    public Long getQtdTesourariaCredito() {
        return qtdTesourariaCredito;
    }

    public void setQtdTesourariaCredito(Long qtdTesourariaCredito) {
        this.qtdTesourariaCredito = qtdTesourariaCredito;
    }

    public Long getQtdTesourariaDebito() {
        return qtdTesourariaDebito;
    }

    public void setQtdTesourariaDebito(Long qtdTesourariaDebito) {
        this.qtdTesourariaDebito = qtdTesourariaDebito;
    }

    public Long getQtdTituloPagar() {
        return qtdTituloPagar;
    }

    public void setQtdTituloPagar(Long qtdTituloPagar) {
        this.qtdTituloPagar = qtdTituloPagar;
    }

    public Long getQtdTituloPagarBaixa() {
        return qtdTituloPagarBaixa;
    }

    public void setQtdTituloPagarBaixa(Long qtdTituloPagarBaixa) {
        this.qtdTituloPagarBaixa = qtdTituloPagarBaixa;
    }

    public Long getQtdTituloReceber() {
        return qtdTituloReceber;
    }

    public void setQtdTituloReceber(Long qtdTituloReceber) {
        this.qtdTituloReceber = qtdTituloReceber;
    }

    public Long getQtdTituloReceberBaixa() {
        return qtdTituloReceberBaixa;
    }

    public void setQtdTituloReceberBaixa(Long qtdTituloReceberBaixa) {
        this.qtdTituloReceberBaixa = qtdTituloReceberBaixa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return this.getLote();
    }
}//fim


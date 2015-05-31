package br.com.pempec.finance.models;

import java.io.Serializable;

/**
 * @author Pempec
 */
public class TransferenciaEntreContasModel implements Serializable {

    private ContaBancariaCreditoModel contaBancariaCreditoModel;
    private ContaBancariaDebitoModel contaBancariaDebitoModel;
    private TipoOperacaoBancariaModel tipoOperacaoBancariaModel;
    private LoteContabilModel loteContabil;
    private FuncionarioModel responsavel;
    private Double valor;
    private String observacao;

    public ContaBancariaCreditoModel getContaBancariaCreditoModel() {
        return contaBancariaCreditoModel;
    }

    public void setContaBancariaCreditoModel(ContaBancariaCreditoModel contaBancariaCreditoModel) {
        this.contaBancariaCreditoModel = contaBancariaCreditoModel;
    }

    public ContaBancariaDebitoModel getContaBancariaDebitoModel() {
        return contaBancariaDebitoModel;
    }

    public void setContaBancariaDebitoModel(ContaBancariaDebitoModel contaBancariaDebitoModel) {
        this.contaBancariaDebitoModel = contaBancariaDebitoModel;
    }

    public TipoOperacaoBancariaModel getTipoOperacaoBancariaModel() {
        return tipoOperacaoBancariaModel;
    }

    public void setTipoOperacaoBancariaModel(TipoOperacaoBancariaModel tipoOperacaoBancariaModel) {
        this.tipoOperacaoBancariaModel = tipoOperacaoBancariaModel;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public FuncionarioModel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(FuncionarioModel responsavel) {
        this.responsavel = responsavel;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LoteContabilModel getLoteContabil() {
        return loteContabil;
    }

    public void setLoteContabil(LoteContabilModel loteContabil) {
        this.loteContabil = loteContabil;
    }
}

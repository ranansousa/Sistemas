package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Pempec
 */
public class ContaBancariaTransferenciaModel extends FinancePkModel implements Serializable {

    private ContaBancariaCreditoModel contaBancariaCreditoModel;
    private ContaBancariaDebitoModel contaBancariaDebitoModel;
    private TipoOperacaoBancariaModel tipoOperacaoBancariaModel;
    private UsuarioModel usuarioModel;
    private FuncionarioModel responsavel;
    private LoteContabilModel loteContabil;
    private Double valor;
    private String observacao;
    private Date dataRegistro;
    private Date dataMovimento;
    private String identificacao;
    private MovimentoDiarioModel movimentoDiarioModel;

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

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

    public Date getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(Date dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public LoteContabilModel getLoteContabil() {
        return loteContabil;
    }

    public void setLoteContabil(LoteContabilModel loteContabil) {
        this.loteContabil = loteContabil;
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

    public TipoOperacaoBancariaModel getTipoOperacaoBancariaModel() {
        return tipoOperacaoBancariaModel;
    }

    public void setTipoOperacaoBancariaModel(TipoOperacaoBancariaModel tipoOperacaoBancariaModel) {
        this.tipoOperacaoBancariaModel = tipoOperacaoBancariaModel;
    }

    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    public void setUsuarioModel(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return getIdentificacao().toString();
    }
}

package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Pempec
 */
public class NotaFiscalEmitidaModel extends FinancePkModel implements Serializable {

    private FuncionarioModel responsavel;
    private UsuarioModel usuario;
    private TipoNotaFiscalModel tipoDocumento;
    private MovimentoDiarioModel movimentoDiarioModel;
    private String numero;
    private String descricao;
    private String observacao;
    private String serie;
    private String subSerie;
    private String aliquota;
    private Date dataRegistro;
    private Date dataEmissao;
    private Date dataProtocolo;
    private Date dataRetencao;
    private Double valor;
    private Double valorIss;
    private Double baseCalculo;

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public String getAliquota() {
        return aliquota;
    }

    public void setAliquota(String aliquota) {
        this.aliquota = aliquota;
    }

    public Double getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(Double baseCalculo) {
        this.baseCalculo = baseCalculo;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataProtocolo() {
        return dataProtocolo;
    }

    public void setDataProtocolo(Date dataProtocolo) {
        this.dataProtocolo = dataProtocolo;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Date getDataRetencao() {
        return dataRetencao;
    }

    public void setDataRetencao(Date dataRetencao) {
        this.dataRetencao = dataRetencao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getSubSerie() {
        return subSerie;
    }

    public void setSubSerie(String subSerie) {
        this.subSerie = subSerie;
    }

    public TipoNotaFiscalModel getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoNotaFiscalModel tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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

    public Double getValorIss() {
        return valorIss;
    }

    public void setValorIss(Double valorIss) {
        this.valorIss = valorIss;
    }

    @Override
    public String toString() {
        return getNumero();
    }
}

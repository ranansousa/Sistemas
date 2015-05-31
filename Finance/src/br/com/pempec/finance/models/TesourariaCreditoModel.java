package br.com.pempec.finance.models;
/*

 EQUIPE PEMPEC
 */

import java.io.Serializable;
import java.util.Date;

public class TesourariaCreditoModel extends FinancePkModel implements Serializable {

    private HistoricoModel historico;
    private FuncionarioModel responsavel;
    private UsuarioModel usuario;
    private MovimentoDiarioModel movimentoDiarioModel;
    private Date dataRegistro;
    private Date dataMovimento;
    private Date dataContabil;
    private Double valorNominal;
    private String observacao;
    private String numeroDocumento;
    private String descricao;
    private String tipoLancamento;
    private SacadoModel sacadoModel;
    private ContaBancariaDebitoModel contaBancariaDebitoModel;
    private TituloReceberBaixaChequeModel tituloReceberBaixaChequeModel;
    private TituloReceberBaixaModel tituloReceberBaixaModel;
    private LoteContabilModel loteContabil;

    public LoteContabilModel getLoteContabil() {
        return loteContabil;
    }

    public void setLoteContabil(LoteContabilModel loteContabil) {
        this.loteContabil = loteContabil;
    }

    public Date getDataContabil() {
        return dataContabil;
    }

    public void setDataContabil(Date dataContabil) {
        this.dataContabil = dataContabil;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public HistoricoModel getHistorico() {
        return historico;
    }

    public void setHistorico(HistoricoModel historico) {
        this.historico = historico;
    }

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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

    public SacadoModel getSacadoModel() {
        return sacadoModel;
    }

    public void setSacadoModel(SacadoModel sacadoModel) {
        this.sacadoModel = sacadoModel;
    }

    public String getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(String tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public TituloReceberBaixaChequeModel getTituloReceberBaixaChequeModel() {
        return tituloReceberBaixaChequeModel;
    }

    public void setTituloReceberBaixaChequeModel(TituloReceberBaixaChequeModel tituloReceberBaixaChequeModel) {
        this.tituloReceberBaixaChequeModel = tituloReceberBaixaChequeModel;
    }

    public TituloReceberBaixaModel getTituloReceberBaixaModel() {
        return tituloReceberBaixaModel;
    }

    public void setTituloReceberBaixaModel(TituloReceberBaixaModel tituloReceberBaixaModel) {
        this.tituloReceberBaixaModel = tituloReceberBaixaModel;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public Double getValorNominal() {
        return valorNominal;
    }

    public ContaBancariaDebitoModel getContaBancariaDebitoModel() {
        return contaBancariaDebitoModel;
    }

    public void setContaBancariaDebitoModel(ContaBancariaDebitoModel contaBancariaDebitoModel) {
        this.contaBancariaDebitoModel = contaBancariaDebitoModel;
    }

    public void setValorNominal(Double valorNominal) {
        this.valorNominal = valorNominal;
    }

    @Override
    public String toString() {
        return this.getNumeroDocumento();
    }
}// fim da class


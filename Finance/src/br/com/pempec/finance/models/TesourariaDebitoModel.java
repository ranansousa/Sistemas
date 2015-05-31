package br.com.pempec.finance.models;
/*
 Equipe Pempec
 */

import java.io.Serializable;
import java.util.Date;

public class TesourariaDebitoModel extends FinancePkModel implements Serializable {

    private String numeroDocumento;
    private String descricao;
    private HistoricoModel historico; // conta a ser creditada será a vinculada ao historico. caixa
    private FuncionarioModel responsavel;
    private Date dataRegistro;
    private Date dataMovimento;
    private Date dataContabil;
    private Double valorNominal;
    private String observacao;
    private UsuarioModel usuario;
    private String tipoLancamento;
    private CedenteModel cedenteModel;
    private MovimentoDiarioModel movimentoDiarioModel;
    private TituloPagarBaixaModel tituloPagarBaixaModel;
    private LoteContabilModel loteContabil;

    public LoteContabilModel getLoteContabil() {
        return loteContabil;
    }

    public void setLoteContabil(LoteContabilModel loteContabil) {
        this.loteContabil = loteContabil;
    }

    public CedenteModel getCedenteModel() {
        return cedenteModel;
    }

    public void setCedenteModel(CedenteModel cedenteModel) {
        this.cedenteModel = cedenteModel;
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

    public String getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(String tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public TituloPagarBaixaModel getTituloPagarBaixaModel() {
        return tituloPagarBaixaModel;
    }

    public void setTituloPagarBaixaModel(TituloPagarBaixaModel tituloPagarBaixaModel) {
        this.tituloPagarBaixaModel = tituloPagarBaixaModel;
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

    public void setValorNominal(Double valorNominal) {
        this.valorNominal = valorNominal;
    }

    @Override
    public String toString() {
        return this.getNumeroDocumento();
    }
}// fim da class


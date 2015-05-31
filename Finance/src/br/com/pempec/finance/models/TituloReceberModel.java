package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class TituloReceberModel extends FinancePkModel implements Serializable {

    private Boolean check = false;
    private HistoricoModel historico;
    private SacadoModel sacado;
    private TipoCobrancaModel tipoCobranca;
    private FuncionarioModel responsavel;
    private LocalPagamentoModel localPagamento;
    private MovimentoDiarioModel movimentoDiarioModel;
    private TituloReceberModel tituloAnterior;
    private UsuarioModel usuario;
    private TipoStatusModel status;
    private CentroCustoModel centroCusto;
    private NotaFiscalEmitidaModel notaFiscal;
    private LoteContabilModel loteContabil;
    private Date dataRegistro;
    private Date dataVencimento;
    private Date dataEmissao;
    private Date dataProtocolo;
    private Date dataUltimaAtualizacao;
    private Date previsaoCartorio;
    private Date dataPagamento;
    private Double valorNominal;
    private Double valorAntecipado;
    private String moeda;
    private String carteira;
    private String codigoBarras;
    private String contrato;
    private String parcela;
    private String observacao;
    private String numeroDocumento;
    private String registroProvisao;
    private String descricao;
    private ContaContabilModel contaContabilDebito;
    private ContaContabilModel contaContabilCredito;
    private Set<TituloReceberRateioCCModel> collCentroCustosRateio;
    private Set<TituloReceberRateioHistoricoModel> collHistoricosRateio;
    private Integer provisao;
    private boolean botaoGerar;
    private LoteRecebimentoTituloModel loteRecebimentoTituloModel;

    @Override
    public String toString() {
        return this.getNumeroDocumento();
    }

    public String getRegistroProvisao() {
        return registroProvisao;
    }

    public void setRegistroProvisao(String registroProvisao) {
        this.registroProvisao = registroProvisao;
    }

    public String getCarteira() {
        return carteira;
    }

    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }

    public Integer getProvisao() {
        return provisao;
    }

    public void setProvisao(Integer provisao) {
        this.provisao = provisao;
    }

    public Boolean isProvisao() {
        return (provisao == null || provisao == 0) ? false : true;
    }

    public CentroCustoModel getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCustoModel centroCusto) {
        this.centroCusto = centroCusto;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public ContaContabilModel getContaContabilCredito() {
        return contaContabilCredito;
    }

    public void setContaContabilCredito(ContaContabilModel contaContabilCredito) {
        this.contaContabilCredito = contaContabilCredito;
    }

    public ContaContabilModel getContaContabilDebito() {
        return contaContabilDebito;
    }

    public void setContaContabilDebito(ContaContabilModel contaContabilDebito) {
        this.contaContabilDebito = contaContabilDebito;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
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

    public Date getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
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

    public LocalPagamentoModel getLocalPagamento() {
        return localPagamento;
    }

    public void setLocalPagamento(LocalPagamentoModel localPagamento) {
        this.localPagamento = localPagamento;
    }

    public LoteContabilModel getLoteContabil() {
        return loteContabil;
    }

    public void setLoteContabil(LoteContabilModel loteContabil) {
        this.loteContabil = loteContabil;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
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

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public Date getPrevisaoCartorio() {
        return previsaoCartorio;
    }

    public void setPrevisaoCartorio(Date previsaoCartorio) {
        this.previsaoCartorio = previsaoCartorio;
    }

    public FuncionarioModel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(FuncionarioModel responsavel) {
        this.responsavel = responsavel;
    }

    public SacadoModel getSacado() {
        return sacado;
    }

    public void setSacado(SacadoModel sacado) {
        this.sacado = sacado;
    }

    public TipoStatusModel getStatus() {
        return status;
    }

    public void setStatus(TipoStatusModel status) {
        this.status = status;
    }

    public TipoCobrancaModel getTipoCobranca() {
        return tipoCobranca;
    }

    public void setTipoCobranca(TipoCobrancaModel tipoCobranca) {
        this.tipoCobranca = tipoCobranca;
    }

    public TituloReceberModel getTituloAnterior() {
        return tituloAnterior;
    }

    public void setTituloAnterior(TituloReceberModel tituloAnterior) {
        this.tituloAnterior = tituloAnterior;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public Double getValorAntecipado() {
        return valorAntecipado;
    }

    public void setValorAntecipado(Double valorAntecipado) {
        this.valorAntecipado = valorAntecipado;
    }

    public Double getValorNominal() {
        return valorNominal;
    }

    public void setValorNominal(Double valorNominal) {
        this.valorNominal = valorNominal;
    }

    public NotaFiscalEmitidaModel getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscalEmitidaModel notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public Set<TituloReceberRateioCCModel> getCollCentroCustosRateio() {
        return collCentroCustosRateio;
    }

    public void setCollCentroCustosRateio(Set<TituloReceberRateioCCModel> collCentroCustosRateio) {
        this.collCentroCustosRateio = collCentroCustosRateio;
    }

    public Set<TituloReceberRateioHistoricoModel> getCollHistoricosRateio() {
        return collHistoricosRateio;
    }

    public void setCollHistoricosRateio(Set<TituloReceberRateioHistoricoModel> collHistoricosRateio) {
        this.collHistoricosRateio = collHistoricosRateio;
    }

    public boolean isBotaoGerar() {
        return botaoGerar;
    }

    public void setBotaoGerar(boolean botaoGerar) {
        this.botaoGerar = botaoGerar;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public LoteRecebimentoTituloModel getLoteRecebimentoTituloModel() {
        return loteRecebimentoTituloModel;
    }

    public void setLoteRecebimentoTituloModel(LoteRecebimentoTituloModel loteRecebimentoTituloModel) {
        this.loteRecebimentoTituloModel = loteRecebimentoTituloModel;
    }
}// fim da class


package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class TituloPagarBaixaModel extends FinancePkModel implements Serializable {

    private TituloPagarModel titulo;
    private CentroCustoModel centroCusto;
    private Double valorPago;
    private UsuarioModel usuario;
    private FuncionarioModel responsavel;
    private Date dataRegistro;
    private String tipoBaixa; //se é total ou parcial  
    private Date novaDataVencimento; //Somente utilizado para baixa parciais.
    private LoteContabilModel loteContabil;
    private LocalPagamentoModel localPagamento;
    private Set<TituloPagarBaixaFormaPagamentoModel> formasPagamento;
    private Set<TituloPagarBaixaDeducaoModel> deducoes;
    private Set<TituloPagarBaixaAcrescimoModel> acrescimos;
    private Set<TituloPagarBaixaChequeModel> tituloPagarBaixaCheque;
    private Set<TituloPagarBaixaInternetModel> tituloPagarBaixaInternet;
    private MovimentoDiarioModel movimentoDiarioModel;
    private LotePagamentoTituloModel lotePagamentoModel;

//    private Boolean exportar = true;
//
//    public Boolean getExportar() {
//        return exportar;
//    }
//
//    public void setExportar(Boolean exportar) {
//        this.exportar = exportar;
//    }
    public LotePagamentoTituloModel getLotePagamentoModel() {
        return lotePagamentoModel;
    }

    public void setLotePagamentoModel(LotePagamentoTituloModel lotePagamentoModel) {
        this.lotePagamentoModel = lotePagamentoModel;
    }

    public LoteContabilModel getLoteContabil() {
        return loteContabil;
    }

    public void setLoteContabil(LoteContabilModel loteContabil) {
        this.loteContabil = loteContabil;
    }

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public Set<TituloPagarBaixaInternetModel> getTituloPagarBaixaInternet() {
        return tituloPagarBaixaInternet;
    }

    public void setTituloPagarBaixaInternet(Set<TituloPagarBaixaInternetModel> tituloPagarBaixaInternet) {
        this.tituloPagarBaixaInternet = tituloPagarBaixaInternet;
    }

    public Set<TituloPagarBaixaChequeModel> getTituloPagarBaixaCheque() {
        return tituloPagarBaixaCheque;
    }

    public void setTituloPagarBaixaCheque(Set<TituloPagarBaixaChequeModel> tituloPagarBaixaCheque) {
        this.tituloPagarBaixaCheque = tituloPagarBaixaCheque;
    }

    public Set<TituloPagarBaixaAcrescimoModel> getAcrescimos() {
        return acrescimos;
    }

    public void setAcrescimos(Set<TituloPagarBaixaAcrescimoModel> acrescimos) {
        this.acrescimos = acrescimos;
    }

    public CentroCustoModel getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCustoModel centroCusto) {
        this.centroCusto = centroCusto;
    }

    public Date getNovaDataVencimento() {
        return novaDataVencimento;
    }

    public void setNovaDataVencimento(Date novaDataVencimento) {
        this.novaDataVencimento = novaDataVencimento;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Set<TituloPagarBaixaDeducaoModel> getDeducoes() {
        return deducoes;
    }

    public void setDeducoes(Set<TituloPagarBaixaDeducaoModel> deducoes) {
        this.deducoes = deducoes;
    }

    public Set<TituloPagarBaixaFormaPagamentoModel> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(Set<TituloPagarBaixaFormaPagamentoModel> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public LocalPagamentoModel getLocalPagamento() {
        return localPagamento;
    }

    public void setLocalPagamento(LocalPagamentoModel localPagamento) {
        this.localPagamento = localPagamento;
    }

    public FuncionarioModel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(FuncionarioModel responsavel) {
        this.responsavel = responsavel;
    }

    public String getTipoBaixa() {
        return tipoBaixa;
    }

    public void setTipoBaixa(String tipoBaixa) {
        this.tipoBaixa = tipoBaixa;
    }

    public TituloPagarModel getTitulo() {
        return titulo;
    }

    public void setTitulo(TituloPagarModel titulo) {
        this.titulo = titulo;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }
}// fim da class


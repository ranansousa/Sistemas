package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class TituloReceberBaixaModel extends FinancePkModel implements Serializable {

    private TituloReceberModel titulo;
    private CentroCustoModel centroCusto;
    private Double valorPago;
    private UsuarioModel usuario;
    private FuncionarioModel responsavel;
    private Date dataRegistro;
    private String tipoBaixa; //se é total ou parcial  
    private Date novaDataVencimento; //Somente utilizado para baixa parciais.
    private LocalPagamentoModel localPagamento;
    private Set<TituloReceberBaixaFormaPagamentoModel> formasPagamento;
    private Set<TituloReceberBaixaDeducaoModel> deducoes;
    private Set<TituloReceberBaixaAcrescimoModel> acrescimos;
    private Set<TituloReceberBaixaChequeModel> tituloReceberBaixaCheque;
    private Set<TituloReceberBaixaInternetModel> tituloReceberBaixaInternet;
    private MovimentoDiarioModel movimentoDiarioModel;
    private LoteContabilModel loteContabil;

    public LoteContabilModel getLoteContabil() {
        return loteContabil;
    }

    public void setLoteContabil(LoteContabilModel loteContabil) {
        this.loteContabil = loteContabil;
    }

    public Set<TituloReceberBaixaAcrescimoModel> getAcrescimos() {
        return acrescimos;
    }

    public void setAcrescimos(Set<TituloReceberBaixaAcrescimoModel> acrescimos) {
        this.acrescimos = acrescimos;
    }

    public CentroCustoModel getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCustoModel centroCusto) {
        this.centroCusto = centroCusto;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Set<TituloReceberBaixaDeducaoModel> getDeducoes() {
        return deducoes;
    }

    public void setDeducoes(Set<TituloReceberBaixaDeducaoModel> deducoes) {
        this.deducoes = deducoes;
    }

    public Set<TituloReceberBaixaFormaPagamentoModel> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(Set<TituloReceberBaixaFormaPagamentoModel> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public LocalPagamentoModel getLocalPagamento() {
        return localPagamento;
    }

    public void setLocalPagamento(LocalPagamentoModel localPagamento) {
        this.localPagamento = localPagamento;
    }

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public Date getNovaDataVencimento() {
        return novaDataVencimento;
    }

    public void setNovaDataVencimento(Date novaDataVencimento) {
        this.novaDataVencimento = novaDataVencimento;
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

    public TituloReceberModel getTitulo() {
        return titulo;
    }

    public void setTitulo(TituloReceberModel titulo) {
        this.titulo = titulo;
    }

    public Set<TituloReceberBaixaChequeModel> getTituloReceberBaixaCheque() {
        return tituloReceberBaixaCheque;
    }

    public void setTituloReceberBaixaCheque(Set<TituloReceberBaixaChequeModel> tituloReceberBaixaCheque) {
        this.tituloReceberBaixaCheque = tituloReceberBaixaCheque;
    }

    public Set<TituloReceberBaixaInternetModel> getTituloReceberBaixaInternet() {
        return tituloReceberBaixaInternet;
    }

    public void setTituloReceberBaixaInternet(Set<TituloReceberBaixaInternetModel> tituloReceberBaixaInternet) {
        this.tituloReceberBaixaInternet = tituloReceberBaixaInternet;
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


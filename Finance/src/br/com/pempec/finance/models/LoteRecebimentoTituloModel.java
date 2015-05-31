package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Equipe Pempec
 */
public class LoteRecebimentoTituloModel extends FinancePkModel implements Serializable {

    private UsuarioModel usuario;
    private ContaBancariaModel contaBancariaModel;
    private FuncionarioModel responsavel;
    private String numeroCheque;
    private String agencia;
    private String conta;
    private BancoModel banco;
    private TipoOperacaoBancariaModel tipoOperacaoBancariaModel;
    private FormaPagamentoModel formaPagamentoModel;
    private MovimentoDiarioModel movimentoDiarioModel;
    private String lote; //numero do lote
    private String status;
    private Date dataRegistro; //data inicio do registro do lote
    private Date dataPagamento;
    private Double valor;
    private long qtdTituloReceber;

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public ContaBancariaModel getContaBancariaModel() {
        return contaBancariaModel;
    }

    public void setContaBancariaModel(ContaBancariaModel contaBancariaModel) {
        this.contaBancariaModel = contaBancariaModel;
    }

    public FuncionarioModel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(FuncionarioModel responsavel) {
        this.responsavel = responsavel;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public BancoModel getBanco() {
        return banco;
    }

    public void setBanco(BancoModel banco) {
        this.banco = banco;
    }

    public TipoOperacaoBancariaModel getTipoOperacaoBancariaModel() {
        return tipoOperacaoBancariaModel;
    }

    public void setTipoOperacaoBancariaModel(TipoOperacaoBancariaModel tipoOperacaoBancariaModel) {
        this.tipoOperacaoBancariaModel = tipoOperacaoBancariaModel;
    }

    public FormaPagamentoModel getFormaPagamentoModel() {
        return formaPagamentoModel;
    }

    public void setFormaPagamentoModel(FormaPagamentoModel formaPagamentoModel) {
        this.formaPagamentoModel = formaPagamentoModel;
    }

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public long getQtdTituloReceber() {
        return qtdTituloReceber;
    }

    public void setQtdTituloReceber(long qtdTituloReceber) {
        this.qtdTituloReceber = qtdTituloReceber;
    }

           
       
    @Override
    public String toString() {
        return this.getLote();
    }
}

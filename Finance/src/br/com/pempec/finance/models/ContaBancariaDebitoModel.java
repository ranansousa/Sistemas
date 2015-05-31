package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Date;

/**
 * * @author Equipe Pempec
 */
public class ContaBancariaDebitoModel extends FinancePkModel implements Serializable {

    private FuncionarioModel responsavel;
    private UsuarioModel usuario;
    private ContaBancariaModel contaBancaria;
    private TipoOperacaoBancariaModel tipoOperacaoBancaria;
    private TituloPagarModel tituloPagar;
    private ContaBancariaChequeModel contaBancariaCheque;
    private LoteContabilModel loteContabil;
    private String descricao;
    private Date dataRegistro;
    private Date dataMovimento;
    private Date dataContabil;
    private Double valor;
    private String Observacao;
    private String tipoLancamento;
    private String identificacao;
    private MovimentoDiarioModel movimentoDiarioModel;

    public Date getDataContabil() {
        return dataContabil;
    }

    public void setDataContabil(Date dataContabil) {
        this.dataContabil = dataContabil;
    }

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public LoteContabilModel getLoteContabil() {
        return loteContabil;
    }

    public void setLoteContabil(LoteContabilModel loteContabil) {
        this.loteContabil = loteContabil;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }

    public ContaBancariaModel getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancariaModel contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public ContaBancariaChequeModel getContaBancariaCheque() {
        return contaBancariaCheque;
    }

    public void setContaBancariaCheque(ContaBancariaChequeModel contaBancariaCheque) {
        this.contaBancariaCheque = contaBancariaCheque;
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

    public TipoOperacaoBancariaModel getTipoOperacaoBancaria() {
        return tipoOperacaoBancaria;
    }

    public void setTipoOperacaoBancaria(TipoOperacaoBancariaModel tipoOperacaoBancaria) {
        this.tipoOperacaoBancaria = tipoOperacaoBancaria;
    }

    public TituloPagarModel getTituloPagar() {
        return tituloPagar;
    }

    public void setTituloPagar(TituloPagarModel tituloPagar) {
        this.tituloPagar = tituloPagar;
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

    @Override
    public String toString() {
        return getIdentificacao();
    }
}

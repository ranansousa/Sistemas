package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Date;

/**
 * * @author Equipe Pempec
 */
public class ContaBancariaCreditoModel extends FinancePkModel implements Serializable {

    private FuncionarioModel responsavel;
    private UsuarioModel usuario;
    private ContaBancariaModel contaBancaria;
    private TipoOperacaoBancariaModel tipoOperacaoBancaria;
    private TituloReceberModel tituloReceber;
    private LoteContabilModel loteContabil;
    private MovimentoDiarioModel movimentoDiarioModel;
    private String descricao;
    private Date dataRegistro;
    private Date dataMovimento;
    private Date dataContabil;
    private Double valor;
    private String Observacao;
    private String identificacao;
    private String tipoLancamento;

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

    public String getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(String tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public Date getDataContabil() {
        return dataContabil;
    }

    public void setDataContabil(Date dataContabil) {
        this.dataContabil = dataContabil;
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

    public TipoOperacaoBancariaModel getTipoOperacaoBancaria() {
        return tipoOperacaoBancaria;
    }

    public void setTipoOperacaoBancaria(TipoOperacaoBancariaModel tipoOperacaoBancaria) {
        this.tipoOperacaoBancaria = tipoOperacaoBancaria;
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

    public TituloReceberModel getTituloReceber() {
        return tituloReceber;
    }

    public void setTituloReceber(TituloReceberModel tituloReceber) {
        this.tituloReceber = tituloReceber;
    }

    @Override
    public String toString() {
        return getIdentificacao();
    }
}

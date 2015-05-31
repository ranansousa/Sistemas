package br.com.pempec.finance.models;

import java.io.Serializable;

/**
 *
 * @author Equipe Pempec
 */
public class TipoOperacaoBancariaModel extends FinancePkModel implements Serializable {

    private Integer codigo;
    private String descricao;
    private ContaContabilModel contaContabil;
    private MovimentoDiarioModel movimentoDiarioModel;
    //tipo operacao é definido para preencher o combo apenas com as opcoes possiveis na tela.
    // tipo 1 sera agencia e tipo 2 será www
    private Integer tipoOperacao;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(Integer tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public ContaContabilModel getContaContabil() {
        return contaContabil;
    }

    public void setContaContabil(ContaContabilModel contaContabil) {
        this.contaContabil = contaContabil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }
}// fim da class


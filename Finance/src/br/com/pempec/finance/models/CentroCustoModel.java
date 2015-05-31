package br.com.pempec.finance.models;

// Equipe Pempec  
import java.io.Serializable;

public class CentroCustoModel extends FinancePkModel implements Serializable {

    private Integer codigo;
    private String descricao;
    private MovimentoDiarioModel movimentoDiarioModel;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }
}// fim da classe


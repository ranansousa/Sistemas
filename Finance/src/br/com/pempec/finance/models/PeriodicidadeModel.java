package br.com.pempec.finance.models;

/**
 *
 * @author PEMPEC
 */
public class PeriodicidadeModel {

    private Integer id;
    private String descricao;

    public PeriodicidadeModel(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }
}

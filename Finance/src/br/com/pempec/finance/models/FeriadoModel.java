package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Date;

public class FeriadoModel extends FinanceIdModel implements Serializable {

    private String descricao;
    private Date data;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

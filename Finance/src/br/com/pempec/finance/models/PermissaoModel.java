package br.com.pempec.finance.models;

import java.io.Serializable;

public class PermissaoModel implements Serializable {

    private String descricao;
    private Boolean checkCadastrar = false;
    private Boolean checkAlterar = false;
    private Boolean checkExcluir = false;
    private Boolean checkImpressao = false;
    private Boolean checkOutros = false;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getCheckAlterar() {
        return checkAlterar;
    }

    public void setCheckAlterar(Boolean checkAlterar) {
        this.checkAlterar = checkAlterar;
    }

    public Boolean getCheckCadastrar() {
        return checkCadastrar;
    }

    public void setCheckCadastrar(Boolean checkCadastrar) {
        this.checkCadastrar = checkCadastrar;
    }

    public Boolean getCheckExcluir() {
        return checkExcluir;
    }

    public void setCheckExcluir(Boolean checkExcluir) {
        this.checkExcluir = checkExcluir;
    }

    public Boolean getCheckImpressao() {
        return checkImpressao;
    }

    public void setCheckImpressao(Boolean checkImpressao) {
        this.checkImpressao = checkImpressao;
    }

    public Boolean getCheckOutros() {
        return checkOutros;
    }

    public void setCheckOutros(Boolean checkOutros) {
        this.checkOutros = checkOutros;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.models;

/**
 *
 * @author Alexandre
 */
public class CampoAuditoria {

    private String descricaoCampo;
    private String valorCampoAntigo;
    private String valorCampoNovo;

    public CampoAuditoria() {
    }

    public CampoAuditoria(String descricaoCampo, String valorCampoAntigo, String valorCampoNovo) {
        this.descricaoCampo = descricaoCampo;
        this.valorCampoAntigo = valorCampoAntigo;
        this.valorCampoNovo = valorCampoNovo;
    }

    public String getDescricaoCampo() {
        return descricaoCampo;
    }

    public void setDescricaoCampo(String descricaoCampo) {
        this.descricaoCampo = descricaoCampo;
    }

    public String getValorCampoAntigo() {
        return valorCampoAntigo;
    }

    public void setValorCampoAntigo(String valorCampoAntigo) {
        this.valorCampoAntigo = valorCampoAntigo;
    }

    public String getValorCampoNovo() {
        return valorCampoNovo;
    }

    public void setValorCampoNovo(String valorCampoNovo) {
        this.valorCampoNovo = valorCampoNovo;
    }
}

package br.com.pempec.finance.models;

import java.io.Serializable;

/* 
 * Equipe Pempec
 */
public class BancoModel extends FinanceIdModel implements Serializable {

    private String nomeBanco;
    private String codigoBanco;
    private String siglaBanco;
    private MovimentoDiarioModel movimentoDiarioModel;

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public String getSiglaBanco() {
        return siglaBanco;
    }

    public void setSiglaBanco(String siglaBanco) {
        this.siglaBanco = siglaBanco;
    }

    @Override
    public String toString() {
        return this.getNomeBanco();
    }
}// fim da class

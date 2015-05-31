/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils;

import br.com.pempec.finance.models.ContaBancariaModel;

/**
 *
 * @author Ranansousa
 */
public class SaldoBancario {

    private ContaBancariaModel contaBancariaModel;
    private Double saldo;
    private Double cheques;
    private Boolean check = false;

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public ContaBancariaModel getContaBancariaModel() {
        return contaBancariaModel;
    }

    public void setContaBancariaModel(ContaBancariaModel contaBancariaModel) {
        this.contaBancariaModel = contaBancariaModel;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getCheques() {
        return cheques;
    }

    public void setCheques(Double cheques) {
        this.cheques = cheques;
    }
}//fim

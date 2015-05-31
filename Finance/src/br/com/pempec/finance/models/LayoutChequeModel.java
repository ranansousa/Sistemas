package br.com.pempec.finance.models;

import java.io.Serializable;

public class LayoutChequeModel extends FinancePkModel implements Serializable {

    private ContaBancariaModel conta;
    private MovimentoDiarioModel movimentoDiarioModel;
    private int valorLinha;
    private int valorColuna;
    private int extensoLinha;
    private int extensoColuna;
    private int extensoCompLinha;
    private int extensoCompColuna;
    private int portadorLinha;
    private int portadorColuna;
    private int cidadeLinha;
    private int cidadeColuna;
    private int diaLinha;
    private int diaColuna;
    private int mesLinha;
    private int mesColuna;
    private int anoLinha;
    private int anoColuna;

    public int getAnoColuna() {
        return anoColuna;
    }

    public void setAnoColuna(int anoColuna) {
        this.anoColuna = anoColuna;
    }

    public int getAnoLinha() {
        return anoLinha;
    }

    public void setAnoLinha(int anoLinha) {
        this.anoLinha = anoLinha;
    }

    public int getCidadeColuna() {
        return cidadeColuna;
    }

    public void setCidadeColuna(int cidadeColuna) {
        this.cidadeColuna = cidadeColuna;
    }

    public int getCidadeLinha() {
        return cidadeLinha;
    }

    public void setCidadeLinha(int cidadeLinha) {
        this.cidadeLinha = cidadeLinha;
    }

    public ContaBancariaModel getConta() {
        return conta;
    }

    public void setConta(ContaBancariaModel conta) {
        this.conta = conta;
    }

    public int getDiaColuna() {
        return diaColuna;
    }

    public void setDiaColuna(int diaColuna) {
        this.diaColuna = diaColuna;
    }

    public int getDiaLinha() {
        return diaLinha;
    }

    public void setDiaLinha(int diaLinha) {
        this.diaLinha = diaLinha;
    }

    public int getExtensoColuna() {
        return extensoColuna;
    }

    public void setExtensoColuna(int extensoColuna) {
        this.extensoColuna = extensoColuna;
    }

    public int getExtensoCompColuna() {
        return extensoCompColuna;
    }

    public void setExtensoCompColuna(int extensoCompColuna) {
        this.extensoCompColuna = extensoCompColuna;
    }

    public int getExtensoCompLinha() {
        return extensoCompLinha;
    }

    public void setExtensoCompLinha(int extensoCompLinha) {
        this.extensoCompLinha = extensoCompLinha;
    }

    public int getExtensoLinha() {
        return extensoLinha;
    }

    public void setExtensoLinha(int extensoLinha) {
        this.extensoLinha = extensoLinha;
    }

    public int getMesColuna() {
        return mesColuna;
    }

    public void setMesColuna(int mesColuna) {
        this.mesColuna = mesColuna;
    }

    public int getMesLinha() {
        return mesLinha;
    }

    public void setMesLinha(int mesLinha) {
        this.mesLinha = mesLinha;
    }

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public int getPortadorColuna() {
        return portadorColuna;
    }

    public void setPortadorColuna(int portadorColuna) {
        this.portadorColuna = portadorColuna;
    }

    public int getPortadorLinha() {
        return portadorLinha;
    }

    public void setPortadorLinha(int portadorLinha) {
        this.portadorLinha = portadorLinha;
    }

    public int getValorColuna() {
        return valorColuna;
    }

    public void setValorColuna(int valorColuna) {
        this.valorColuna = valorColuna;
    }

    public int getValorLinha() {
        return valorLinha;
    }

    public void setValorLinha(int valorLinha) {
        this.valorLinha = valorLinha;
    }

    @Override
    public String toString() {
        return this.getConta().getConta();
    }
}// fim da class


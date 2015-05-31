/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils;

import br.com.pempec.finance.businessObjects.ContaBancariaChequeBO;
import br.com.pempec.finance.businessObjects.ContaBancariaCreditoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaDebitoBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaModel;

/**
 *
 * @author PEMPEC
 */
public class ContaBancariaServices {

    private ContaBancariaModel contaBancariaModel;
    private ContaBancariaCreditoBO contaBancariaCreditoBO = new ContaBancariaCreditoBO();
    private ContaBancariaDebitoBO contaBancariaDebitoBO = new ContaBancariaDebitoBO();
    private ContaBancariaChequeBO contaBancariaChequeBO = new ContaBancariaChequeBO();

    public ContaBancariaServices(ContaBancariaModel conta) throws SystemException, ApplicationException {
        this.contaBancariaModel = conta;
    }

    public ContaBancariaServices() {
    }

    public Double obterSaldoContaBancaria() throws SystemException, ApplicationException {

        Double saldo = 0d;

        saldo = obterCreditoContaBancaria() - obterDebitoContaBancaria();

        return saldo;
    }

    public Double obterSaldoDisponivel() throws SystemException, ApplicationException {

        Double saldoDisponivel = 0d;

        saldoDisponivel = (obterCreditoContaBancaria() - (obterDebitoContaBancaria() + obterTotalChequesACompensar()));

        return saldoDisponivel;
    }

    public Double obterCreditoContaBancaria() throws SystemException, ApplicationException {

        Double credito = contaBancariaCreditoBO.obterTotal(contaBancariaModel);

        return credito == null ? 0d : credito;
    }

    public Double obterDebitoContaBancaria() throws SystemException, ApplicationException {

        Double debito = contaBancariaDebitoBO.obterTotal(contaBancariaModel);

        return debito == null ? 0d : debito;
    }

    public Double obterTotalChequesACompensar() throws SystemException, ApplicationException {

        Double cheques = contaBancariaChequeBO.obterTotalChequesACompensar(contaBancariaModel);

        return cheques == null ? 0d : cheques;
    }

    public ContaBancariaModel getContaBancariaModel() {
        return contaBancariaModel;
    }

    public void setContaBancariaModel(ContaBancariaModel contaBancariaModel) {
        this.contaBancariaModel = contaBancariaModel;
    }
} //fim class

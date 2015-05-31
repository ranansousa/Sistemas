/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils;

import br.com.pempec.finance.businessObjects.OrganizacaoBO;
import br.com.pempec.finance.businessObjects.TesourariaCreditoBO;
import br.com.pempec.finance.businessObjects.TesourariaDebitoBO;
import br.com.pempec.finance.businessObjects.TituloReceberBaixaChequeBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;

/**
 *
 * @author PEMPEC
 */
public class TesourariaServices {

    // classe de serviços utilizada para obter todos os debitos e creditos da tesouraria
    //retorna o total de debito, credito e saldo
    private TesourariaCreditoBO tesourariaCreditoBO = new TesourariaCreditoBO();
    private TesourariaDebitoBO tesourariaDebitoBO = new TesourariaDebitoBO();
    private TituloReceberBaixaChequeBO tituloReceberBaixaChequeBO = new TituloReceberBaixaChequeBO();
    private OrganizacaoModel organizacaoModel;

    public TesourariaServices(OrganizacaoModel organizacao) throws SystemException, ApplicationException {

        this.organizacaoModel = new OrganizacaoBO().consultarPorTemplate(organizacao);
    }

    public Double obterSaldoTesouraria() throws SystemException, ApplicationException {

        Double saldo = 0d;

        saldo = (obterTotalCredito(organizacaoModel) - obterTotalDebito(organizacaoModel));

        return saldo;
    }

    public Double obterTotalCredito(OrganizacaoModel org) throws SystemException, ApplicationException {

        Double credito = tesourariaCreditoBO.obterTotal(organizacaoModel);

        return credito == null ? 0d : credito;
    }

    public Double obterTotalDebito(OrganizacaoModel org) throws SystemException, ApplicationException {

        Double debito = tesourariaDebitoBO.obterTotal(organizacaoModel);

        return debito == null ? 0d : debito;
    }

    public Double obterSaldoCheque(OrganizacaoModel org) throws SystemException, ApplicationException {

        Double saldo = tituloReceberBaixaChequeBO.obterSaldoCheque(organizacaoModel);

        return saldo == null ? 0d : saldo;
    }
} //fim class

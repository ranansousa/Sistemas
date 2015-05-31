package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TituloPagarBaixaChequeDAOIf {

    public void inserir(TituloPagarBaixaChequeModel model) throws SystemException;

    public void alterar(TituloPagarBaixaChequeModel model) throws SystemException;

    public TituloPagarBaixaChequeModel consultarPorPk(TituloPagarBaixaChequeModel model) throws SystemException;

    public List<TituloPagarBaixaChequeModel> obterPorFiltro(TituloPagarBaixaChequeModel model)
            throws SystemException;

    public List<TituloPagarBaixaChequeModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public List<TituloPagarBaixaChequeModel> obterPorChequesEmitidos(ContaBancariaModel model, OrganizacaoModel organizacaoModel)
            throws SystemException;

    public void excluirTodos(Collection<TituloPagarBaixaChequeModel> coll)
            throws SystemException;

    public TituloPagarBaixaChequeModel consultarPorTemplate(TituloPagarBaixaChequeModel model)
            throws SystemException;

    public List<TituloPagarBaixaChequeModel> obterPorTituloPagarBaixa(TituloPagarBaixaModel model)
            throws SystemException;

    public void excluir(TituloPagarBaixaChequeModel model) throws SystemException;

    public List<TituloPagarBaixaChequeModel> obterPorCheque(ContaBancariaChequeModel model)
            throws SystemException;
}

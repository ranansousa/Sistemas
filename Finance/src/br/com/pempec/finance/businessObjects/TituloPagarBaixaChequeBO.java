package br.com.pempec.finance.businessObjects;

import java.util.Collection;
import java.util.List;

import br.com.pempec.finance.daos.DAOFactory;

import br.com.pempec.finance.daosIf.TituloPagarDAOIf;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;

/**
 * @author PEMPEC
 */
public class TituloPagarBaixaChequeBO {

    public void inserir(TituloPagarBaixaChequeModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaChequeDAO().inserir(model);
    }

    public void alterar(TituloPagarBaixaChequeModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaChequeDAO().alterar(model);
    }

    public TituloPagarBaixaChequeModel consultarPorPk(TituloPagarBaixaChequeModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaChequeDAO().consultarPorPk(model);
    }

    public List<TituloPagarBaixaChequeModel> obterPorFiltro(TituloPagarBaixaChequeModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaChequeDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<TituloPagarBaixaChequeModel> coll) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaChequeDAO().excluirTodos(coll);
    }

    public List<TituloPagarBaixaChequeModel> obterTodos(OrganizacaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaChequeDAO().obterTodos(model);
    }

    public List<TituloPagarBaixaChequeModel> obterPorTituloPagarBaixa(TituloPagarBaixaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaChequeDAO().obterPorTituloPagarBaixa(model);
    }

    public TituloPagarBaixaChequeModel consultarPorTemplate(TituloPagarBaixaChequeModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaChequeDAO().consultarPorTemplate(model);
    }

    public void excluir(TituloPagarBaixaChequeModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaChequeDAO().excluir(model);
    }

    public List<TituloPagarBaixaChequeModel> obterPorCheque(ContaBancariaChequeModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaChequeDAO().obterPorCheque(model);
    }

    public List<TituloPagarBaixaChequeModel> obterPorChequesEmitidos(ContaBancariaModel model, OrganizacaoModel organizacaoModel)
            throws SystemException, ApplicationException {

        List<TituloPagarBaixaChequeModel> coll = DAOFactory.getInstance().getTituloPagarBaixaChequeDAO().obterPorChequesEmitidos(model, organizacaoModel);

        TituloPagarDAOIf tituloPagarDAO = DAOFactory.getInstance().getTituloPagarDAO();

        for (TituloPagarBaixaChequeModel tituloPagarBaixaChequeModel : coll) {

            tituloPagarBaixaChequeModel.getTituloPagarBaixa().setTitulo(tituloPagarDAO.consultarPorPk(tituloPagarBaixaChequeModel.getTituloPagarBaixa().getTitulo()));

        }

        return coll;
    }
}

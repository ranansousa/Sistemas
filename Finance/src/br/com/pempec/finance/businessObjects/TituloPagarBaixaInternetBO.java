package br.com.pempec.finance.businessObjects;

import java.util.Collection;
import java.util.List;

import br.com.pempec.finance.daos.DAOFactory;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaInternetModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;

/**
 * @author PEMPEC
 */
public class TituloPagarBaixaInternetBO {

    public void inserir(TituloPagarBaixaInternetModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaInternetDAO().inserir(model);
    }

    public void alterar(TituloPagarBaixaInternetModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaInternetDAO().alterar(model);
    }

    public TituloPagarBaixaInternetModel consultarPorPk(TituloPagarBaixaInternetModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaInternetDAO().consultarPorPk(model);
    }

    public List<TituloPagarBaixaInternetModel> obterPorFiltro(TituloPagarBaixaInternetModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaInternetDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<TituloPagarBaixaInternetModel> coll) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaInternetDAO().excluirTodos(coll);
    }

    public List<TituloPagarBaixaInternetModel> obterTodos(OrganizacaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaInternetDAO().obterTodos(model);
    }

    public List<TituloPagarBaixaInternetModel> obterPorTituloPagarBaixa(TituloPagarBaixaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaInternetDAO().obterPorTituloPagarBaixa(model);
    }

    public TituloPagarBaixaInternetModel consultarPorTemplate(TituloPagarBaixaInternetModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaInternetDAO().consultarPorTemplate(model);
    }

    public void excluir(TituloPagarBaixaInternetModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaInternetDAO().excluir(model);
    }
}

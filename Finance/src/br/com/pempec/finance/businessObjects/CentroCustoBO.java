package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CentroCustoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class CentroCustoBO {

    public void inserir(CentroCustoModel model) throws SystemException,
            ApplicationException {

        DAOFactory.getInstance().getCentroCustoDAO().inserir(model);
    }

    public void alterar(CentroCustoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getCentroCustoDAO().alterar(model);
    }

    public CentroCustoModel consultarPorPk(CentroCustoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCentroCustoDAO().consultarPorPk(
                model);
    }

    public List<CentroCustoModel> obterPorFiltro(CentroCustoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCentroCustoDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<CentroCustoModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getCentroCustoDAO().excluirTodos(coll);
    }

    public List<CentroCustoModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCentroCustoDAO().obterTodos(model);
    }

    public void excluir(CentroCustoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getCentroCustoDAO().excluir(model);
    }

    public CentroCustoModel consultarPorTemplate(CentroCustoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCentroCustoDAO().consultarPorTemplate(model);
    }
}

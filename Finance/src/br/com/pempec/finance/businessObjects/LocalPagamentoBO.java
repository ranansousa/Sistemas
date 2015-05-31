package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.LocalPagamentoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class LocalPagamentoBO {

    public void inserir(LocalPagamentoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getLocalPagamentoDAO().inserir(model);
    }

    public void alterar(LocalPagamentoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getLocalPagamentoDAO().alterar(model);
    }

    public LocalPagamentoModel consultarPorPk(LocalPagamentoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLocalPagamentoDAO().consultarPorPk(
                model);
    }

    public List<LocalPagamentoModel> obterPorFiltro(LocalPagamentoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLocalPagamentoDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<LocalPagamentoModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getLocalPagamentoDAO().excluirTodos(coll);
    }

    public List<LocalPagamentoModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLocalPagamentoDAO().obterTodos(model);
    }

    public void excluir(LocalPagamentoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getLocalPagamentoDAO().excluir(model);
    }

    public LocalPagamentoModel consultarPorTemplate(LocalPagamentoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLocalPagamentoDAO().consultarPorTemplate(model);
    }
}

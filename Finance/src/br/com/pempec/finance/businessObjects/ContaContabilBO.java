package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class ContaContabilBO {

    public void inserir(ContaContabilModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaContabilDAO().inserir(model);
    }

    public void alterar(ContaContabilModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaContabilDAO().alterar(model);
    }

    public ContaContabilModel consultarPorPk(ContaContabilModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaContabilDAO().consultarPorPk(
                model);
    }

    public List<ContaContabilModel> obterPorFiltro(ContaContabilModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaContabilDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<ContaContabilModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getContaContabilDAO().excluirTodos(coll);
    }

    public List<ContaContabilModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaContabilDAO().obterTodos(model);
    }

    public void excluir(ContaContabilModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getContaContabilDAO().excluir(model);
    }

    public ContaContabilModel consultarPorTemplate(ContaContabilModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaContabilDAO().consultarPorTemplate(model);
    }

    public void sincronizeMegaContabil(Collection<ContaContabilModel> collContaContabilInsert, Collection<ContaContabilModel> collContaContabilUpdate) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getContaContabilDAO().sincronizeMegaContabil(collContaContabilInsert, collContaContabilUpdate);
    }
}

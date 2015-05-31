package br.com.pempec.finance.businessObjects;

import java.util.Collection;
import java.util.List;

import br.com.pempec.finance.daos.DAOFactory;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.ContaContabilModel;

/**
 * @author PEMPEC
 */
public class CedenteBO {

    public void inserir(CedenteModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getCedenteDAO().inserir(model);
    }

    public void alterar(CedenteModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getCedenteDAO().alterar(model);
    }

    public CedenteModel consultarPorPk(CedenteModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCedenteDAO().consultarPorPk(
                model);
    }

    public CedenteModel consultarPorContaContabil(ContaContabilModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCedenteDAO().consultarPorContaContabil(
                model);
    }

    public void excluir(CedenteModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getCedenteDAO().excluir(model);
    }

    public CedenteModel consultarPorTemplate(CedenteModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCedenteDAO().consultarPorTemplate(model);
    }

    public List<CedenteModel> obterPorFiltro(CedenteModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCedenteDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<CedenteModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getCedenteDAO().excluirTodos(coll);
    }

    public List<CedenteModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCedenteDAO().obterTodos(model);
    }

    public List<CedenteModel> obterTodosCartaoCredito(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCedenteDAO().obterTodosCartaoCredito(model);
    }
}

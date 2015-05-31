package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.SacadoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class SacadoBO {

    public void inserir(SacadoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getSacadoDAO().inserir(model);
    }

    public void alterar(SacadoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getSacadoDAO().alterar(model);
    }

    public SacadoModel consultarPorPk(SacadoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getSacadoDAO().consultarPorPk(
                model);
    }

    public SacadoModel consultarPorContaContabil(ContaContabilModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getSacadoDAO().consultarPorContaContabil(
                model);
    }

    public void excluir(SacadoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getSacadoDAO().excluir(model);
    }

    public SacadoModel consultarPorTemplate(SacadoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getSacadoDAO().consultarPorTemplate(model);
    }

    public List<SacadoModel> obterPorFiltro(SacadoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getSacadoDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<SacadoModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getSacadoDAO().excluirTodos(coll);
    }

    public List<SacadoModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getSacadoDAO().obterTodos(model);
    }
}

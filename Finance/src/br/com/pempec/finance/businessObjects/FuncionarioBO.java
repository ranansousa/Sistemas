package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class FuncionarioBO {

    public void inserir(FuncionarioModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getFuncionarioDAO().inserir(model);
    }

    public void alterar(FuncionarioModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getFuncionarioDAO().alterar(model);
    }

    public FuncionarioModel consultarPorPk(FuncionarioModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getFuncionarioDAO().consultarPorPk(
                model);
    }

    public void excluir(FuncionarioModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getFuncionarioDAO().excluir(model);
    }

    public FuncionarioModel consultarPorTemplate(FuncionarioModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getFuncionarioDAO().consultarPorTemplate(model);
    }

    public List<FuncionarioModel> obterPorFiltro(FuncionarioModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getFuncionarioDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<FuncionarioModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getFuncionarioDAO().excluirTodos(coll);
    }

    public List<FuncionarioModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getFuncionarioDAO().obterTodos(model);
    }

    public List<FuncionarioModel> obterTodosComEmail(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getFuncionarioDAO().obterTodosComEmail(model);
    }
}

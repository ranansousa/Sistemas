package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContatoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class ContatoBO {

    public void inserir(ContatoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContatoDAO().inserir(model);
    }

    public void alterar(ContatoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContatoDAO().alterar(model);
    }

    public ContatoModel consultarPorPk(ContatoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContatoDAO().consultarPorPk(
                model);
    }

    public void excluir(ContatoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getContatoDAO().excluir(model);
    }

    public ContatoModel consultarPorTemplate(ContatoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContatoDAO().consultarPorTemplate(model);
    }

    public List<ContatoModel> obterPorFiltro(ContatoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContatoDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<ContatoModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getContatoDAO().excluirTodos(coll);
    }

    public List<ContatoModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContatoDAO().obterTodos(model);
    }
}

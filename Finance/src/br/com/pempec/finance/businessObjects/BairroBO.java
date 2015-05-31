package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.BairroModel;
import br.com.pempec.finance.models.CidadeModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class BairroBO {

    public void inserir(BairroModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getBairroDAO().inserir(model);
    }

    public void alterar(BairroModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getBairroDAO().alterar(model);
    }

    public BairroModel consultarPorPk(BairroModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getBairroDAO().consultarPorPk(
                model);
    }

    public void excluir(BairroModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getBairroDAO().excluir(model);
    }

    public BairroModel consultarPorTemplate(BairroModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getBairroDAO().consultarPorTemplate(model);
    }

    public List<BairroModel> obterPorFiltro(BairroModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getBairroDAO().obterPorFiltro(
                model);
    }

    public List<BairroModel> obterPorCidade(CidadeModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getBairroDAO().obterPorCidade(
                model);
    }

    public void excluirTodos(Collection<BairroModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getBairroDAO().excluirTodos(coll);
    }

    public List<BairroModel> obterTodos()
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getBairroDAO().obterTodos();
    }
}

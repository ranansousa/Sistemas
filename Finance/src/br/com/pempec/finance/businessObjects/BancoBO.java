package br.com.pempec.finance.businessObjects;

import java.util.Collection;
import java.util.List;

import br.com.pempec.finance.daos.DAOFactory;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.BancoModel;
import br.com.pempec.finance.models.OrganizacaoModel;

/**
 * @author PEMPEC
 */
public class BancoBO {

    public void inserir(BancoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getBancoDAO().inserir(model);
    }

    public void alterar(BancoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getBancoDAO().alterar(model);
    }

    public BancoModel consultarPorPk(BancoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getBancoDAO().consultarPorPk(model);
    }

    public List<BancoModel> obterPorFiltro(BancoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getBancoDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<BancoModel> coll) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getBancoDAO().excluirTodos(coll);
    }

    public List<BancoModel> obterTodos() throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getBancoDAO().obterTodos();
    }

    public BancoModel consultarPorTemplate(BancoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getBancoDAO().consultarPorTemplate(model);
    }

    public void excluir(BancoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getBancoDAO().excluir(model);
    }
}

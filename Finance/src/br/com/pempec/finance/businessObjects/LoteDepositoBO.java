package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.LoteDepositoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class LoteDepositoBO {

    public void inserir(LoteDepositoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getLoteDepositoDAO().inserir(model);
    }

    public void alterar(LoteDepositoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getLoteDepositoDAO().alterar(model);
    }

    public LoteDepositoModel consultarPorPk(LoteDepositoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLoteDepositoDAO().consultarPorPk(model);
    }

    public List<LoteDepositoModel> obterPorFiltro(LoteDepositoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLoteDepositoDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<LoteDepositoModel> coll) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getLoteDepositoDAO().excluirTodos(coll);
    }

    public List<LoteDepositoModel> obterTodos(OrganizacaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLoteDepositoDAO().obterTodos(model);
    }

    public void excluir(LoteDepositoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getLoteDepositoDAO().excluir(model);
    }

    public LoteDepositoModel consultarPorTemplate(LoteDepositoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLoteDepositoDAO().consultarPorTemplate(model);
    }
}

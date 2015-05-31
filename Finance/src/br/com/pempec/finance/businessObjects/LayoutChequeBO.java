package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.LayoutChequeModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class LayoutChequeBO {

    public void inserir(LayoutChequeModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getLayoutChequeDAO().inserir(model);
    }

    public void alterar(LayoutChequeModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getLayoutChequeDAO().alterar(model);
    }

    public LayoutChequeModel consultarPorPk(LayoutChequeModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLayoutChequeDAO().consultarPorPk(
                model);
    }

    public List<LayoutChequeModel> obterPorFiltro(LayoutChequeModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLayoutChequeDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<LayoutChequeModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getLayoutChequeDAO().excluirTodos(coll);
    }

    public List<LayoutChequeModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLayoutChequeDAO().obterTodos(model);
    }

    public void excluir(LayoutChequeModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getLayoutChequeDAO().excluir(model);
    }

    public LayoutChequeModel consultarPorTemplate(LayoutChequeModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLayoutChequeDAO().consultarPorTemplate(model);
    }
}

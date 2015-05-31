package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoNotaFiscalModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class TipoNotaFiscalBO {

    public void inserir(TipoNotaFiscalModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTipoNotaFiscalDAO().inserir(model);
    }

    public void alterar(TipoNotaFiscalModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTipoNotaFiscalDAO().alterar(model);
    }

    public TipoNotaFiscalModel consultarPorPk(TipoNotaFiscalModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoNotaFiscalDAO().consultarPorPk(
                model);
    }

    public void excluir(TipoNotaFiscalModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTipoNotaFiscalDAO().excluir(model);
    }

    public TipoNotaFiscalModel consultarPorTemplate(TipoNotaFiscalModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoNotaFiscalDAO().consultarPorTemplate(model);
    }

    public List<TipoNotaFiscalModel> obterPorFiltro(TipoNotaFiscalModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoNotaFiscalDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<TipoNotaFiscalModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTipoNotaFiscalDAO().excluirTodos(coll);
    }

    public List<TipoNotaFiscalModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoNotaFiscalDAO().obterTodos(model);
    }
}

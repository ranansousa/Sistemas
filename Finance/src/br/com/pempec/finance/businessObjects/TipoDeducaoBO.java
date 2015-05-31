package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoDeducaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class TipoDeducaoBO {

    public void inserir(TipoDeducaoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTipoDeducaoDAO().inserir(model);
    }

    public void alterar(TipoDeducaoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTipoDeducaoDAO().alterar(model);
    }

    public TipoDeducaoModel consultarPorPk(TipoDeducaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoDeducaoDAO().consultarPorPk(
                model);
    }

    public void excluir(TipoDeducaoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTipoDeducaoDAO().excluir(model);
    }

    public TipoDeducaoModel consultarPorTemplate(TipoDeducaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoDeducaoDAO().consultarPorTemplate(model);
    }

    public List<TipoDeducaoModel> obterPorFiltro(TipoDeducaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoDeducaoDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<TipoDeducaoModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTipoDeducaoDAO().excluirTodos(coll);
    }

    public List<TipoDeducaoModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoDeducaoDAO().obterTodos(model);
    }
}

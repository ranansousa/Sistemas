package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoAcrescimoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class TipoAcrescimoBO {

    public void inserir(TipoAcrescimoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTipoAcrescimoDAO().inserir(model);
    }

    public void alterar(TipoAcrescimoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTipoAcrescimoDAO().alterar(model);
    }

    public TipoAcrescimoModel consultarPorPk(TipoAcrescimoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoAcrescimoDAO().consultarPorPk(
                model);
    }

    public void excluir(TipoAcrescimoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTipoAcrescimoDAO().excluir(model);
    }

    public TipoAcrescimoModel consultarPorTemplate(TipoAcrescimoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoAcrescimoDAO().consultarPorTemplate(model);
    }

    public List<TipoAcrescimoModel> obterPorFiltro(TipoAcrescimoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoAcrescimoDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<TipoAcrescimoModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTipoAcrescimoDAO().excluirTodos(coll);
    }

    public List<TipoAcrescimoModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoAcrescimoDAO().obterTodos(model);
    }
}

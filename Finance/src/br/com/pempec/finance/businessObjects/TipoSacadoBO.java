package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoSacadoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class TipoSacadoBO {

    public void inserir(TipoSacadoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTipoSacadoDAO().inserir(model);
    }

    public void alterar(TipoSacadoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTipoSacadoDAO().alterar(model);
    }

    public TipoSacadoModel consultarPorPk(TipoSacadoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoSacadoDAO().consultarPorPk(model);
    }

    public List<TipoSacadoModel> obterPorFiltro(TipoSacadoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoSacadoDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<TipoSacadoModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTipoSacadoDAO().excluirTodos(coll);
    }

    public List<TipoSacadoModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoSacadoDAO().obterTodos(model);
    }

    public TipoSacadoModel consultarPorTemplate(TipoSacadoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoSacadoDAO().consultarPorTemplate(model);
    }

    public void excluir(TipoSacadoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTipoSacadoDAO().excluir(model);
    }
}

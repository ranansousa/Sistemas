package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.GrupoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class GrupoBO {

    public void inserir(GrupoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getGrupoDAO().inserir(model);
    }

    public void alterar(GrupoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getGrupoDAO().alterar(model);
    }

    public GrupoModel consultarPorPk(GrupoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getGrupoDAO().consultarPorPk(model);
    }

    public List<GrupoModel> obterPorFiltro(GrupoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getGrupoDAO().obterPorFiltro(model);
    }

    public void excluirTodos(String[] array) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getGrupoDAO().excluirTodos(array);
    }

    public List<GrupoModel> obterTodos() throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getGrupoDAO().obterTodos();
    }

    public GrupoModel consultarPorTemplate(GrupoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getGrupoDAO().consultarPorTemplate(model);
    }

    public void excluirTodos(Collection<GrupoModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getGrupoDAO().excluirTodos(coll);
    }

    public void excluir(GrupoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getGrupoDAO().excluir(model);
    }
}

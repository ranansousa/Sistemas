package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.GrupoActionModel;
import br.com.pempec.finance.models.GrupoModel;
import java.util.List;

/**
 * @author PEMPEC
 */
public class GrupoActionBO {

    public void inserir(GrupoActionModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getGrupoActionDAO().inserir(model);
    }

    public void alterar(GrupoActionModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getGrupoActionDAO().alterar(model);
    }

    public GrupoActionModel consultarPorPk(GrupoActionModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getGrupoActionDAO().consultarPorPk(model);
    }

    public List<GrupoActionModel> obterPorGrupo(GrupoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getGrupoActionDAO().obterPorGrupo(model);
    }

    public void excluirTodos(String[] array) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getGrupoActionDAO().excluirTodos(array);
    }

    public List<GrupoActionModel> obterTodos() throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getGrupoActionDAO().obterTodos();
    }

    public void excluirTodosPorGrupo(GrupoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getGrupoActionDAO().excluirTodosPorGrupo(model);
    }

    public void inserirPermissaoPorGrupo(GrupoModel model, String[] permissoes) throws SystemException, ApplicationException {
        this.excluirTodosPorGrupo(model);
        DAOFactory.getInstance().getGrupoActionDAO().inserirPermissaoPorGrupo(model, permissoes);
    }
}

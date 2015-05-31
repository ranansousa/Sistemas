package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.GrupoModel;
import br.com.pempec.finance.models.GrupoUsuarioModel;
import br.com.pempec.finance.models.UsuarioModel;
import java.util.List;

/**
 * @author PEMPEC
 */
public class GrupoUsuarioBO {

    public void inserir(GrupoUsuarioModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getGrupoUsuarioDAO().inserir(model);
    }

    public void alterar(GrupoUsuarioModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getGrupoUsuarioDAO().alterar(model);
    }

    public GrupoUsuarioModel consultarPorPk(GrupoUsuarioModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getGrupoUsuarioDAO().consultarPorPk(model);
    }

    public List<GrupoUsuarioModel> obterPorGrupo(GrupoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getGrupoUsuarioDAO().obterPorGrupo(model);
    }

    public List<GrupoUsuarioModel> obterPorUsuario(UsuarioModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getGrupoUsuarioDAO().obterPorUsuario(model);
    }

    public void excluirTodos(String[] array) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getGrupoUsuarioDAO().excluirTodos(array);
    }

    public List<GrupoUsuarioModel> obterTodos() throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getGrupoUsuarioDAO().obterTodos();
    }

    public void excluirTodosPorGrupo(GrupoModel grupo) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getGrupoUsuarioDAO().excluirTodosPorGrupo(grupo);
    }
}

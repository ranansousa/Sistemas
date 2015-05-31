package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.UsuarioActionModel;
import br.com.pempec.finance.models.UsuarioModel;
import java.util.List;

/**
 * @author PEMPEC
 */
public class UsuarioActionBO {

    public void inserir(UsuarioActionModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getUsuarioActionDAO().inserir(model);
    }

    public void alterar(UsuarioActionModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getUsuarioActionDAO().alterar(model);
    }

    public UsuarioActionModel consultarPorPk(UsuarioActionModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getUsuarioActionDAO().consultarPorPk(model);
    }

    public List<UsuarioActionModel> obterPorUsuario(UsuarioModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getUsuarioActionDAO().obterPorUsuario(model);
    }

    public void excluirTodos(String[] array) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getUsuarioActionDAO().excluirTodos(array);
    }

    public List<UsuarioActionModel> obterTodos() throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getUsuarioActionDAO().obterTodos();
    }

    public void excluirTodosPorUsuario(UsuarioModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getUsuarioActionDAO().excluirTodosPorUsuario(model);
    }

    public void inserirPermissaoPorUsuario(UsuarioModel model, String[] permissoes) throws SystemException, ApplicationException {
        this.excluirTodosPorUsuario(model);
        DAOFactory.getInstance().getUsuarioActionDAO().inserirPermissaoPorUsuario(model, permissoes);
    }
}

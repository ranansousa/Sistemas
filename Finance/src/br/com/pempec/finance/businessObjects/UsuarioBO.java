package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.UsuarioModel;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public class UsuarioBO {

    public void salvar(UsuarioModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getUsuarioDAO().salvar(model);
    }

    public void resetarSenha(UsuarioModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getUsuarioDAO().resetarSenha(model);
    }

    public void registraAcesso(UsuarioModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getUsuarioDAO().registraAcesso(model);
    }

    public UsuarioModel consultarPorPk(UsuarioModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getUsuarioDAO().consultarPorPk(model);
    }

    public UsuarioModel consultarPorLogin(UsuarioModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getUsuarioDAO().consultarPorLogin(model);
    }

    public UsuarioModel validarUsuario(UsuarioModel model) throws SystemException, ApplicationException {
        UsuarioModel retorno = DAOFactory.getInstance().getUsuarioDAO().validarUsuario(model);

//        if (retorno != null && !retorno.isAtivo()) {
//            throw new ApplicationException("Usuário Inativo!");
//        }

        return retorno;
    }

    public List<UsuarioModel> obterPorFiltro(UsuarioModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getUsuarioDAO().obterPorFiltro(model);
    }

    public void excluir(UsuarioModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getUsuarioDAO().excluir(model);
    }

    public void excluirTodos(String[] array) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getUsuarioDAO().excluirTodos(array);
    }

    public List<UsuarioModel> obterTodos() throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getUsuarioDAO().obterTodos();
    }

    public UsuarioModel consultarPorTemplate(UsuarioModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getUsuarioDAO().consultarPorTemplate(model);
    }

    public Date obterDataServidorBD()
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getUsuarioDAO().obterDataServidorBD();
    }
}

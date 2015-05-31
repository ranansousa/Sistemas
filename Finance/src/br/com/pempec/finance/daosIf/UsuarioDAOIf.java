package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.UsuarioModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author EQUIPE PEMPEC
 *
 */
public interface UsuarioDAOIf {

    public void salvar(UsuarioModel model) throws SystemException;

    public void registraAcesso(UsuarioModel model) throws SystemException;

    public void resetarSenha(UsuarioModel model) throws SystemException;

    public UsuarioModel consultarPorPk(UsuarioModel model) throws SystemException;

    public List<UsuarioModel> obterTodos() throws SystemException;

    public List<UsuarioModel> obterPorFiltro(UsuarioModel model) throws SystemException;

    public void excluir(UsuarioModel model) throws SystemException;

    public void excluirTodos(String[] array) throws SystemException;

    public UsuarioModel validarUsuario(UsuarioModel model) throws SystemException;

    public UsuarioModel consultarPorLogin(UsuarioModel model) throws SystemException;

    public UsuarioModel consultarPorTemplate(UsuarioModel model)
            throws SystemException;

    public Date obterDataServidorBD() throws SystemException;
}

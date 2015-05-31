package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.UsuarioActionModel;
import br.com.pempec.finance.models.UsuarioModel;
import java.util.List;

/**
 *
 * @author EQUIPE PEMPEC
 *
 */
public interface UsuarioActionDAOIf {

    public void inserir(UsuarioActionModel model) throws SystemException;

    public void alterar(UsuarioActionModel model) throws SystemException;

    public UsuarioActionModel consultarPorPk(UsuarioActionModel model) throws SystemException;

    public List<UsuarioActionModel> obterTodos() throws SystemException;

    public List<UsuarioActionModel> obterPorUsuario(UsuarioModel model) throws SystemException;

    public void excluirTodos(String[] array) throws SystemException;

    public void excluirTodosPorUsuario(UsuarioModel model) throws SystemException;

    public void inserirPermissaoPorUsuario(UsuarioModel model, String[] permissoes) throws SystemException;
}

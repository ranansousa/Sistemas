package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.GrupoModel;
import br.com.pempec.finance.models.GrupoUsuarioModel;
import br.com.pempec.finance.models.UsuarioModel;
import java.util.List;

/**
 *
 * @author PEMPEC
 *
 */
public interface GrupoUsuarioDAOIf {

    public void inserir(GrupoUsuarioModel model) throws SystemException;

    public void alterar(GrupoUsuarioModel model) throws SystemException;

    public GrupoUsuarioModel consultarPorPk(GrupoUsuarioModel model) throws SystemException;

    public List<GrupoUsuarioModel> obterTodos() throws SystemException;

    public List<GrupoUsuarioModel> obterPorGrupo(GrupoModel model) throws SystemException;

    public List<GrupoUsuarioModel> obterPorUsuario(UsuarioModel model) throws SystemException;

    public void excluirTodos(String[] array) throws SystemException;

    public void excluirTodosPorGrupo(GrupoModel grupo) throws SystemException;
}

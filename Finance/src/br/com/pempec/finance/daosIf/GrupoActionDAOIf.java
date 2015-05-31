package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.GrupoActionModel;
import br.com.pempec.finance.models.GrupoModel;
import java.util.List;

/**
 *
 * @author PEMPEC
 *
 */
public interface GrupoActionDAOIf {

    public void inserir(GrupoActionModel model) throws SystemException;

    public void alterar(GrupoActionModel model) throws SystemException;

    public GrupoActionModel consultarPorPk(GrupoActionModel model) throws SystemException;

    public List<GrupoActionModel> obterTodos() throws SystemException;

    public List<GrupoActionModel> obterPorGrupo(GrupoModel model) throws SystemException;

    public void excluirTodos(String[] array) throws SystemException;

    public void excluirTodosPorGrupo(GrupoModel model) throws SystemException;

    public void inserirPermissaoPorGrupo(GrupoModel model, String[] permissoes) throws SystemException;
}

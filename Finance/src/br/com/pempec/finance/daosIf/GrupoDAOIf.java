package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.GrupoModel;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author //EQUIPE PEMPEC
 *
 */
public interface GrupoDAOIf {

    public void inserir(GrupoModel model) throws SystemException;

    public void alterar(GrupoModel model) throws SystemException;

    public GrupoModel consultarPorPk(GrupoModel model) throws SystemException;

    public List<GrupoModel> obterTodos() throws SystemException;

    public List<GrupoModel> obterPorFiltro(GrupoModel model) throws SystemException;

    public void excluirTodos(String[] array) throws SystemException;

    public GrupoModel consultarPorTemplate(GrupoModel model)
            throws SystemException;

    public void excluirTodos(Collection<GrupoModel> coll)
            throws SystemException;

    public void excluir(GrupoModel model) throws SystemException;
}

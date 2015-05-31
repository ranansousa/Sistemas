package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoSacadoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TipoSacadoDAOIf {

    public void inserir(TipoSacadoModel model) throws SystemException;

    public void alterar(TipoSacadoModel model) throws SystemException;

    public TipoSacadoModel consultarPorPk(TipoSacadoModel model) throws SystemException;

    public List<TipoSacadoModel> obterPorFiltro(TipoSacadoModel model)
            throws SystemException;

    public List<TipoSacadoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluirTodos(Collection<TipoSacadoModel> coll)
            throws SystemException;

    public void excluir(TipoSacadoModel model) throws SystemException;

    public TipoSacadoModel consultarPorTemplate(TipoSacadoModel model)
            throws SystemException;
}

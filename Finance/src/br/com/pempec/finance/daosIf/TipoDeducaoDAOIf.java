package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoDeducaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TipoDeducaoDAOIf {

    public void inserir(TipoDeducaoModel model) throws SystemException;

    public void alterar(TipoDeducaoModel model) throws SystemException;

    public TipoDeducaoModel consultarPorPk(TipoDeducaoModel model)
            throws SystemException;

    public List<TipoDeducaoModel> obterPorFiltro(TipoDeducaoModel model)
            throws SystemException;

    public List<TipoDeducaoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(TipoDeducaoModel model) throws SystemException;

    public TipoDeducaoModel consultarPorTemplate(TipoDeducaoModel model)
            throws SystemException;

    public void excluirTodos(Collection<TipoDeducaoModel> coll)
            throws SystemException;
}

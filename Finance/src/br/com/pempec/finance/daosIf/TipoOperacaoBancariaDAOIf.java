package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TipoOperacaoBancariaDAOIf {

    public void inserir(TipoOperacaoBancariaModel model) throws SystemException;

    public void alterar(TipoOperacaoBancariaModel model) throws SystemException;

    public TipoOperacaoBancariaModel consultarPorPk(TipoOperacaoBancariaModel model)
            throws SystemException;

    public List<TipoOperacaoBancariaModel> obterPorFiltro(TipoOperacaoBancariaModel model)
            throws SystemException;

    public List<TipoOperacaoBancariaModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public List<TipoOperacaoBancariaModel> obterTodosPorTipo(OrganizacaoModel model, int tipo)
            throws SystemException;

    public void excluir(TipoOperacaoBancariaModel model) throws SystemException;

    public TipoOperacaoBancariaModel consultarPorTemplate(TipoOperacaoBancariaModel model)
            throws SystemException;

    public void excluirTodos(Collection<TipoOperacaoBancariaModel> coll)
            throws SystemException;
}

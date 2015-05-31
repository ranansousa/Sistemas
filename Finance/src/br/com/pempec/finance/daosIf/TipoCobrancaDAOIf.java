package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoCobrancaModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TipoCobrancaDAOIf {

    public void inserir(TipoCobrancaModel model) throws SystemException;

    public void alterar(TipoCobrancaModel model) throws SystemException;

    public TipoCobrancaModel consultarPorPk(TipoCobrancaModel model)
            throws SystemException;

    public List<TipoCobrancaModel> obterPorFiltro(TipoCobrancaModel model)
            throws SystemException;

    public List<TipoCobrancaModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(TipoCobrancaModel model) throws SystemException;

    public TipoCobrancaModel consultarPorTemplate(TipoCobrancaModel model)
            throws SystemException;

    public void excluirTodos(Collection<TipoCobrancaModel> coll)
            throws SystemException;
}

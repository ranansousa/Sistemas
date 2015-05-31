package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoNotaFiscalModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TipoNotaFiscalDAOIf {

    public void inserir(TipoNotaFiscalModel model) throws SystemException;

    public void alterar(TipoNotaFiscalModel model) throws SystemException;

    public TipoNotaFiscalModel consultarPorPk(TipoNotaFiscalModel model)
            throws SystemException;

    public List<TipoNotaFiscalModel> obterPorFiltro(TipoNotaFiscalModel model)
            throws SystemException;

    public List<TipoNotaFiscalModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(TipoNotaFiscalModel model) throws SystemException;

    public TipoNotaFiscalModel consultarPorTemplate(TipoNotaFiscalModel model)
            throws SystemException;

    public void excluirTodos(Collection<TipoNotaFiscalModel> coll)
            throws SystemException;
}

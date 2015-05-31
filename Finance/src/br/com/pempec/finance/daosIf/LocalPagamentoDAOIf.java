package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.LocalPagamentoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface LocalPagamentoDAOIf {

    public void inserir(LocalPagamentoModel model) throws SystemException;

    public void alterar(LocalPagamentoModel model) throws SystemException;

    public LocalPagamentoModel consultarPorPk(LocalPagamentoModel model)
            throws SystemException;

    public List<LocalPagamentoModel> obterPorFiltro(LocalPagamentoModel model)
            throws SystemException;

    public List<LocalPagamentoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluirTodos(Collection<LocalPagamentoModel> coll)
            throws SystemException;

    public void excluir(LocalPagamentoModel model) throws SystemException;

    public LocalPagamentoModel consultarPorTemplate(LocalPagamentoModel model)
            throws SystemException;
}

package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.FormaPagamentoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface FormaPagamentoDAOIf {

    public void inserir(FormaPagamentoModel model) throws SystemException;

    public void alterar(FormaPagamentoModel model) throws SystemException;

    public FormaPagamentoModel consultarPorPk(FormaPagamentoModel model)
            throws SystemException;

    public List<FormaPagamentoModel> obterPorFiltro(FormaPagamentoModel model)
            throws SystemException;

    public List<FormaPagamentoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(FormaPagamentoModel model) throws SystemException;

    public FormaPagamentoModel consultarPorTemplate(FormaPagamentoModel model)
            throws SystemException;

    public void excluirTodos(Collection<FormaPagamentoModel> coll)
            throws SystemException;
}

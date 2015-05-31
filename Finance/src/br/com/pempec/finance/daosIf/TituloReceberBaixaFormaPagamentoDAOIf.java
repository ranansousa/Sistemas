package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.TituloReceberBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TituloReceberBaixaFormaPagamentoDAOIf {

    public void inserir(TituloReceberBaixaFormaPagamentoModel model) throws SystemException;

    public void alterar(TituloReceberBaixaFormaPagamentoModel model) throws SystemException;

    public TituloReceberBaixaFormaPagamentoModel consultarPorPk(TituloReceberBaixaFormaPagamentoModel model) throws SystemException;

    public List<TituloReceberBaixaFormaPagamentoModel> obterPorFiltro(TituloReceberBaixaFormaPagamentoModel model)
            throws SystemException;

    public List<TituloReceberBaixaFormaPagamentoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluirTodos(Collection<TituloReceberBaixaFormaPagamentoModel> coll)
            throws SystemException;

    public TituloReceberBaixaFormaPagamentoModel consultarPorTemplate(TituloReceberBaixaFormaPagamentoModel model)
            throws SystemException;

    public void excluir(TituloReceberBaixaFormaPagamentoModel model) throws SystemException;
}

package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.FormaPagamentoModel;
import br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TituloPagarBaixaFormaPagamentoDAOIf {

    public void inserir(TituloPagarBaixaFormaPagamentoModel model) throws SystemException;

    public void alterar(TituloPagarBaixaFormaPagamentoModel model) throws SystemException;

    public TituloPagarBaixaFormaPagamentoModel consultarPorPk(TituloPagarBaixaFormaPagamentoModel model) throws SystemException;

    public List<TituloPagarBaixaFormaPagamentoModel> obterPorFiltro(TituloPagarBaixaFormaPagamentoModel model)
            throws SystemException;

    public List<TituloPagarBaixaFormaPagamentoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public List<TituloPagarBaixaFormaPagamentoModel> obterTodosPorFormaPagto(FormaPagamentoModel model)
            throws SystemException;

    public void excluirTodos(Collection<TituloPagarBaixaFormaPagamentoModel> coll)
            throws SystemException;

    public TituloPagarBaixaFormaPagamentoModel consultarPorTemplate(TituloPagarBaixaFormaPagamentoModel model)
            throws SystemException;

    public void excluir(TituloPagarBaixaFormaPagamentoModel model) throws SystemException;
}

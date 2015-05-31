package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CentroCustoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloPagarRateioCCModel;
import br.com.pempec.finance.models.reports.FilterReportTituloPagar;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TituloPagarRateioCCDAOIf {

    public void inserir(Collection<TituloPagarRateioCCModel> titulos) throws SystemException;

    public void inserir(TituloPagarRateioCCModel model) throws SystemException;

    public void alterar(TituloPagarRateioCCModel model) throws SystemException;

    public TituloPagarRateioCCModel consultarPorPk(TituloPagarRateioCCModel model)
            throws SystemException;

    public List<TituloPagarRateioCCModel> obterPorFiltro(TituloPagarRateioCCModel model)
            throws SystemException;

    public List<TituloPagarRateioCCModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public List<TituloPagarRateioCCModel> obterRelatorio(FilterReportTituloPagar model)
            throws SystemException;

    public void excluir(TituloPagarRateioCCModel model) throws SystemException;

    public TituloPagarRateioCCModel consultarPorTemplate(TituloPagarRateioCCModel model)
            throws SystemException;

    public void excluirTodos(Collection<TituloPagarRateioCCModel> coll)
            throws SystemException;

    public List<TituloPagarRateioCCModel> obterTodosPorCentroCusto(CentroCustoModel centroCusto)
            throws SystemException;
}//fim

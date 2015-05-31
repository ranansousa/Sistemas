package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CentroCustoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloReceberRateioCCModel;
import br.com.pempec.finance.models.reports.FilterReportTituloReceber;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TituloReceberRateioCCDAOIf {

    public void inserir(Collection<TituloReceberRateioCCModel> titulos) throws SystemException;

    public void inserir(TituloReceberRateioCCModel model) throws SystemException;

    public void alterar(TituloReceberRateioCCModel model) throws SystemException;

    public TituloReceberRateioCCModel consultarPorPk(TituloReceberRateioCCModel model)
            throws SystemException;

    public List<TituloReceberRateioCCModel> obterPorFiltro(TituloReceberRateioCCModel model)
            throws SystemException;

    public List<TituloReceberRateioCCModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public List<TituloReceberRateioCCModel> obterRelatorio(FilterReportTituloReceber model)
            throws SystemException;

    public void excluir(TituloReceberRateioCCModel model) throws SystemException;

    public TituloReceberRateioCCModel consultarPorTemplate(TituloReceberRateioCCModel model)
            throws SystemException;

    public void excluirTodos(Collection<TituloReceberRateioCCModel> coll)
            throws SystemException;

    public List<TituloReceberRateioCCModel> obterTodosPorCentroCusto(CentroCustoModel centroCusto)
            throws SystemException;
}//fim

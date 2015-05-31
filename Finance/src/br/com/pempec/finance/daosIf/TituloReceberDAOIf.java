package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ImpressaoMultiplosRecibosFilter;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.ParticionadorTituloReceberModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.models.TituloReceberRateioCCModel;
import br.com.pempec.finance.models.TituloReceberRateioHistoricoModel;
import br.com.pempec.finance.models.reports.FilterReportFluxoCaixa;
import br.com.pempec.finance.models.reports.FilterReportTituloReceber;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TituloReceberDAOIf {

    public void inserir(Collection<TituloReceberModel> titulos) throws SystemException;

    public void inserir(TituloReceberModel model) throws SystemException;

    public void alterar(TituloReceberModel model) throws SystemException;

    public TituloReceberModel consultarPorPk(TituloReceberModel model)
            throws SystemException;

    public List<TituloReceberModel> obterPorFiltro(TituloReceberModel model)
            throws SystemException;

    public List<TituloReceberModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public List<TituloReceberModel> obterImpressaoMultiplosRecibos(ImpressaoMultiplosRecibosFilter filter)
            throws SystemException;

    public List<TituloReceberModel> obterTitulosExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<TituloReceberModel> obterTitulosExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<TituloReceberModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException;

    public void excluir(TituloReceberModel model) throws SystemException;

    public TituloReceberModel consultarPorTemplate(TituloReceberModel model)
            throws SystemException;

    public List<TituloReceberRateioHistoricoModel> obterHistoricosPorTituloReceber(TituloReceberModel titulo)
            throws SystemException;

    public List<TituloReceberRateioCCModel> obterRateioCustosPorTituloReceber(TituloReceberModel titulo)
            throws SystemException;

    public List<TituloReceberModel> obterTodosRecibo(OrganizacaoModel model)
            throws SystemException;

    public TituloReceberModel consultarPorTemplateAReceber(TituloReceberModel model)
            throws SystemException;

    public TituloReceberModel consultarPorTemplatePago(TituloReceberModel model)
            throws SystemException;

    public void excluirTodos(Collection<TituloReceberModel> coll)
            throws SystemException;

    public List<TituloReceberModel> obterTodosPagos(OrganizacaoModel model)
            throws SystemException;

    public List<TituloReceberModel> obterTodosAReceber(OrganizacaoModel model)
            throws SystemException;

    public List<TituloReceberModel> obterFilhos(TituloReceberModel model)
            throws SystemException;

    public List<TituloReceberModel> obterTitulosAntecipados(TituloReceberModel model)
            throws SystemException;

    public List<TituloReceberModel> obterTitulosFilhos(TituloReceberModel model) throws SystemException;

    public void particionar(ParticionadorTituloReceberModel model) throws SystemException;

    public List<TituloReceberModel> obterRelatorio(FilterReportTituloReceber model)
            throws SystemException;

    public List<TituloReceberModel> obterRelatorioFluxo(FilterReportFluxoCaixa model)
            throws SystemException;

    public List<TituloReceberModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException;

    
    public List<TituloReceberModel> obterTodosPorPeriodo(TituloReceberModel model, Date dataInicial, Date dataFinal)
            throws SystemException;
    
    public void corrigeRegistroProvisao() throws SystemException;
}

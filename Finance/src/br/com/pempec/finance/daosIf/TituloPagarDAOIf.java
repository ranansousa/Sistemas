package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ImpressaoMultiplosRecibosFilter;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.LotePagamentoTituloModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.ParticionadorTituloPagarModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloPagarRateioCCModel;
import br.com.pempec.finance.models.TituloPagarRateioHistoricoModel;
import br.com.pempec.finance.models.reports.FilterReportFluxoCaixa;
import br.com.pempec.finance.models.reports.FilterReportTituloPagar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TituloPagarDAOIf {

    public void inserir(Collection<TituloPagarModel> titulos) throws SystemException;

    public void inserir(TituloPagarModel model) throws SystemException;

    public void particionar(ParticionadorTituloPagarModel model) throws SystemException;

    public void alterar(TituloPagarModel model) throws SystemException;

    public TituloPagarModel consultarPorPk(TituloPagarModel model)
            throws SystemException;

    public List<TituloPagarModel> obterPorFiltro(TituloPagarModel model)
            throws SystemException;

    public List<TituloPagarModel> obterImpressaoMultiplosRecibos(ImpressaoMultiplosRecibosFilter filter)
            throws SystemException;

    public List<TituloPagarRateioCCModel> obterRateioPorTituloPagar(TituloPagarModel titulo)
            throws SystemException;

    public List<TituloPagarRateioHistoricoModel> obterHistoricosPorTituloPagar(TituloPagarModel titulo)
            throws SystemException;

    public List<TituloPagarModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public List<TituloPagarModel> obterRelatorio(FilterReportTituloPagar model)
            throws SystemException;

    public List<TituloPagarModel> obterRelatorioFluxo(FilterReportFluxoCaixa model)
            throws SystemException;

    public List<TituloPagarModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException;

    public List<TituloPagarModel> obterTodosRecibo(OrganizacaoModel model)
            throws SystemException;

    public void excluir(TituloPagarModel model) throws SystemException;

    public TituloPagarModel consultarPorTemplate(TituloPagarModel model)
            throws SystemException;

    public TituloPagarModel consultarPorTemplateAPagar(TituloPagarModel model)
            throws SystemException;

    public TituloPagarModel consultarPorTemplatePago(TituloPagarModel model)
            throws SystemException;

    public List<TituloPagarModel> obterTitulosExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<TituloPagarModel> obterTitulosExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public void excluirTodos(Collection<TituloPagarModel> coll)
            throws SystemException;

    public List<TituloPagarModel> obterTodosPagos(OrganizacaoModel model)
            throws SystemException;

    public List<TituloPagarModel> obterTodosAPagar(OrganizacaoModel model)
            throws SystemException;

    public List<TituloPagarModel> obterTitulosFilhos(TituloPagarModel model) throws SystemException;

    public List<TituloPagarModel> obterTitulosAntecipados(TituloPagarModel model)
            throws SystemException;

    public List<TituloPagarModel> obterTitulosLotePagamento(LotePagamentoTituloModel model) throws SystemException;

    public List<TituloPagarModel> obterTodosPorPeriodo(TituloPagarModel model, Date dataInicial, Date dataFinal, String[] ordem)
            throws SystemException;

    public List<TituloPagarModel> obterNotaFiscaisPorPeriodo(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<TituloPagarModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException;

    public void corrigeRegistroProvisao() throws SystemException;
}//fim

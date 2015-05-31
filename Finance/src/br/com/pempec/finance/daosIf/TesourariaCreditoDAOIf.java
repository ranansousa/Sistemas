package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TesourariaCreditoModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;
import br.com.pempec.finance.models.reports.FilterReportExtratoTesouraria;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TesourariaCreditoDAOIf {

    public void inserir(TesourariaCreditoModel model) throws SystemException;

    public void alterar(TesourariaCreditoModel model) throws SystemException;

    public TesourariaCreditoModel consultarPorPk(TesourariaCreditoModel model)
            throws SystemException;

    public List<TesourariaCreditoModel> obterPorFiltro(TesourariaCreditoModel model)
            throws SystemException;

    public List<TesourariaCreditoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(TesourariaCreditoModel model) throws SystemException;

    public TesourariaCreditoModel consultarPorTemplate(TesourariaCreditoModel model)
            throws SystemException;

    public void excluirTodos(Collection<TesourariaCreditoModel> coll)
            throws SystemException;

    public List<TesourariaCreditoModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException;

    public List<TesourariaCreditoModel> obterTodosPorTituloRecebido(TituloReceberBaixaModel model)
            throws SystemException;

    public List<TesourariaCreditoModel> obterTesourariaCreditoExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<TesourariaCreditoModel> obterTesourariaCreditoExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<TesourariaCreditoModel> obterRelatorioExtratoTesouria(FilterReportExtratoTesouraria filter) throws SystemException;

    public Double obterSaldoAnterior(FilterReportExtratoTesouraria filter) throws SystemException;

    public Double obterTotal(OrganizacaoModel organizacaoModel) throws SystemException;

    public List<TesourariaCreditoModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException;
}

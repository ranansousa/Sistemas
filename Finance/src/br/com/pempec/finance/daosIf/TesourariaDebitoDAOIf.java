package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.LoteDepositoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TesourariaDebitoModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.models.TituloReceberBaixaChequeModel;
import br.com.pempec.finance.models.reports.FilterReportExtratoTesouraria;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TesourariaDebitoDAOIf {

    public void inserir(TesourariaDebitoModel model) throws SystemException;

    public void alterar(TesourariaDebitoModel model) throws SystemException;

    public TesourariaDebitoModel consultarPorPk(TesourariaDebitoModel model)
            throws SystemException;

    public List<TesourariaDebitoModel> obterPorFiltro(TesourariaDebitoModel model)
            throws SystemException;

    public List<TesourariaDebitoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(TesourariaDebitoModel model) throws SystemException;

    public TesourariaDebitoModel consultarPorTemplate(TesourariaDebitoModel model)
            throws SystemException;

    public void excluirTodos(Collection<TesourariaDebitoModel> coll)
            throws SystemException;

    public List<TesourariaDebitoModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException;

    public List<TesourariaDebitoModel> obterTodosPorTituloPago(TituloPagarBaixaModel model)
            throws SystemException;

    public List<TesourariaDebitoModel> obterTesourariaDebitoExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<TesourariaDebitoModel> obterTesourariaDebitoExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public void depositarBanco(TesourariaDebitoModel tesourariaModel, ContaBancariaCreditoModel ctbCrModel)
            throws SystemException;

    public void excluirDepositoBanco(TesourariaDebitoModel tesourariaModel, ContaBancariaCreditoModel ctbCrModel)
            throws SystemException;

    public void depositarChequesBanco(Collection<TituloReceberBaixaChequeModel> collCheques, ContaBancariaCreditoModel ctbCrModel, LoteDepositoModel lote)
            throws SystemException;

    public List<TesourariaDebitoModel> obterTodosTitulosPagos(OrganizacaoModel model)
            throws SystemException;

    public List<TesourariaDebitoModel> obterRelatorioExtratoTesouria(FilterReportExtratoTesouraria filter) throws SystemException;

    public Double obterSaldoAnterior(FilterReportExtratoTesouraria filter) throws SystemException;

    public Double obterTotal(OrganizacaoModel organizacaoModel) throws SystemException;

    public List<TesourariaDebitoModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException;
}

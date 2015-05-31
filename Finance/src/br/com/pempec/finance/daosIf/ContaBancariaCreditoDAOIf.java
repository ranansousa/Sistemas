package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.reports.FilterReportContaBancariaCredito;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface ContaBancariaCreditoDAOIf {

    public void inserir(ContaBancariaCreditoModel model) throws SystemException;

    public void alterar(ContaBancariaCreditoModel model) throws SystemException;

    public ContaBancariaCreditoModel consultarPorPk(ContaBancariaCreditoModel model)
            throws SystemException;

    public List<ContaBancariaCreditoModel> obterPorFiltro(ContaBancariaCreditoModel model)
            throws SystemException;

    public List<ContaBancariaCreditoModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException;

    public List<ContaBancariaCreditoModel> obterPorContaBancaria(ContaBancariaModel model)
            throws SystemException;

    public List<ContaBancariaCreditoModel> obterPorContaBancariaStatus(ContaBancariaModel model, TipoStatusModel status)
            throws SystemException;

    public List<ContaBancariaCreditoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public List<ContaBancariaCreditoModel> obterTodosLancamentosBancariosPorTipo(OrganizacaoModel model, String tipo)
            throws SystemException;

    public List<ContaBancariaCreditoModel> obterTodosLancamentosTesourariaPorTipo(OrganizacaoModel model, String tipo)
            throws SystemException;

    public void excluir(ContaBancariaCreditoModel model) throws SystemException;

    public ContaBancariaCreditoModel consultarPorTemplate(ContaBancariaCreditoModel model)
            throws SystemException;

    public ContaBancariaCreditoModel obterPorCheque(ContaBancariaCreditoModel model)
            throws SystemException;

    public ContaBancariaCreditoModel consultarPorTemplateLancamentoBancario(ContaBancariaCreditoModel model)
            throws SystemException;

    public void excluirTodos(Collection<ContaBancariaCreditoModel> coll)
            throws SystemException;

    public List<ContaBancariaCreditoModel> obterContaBancariaCreditoExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<ContaBancariaCreditoModel> obterContaBancariaCreditoExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<ContaBancariaCreditoModel> obterRelatorio(FilterReportContaBancariaCredito filtro)
            throws SystemException;

    public Double obterSaldoAnterior(FilterReportContaBancariaCredito filter) throws SystemException;

    public Double obterTotal(ContaBancariaModel contaBancaria) throws SystemException;

    public List<ContaBancariaCreditoModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException;
}

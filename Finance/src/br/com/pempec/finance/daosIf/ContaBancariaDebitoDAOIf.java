package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.TesourariaCreditoModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.reports.FilterReportContaBancariaDebito;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface ContaBancariaDebitoDAOIf {

    public void transfereBancoTesouraria(ContaBancariaDebitoModel debito, TesourariaCreditoModel credito) throws SystemException;

    public void estornoTransferenciaBancoTesouraria(ContaBancariaDebitoModel debito, TesourariaCreditoModel credito) throws SystemException;

    public void inserir(ContaBancariaDebitoModel model) throws SystemException;

    public void inserirMultiplos(Collection<ContaBancariaDebitoModel> lista) throws SystemException;

    public void alterar(ContaBancariaDebitoModel model) throws SystemException;

    public ContaBancariaDebitoModel consultarPorPk(ContaBancariaDebitoModel model)
            throws SystemException;

    public List<ContaBancariaDebitoModel> obterPorFiltro(ContaBancariaDebitoModel model)
            throws SystemException;

    public List<ContaBancariaDebitoModel> obterPorContaBancaria(ContaBancariaModel model)
            throws SystemException;

    public List<ContaBancariaDebitoModel> obterPorContaBancariaStatus(ContaBancariaModel model, TipoStatusModel status)
            throws SystemException;

    public List<ContaBancariaDebitoModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException;

    public List<ContaBancariaDebitoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public List<ContaBancariaDebitoModel> obterTodosLancamentosBancariosPorTipo(OrganizacaoModel model, String tipo)
            throws SystemException;

    public List<ContaBancariaDebitoModel> obterTodosLancamentosTesourariaPorTipo(OrganizacaoModel model, String tipo)
            throws SystemException;

    public void excluir(ContaBancariaDebitoModel model) throws SystemException;

    public ContaBancariaDebitoModel consultarPorTemplate(ContaBancariaDebitoModel model)
            throws SystemException;

    public ContaBancariaDebitoModel obterPorCheque(ContaBancariaDebitoModel model)
            throws SystemException;

    public ContaBancariaDebitoModel consultarPorTemplateLancamentoBancario(ContaBancariaDebitoModel model)
            throws SystemException;

    public void excluirTodos(Collection<ContaBancariaDebitoModel> coll)
            throws SystemException;

    public List<ContaBancariaDebitoModel> obterContaBancariaDebitoExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<ContaBancariaDebitoModel> obterContaBancariaDebitoExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<ContaBancariaDebitoModel> obterTodosTitulosPagos(OrganizacaoModel model)
            throws SystemException;

    public List<ContaBancariaDebitoModel> obterRelatorio(FilterReportContaBancariaDebito filtro)
            throws SystemException;

    public Double obterSaldoAnterior(FilterReportContaBancariaDebito filter) throws SystemException;

    public Double obterTotal(ContaBancariaModel contaBancaria) throws SystemException;

    public List<ContaBancariaDebitoModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException;

    public List<ContaBancariaDebitoModel> obterTodosPorOperacaoBancaria(OrganizacaoModel model, TipoOperacaoBancariaModel operacao)
            throws SystemException;
}

package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.ContaBancariaTransferenciaModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.reports.FilterReportContaBancariaTransferencia;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface ContaBancariaTransferenciaDAOIf {

    public void inserir(ContaBancariaTransferenciaModel model) throws SystemException;

    public void alterar(ContaBancariaTransferenciaModel model) throws SystemException;

    public ContaBancariaTransferenciaModel consultarPorPk(ContaBancariaTransferenciaModel model)
            throws SystemException;

    public List<ContaBancariaTransferenciaModel> obterPorFiltro(ContaBancariaTransferenciaModel model)
            throws SystemException;

    public List<ContaBancariaTransferenciaModel> obterPorContaBancaria(ContaBancariaModel conta)
            throws SystemException;

    public List<ContaBancariaTransferenciaModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public List<ContaBancariaTransferenciaModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException;

    public void excluirTodos(Collection<ContaBancariaTransferenciaModel> coll)
            throws SystemException;

    public void excluir(ContaBancariaTransferenciaModel model) throws SystemException;

    public ContaBancariaTransferenciaModel consultarPorTemplate(ContaBancariaTransferenciaModel model)
            throws SystemException;

    public List<ContaBancariaTransferenciaModel> obterContaBancariaTransferenciaExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<ContaBancariaTransferenciaModel> obterContaBancariaTransferenciaExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<ContaBancariaTransferenciaModel> obterRelatorio(FilterReportContaBancariaTransferencia filtro)
            throws SystemException;

    public List<ContaBancariaTransferenciaModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException;
}

package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
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
public class ContaBancariaCreditoBO {

    public void inserir(ContaBancariaCreditoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaBancariaCreditoDAO().inserir(model);
    }

    public void alterar(ContaBancariaCreditoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaBancariaCreditoDAO().alterar(model);
    }

    public ContaBancariaCreditoModel consultarPorPk(ContaBancariaCreditoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaCreditoDAO().consultarPorPk(
                model);
    }

    public void excluir(ContaBancariaCreditoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getContaBancariaCreditoDAO().excluir(model);
    }

    public ContaBancariaCreditoModel consultarPorTemplate(ContaBancariaCreditoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaCreditoDAO().consultarPorTemplate(model);
    }

    public List<ContaBancariaCreditoModel> obterPorFiltro(ContaBancariaCreditoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaCreditoDAO().obterPorFiltro(
                model);
    }

    public List<ContaBancariaCreditoModel> obterPorContaBancaria(ContaBancariaModel model)
            throws SystemException, ApplicationException {

        return DAOFactory.getInstance().getContaBancariaCreditoDAO().obterPorContaBancaria(
                model);
    }

    public List<ContaBancariaCreditoModel> obterPorContaBancariaStatus(ContaBancariaModel model, TipoStatusModel status)
            throws SystemException, ApplicationException {

        return DAOFactory.getInstance().getContaBancariaCreditoDAO().obterPorContaBancariaStatus(
                model, status);
    }

    public void excluirTodos(Collection<ContaBancariaCreditoModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getContaBancariaCreditoDAO().excluirTodos(coll);
    }

    public List<ContaBancariaCreditoModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaCreditoDAO().obterTodos(model);
    }

    public List<ContaBancariaCreditoModel> obterTodosLancamentosBancariosPorTipo(OrganizacaoModel model, String tipo)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaCreditoDAO().obterTodosLancamentosBancariosPorTipo(model, tipo);
    }

    public ContaBancariaCreditoModel consultarPorTemplateLancamentoBancario(ContaBancariaCreditoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaCreditoDAO().consultarPorTemplateLancamentoBancario(model);
    }

    public List<ContaBancariaCreditoModel> obterTodosLancamentosTesourariaPorTipo(OrganizacaoModel model, String tipo)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaCreditoDAO().obterTodosLancamentosTesourariaPorTipo(model, tipo);
    }

    public ContaBancariaCreditoModel obterPorCheque(ContaBancariaCreditoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaCreditoDAO().obterPorCheque(model);
    }

    public List<ContaBancariaCreditoModel> obterContaBancariaCreditoExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaCreditoDAO().obterContaBancariaCreditoExportacao(model, dataInicial, dataFinal);
    }

    public List<ContaBancariaCreditoModel> obterContaBancariaCreditoExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaCreditoDAO().obterContaBancariaCreditoExportacaoRelatorio(model, dataInicial, dataFinal);
    }

    public List<ContaBancariaCreditoModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaCreditoDAO().obterTitulosExportados(model);
    }

    public List<ContaBancariaCreditoModel> obterRelatorio(FilterReportContaBancariaCredito filtro)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaCreditoDAO().obterRelatorio(filtro);
    }

    public Double obterSaldoAnterior(FilterReportContaBancariaCredito filter) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaCreditoDAO().obterSaldoAnterior(filter);
    }

    public Double obterTotal(ContaBancariaModel contaBancaria) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaCreditoDAO().obterTotal(contaBancaria);
    }

    public List<ContaBancariaCreditoModel> obterTodosPorData(OrganizacaoModel org, Date dataInicial) {
        try {
            return DAOFactory.getInstance().getContaBancariaCreditoDAO().obterTodosPorData(org, dataInicial);
        } catch (SystemException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

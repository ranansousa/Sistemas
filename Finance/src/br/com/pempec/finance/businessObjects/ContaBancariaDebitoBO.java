package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
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
public class ContaBancariaDebitoBO {

    public void transfereBancoTesouraria(ContaBancariaDebitoModel debito, TesourariaCreditoModel credito) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaBancariaDebitoDAO().transfereBancoTesouraria(debito, credito);
    }

    public void estornoTransferenciaBancoTesouraria(ContaBancariaDebitoModel debito, TesourariaCreditoModel credito) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaBancariaDebitoDAO().estornoTransferenciaBancoTesouraria(debito, credito);
    }

    public void inserir(ContaBancariaDebitoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaBancariaDebitoDAO().inserir(model);
    }

    public void inserirMultiplos(Collection<ContaBancariaDebitoModel> lista) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaBancariaDebitoDAO().inserirMultiplos(lista);
    }

    public void alterar(ContaBancariaDebitoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaBancariaDebitoDAO().alterar(model);
    }

    public ContaBancariaDebitoModel consultarPorPk(ContaBancariaDebitoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDebitoDAO().consultarPorPk(
                model);
    }

    public void excluir(ContaBancariaDebitoModel model) throws SystemException, ApplicationException {


        if (model.getContaBancariaCheque() != null) {

            ContaBancariaChequeModel cheque = model.getContaBancariaCheque();

            DAOFactory.getInstance().getContaBancariaChequeDAO().alterar(cheque);
        }


        DAOFactory.getInstance().getContaBancariaDebitoDAO().excluir(model);
    }

    public ContaBancariaDebitoModel consultarPorTemplate(ContaBancariaDebitoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDebitoDAO().consultarPorTemplate(model);
    }

    public List<ContaBancariaDebitoModel> obterPorFiltro(ContaBancariaDebitoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDebitoDAO().obterPorFiltro(
                model);
    }

    public List<ContaBancariaDebitoModel> obterPorContaBancaria(ContaBancariaModel model)
            throws SystemException, ApplicationException {

        return DAOFactory.getInstance().getContaBancariaDebitoDAO().obterPorContaBancaria(
                model);
    }

    public List<ContaBancariaDebitoModel> obterPorContaBancariaStatus(ContaBancariaModel model, TipoStatusModel status)
            throws SystemException, ApplicationException {

        return DAOFactory.getInstance().getContaBancariaDebitoDAO().obterPorContaBancariaStatus(
                model, status);
    }

    public void excluirTodos(Collection<ContaBancariaDebitoModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getContaBancariaDebitoDAO().excluirTodos(coll);
    }

    public List<ContaBancariaDebitoModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDebitoDAO().obterTodos(model);
    }

    public List<ContaBancariaDebitoModel> obterTodosLancamentosBancariosPorTipo(OrganizacaoModel model, String tipo)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDebitoDAO().obterTodosLancamentosBancariosPorTipo(model, tipo);
    }

    public ContaBancariaDebitoModel consultarPorTemplateLancamentoBancario(ContaBancariaDebitoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDebitoDAO().consultarPorTemplateLancamentoBancario(model);
    }

    public List<ContaBancariaDebitoModel> obterTodosLancamentosTesourariaPorTipo(OrganizacaoModel model, String tipo)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDebitoDAO().obterTodosLancamentosTesourariaPorTipo(model, tipo);
    }

    public ContaBancariaDebitoModel obterPorCheque(ContaBancariaDebitoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDebitoDAO().obterPorCheque(model);
    }

    public List<ContaBancariaDebitoModel> obterContaBancariaDebitoExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDebitoDAO().obterContaBancariaDebitoExportacao(model, dataInicial, dataFinal);
    }

    public List<ContaBancariaDebitoModel> obterContaBancariaDebitoExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDebitoDAO().obterContaBancariaDebitoExportacaoRelatorio(model, dataInicial, dataFinal);
    }

    public List<ContaBancariaDebitoModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDebitoDAO().obterTitulosExportados(model);
    }

    public List<ContaBancariaDebitoModel> obterRelatorio(FilterReportContaBancariaDebito filtro)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDebitoDAO().obterRelatorio(filtro);
    }

    public List<ContaBancariaDebitoModel> obterTodosTitulosPagos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDebitoDAO().obterTodosTitulosPagos(model);
    }

    public Double obterSaldoAnterior(FilterReportContaBancariaDebito filter) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDebitoDAO().obterSaldoAnterior(filter);
    }

    public Double obterTotal(ContaBancariaModel contaBancaria) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDebitoDAO().obterTotal(contaBancaria);
    }

    public List<ContaBancariaDebitoModel> obterTodosPorData(OrganizacaoModel org, Date dataInicial) {
        try {
            return DAOFactory.getInstance().getContaBancariaDebitoDAO().obterTodosPorData(org, dataInicial);
        } catch (SystemException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<ContaBancariaDebitoModel> obterTodosPorOperacaoBancaria(OrganizacaoModel model, TipoOperacaoBancariaModel operacao)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDebitoDAO().obterTodosPorOperacaoBancaria(model, operacao);
    }
}

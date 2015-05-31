package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.ContaBancariaTransferenciaModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.reports.FilterReportContaBancariaTransferencia;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public class ContaBancariaTransferenciaBO {

    public void inserir(ContaBancariaTransferenciaModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaBancariaTransferenciaDAO().inserir(model);
    }

    public void alterar(ContaBancariaTransferenciaModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaBancariaTransferenciaDAO().alterar(model);
    }

    public ContaBancariaTransferenciaModel consultarPorPk(ContaBancariaTransferenciaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaTransferenciaDAO().consultarPorPk(
                model);
    }

    public void excluir(ContaBancariaTransferenciaModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getContaBancariaTransferenciaDAO().excluir(model);
    }

    public ContaBancariaTransferenciaModel consultarPorTemplate(ContaBancariaTransferenciaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaTransferenciaDAO().consultarPorTemplate(model);
    }

    public List<ContaBancariaTransferenciaModel> obterPorFiltro(ContaBancariaTransferenciaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaTransferenciaDAO().obterPorFiltro(
                model);
    }

    public List<ContaBancariaTransferenciaModel> obterPorContaBancaria(ContaBancariaModel model)
            throws SystemException, ApplicationException {

        return DAOFactory.getInstance().getContaBancariaTransferenciaDAO().obterPorContaBancaria(
                model);
    }

    public void excluirTodos(Collection<ContaBancariaTransferenciaModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getContaBancariaTransferenciaDAO().excluirTodos(coll);
    }

    public List<ContaBancariaTransferenciaModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaTransferenciaDAO().obterTodos(model);
    }

    public List<ContaBancariaTransferenciaModel> obterContaBancariaTransferenciaExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaTransferenciaDAO().obterContaBancariaTransferenciaExportacao(model, dataInicial, dataFinal);
    }

    public List<ContaBancariaTransferenciaModel> obterContaBancariaTransferenciaExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaTransferenciaDAO().obterContaBancariaTransferenciaExportacaoRelatorio(model, dataInicial, dataFinal);
    }

    public List<ContaBancariaTransferenciaModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaTransferenciaDAO().obterTitulosExportados(model);
    }

    public List<ContaBancariaTransferenciaModel> obterRelatorio(FilterReportContaBancariaTransferencia filtro)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaTransferenciaDAO().obterRelatorio(filtro);
    }

    public List<ContaBancariaTransferenciaModel> obterTodosPorData(OrganizacaoModel org, Date dataInicial) {
        try {
            return DAOFactory.getInstance().getContaBancariaTransferenciaDAO().obterTodosPorData(org, dataInicial);
        } catch (SystemException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

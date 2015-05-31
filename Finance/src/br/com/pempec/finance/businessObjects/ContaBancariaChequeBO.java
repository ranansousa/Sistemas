package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.reports.FilterReportContaBancariaCheque;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class ContaBancariaChequeBO {

    public void gerarCheques(Collection<ContaBancariaChequeModel> coll) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaBancariaChequeDAO().gerarCheques(coll);
    }

    public void inserir(ContaBancariaChequeModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaBancariaChequeDAO().inserir(model);
    }

    public void alterar(ContaBancariaChequeModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaBancariaChequeDAO().alterar(model);
    }

    public void alterar(Collection<ContaBancariaChequeModel> coll) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaBancariaChequeDAO().alterar(coll);
    }

    public void alterarChequeAvulso(ContaBancariaChequeModel model, TituloPagarModel titulo) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaBancariaChequeDAO().alterarChequeAvulso(model, titulo);
    }

    public void cancelarChequeAvulso(ContaBancariaChequeModel model, TituloPagarModel titulo) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaBancariaChequeDAO().cancelarChequeAvulso(model, titulo);
    }

    public ContaBancariaChequeModel consultarPorPk(ContaBancariaChequeModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaChequeDAO().consultarPorPk(
                model);
    }

    public List<ContaBancariaChequeModel> obterPorContaBancariaTodosNumeroCheque(ContaBancariaModel model, String numeroChequeInicial, String numeroChequeFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaChequeDAO().obterPorContaBancariaTodosNumeroCheque(model, numeroChequeInicial, numeroChequeFinal);
    }

    public List<ContaBancariaChequeModel> obterPorContaBancariaNumeroCheque(ContaBancariaModel model, String numeroChequeInicial, String numeroChequeFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaChequeDAO().obterPorContaBancariaNumeroCheque(model, numeroChequeInicial, numeroChequeFinal);
    }

    public void excluir(ContaBancariaChequeModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getContaBancariaChequeDAO().excluir(model);
    }

    public ContaBancariaChequeModel consultarPorTemplate(ContaBancariaChequeModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaChequeDAO().consultarPorTemplate(model);
    }

    public List<ContaBancariaChequeModel> obterPorFiltro(ContaBancariaChequeModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaChequeDAO().obterPorFiltro(
                model);
    }

    public List<ContaBancariaChequeModel> obterPorContaBancaria(ContaBancariaModel model)
            throws SystemException, ApplicationException {

        return DAOFactory.getInstance().getContaBancariaChequeDAO().obterPorContaBancaria(
                model);
    }

    public List<ContaBancariaChequeModel> obterPorContaBancariaStatus(ContaBancariaModel model, TipoStatusModel status)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaChequeDAO().obterPorContaBancariaStatus(model, status);
    }

    public List<ContaBancariaChequeModel> obterPorContaBancariaStatus(ContaBancariaModel model, Collection<TipoStatusModel> collStatus)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaChequeDAO().obterPorContaBancariaStatus(model, collStatus);
    }

    public List<ContaBancariaChequeModel> obterPorContaBancariaStatusEmitidoECompensado(ContaBancariaModel model)
            throws SystemException, ApplicationException {

        return DAOFactory.getInstance().getContaBancariaChequeDAO().obterPorContaBancariaStatusEmitidoECompensado(model);

    }

    public void excluirTodos(Collection<ContaBancariaChequeModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getContaBancariaChequeDAO().excluirTodos(coll);
    }

    public List<ContaBancariaChequeModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaChequeDAO().obterTodos(model);
    }

    public List<ContaBancariaChequeModel> obterRelatorio(FilterReportContaBancariaCheque filtro)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaChequeDAO().obterRelatorio(filtro);
    }

    public Double obterTotalChequesACompensar(ContaBancariaModel contaBancaria) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaChequeDAO().obterTotalChequesACompensar(contaBancaria);
    }

    public void zeraQtdImpressaoCheque()
            throws SystemException {
        DAOFactory.getInstance().getContaBancariaChequeDAO().zeraQtdImpressaoCheque();
    }
}

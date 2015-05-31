package br.com.pempec.finance.daosIf;

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
public interface ContaBancariaChequeDAOIf {

    public void gerarCheques(Collection<ContaBancariaChequeModel> coll) throws SystemException;

    public void inserir(ContaBancariaChequeModel model) throws SystemException;

    public void alterar(ContaBancariaChequeModel model) throws SystemException;

    public void alterar(Collection<ContaBancariaChequeModel> coll) throws SystemException;

    public void alterarChequeAvulso(ContaBancariaChequeModel model, TituloPagarModel titulo) throws SystemException;

    public void cancelarChequeAvulso(ContaBancariaChequeModel model, TituloPagarModel titulo) throws SystemException;

    public ContaBancariaChequeModel consultarPorPk(ContaBancariaChequeModel model)
            throws SystemException;

    public List<ContaBancariaChequeModel> obterPorFiltro(ContaBancariaChequeModel model)
            throws SystemException;

    public List<ContaBancariaChequeModel> obterPorContaBancaria(ContaBancariaModel model)
            throws SystemException;

    public List<ContaBancariaChequeModel> obterPorContaBancariaTodosNumeroCheque(ContaBancariaModel model, String numeroChequeInicial, String numeroChequeFinal)
            throws SystemException;

    public List<ContaBancariaChequeModel> obterPorContaBancariaNumeroCheque(ContaBancariaModel model, String numeroChequeInicial, String numeroChequeFinal)
            throws SystemException;

    public List<ContaBancariaChequeModel> obterPorContaBancariaStatus(ContaBancariaModel model, TipoStatusModel status)
            throws SystemException;

    public List<ContaBancariaChequeModel> obterPorContaBancariaStatus(ContaBancariaModel model, Collection<TipoStatusModel> collStatus)
            throws SystemException;

    public List<ContaBancariaChequeModel> obterPorContaBancariaStatusEmitidoECompensado(ContaBancariaModel model)
            throws SystemException;

    public List<ContaBancariaChequeModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(ContaBancariaChequeModel model) throws SystemException;

    public ContaBancariaChequeModel consultarPorTemplate(ContaBancariaChequeModel model)
            throws SystemException;

    public void excluirTodos(Collection<ContaBancariaChequeModel> coll)
            throws SystemException;

    public List<ContaBancariaChequeModel> obterRelatorio(FilterReportContaBancariaCheque filtro)
            throws SystemException;

    public Double obterTotalChequesACompensar(ContaBancariaModel contaBancaria) throws SystemException;

    public void zeraQtdImpressaoCheque() throws SystemException;
}

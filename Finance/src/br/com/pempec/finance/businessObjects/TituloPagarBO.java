package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ImpressaoMultiplosRecibosFilter;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.LotePagamentoTituloModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.ParticionadorTituloPagarModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloPagarRateioCCModel;
import br.com.pempec.finance.models.TituloPagarRateioHistoricoModel;
import br.com.pempec.finance.models.reports.FilterReportFluxoCaixa;
import br.com.pempec.finance.models.reports.FilterReportTituloPagar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author PEMPEC
 */
public class TituloPagarBO {

    public void inserir(Collection<TituloPagarModel> titulos) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloPagarDAO().inserir(titulos);
    }

    public void inserir(TituloPagarModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloPagarDAO().inserir(model);
    }

    public void particionar(ParticionadorTituloPagarModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloPagarDAO().particionar(model);
    }

    public void alterar(TituloPagarModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloPagarDAO().alterar(model);
    }

    public TituloPagarModel consultarPorPk(TituloPagarModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarDAO().consultarPorPk(
                model);
    }

    public void excluir(TituloPagarModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarDAO().excluir(model);
    }

    public TituloPagarModel consultarPorTemplate(TituloPagarModel model)
            throws SystemException, ApplicationException {

        TituloPagarModel retorno = DAOFactory.getInstance().getTituloPagarDAO().consultarPorTemplate(model);
        //Pegando os Rateios.
        if (retorno != null) {
            retorno.setCollCentroCustosRateio(new HashSet<TituloPagarRateioCCModel>());
            retorno.getCollCentroCustosRateio().addAll(DAOFactory.getInstance().getTituloPagarDAO().obterRateioPorTituloPagar(retorno));
            retorno.setCollHistoricosRateio(new HashSet<TituloPagarRateioHistoricoModel>());
            retorno.getCollHistoricosRateio().addAll(DAOFactory.getInstance().getTituloPagarDAO().obterHistoricosPorTituloPagar(retorno));
        }

        return retorno;
    }

    public TituloPagarModel consultarPorTemplatePago(TituloPagarModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarDAO().consultarPorTemplatePago(model);
    }

    public TituloPagarModel consultarPorTemplateAPagar(TituloPagarModel model)
            throws SystemException, ApplicationException {

        TituloPagarModel retorno = DAOFactory.getInstance().getTituloPagarDAO().consultarPorTemplateAPagar(model);

        if (retorno != null) {
            retorno.setCollCentroCustosRateio(new HashSet<TituloPagarRateioCCModel>());
            retorno.getCollCentroCustosRateio().addAll(DAOFactory.getInstance().getTituloPagarDAO().obterRateioPorTituloPagar(retorno));
            retorno.setCollHistoricosRateio(new HashSet<TituloPagarRateioHistoricoModel>());
            retorno.getCollHistoricosRateio().addAll(DAOFactory.getInstance().getTituloPagarDAO().obterHistoricosPorTituloPagar(retorno));
        }

        return retorno;

    }

    public List<TituloPagarModel> obterPorFiltro(TituloPagarModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarDAO().obterPorFiltro(
                model);
    }

    public List<TituloPagarModel> obterImpressaoMultiplosRecibos(ImpressaoMultiplosRecibosFilter filter)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarDAO().obterImpressaoMultiplosRecibos(filter);
    }

    public List<TituloPagarModel> obterTodosRecibo(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarDAO().obterTodosRecibo(model);
    }

    public void excluirTodos(Collection<TituloPagarModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarDAO().excluirTodos(coll);
    }

    public List<TituloPagarModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarDAO().obterTodos(model);
    }

    public List<TituloPagarModel> obterTitulosExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {

        List<TituloPagarModel> retorno = DAOFactory.getInstance().getTituloPagarDAO().obterTitulosExportacao(model, dataInicial, dataFinal);

        for (TituloPagarModel tituloPagarModel : retorno) {
            //Pegando os Rateios.
            if (tituloPagarModel != null) {
                tituloPagarModel.setCollCentroCustosRateio(new HashSet<TituloPagarRateioCCModel>());
                tituloPagarModel.getCollCentroCustosRateio().addAll(DAOFactory.getInstance().getTituloPagarDAO().obterRateioPorTituloPagar(tituloPagarModel));
                tituloPagarModel.setCollHistoricosRateio(new HashSet<TituloPagarRateioHistoricoModel>());
                tituloPagarModel.getCollHistoricosRateio().addAll(DAOFactory.getInstance().getTituloPagarDAO().obterHistoricosPorTituloPagar(tituloPagarModel));
            }

        }

        return retorno;
    }

    public List<TituloPagarModel> obterTitulosExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {

        List<TituloPagarModel> retorno = DAOFactory.getInstance().getTituloPagarDAO().obterTitulosExportacaoRelatorio(model, dataInicial, dataFinal);

        for (TituloPagarModel tituloPagarModel : retorno) {
            //Pegando os Rateios.
            if (tituloPagarModel != null) {
                tituloPagarModel.setCollCentroCustosRateio(new HashSet<TituloPagarRateioCCModel>());
                tituloPagarModel.getCollCentroCustosRateio().addAll(DAOFactory.getInstance().getTituloPagarDAO().obterRateioPorTituloPagar(tituloPagarModel));
                tituloPagarModel.setCollHistoricosRateio(new HashSet<TituloPagarRateioHistoricoModel>());
                tituloPagarModel.getCollHistoricosRateio().addAll(DAOFactory.getInstance().getTituloPagarDAO().obterHistoricosPorTituloPagar(tituloPagarModel));
            }

        }

        return retorno;

    }

    public List<TituloPagarModel> obterRelatorio(FilterReportTituloPagar model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarDAO().obterRelatorio(model);
    }

    public List<TituloPagarModel> obterRelatorioFluxo(FilterReportFluxoCaixa model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarDAO().obterRelatorioFluxo(model);
    }

    public List<TituloPagarModel> obterTodosAPagar(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarDAO().obterTodosAPagar(model);
    }

    public List<TituloPagarModel> obterTodosPagos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarDAO().obterTodosPagos(model);
    }

    public List<TituloPagarModel> obterTitulosAntecipados(TituloPagarModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarDAO().obterTitulosAntecipados(model);
    }

    public List<TituloPagarModel> obterTitulosFilhos(TituloPagarModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarDAO().obterTitulosFilhos(model);
    }

    public List<TituloPagarModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException, ApplicationException {


        List<TituloPagarModel> retorno = DAOFactory.getInstance().getTituloPagarDAO().obterTitulosExportados(model);

        for (TituloPagarModel tituloPagarModel : retorno) {
            //Pegando os Rateios.
            if (tituloPagarModel != null) {
                tituloPagarModel.setCollCentroCustosRateio(new HashSet<TituloPagarRateioCCModel>());
                tituloPagarModel.getCollCentroCustosRateio().addAll(DAOFactory.getInstance().getTituloPagarDAO().obterRateioPorTituloPagar(tituloPagarModel));
                tituloPagarModel.setCollHistoricosRateio(new HashSet<TituloPagarRateioHistoricoModel>());
                tituloPagarModel.getCollHistoricosRateio().addAll(DAOFactory.getInstance().getTituloPagarDAO().obterHistoricosPorTituloPagar(tituloPagarModel));
            }

        }

        return retorno;

    }

    public List<TituloPagarModel> obterTitulosLotePagamento(LotePagamentoTituloModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarDAO().obterTitulosLotePagamento(model);
    }

    public List<TituloPagarModel> obterTodosPorPeriodo(TituloPagarModel model, Date dataInicial, Date dataFinal, String[] ordem)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarDAO().obterTodosPorPeriodo(model, dataInicial, dataFinal, ordem);
    }

    public List<TituloPagarModel> obterNotaFiscaisPorPeriodo(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarDAO().obterNotaFiscaisPorPeriodo(model, dataInicial, dataFinal);
    }

    public List<TituloPagarModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial) {
        try {
            return DAOFactory.getInstance().getTituloPagarDAO().obterTodosPorData(model, dataInicial);
        } catch (SystemException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public void corrigeRegistroProvisao() throws SystemException {
        DAOFactory.getInstance().getTituloPagarDAO().corrigeRegistroProvisao();
    }
}//fim

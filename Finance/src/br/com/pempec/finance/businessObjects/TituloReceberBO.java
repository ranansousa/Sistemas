package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ImpressaoMultiplosRecibosFilter;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.ParticionadorTituloReceberModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.models.TituloReceberRateioCCModel;
import br.com.pempec.finance.models.TituloReceberRateioHistoricoModel;
import br.com.pempec.finance.models.reports.FilterReportFluxoCaixa;
import br.com.pempec.finance.models.reports.FilterReportTituloReceber;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author PEMPEC
 */
public class TituloReceberBO {

    public void inserir(Collection<TituloReceberModel> titulos) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloReceberDAO().inserir(titulos);
    }

    public void inserir(TituloReceberModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloReceberDAO().inserir(model);
    }

    public void alterar(TituloReceberModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloReceberDAO().alterar(model);
    }

    public TituloReceberModel consultarPorPk(TituloReceberModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberDAO().consultarPorPk(
                model);
    }

    public void excluir(TituloReceberModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberDAO().excluir(model);
    }

    public TituloReceberModel consultarPorTemplate(TituloReceberModel model)
            throws SystemException, ApplicationException {

        TituloReceberModel retorno = DAOFactory.getInstance().getTituloReceberDAO().consultarPorTemplate(model);

        if (retorno != null) {
            retorno.setCollHistoricosRateio(new HashSet<TituloReceberRateioHistoricoModel>());
            retorno.getCollHistoricosRateio().addAll(DAOFactory.getInstance().getTituloReceberDAO().obterHistoricosPorTituloReceber(retorno));
            retorno.setCollCentroCustosRateio(new HashSet<TituloReceberRateioCCModel>());
            retorno.getCollCentroCustosRateio().addAll(DAOFactory.getInstance().getTituloReceberDAO().obterRateioCustosPorTituloReceber(retorno));
        }

        return retorno;
    }

    public TituloReceberModel consultarPorTemplatePago(TituloReceberModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberDAO().consultarPorTemplatePago(model);
    }

    public TituloReceberModel consultarPorTemplateAReceber(TituloReceberModel model)
            throws SystemException, ApplicationException {

        TituloReceberModel retorno = DAOFactory.getInstance().getTituloReceberDAO().consultarPorTemplateAReceber(model);

        if (retorno != null) {
            retorno.setCollHistoricosRateio(new HashSet<TituloReceberRateioHistoricoModel>());
            retorno.getCollHistoricosRateio().addAll(DAOFactory.getInstance().getTituloReceberDAO().obterHistoricosPorTituloReceber(retorno));
            retorno.setCollCentroCustosRateio(new HashSet<TituloReceberRateioCCModel>());
            retorno.getCollCentroCustosRateio().addAll(DAOFactory.getInstance().getTituloReceberDAO().obterRateioCustosPorTituloReceber(retorno));
        }

        return retorno;
    }

    public List<TituloReceberModel> obterPorFiltro(TituloReceberModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberDAO().obterPorFiltro(
                model);
    }

    public List<TituloReceberModel> obterTitulosExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {

        List<TituloReceberModel> retorno = DAOFactory.getInstance().getTituloReceberDAO().obterTitulosExportacao(model, dataInicial, dataFinal);

        for (TituloReceberModel tituloReceberModel : retorno) {

            if (tituloReceberModel != null) {
                tituloReceberModel.setCollHistoricosRateio(new HashSet<TituloReceberRateioHistoricoModel>());
                tituloReceberModel.getCollHistoricosRateio().addAll(DAOFactory.getInstance().getTituloReceberDAO().obterHistoricosPorTituloReceber(tituloReceberModel));
                tituloReceberModel.setCollCentroCustosRateio(new HashSet<TituloReceberRateioCCModel>());
                tituloReceberModel.getCollCentroCustosRateio().addAll(DAOFactory.getInstance().getTituloReceberDAO().obterRateioCustosPorTituloReceber(tituloReceberModel));
            }

        }

        return retorno;

    }

    public List<TituloReceberModel> obterTitulosExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {

        List<TituloReceberModel> retorno = DAOFactory.getInstance().getTituloReceberDAO().obterTitulosExportacaoRelatorio(model, dataInicial, dataFinal);

        for (TituloReceberModel tituloReceberModel : retorno) {

            if (tituloReceberModel != null) {
                tituloReceberModel.setCollHistoricosRateio(new HashSet<TituloReceberRateioHistoricoModel>());
                tituloReceberModel.getCollHistoricosRateio().addAll(DAOFactory.getInstance().getTituloReceberDAO().obterHistoricosPorTituloReceber(tituloReceberModel));
                tituloReceberModel.setCollCentroCustosRateio(new HashSet<TituloReceberRateioCCModel>());
                tituloReceberModel.getCollCentroCustosRateio().addAll(DAOFactory.getInstance().getTituloReceberDAO().obterRateioCustosPorTituloReceber(tituloReceberModel));
            }

        }

        return retorno;

    }

    public void excluirTodos(Collection<TituloReceberModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberDAO().excluirTodos(coll);
    }

    public List<TituloReceberModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberDAO().obterTodos(model);
    }

    public List<TituloReceberModel> obterImpressaoMultiplosRecibos(ImpressaoMultiplosRecibosFilter filter)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberDAO().obterImpressaoMultiplosRecibos(filter);
    }

    public List<TituloReceberModel> obterTodosRecibo(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberDAO().obterTodosRecibo(model);
    }

    public List<TituloReceberModel> obterTodosAReceber(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberDAO().obterTodosAReceber(model);
    }

    public List<TituloReceberModel> obterTodosPagos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberDAO().obterTodosPagos(model);
    }

    public List<TituloReceberModel> obterFilhos(TituloReceberModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberDAO().obterFilhos(model);
    }

    public List<TituloReceberModel> obterTitulosAntecipados(TituloReceberModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberDAO().obterTitulosAntecipados(model);
    }

    public List<TituloReceberModel> obterTitulosFilhos(TituloReceberModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberDAO().obterTitulosFilhos(model);
    }

    public void particionar(ParticionadorTituloReceberModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloReceberDAO().particionar(model);
    }

    public List<TituloReceberModel> obterRelatorio(FilterReportTituloReceber model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberDAO().obterRelatorio(model);
    }

    public List<TituloReceberModel> obterRelatorioFluxo(FilterReportFluxoCaixa model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberDAO().obterRelatorioFluxo(model);
    }

    public List<TituloReceberModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException, ApplicationException {

        List<TituloReceberModel> retorno = DAOFactory.getInstance().getTituloReceberDAO().obterTitulosExportados(model);

        for (TituloReceberModel tituloReceberModel : retorno) {

            if (tituloReceberModel != null) {
                tituloReceberModel.setCollHistoricosRateio(new HashSet<TituloReceberRateioHistoricoModel>());
                tituloReceberModel.getCollHistoricosRateio().addAll(DAOFactory.getInstance().getTituloReceberDAO().obterHistoricosPorTituloReceber(tituloReceberModel));
                tituloReceberModel.setCollCentroCustosRateio(new HashSet<TituloReceberRateioCCModel>());
                tituloReceberModel.getCollCentroCustosRateio().addAll(DAOFactory.getInstance().getTituloReceberDAO().obterRateioCustosPorTituloReceber(tituloReceberModel));
            }

        }

        return retorno;

    }

    public List<TituloReceberModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial) {
        try {
            return DAOFactory.getInstance().getTituloReceberDAO().obterTodosPorData(model, dataInicial);
        } catch (SystemException ex) {
            ex.printStackTrace();
            return null;
        }

    }
    
    
    
    public List<TituloReceberModel> obterTodosPorPeriodo(TituloReceberModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberDAO().obterTodosPorPeriodo(model, dataInicial, dataFinal);
    }

   

    public void corrigeRegistroProvisao() throws SystemException {
        DAOFactory.getInstance().getTituloReceberDAO().corrigeRegistroProvisao();
    }
}

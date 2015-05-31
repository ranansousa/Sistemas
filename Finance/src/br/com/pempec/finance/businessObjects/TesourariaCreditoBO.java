package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TesourariaCreditoModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;
import br.com.pempec.finance.models.reports.FilterReportExtratoTesouraria;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public class TesourariaCreditoBO {

    public void inserir(TesourariaCreditoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTesourariaCreditoDAO().inserir(model);
    }

    public void alterar(TesourariaCreditoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTesourariaCreditoDAO().alterar(model);
    }

    public TesourariaCreditoModel consultarPorPk(TesourariaCreditoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaCreditoDAO().consultarPorPk(
                model);
    }

    public void excluir(TesourariaCreditoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTesourariaCreditoDAO().excluir(model);
    }

    public TesourariaCreditoModel consultarPorTemplate(TesourariaCreditoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaCreditoDAO().consultarPorTemplate(model);
    }

    public List<TesourariaCreditoModel> obterPorFiltro(TesourariaCreditoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaCreditoDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<TesourariaCreditoModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTesourariaCreditoDAO().excluirTodos(coll);
    }

    public List<TesourariaCreditoModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaCreditoDAO().obterTodos(model);
    }

    public List<TesourariaCreditoModel> obterTodosPorTituloRecebido(TituloReceberBaixaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaCreditoDAO().obterTodosPorTituloRecebido(model);
    }

    public List<TesourariaCreditoModel> obterTesourariaCreditoExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaCreditoDAO().obterTesourariaCreditoExportacao(
                model, dataInicial, dataFinal);
    }

    public List<TesourariaCreditoModel> obterTesourariaCreditoExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaCreditoDAO().obterTesourariaCreditoExportacaoRelatorio(
                model, dataInicial, dataFinal);
    }

    public List<TesourariaCreditoModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaCreditoDAO().obterTitulosExportados(model);
    }

    public List<TesourariaCreditoModel> obterRelatorioExtratoTesouria(FilterReportExtratoTesouraria filter)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaCreditoDAO().obterRelatorioExtratoTesouria(filter);
    }

    public Double obterSaldoAnterior(FilterReportExtratoTesouraria filter) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaCreditoDAO().obterSaldoAnterior(filter);
    }

    public Double obterTotal(OrganizacaoModel organizacaoModel) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaCreditoDAO().obterTotal(organizacaoModel);
    }

    public List<TesourariaCreditoModel> obterTodosPorData(OrganizacaoModel org, Date dataInicial) {
        try {
            return DAOFactory.getInstance().getTesourariaCreditoDAO().obterTodosPorData(org, dataInicial);
        } catch (SystemException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

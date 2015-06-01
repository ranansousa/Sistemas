package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.LoteDepositoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TesourariaDebitoModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.models.TituloReceberBaixaChequeModel;
import br.com.pempec.finance.models.reports.FilterReportExtratoTesouraria;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public class TesourariaDebitoBO {

    public void inserir(TesourariaDebitoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTesourariaDebitoDAO().inserir(model);
    }

    public void alterar(TesourariaDebitoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTesourariaDebitoDAO().alterar(model);
    }

    public TesourariaDebitoModel consultarPorPk(TesourariaDebitoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaDebitoDAO().consultarPorPk(
                model);
    }

    public void excluir(TesourariaDebitoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTesourariaDebitoDAO().excluir(model);
    }

    public TesourariaDebitoModel consultarPorTemplate(TesourariaDebitoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaDebitoDAO().consultarPorTemplate(model);
    }

    public List<TesourariaDebitoModel> obterPorFiltro(TesourariaDebitoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaDebitoDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<TesourariaDebitoModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTesourariaDebitoDAO().excluirTodos(coll);
    }

    public List<TesourariaDebitoModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaDebitoDAO().obterTodos(model);
    }

    public List<TesourariaDebitoModel> obterTodosPorTituloPago(TituloPagarBaixaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaDebitoDAO().obterTodosPorTituloPago(model);
    }

    public List<TesourariaDebitoModel> obterTesourariaDebitoExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaDebitoDAO().obterTesourariaDebitoExportacao(
                model, dataInicial, dataFinal);
    }

    public List<TesourariaDebitoModel> obterTesourariaDebitoExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaDebitoDAO().obterTesourariaDebitoExportacaoRelatorio(
                model, dataInicial, dataFinal);
    }

    public List<TesourariaDebitoModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaDebitoDAO().obterTitulosExportados(model);
    }

    public void depositarBanco(TesourariaDebitoModel tesourariaModel, ContaBancariaCreditoModel ctbCrModel)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTesourariaDebitoDAO().depositarBanco(tesourariaModel, ctbCrModel);
    }
    
   public List<TesourariaDebitoModel> obterDepositoBanco(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaDebitoDAO().obterDepositoBanco(model, dataInicial, dataFinal);
    }

    
    public void excluirDepositoBanco(TesourariaDebitoModel tesourariaModel, ContaBancariaCreditoModel ctbCrModel)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTesourariaDebitoDAO().excluirDepositoBanco(tesourariaModel, ctbCrModel);
    }
    
            
            
    public void depositarChequesBanco(Collection<TituloReceberBaixaChequeModel> collCheques, ContaBancariaCreditoModel ctbCrModel, LoteDepositoModel lote)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTesourariaDebitoDAO().depositarChequesBanco(collCheques, ctbCrModel, lote);
    }

    public List<TesourariaDebitoModel> obterTodosTitulosPagos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaDebitoDAO().obterTodosTitulosPagos(model);
    }

    public List<TesourariaDebitoModel> obterRelatorioExtratoTesouria(FilterReportExtratoTesouraria filter)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaDebitoDAO().obterRelatorioExtratoTesouria(filter);
    }

    public Double obterSaldoAnterior(FilterReportExtratoTesouraria filter) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaDebitoDAO().obterSaldoAnterior(filter);
    }

    public Double obterTotal(OrganizacaoModel organizacaoModel) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTesourariaDebitoDAO().obterTotal(organizacaoModel);
    }

    public List<TesourariaDebitoModel> obterTodosPorData(OrganizacaoModel org, Date dataInicial) {
        try {
            return DAOFactory.getInstance().getTesourariaDebitoDAO().obterTodosPorData(org, dataInicial);
        } catch (SystemException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

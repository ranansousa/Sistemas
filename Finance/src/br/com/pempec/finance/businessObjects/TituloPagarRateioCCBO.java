package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CentroCustoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloPagarRateioCCModel;
import br.com.pempec.finance.models.reports.FilterReportTituloPagar;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class TituloPagarRateioCCBO {

    public void inserir(Collection<TituloPagarRateioCCModel> titulos) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloPagarRateioCCDAO().inserir(titulos);
    }

    public void inserir(TituloPagarRateioCCModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloPagarRateioCCDAO().inserir(model);
    }

    public void alterar(TituloPagarRateioCCModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloPagarRateioCCDAO().alterar(model);
    }

    public TituloPagarRateioCCModel consultarPorPk(TituloPagarRateioCCModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarRateioCCDAO().consultarPorPk(
                model);
    }

    public void excluir(TituloPagarRateioCCModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarRateioCCDAO().excluir(model);
    }

    public TituloPagarRateioCCModel consultarPorTemplate(TituloPagarRateioCCModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarRateioCCDAO().consultarPorTemplate(
                model);
    }

    public List<TituloPagarRateioCCModel> obterPorFiltro(TituloPagarRateioCCModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarRateioCCDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<TituloPagarRateioCCModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarRateioCCDAO().excluirTodos(coll);
    }

    public List<TituloPagarRateioCCModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarRateioCCDAO().obterTodos(model);
    }

    public List<TituloPagarRateioCCModel> obterRelatorio(FilterReportTituloPagar model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarRateioCCDAO().obterRelatorio(model);
    }

    public List<TituloPagarRateioCCModel> obterTodosPorCentroCusto(CentroCustoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarRateioCCDAO().obterTodosPorCentroCusto(model);
    }
}//fim

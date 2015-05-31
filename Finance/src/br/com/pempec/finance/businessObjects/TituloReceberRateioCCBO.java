package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CentroCustoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloReceberRateioCCModel;
import br.com.pempec.finance.models.reports.FilterReportTituloReceber;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class TituloReceberRateioCCBO {

    public void inserir(Collection<TituloReceberRateioCCModel> titulos) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloReceberRateioCCDAO().inserir(titulos);
    }

    public void inserir(TituloReceberRateioCCModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloReceberRateioCCDAO().inserir(model);
    }

    public void alterar(TituloReceberRateioCCModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTituloReceberRateioCCDAO().alterar(model);
    }

    public TituloReceberRateioCCModel consultarPorPk(TituloReceberRateioCCModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberRateioCCDAO().consultarPorPk(
                model);
    }

    public void excluir(TituloReceberRateioCCModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberRateioCCDAO().excluir(model);
    }

    public TituloReceberRateioCCModel consultarPorTemplate(TituloReceberRateioCCModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberRateioCCDAO().consultarPorTemplate(
                model);
    }

    public List<TituloReceberRateioCCModel> obterPorFiltro(TituloReceberRateioCCModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberRateioCCDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<TituloReceberRateioCCModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberRateioCCDAO().excluirTodos(coll);
    }

    public List<TituloReceberRateioCCModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberRateioCCDAO().obterTodos(model);
    }

    public List<TituloReceberRateioCCModel> obterRelatorio(FilterReportTituloReceber model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberRateioCCDAO().obterRelatorio(model);
    }

    public List<TituloReceberRateioCCModel> obterTodosPorCentroCusto(CentroCustoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberRateioCCDAO().obterTodosPorCentroCusto(model);
    }
}//fim

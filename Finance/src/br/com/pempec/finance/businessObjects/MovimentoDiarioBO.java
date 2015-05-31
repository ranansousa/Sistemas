package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.reports.FilterReportMovimentoDiario;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public class MovimentoDiarioBO {

    public void inserir(Collection<MovimentoDiarioModel> titulos) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getMovimentoDiarioDAO().inserir(titulos);
    }

    public void inserir(MovimentoDiarioModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getMovimentoDiarioDAO().inserir(model);
    }

    public void alterar(MovimentoDiarioModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getMovimentoDiarioDAO().alterar(model);
    }

    public MovimentoDiarioModel consultarPorPk(MovimentoDiarioModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getMovimentoDiarioDAO().consultarPorPk(
                model);
    }

    public void excluir(MovimentoDiarioModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getMovimentoDiarioDAO().excluir(model);
    }

    public MovimentoDiarioModel consultarPorTemplate(MovimentoDiarioModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getMovimentoDiarioDAO().consultarPorTemplate(model);
    }

    public List<MovimentoDiarioModel> obterPorFiltro(MovimentoDiarioModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getMovimentoDiarioDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<MovimentoDiarioModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getMovimentoDiarioDAO().excluirTodos(coll);
    }

    public List<MovimentoDiarioModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getMovimentoDiarioDAO().obterTodos(model);
    }

    public List<MovimentoDiarioModel> obterRelatorio(FilterReportMovimentoDiario model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getMovimentoDiarioDAO().obterRelatorio(model);
    }

    public List<MovimentoDiarioModel> obterTodosPorPeriodo(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getMovimentoDiarioDAO().obterTodosPorPeriodo(model, dataInicial, dataFinal);
    }

    public List<MovimentoDiarioModel> obterTodosPorData(OrganizacaoModel org, Date dataInicial) {
        try {
            return DAOFactory.getInstance().getMovimentoDiarioDAO().obterTodosPorData(org, dataInicial);
        } catch (SystemException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}//fim

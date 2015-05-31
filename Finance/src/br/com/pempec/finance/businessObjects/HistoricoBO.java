package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class HistoricoBO {

    public void inserir(HistoricoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getHistoricoDAO().inserir(model);
    }

    public void alterar(HistoricoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getHistoricoDAO().alterar(model);
    }

    public HistoricoModel consultarPorPk(HistoricoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getHistoricoDAO().consultarPorPk(model);
    }

    public List<HistoricoModel> obterPorFiltro(HistoricoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getHistoricoDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<HistoricoModel> coll) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getHistoricoDAO().excluirTodos(coll);
    }

    public List<HistoricoModel> obterTodos(OrganizacaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getHistoricoDAO().obterTodos(model);
    }

    public List<HistoricoModel> obterTodosPorTipo(OrganizacaoModel model, String tipo) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getHistoricoDAO().obterTodosPorTipo(model, tipo);
    }

    public void excluir(HistoricoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getHistoricoDAO().excluir(model);
    }

    public HistoricoModel consultarPorTemplate(HistoricoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getHistoricoDAO().consultarPorTemplate(model);
    }
}

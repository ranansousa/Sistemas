package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.FormaPagamentoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class FormaPagamentoBO {

    public void inserir(FormaPagamentoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getFormaPagamentoDAO().inserir(model);
    }

    public void alterar(FormaPagamentoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getFormaPagamentoDAO().alterar(model);
    }

    public FormaPagamentoModel consultarPorPk(FormaPagamentoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getFormaPagamentoDAO().consultarPorPk(model);
    }

    public List<FormaPagamentoModel> obterPorFiltro(FormaPagamentoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getFormaPagamentoDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<FormaPagamentoModel> coll) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getFormaPagamentoDAO().excluirTodos(coll);
    }

    public List<FormaPagamentoModel> obterTodos(OrganizacaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getFormaPagamentoDAO().obterTodos(model);
    }

    public void excluir(FormaPagamentoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getFormaPagamentoDAO().excluir(model);
    }

    public FormaPagamentoModel consultarPorTemplate(FormaPagamentoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getFormaPagamentoDAO().consultarPorTemplate(model);
    }
}

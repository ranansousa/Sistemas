package br.com.pempec.finance.businessObjects;

import java.util.Collection;
import java.util.List;

import br.com.pempec.finance.daos.DAOFactory;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.FormaPagamentoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel;

/**
 * @author PEMPEC
 */
public class TituloPagarBaixaFormaPagamentoBO {

    public void inserir(TituloPagarBaixaFormaPagamentoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaFormaPagamentoDAO().inserir(model);
    }

    public void alterar(TituloPagarBaixaFormaPagamentoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaFormaPagamentoDAO().alterar(model);
    }

    public TituloPagarBaixaFormaPagamentoModel consultarPorPk(TituloPagarBaixaFormaPagamentoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaFormaPagamentoDAO().consultarPorPk(model);
    }

    public List<TituloPagarBaixaFormaPagamentoModel> obterPorFiltro(TituloPagarBaixaFormaPagamentoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaFormaPagamentoDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<TituloPagarBaixaFormaPagamentoModel> coll) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaFormaPagamentoDAO().excluirTodos(coll);
    }

    public List<TituloPagarBaixaFormaPagamentoModel> obterTodos(OrganizacaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaFormaPagamentoDAO().obterTodos(model);
    }

    public TituloPagarBaixaFormaPagamentoModel consultarPorTemplate(TituloPagarBaixaFormaPagamentoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaFormaPagamentoDAO().consultarPorTemplate(model);
    }

    public void excluir(TituloPagarBaixaFormaPagamentoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaFormaPagamentoDAO().excluir(model);
    }

    public List<TituloPagarBaixaFormaPagamentoModel> obterTodosPorFormaPagto(FormaPagamentoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaFormaPagamentoDAO().obterTodosPorFormaPagto(model);
    }
}

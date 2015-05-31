package br.com.pempec.finance.businessObjects;

import java.util.Collection;
import java.util.List;

import br.com.pempec.finance.daos.DAOFactory;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloReceberBaixaFormaPagamentoModel;

/**
 * @author PEMPEC
 */
public class TituloReceberBaixaFormaPagamentoBO {

    public void inserir(TituloReceberBaixaFormaPagamentoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaFormaPagamentoDAO().inserir(model);
    }

    public void alterar(TituloReceberBaixaFormaPagamentoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaFormaPagamentoDAO().alterar(model);
    }

    public TituloReceberBaixaFormaPagamentoModel consultarPorPk(TituloReceberBaixaFormaPagamentoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaFormaPagamentoDAO().consultarPorPk(model);
    }

    public List<TituloReceberBaixaFormaPagamentoModel> obterPorFiltro(TituloReceberBaixaFormaPagamentoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaFormaPagamentoDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<TituloReceberBaixaFormaPagamentoModel> coll) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaFormaPagamentoDAO().excluirTodos(coll);
    }

    public List<TituloReceberBaixaFormaPagamentoModel> obterTodos(OrganizacaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaFormaPagamentoDAO().obterTodos(model);
    }

    public TituloReceberBaixaFormaPagamentoModel consultarPorTemplate(TituloReceberBaixaFormaPagamentoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaFormaPagamentoDAO().consultarPorTemplate(model);
    }

    public void excluir(TituloReceberBaixaFormaPagamentoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaFormaPagamentoDAO().excluir(model);
    }
}

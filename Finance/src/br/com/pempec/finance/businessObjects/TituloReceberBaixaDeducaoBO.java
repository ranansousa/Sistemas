package br.com.pempec.finance.businessObjects;

import java.util.Collection;
import java.util.List;

import br.com.pempec.finance.daos.DAOFactory;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloReceberBaixaDeducaoModel;

/**
 * @author PEMPEC
 */
public class TituloReceberBaixaDeducaoBO {

    public void inserir(TituloReceberBaixaDeducaoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaDeducaoDAO().inserir(model);
    }

    public void alterar(TituloReceberBaixaDeducaoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaDeducaoDAO().alterar(model);
    }

    public TituloReceberBaixaDeducaoModel consultarPorPk(TituloReceberBaixaDeducaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaDeducaoDAO().consultarPorPk(model);
    }

    public List<TituloReceberBaixaDeducaoModel> obterPorFiltro(TituloReceberBaixaDeducaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaDeducaoDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<TituloReceberBaixaDeducaoModel> coll) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaDeducaoDAO().excluirTodos(coll);
    }

    public List<TituloReceberBaixaDeducaoModel> obterTodos(OrganizacaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaDeducaoDAO().obterTodos(model);
    }

    public TituloReceberBaixaDeducaoModel consultarPorTemplate(TituloReceberBaixaDeducaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaDeducaoDAO().consultarPorTemplate(model);
    }

    public void excluir(TituloReceberBaixaDeducaoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaDeducaoDAO().excluir(model);
    }
}

package br.com.pempec.finance.businessObjects;

import java.util.Collection;
import java.util.List;

import br.com.pempec.finance.daos.DAOFactory;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloReceberBaixaAcrescimoModel;

/**
 * @author PEMPEC
 */
public class TituloReceberBaixaAcrescimoBO {

    public void inserir(TituloReceberBaixaAcrescimoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaAcrescimoDAO().inserir(model);
    }

    public void alterar(TituloReceberBaixaAcrescimoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaAcrescimoDAO().alterar(model);
    }

    public TituloReceberBaixaAcrescimoModel consultarPorPk(TituloReceberBaixaAcrescimoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaAcrescimoDAO().consultarPorPk(model);
    }

    public List<TituloReceberBaixaAcrescimoModel> obterPorFiltro(TituloReceberBaixaAcrescimoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaAcrescimoDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<TituloReceberBaixaAcrescimoModel> coll) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaAcrescimoDAO().excluirTodos(coll);
    }

    public List<TituloReceberBaixaAcrescimoModel> obterTodos(OrganizacaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaAcrescimoDAO().obterTodos(model);
    }

    public TituloReceberBaixaAcrescimoModel consultarPorTemplate(TituloReceberBaixaAcrescimoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloReceberBaixaAcrescimoDAO().consultarPorTemplate(model);
    }

    public void excluir(TituloReceberBaixaAcrescimoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloReceberBaixaAcrescimoDAO().excluir(model);
    }
}

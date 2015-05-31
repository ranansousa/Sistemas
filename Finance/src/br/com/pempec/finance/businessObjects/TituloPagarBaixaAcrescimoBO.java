package br.com.pempec.finance.businessObjects;

import java.util.Collection;
import java.util.List;

import br.com.pempec.finance.daos.DAOFactory;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaAcrescimoModel;

/**
 * @author PEMPEC
 */
public class TituloPagarBaixaAcrescimoBO {

    public void inserir(TituloPagarBaixaAcrescimoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaAcrescimoDAO().inserir(model);
    }

    public void alterar(TituloPagarBaixaAcrescimoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaAcrescimoDAO().alterar(model);
    }

    public TituloPagarBaixaAcrescimoModel consultarPorPk(TituloPagarBaixaAcrescimoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaAcrescimoDAO().consultarPorPk(model);
    }

    public List<TituloPagarBaixaAcrescimoModel> obterPorFiltro(TituloPagarBaixaAcrescimoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaAcrescimoDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<TituloPagarBaixaAcrescimoModel> coll) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaAcrescimoDAO().excluirTodos(coll);
    }

    public List<TituloPagarBaixaAcrescimoModel> obterTodos(OrganizacaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaAcrescimoDAO().obterTodos(model);
    }

    public TituloPagarBaixaAcrescimoModel consultarPorTemplate(TituloPagarBaixaAcrescimoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaAcrescimoDAO().consultarPorTemplate(model);
    }

    public void excluir(TituloPagarBaixaAcrescimoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaAcrescimoDAO().excluir(model);
    }
}

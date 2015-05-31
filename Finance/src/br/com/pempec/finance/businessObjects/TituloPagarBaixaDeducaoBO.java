package br.com.pempec.finance.businessObjects;

import java.util.Collection;
import java.util.List;

import br.com.pempec.finance.daos.DAOFactory;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaDeducaoModel;

/**
 * @author PEMPEC
 */
public class TituloPagarBaixaDeducaoBO {

    public void inserir(TituloPagarBaixaDeducaoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaDeducaoDAO().inserir(model);
    }

    public void alterar(TituloPagarBaixaDeducaoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaDeducaoDAO().alterar(model);
    }

    public TituloPagarBaixaDeducaoModel consultarPorPk(TituloPagarBaixaDeducaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaDeducaoDAO().consultarPorPk(model);
    }

    public List<TituloPagarBaixaDeducaoModel> obterPorFiltro(TituloPagarBaixaDeducaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaDeducaoDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<TituloPagarBaixaDeducaoModel> coll) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaDeducaoDAO().excluirTodos(coll);
    }

    public List<TituloPagarBaixaDeducaoModel> obterTodos(OrganizacaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaDeducaoDAO().obterTodos(model);
    }

    public TituloPagarBaixaDeducaoModel consultarPorTemplate(TituloPagarBaixaDeducaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTituloPagarBaixaDeducaoDAO().consultarPorTemplate(model);
    }

    public void excluir(TituloPagarBaixaDeducaoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTituloPagarBaixaDeducaoDAO().excluir(model);
    }
}

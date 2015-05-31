package br.com.pempec.finance.businessObjects;

import java.util.Collection;
import java.util.List;

import br.com.pempec.finance.daos.DAOFactory;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.CartaoCreditoModel;

/**
 * @author PEMPEC
 */
public class CartaoCreditoBO {

    public void inserir(CartaoCreditoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getCartaoCreditoDAO().inserir(model);
    }

    public void alterar(CartaoCreditoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getCartaoCreditoDAO().alterar(model);
    }

    public CartaoCreditoModel consultarPorPk(CartaoCreditoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCartaoCreditoDAO().consultarPorPk(
                model);
    }

    public void excluir(CartaoCreditoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getCartaoCreditoDAO().excluir(model);
    }

    public CartaoCreditoModel consultarPorTemplate(CartaoCreditoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCartaoCreditoDAO().consultarPorTemplate(model);
    }

    public List<CartaoCreditoModel> obterPorFiltro(CartaoCreditoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCartaoCreditoDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<CartaoCreditoModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getCartaoCreditoDAO().excluirTodos(coll);
    }

    public List<CartaoCreditoModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCartaoCreditoDAO().obterTodos(model);
    }
}

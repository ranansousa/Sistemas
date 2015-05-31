package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CartaoCreditoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface CartaoCreditoDAOIf {

    public void inserir(CartaoCreditoModel model) throws SystemException;

    public void alterar(CartaoCreditoModel model) throws SystemException;

    public CartaoCreditoModel consultarPorPk(CartaoCreditoModel model)
            throws SystemException;

    public List<CartaoCreditoModel> obterPorFiltro(CartaoCreditoModel model)
            throws SystemException;

    public List<CartaoCreditoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(CartaoCreditoModel model) throws SystemException;

    public CartaoCreditoModel consultarPorTemplate(CartaoCreditoModel model)
            throws SystemException;

    public void excluirTodos(Collection<CartaoCreditoModel> coll)
            throws SystemException;
}

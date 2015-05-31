package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class TipoOperacaoBancariaBO {

    public void inserir(TipoOperacaoBancariaModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTipoOperacaoBancariaDAO().inserir(model);
    }

    public void alterar(TipoOperacaoBancariaModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTipoOperacaoBancariaDAO().alterar(model);
    }

    public TipoOperacaoBancariaModel consultarPorPk(TipoOperacaoBancariaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoOperacaoBancariaDAO().consultarPorPk(
                model);
    }

    public void excluir(TipoOperacaoBancariaModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTipoOperacaoBancariaDAO().excluir(model);
    }

    public TipoOperacaoBancariaModel consultarPorTemplate(TipoOperacaoBancariaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoOperacaoBancariaDAO().consultarPorTemplate(model);
    }

    public List<TipoOperacaoBancariaModel> obterPorFiltro(TipoOperacaoBancariaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoOperacaoBancariaDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<TipoOperacaoBancariaModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTipoOperacaoBancariaDAO().excluirTodos(coll);
    }

    public List<TipoOperacaoBancariaModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoOperacaoBancariaDAO().obterTodos(model);
    }

    public List<TipoOperacaoBancariaModel> obterTodosPorTipo(OrganizacaoModel model, int tipo)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoOperacaoBancariaDAO().obterTodosPorTipo(model, tipo);
    }
}

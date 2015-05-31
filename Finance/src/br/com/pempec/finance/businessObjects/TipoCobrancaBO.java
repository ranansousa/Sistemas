package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoCobrancaModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class TipoCobrancaBO {

    public void inserir(TipoCobrancaModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTipoCobrancaDAO().inserir(model);
    }

    public void alterar(TipoCobrancaModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTipoCobrancaDAO().alterar(model);
    }

    public TipoCobrancaModel consultarPorPk(TipoCobrancaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoCobrancaDAO().consultarPorPk(
                model);
    }

    public void excluir(TipoCobrancaModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTipoCobrancaDAO().excluir(model);
    }

    public TipoCobrancaModel consultarPorTemplate(TipoCobrancaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoCobrancaDAO().consultarPorTemplate(model);
    }

    public List<TipoCobrancaModel> obterPorFiltro(TipoCobrancaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoCobrancaDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<TipoCobrancaModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTipoCobrancaDAO().excluirTodos(coll);
    }

    public List<TipoCobrancaModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoCobrancaDAO().obterTodos(model);
    }
}

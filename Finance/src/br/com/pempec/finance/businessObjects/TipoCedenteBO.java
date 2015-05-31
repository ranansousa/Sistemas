package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoCedenteModel;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class TipoCedenteBO {

    public void inserir(TipoCedenteModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTipoCedenteDAO().inserir(model);
    }

    public void alterar(TipoCedenteModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTipoCedenteDAO().alterar(model);
    }

    public TipoCedenteModel consultarPorPk(TipoCedenteModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoCedenteDAO().consultarPorPk(model);
    }

    public List<TipoCedenteModel> obterPorFiltro(TipoCedenteModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoCedenteDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<TipoCedenteModel> coll) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTipoCedenteDAO().excluirTodos(coll);
    }

    public List<TipoCedenteModel> obterTodos(OrganizacaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoCedenteDAO().obterTodos(model);
    }

    public TipoCedenteModel consultarPorTemplate(TipoCedenteModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoCedenteDAO().consultarPorTemplate(model);
    }

    public void excluir(TipoCedenteModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTipoCedenteDAO().excluir(model);
    }
}

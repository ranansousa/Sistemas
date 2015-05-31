package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class LoteContabilBO {

    public void inserir(LoteContabilModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getLoteContabilDAO().inserir(model);
    }

    public void alterar(LoteContabilModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getLoteContabilDAO().alterar(model);
    }

    public LoteContabilModel consultarPorPk(LoteContabilModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLoteContabilDAO().consultarPorPk(model);
    }

    public List<LoteContabilModel> obterPorFiltro(LoteContabilModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLoteContabilDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<LoteContabilModel> coll) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getLoteContabilDAO().excluirTodos(coll);
    }

    public List<LoteContabilModel> obterTodos(OrganizacaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLoteContabilDAO().obterTodos(model);
    }

    public void excluir(LoteContabilModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getLoteContabilDAO().excluir(model);
    }

    public LoteContabilModel consultarPorTemplate(LoteContabilModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLoteContabilDAO().consultarPorTemplate(model);
    }
}

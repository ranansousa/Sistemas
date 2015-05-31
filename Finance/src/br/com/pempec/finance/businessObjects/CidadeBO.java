package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.CidadeModel;
import br.com.pempec.finance.models.EstadoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class CidadeBO {

    public void inserir(CidadeModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getCidadeDAO().inserir(model);
    }

    public void alterar(CidadeModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getCidadeDAO().alterar(model);
    }

    public CidadeModel consultarPorPk(CidadeModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCidadeDAO().consultarPorPk(
                model);
    }

    public void excluir(CidadeModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getCidadeDAO().excluir(model);
    }

    public CidadeModel consultarPorTemplate(CidadeModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCidadeDAO().consultarPorTemplate(model);
    }

    public List<CidadeModel> obterPorFiltro(CidadeModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCidadeDAO().obterPorFiltro(
                model);
    }

    public List<CidadeModel> obterPorEstado(EstadoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCidadeDAO().obterPorEstado(
                model);
    }

    public void excluirTodos(Collection<CidadeModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getCidadeDAO().excluirTodos(coll);
    }

    public List<CidadeModel> obterTodos()
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getCidadeDAO().obterTodos();
    }
}

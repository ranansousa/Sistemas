package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.EstadoModel;
import java.util.List;

/**
 * @author PEMPEC
 */
public class EstadoBO {

    public void inserir(EstadoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getEstadoDAO().inserir(model);
    }

    public void alterar(EstadoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getEstadoDAO().alterar(model);
    }

    public EstadoModel consultarPorPk(EstadoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getEstadoDAO().consultarPorPk(
                model);
    }

    public void excluir(EstadoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getEstadoDAO().excluir(model);
    }

    public EstadoModel consultarPorTemplate(EstadoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getEstadoDAO().consultarPorTemplate(model);
    }

    public List<EstadoModel> obterPorFiltro(EstadoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getEstadoDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(String[] array) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getEstadoDAO().excluirTodos(array);
    }

    public List<EstadoModel> obterTodos()
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getEstadoDAO().obterTodos();
    }
}
